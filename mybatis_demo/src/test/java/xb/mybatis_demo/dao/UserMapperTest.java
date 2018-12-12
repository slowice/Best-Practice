package xb.mybatis_demo.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xb.mybatis_demo.MybatisDemoApplicationTests;
import xb.mybatis_demo.bean.User;

import static org.junit.Assert.*;

public class UserMapperTest extends MybatisDemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void selectIfCase() {
        User user = userMapper.selectIfCase("1","1");
        System.out.println(user.getId());
    }
}