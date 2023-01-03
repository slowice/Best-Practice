package xb.randomDataFacotry;

import org.junit.jupiter.api.Test;
import xb.entity.User;
import xb.ApplicationTests;
import xb.randomDataFactory.DefaultRandomDataFactory;
import xb.randomDataFactory.RandomDataFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TestRandomDataFacotry extends ApplicationTests {

    @Test
    public void testRandomDataFactory() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        RandomDataFactory randomDataFactory = new DefaultRandomDataFactory(User.class);
        User u = (User)randomDataFactory.create();
        System.out.println(u);

        List<User> userList = randomDataFactory.create(10);
        System.out.println(userList);
    }
}
