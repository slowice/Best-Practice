package xb.ai.chat.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import xb.ai.chat.service.GlmChatCompletionsService;

@RestController
public class GlmChatCompletionsController {
    private final GlmChatCompletionsService glmChatCompletionsService;

    public GlmChatCompletionsController(GlmChatCompletionsService glmChatCompletionsService) {
        this.glmChatCompletionsService = glmChatCompletionsService;
    }

    @PostMapping(value = "/api/paas/v4/chat/completions", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<String> createChatCompletion(@RequestHeader HttpHeaders requestHeaders,
                                                       @RequestBody String requestBody) {
        return ResponseEntity.ok(glmChatCompletionsService.createChatCompletion(requestBody, requestHeaders));
    }
}
