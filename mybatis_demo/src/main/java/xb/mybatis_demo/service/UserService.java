package xb.mybatis_demo.service;

import xb.mybatis_demo.bean.User;

public interface UserService {
    User findUserById(String id);

}
