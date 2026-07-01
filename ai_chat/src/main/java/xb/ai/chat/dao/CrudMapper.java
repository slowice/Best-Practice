package xb.ai.chat.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import xb.ai.chat.domain.CrudRecord;

@Mapper
public interface CrudMapper {
    @Insert("insert into `CRUD` (created_at, url, response) values (#{createdAt}, #{url}, #{response})")
    int insert(CrudRecord record);

    @Select("select created_at, url, response from `CRUD` where url = #{url} order by created_at desc limit 1")
    @Results(id = "CrudRecordResultMap", value = {
            @Result(column = "created_at", property = "createdAt", id = true),
            @Result(column = "url", property = "url"),
            @Result(column = "response", property = "response")
    })
    CrudRecord findLatestByUrl(@Param("url") String url);
}
