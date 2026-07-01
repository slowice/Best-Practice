package xb.ai.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import xb.ai.chat.dao.CrudMapper;
import xb.ai.chat.domain.CrudRecord;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.headerDoesNotExist;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AiChatApplication.class)
@AutoConfigureMockMvc
@Transactional
class GlmEmbeddingsControllerTest {
    private static final String POSTMAN_COLLECTION_PATH = "/Users/xubin/xb/PROJECTS/智普AI.postman_collection.json";
    private static final PostmanFixture POSTMAN = PostmanFixture.load(Paths.get(POSTMAN_COLLECTION_PATH));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CrudMapper crudMapper;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @DynamicPropertySource
    static void postmanProperties(DynamicPropertyRegistry registry) {
        registry.add("ai-chat.glm.embeddings-url", () -> POSTMAN.url);
    }

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    void createEmbeddingForwardsBusinessHeadersAndStoresEmbeddingResponse() throws Exception {
        mockServer.expect(requestTo(POSTMAN.url))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.AUTHORIZATION, POSTMAN.authorization()))
                .andExpect(headerDoesNotExist(HttpHeaders.ACCEPT_ENCODING))
                .andExpect(content().json(POSTMAN.requestBody))
                .andRespond(withSuccess(POSTMAN.responseBody, MediaType.APPLICATION_JSON));

        mockMvc.perform(post(POSTMAN.path)
                        .headers(POSTMAN.headers)
                        .header(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(POSTMAN.requestBody))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().json(POSTMAN.responseBody));

        mockServer.verify();
        CrudRecord saved = crudMapper.findLatestByUrl(POSTMAN.url);
        Assert.assertNotNull(saved);
        Assert.assertEquals(POSTMAN.url, saved.getUrl());
        Assert.assertEquals(POSTMAN.responseBody, saved.getResponse());
    }

    private static class PostmanFixture {
        private final String url;
        private final String path;
        private final HttpHeaders headers;
        private final String requestBody;
        private final String responseBody;

        private PostmanFixture(String url, HttpHeaders headers, String requestBody, String responseBody) {
            this.url = url;
            this.path = URI.create(url).getRawPath();
            this.headers = headers;
            this.requestBody = requestBody;
            this.responseBody = responseBody;
        }

        private String authorization() {
            String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);
            if (!hasText(authorization)) {
                throw new IllegalStateException("Postman collection missing request header: " + HttpHeaders.AUTHORIZATION);
            }
            return authorization;
        }

        private static PostmanFixture load(Path path) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode root = objectMapper.readTree(Files.newInputStream(path));
                JsonNode item = first(root.path("item"), "item");
                JsonNode request = item.path("request");
                String url = text(request.path("url").path("raw"), "request.url.raw");
                HttpHeaders headers = readHeaders(request.path("header"));
                String requestBody = text(request.path("body").path("raw"), "request.body.raw");
                String responseBody = text(first(item.path("response"), "response").path("body"), "response.body");
                return new PostmanFixture(url, headers, requestBody, responseBody);
            } catch (IOException ex) {
                throw new IllegalStateException("Failed to load Postman collection: " + path, ex);
            }
        }

        private static HttpHeaders readHeaders(JsonNode headersNode) {
            HttpHeaders headers = new HttpHeaders();
            if (headersNode.isArray()) {
                for (JsonNode header : headersNode) {
                    String key = text(header.path("key"), "request.header.key");
                    String value = text(header.path("value"), "request.header." + key);
                    headers.add(key, value);
                }
            }
            return headers;
        }

        private static JsonNode first(JsonNode arrayNode, String fieldName) {
            if (!arrayNode.isArray() || arrayNode.size() == 0) {
                throw new IllegalStateException("Postman collection missing " + fieldName);
            }
            return arrayNode.get(0);
        }

        private static String text(JsonNode node, String fieldName) {
            if (!node.isTextual()) {
                throw new IllegalStateException("Postman collection missing text field: " + fieldName);
            }
            return node.asText();
        }

        private static boolean hasText(String text) {
            return text != null && text.trim().length() > 0;
        }
    }
}
