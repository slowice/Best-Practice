package xb.ai.chat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import xb.ai.chat.dao.CrudMapper;
import xb.ai.chat.domain.CrudRecord;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class GlmEmbeddingsServiceImpl implements GlmEmbeddingsService {
    private static final Set<String> SKIPPED_FORWARD_HEADERS = new HashSet<>(Arrays.asList(
            "accept-encoding",
            "connection",
            "content-length",
            "host",
            "keep-alive",
            "proxy-authenticate",
            "proxy-authorization",
            "te",
            "trailer",
            "transfer-encoding",
            "upgrade"
    ));

    private final CrudMapper crudMapper;
    private final RestTemplate restTemplate;
    private final String embeddingsUrl;
    private final String authorization;

    public GlmEmbeddingsServiceImpl(
            CrudMapper crudMapper,
            RestTemplate restTemplate,
            @Value("${ai-chat.glm.embeddings-url:https://open.bigmodel.cn/api/paas/v4/embeddings}") String embeddingsUrl,
            @Value("${ai-chat.glm.authorization:}") String authorization) {
        this.crudMapper = crudMapper;
        this.restTemplate = restTemplate;
        this.embeddingsUrl = embeddingsUrl;
        this.authorization = authorization;
    }

    @Override
    public String createEmbedding(String requestBody, HttpHeaders requestHeaders) {
        String responseBody = callGlmEmbeddings(requestBody, requestHeaders);
        saveResponse(responseBody);
        return responseBody;
    }

    private String callGlmEmbeddings(String requestBody, HttpHeaders requestHeaders) {
        HttpHeaders headers = buildRemoteHeaders(requestHeaders);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(embeddingsUrl, HttpMethod.POST, request, String.class);
        return response.getBody() == null ? "" : response.getBody();
    }

    private HttpHeaders buildRemoteHeaders(HttpHeaders requestHeaders) {
        HttpHeaders headers = new HttpHeaders();
        requestHeaders.forEach((name, values) -> {
            if (shouldForwardHeader(name)) {
                headers.put(name, new ArrayList<>(values));
            }
        });
        if (!headers.containsKey(HttpHeaders.CONTENT_TYPE)) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION) && StringUtils.hasText(authorization)) {
            headers.set(HttpHeaders.AUTHORIZATION, authorization);
        }
        return headers;
    }

    private boolean shouldForwardHeader(String name) {
        return !SKIPPED_FORWARD_HEADERS.contains(name.toLowerCase(Locale.ROOT));
    }

    private void saveResponse(String responseBody) {
        CrudRecord record = new CrudRecord();
        record.setCreatedAt(Timestamp.from(Instant.now()));
        record.setUrl(embeddingsUrl);
        record.setResponse(responseBody);
        crudMapper.insert(record);
    }
}
