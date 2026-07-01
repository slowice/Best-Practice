package xb.ai.chat.service;

import org.springframework.http.HttpHeaders;

public interface GlmChatCompletionsService {
    String createChatCompletion(String requestBody, HttpHeaders requestHeaders);
}
