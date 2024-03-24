package xb.persistence.mybatis.dao;

import xb.entity.User;
import xb.entity.UserRole;
import xb.dto.UserDTO;
import xb.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapperExternal {
    //批量添加
    int insertBatch(@Param("userList") List<User> userList);

    //批量删除
    int deleteBatch(@Param("userList") List<User> userList);

    //批量查询
    List<User> selectBatchById(@Param("userList") List<User> userList);

    //union all
    List<User> unionTest();

    //分组排序

    //if查询
    List<User> ifTest(UserRequest query);

    List<UserRole> queryRoles();

    UserDTO autoMappingTest(User user);

    List<UserDTO> sharpSelect(@Param("idUser") String idUser);

    List<UserDTO> dollorSelect(@Param("idUser") String idUser);

    List<UserDTO> complexQueryTest(UserRequest query);

    List<UserDTO> selectAllPaged();

    List<UserDTO> test();
}
