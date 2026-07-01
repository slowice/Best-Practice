package xb.ai.chat.service;

import org.springframework.http.HttpHeaders;

public interface GlmEmbeddingsService {
    String createEmbedding(String requestBody, HttpHeaders requestHeaders);
}
