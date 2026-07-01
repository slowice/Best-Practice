package xb.ai.chat.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CrudRecord {
    private Timestamp createdAt;
    private String url;
    private String response;
}
