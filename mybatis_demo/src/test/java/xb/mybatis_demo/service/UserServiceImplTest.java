package xb.mybatis_demo.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xb.mybatis_demo.MybatisDemoApplicationTests;
import xb.mybatis_demo.bean.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceImplTest extends MybatisDemoApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void findUserById() {
        User user = userService.findUserById("1");
        Assert.assertNotNull(user);
    }

    @Test
    public void selectBatchTest(){
        List<User> users = userService.findUsersByIds(Arrays.asList("1","2"));
        Assert.assertNotNull(users);
    }

    @Test
    public void unionAllTest(){
        List<User> users = userService.unionAllTest();
        Assert.assertNotNull(users);
    }
}