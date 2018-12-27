package xb.mybatis_demo.service;

import xb.mybatis_demo.bean.User;

import java.util.List;

public interface UserService {
    User findUserById(String id);

    /**
     * 测试批量查询，根据ID集合查实体集合
     * @param idList
     * @return
     */
    List<User> findUsersByIds(List<String> idList);

    /**
     * 测试unionall关键字，组合user_role表和user表的ID和name字段拼接为User集合返回
     * @return
     */
    List<User> unionAllTest();

}
