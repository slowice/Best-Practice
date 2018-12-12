package xb.mybatis_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.mybatis_demo.bean.User;
import xb.mybatis_demo.dao.UserMapper;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
