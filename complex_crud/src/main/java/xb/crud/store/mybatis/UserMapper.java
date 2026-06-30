package xb.crud.store.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xb.common.entity.User;

@Mapper
public interface UserMapper {
    @Insert("insert into xb_user (id_user, pid, name, birthday) values (#{idUser}, #{pid}, #{name}, #{birthday})")
    int insert(User user);

    @Update("update xb_user set pid = #{pid}, name = #{name}, birthday = #{birthday} where id_user = #{idUser}")
    int updateById(User user);

    @Delete("delete from xb_user where id_user = #{idUser}")
    int deleteById(@Param("idUser") String idUser);

    @Select("select id_user, pid, name, birthday from xb_user where id_user = #{idUser}")
    @Results(id = "UserResultMap", value = {
            @Result(column = "id_user", property = "idUser", id = true),
            @Result(column = "pid", property = "pid"),
            @Result(column = "name", property = "name"),
            @Result(column = "birthday", property = "birthday")
    })
    User findById(@Param("idUser") String idUser);
}
