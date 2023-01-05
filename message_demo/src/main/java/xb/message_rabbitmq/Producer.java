package xb.message_rabbitmq;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class Producer {

    public String sendMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData",messageData);
        manMap.put("createTime",createTime);
        return "123";
    }
}
