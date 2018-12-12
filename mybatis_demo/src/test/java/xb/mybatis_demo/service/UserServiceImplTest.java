package xb.mybatis_demo.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xb.mybatis_demo.MybatisDemoApplicationTests;
import xb.mybatis_demo.bean.User;

import static org.junit.Assert.*;

public class UserServiceImplTest extends MybatisDemoApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void findUserById() {
        User user = userService.findUserById("1");
        Assert.assertNotNull(user);
    }
}