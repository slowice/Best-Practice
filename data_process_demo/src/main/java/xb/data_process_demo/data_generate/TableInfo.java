package xb.data_process_demo.data_generate;

import lombok.Data;

import java.util.List;

@Data
public class TableInfo {
    private String name;
    private String id;
    private List<ColumnInfo> columnInfoList;
}
