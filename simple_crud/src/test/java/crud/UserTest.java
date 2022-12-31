package crud;

import application.Application;
import application.crud.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {
    @Autowired
    UserRepository repository;

    @Test
    public void test1(){
        repository.findById("0000016ef6bad6dd733ba74ca86c78a0");
    }
}
