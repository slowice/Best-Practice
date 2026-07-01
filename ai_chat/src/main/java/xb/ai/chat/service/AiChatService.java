package xb.ai.chat.service;

import org.springframework.stereotype.Service;
import xb.ai.chat.dto.ChatRequest;
import xb.ai.chat.dto.ChatResponse;

@Service
public class AiChatService {
    public ChatResponse chat(ChatRequest request) {
        String sessionId = request == null ? null : request.getSessionId();
        String message = request == null ? null : request.getMessage();
        String answer = "received: " + (message == null ? "" : message);
        return new ChatResponse(sessionId, answer);
    }
}
