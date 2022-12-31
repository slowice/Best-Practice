package crud;

import application.crud.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    UserRepository repository;

    @Test
    public void test1(){
        //repository.findById("123");
    }
}
