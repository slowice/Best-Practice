package xb.data_process_demo.data_generate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ColumnInfoDao {
    List<ColumnInfo> queryColumnInfoList(@Param("tableName") String tableName);
    void insertData();
}
