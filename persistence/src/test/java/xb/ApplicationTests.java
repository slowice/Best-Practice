package xb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xb.persistence.mybatis.dao.UserMapper;

@SpringBootTest
public class ApplicationTests {
    @Autowired
    UserMapper mapper;

    @Test
    public void test1(){
//        User u = new User();
//        u.setIdUser("x14106a036fad497f97b1ec14ef05c5f");
//        u.setName("x1");
//        mapper.insert(u);
    }
}
