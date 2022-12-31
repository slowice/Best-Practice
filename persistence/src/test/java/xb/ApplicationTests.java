package xb;

import bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.mybatis.dao.UserMapper;

@RunWith(SpringRunner.class)
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
