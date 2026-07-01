package xb.ai.chat;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import xb.ai.chat.AiChatApplication;
import xb.ai.chat.dao.CrudMapper;
import xb.ai.chat.domain.CrudRecord;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@SpringBootTest(classes = AiChatApplication.class)
@Transactional
class CrudMapperTest {
    @Autowired
    private CrudMapper crudMapper;

    @Test
    void insertAndFindLatestByUrl() {
        String url = "https://example.test/postman/" + UUID.randomUUID();
        String response = "{\"ok\":true}";
        CrudRecord record = new CrudRecord();
        record.setCreatedAt(Timestamp.from(Instant.now()));
        record.setUrl(url);
        record.setResponse(response);

        int inserted = crudMapper.insert(record);
        CrudRecord saved = crudMapper.findLatestByUrl(url);

        Assert.assertEquals(1, inserted);
        Assert.assertNotNull(saved);
        Assert.assertEquals(url, saved.getUrl());
        Assert.assertEquals(response, saved.getResponse());
    }
}
