package xb.mybatis_demo.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.mybatis_demo.bean.User;
import xb.mybatis_demo.dao.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findUsersByIds(List<String> idList) {
        return userMapper.selectBatch(idList);
    }

    @Override
    public List<User> unionAllTest() {
        return userMapper.unionAllTest("mark");
    }
}
