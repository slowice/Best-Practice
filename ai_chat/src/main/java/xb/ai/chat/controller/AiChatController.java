package xb.ai.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xb.ai.chat.dto.ChatRequest;
import xb.ai.chat.dto.ChatResponse;
import xb.ai.chat.service.AiChatService;

@RestController
@RequestMapping("/ai-chat")
public class AiChatController {
    private final AiChatService aiChatService;

    public AiChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @GetMapping("/health")
    public String health() {
        return "ai_chat ok";
    }

    @PostMapping("/message")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return aiChatService.chat(request);
    }
}
