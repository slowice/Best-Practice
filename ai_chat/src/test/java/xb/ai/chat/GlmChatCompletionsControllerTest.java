package xb.ai.chat;

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
class GlmChatCompletionsControllerTest {
    private static final String CHAT_COMPLETIONS_URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
    private static final String REQUEST_BODY = "{\"model\":\"glm-5.2\",\"messages\":[{\"role\":\"user\",\"content\":\"hello\"}]}";
    private static final String RESPONSE_BODY = "{\"id\":\"chatcmpl-test\",\"object\":\"chat.completion\",\"choices\":[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":\"hello response\"},\"finish_reason\":\"stop\"}]}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CrudMapper crudMapper;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @DynamicPropertySource
    static void chatProperties(DynamicPropertyRegistry registry) {
        registry.add("ai-chat.glm.chat-completions-url", () -> CHAT_COMPLETIONS_URL);
    }

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    void createChatCompletionForwardsBusinessHeadersAndStoresResponse() throws Exception {
        mockServer.expect(requestTo(CHAT_COMPLETIONS_URL))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer test-token"))
                .andExpect(headerDoesNotExist(HttpHeaders.ACCEPT_ENCODING))
                .andExpect(content().json(REQUEST_BODY))
                .andRespond(withSuccess(RESPONSE_BODY, MediaType.APPLICATION_JSON));

        mockMvc.perform(post("/api/paas/v4/chat/completions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer test-token")
                        .header(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(REQUEST_BODY))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().json(RESPONSE_BODY));

        mockServer.verify();
        CrudRecord saved = crudMapper.findLatestByUrl(CHAT_COMPLETIONS_URL);
        Assert.assertNotNull(saved);
        Assert.assertEquals(CHAT_COMPLETIONS_URL, saved.getUrl());
        Assert.assertEquals(RESPONSE_BODY, saved.getResponse());
    }
}
