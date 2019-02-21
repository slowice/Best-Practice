package xb.data_process_demo.data_generate;

import com.sun.tools.hat.internal.model.ArrayTypeCodes;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TableServiceImpl implements TableService{
    @Autowired
    JdbcTemplate template;

    private static final Map<String,String> increaseColumn  = new HashMap<>();

    private static final Map<String,String> defaultColumn  = new HashMap<>();

    private static int insertTimes;

    private static String TableNames;

    private static String SchemaNames;

    {
        //插入条数
        insertTimes = 5;

        //需要设置自增的字段，后两位为自增index,字段初始值
        increaseColumn.put("username","abg01");
        increaseColumn.put("id","ididid00");

        //相同值的字段，字段初始值
        defaultColumn.put("password","123456");
    }

    @Override
    public void handle(String tableName) {
        TableInfo tableInfo = this.getTableInfo(tableName);
        String columnNames;
        String values ;

        List<ColumnInfo> columnInfos = tableInfo.getColumnInfoList();

        List<String> columnNameList = columnInfos.stream().map(ColumnInfo::getName).collect(Collectors.toList());
        for(String columnName: columnNameList){
            columnName = "'" + columnName + "'";
        }
        columnNames = StringUtils.collectionToCommaDelimitedString(columnNameList);

        for(int j = 0;j<insertTimes;j++){

            List<String> valueList = new LinkedList<>();
            //轮训字段集合，判断字段是否在预设值的字段规则中，若存在，则按预设的字段规则进行赋值，若不存在则给空字符串
            for(int i = 0;i<columnInfos.size();i++){
                ColumnInfo info = columnInfos.get(i);
                String value = "";
                if(!CollectionUtils.isEmpty(increaseColumn) && increaseColumn.keySet().contains(info.getName())){
                    value = increaseColumn.get(info.getName());
                    String preValue = value.substring(0,value.length()-2);
                    String sufValue = value.substring(value.length()-2,value.length());
                    Integer sufValueInt = Integer.parseInt(sufValue);
                    sufValueInt += j;
                    value = preValue + sufValueInt.toString();
                }

                if(defaultColumn.keySet().contains(info.getName())){
                    value = defaultColumn.get(info.getName());
                }

                valueList.add("'" + value + "'");
            }

            values = StringUtils.collectionToCommaDelimitedString(valueList);

            String sql = "insert into "+ tableName + "(" + columnNames + ") values (" + values + ")";

            template.execute(sql);
        }
    }

    private BeanPropertyRowMapper<ColumnInfo> rowMapper = new BeanPropertyRowMapper<ColumnInfo>(ColumnInfo.class){
        @Override
        protected void initBeanWrapper(BeanWrapper bw) {
            super.initBeanWrapper(bw);
        }
    };

    private TableInfo getTableInfo(String tableName){
        String sql = "select COLUMN_NAME as name, DATA_TYPE as dataType" +
                "    from information_schema.COLUMNS" +
                "    where TABLE_NAME = " + "'" + tableName + "'" + " and TABLE_SCHEMA = '" + "best_practice" + "'";


        List<Map<String,Object>> maps = template.queryForList(sql);
        List<ColumnInfo> columnList = new ArrayList<>();
        for(Map map : maps){
            ColumnInfo info = new ColumnInfo();
            info.setName(map.get("name").toString());
            info.setDataType(map.get("dataType").toString());
            columnList.add(info);
        }
       // List<ColumnInfo> columnList = dao.queryColumnInfoList(tableName);
        TableInfo table = new TableInfo();
        table.setName(tableName);
        table.setColumnInfoList(columnList);
        return table;
    }
}
