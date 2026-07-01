package xb.ai.chat.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import xb.ai.chat.service.GlmEmbeddingsService;

@RestController
public class GlmEmbeddingsController {
    private final GlmEmbeddingsService glmEmbeddingsService;

    public GlmEmbeddingsController(GlmEmbeddingsService glmEmbeddingsService) {
        this.glmEmbeddingsService = glmEmbeddingsService;
    }

    @PostMapping(value = "/api/paas/v4/embeddings", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<String> createEmbedding(@RequestHeader HttpHeaders requestHeaders,
                                                  @RequestBody String requestBody) {
        return ResponseEntity.ok(glmEmbeddingsService.createEmbedding(requestBody, requestHeaders));
    }
}
