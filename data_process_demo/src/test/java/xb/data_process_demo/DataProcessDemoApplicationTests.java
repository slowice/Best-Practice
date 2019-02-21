package xb.data_process_demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.data_process_demo.data_generate.ColumnInfo;
import xb.data_process_demo.data_generate.ColumnInfoDao;
import xb.data_process_demo.data_generate.TableService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataProcessDemoApplicationTests {
    @Autowired
    ColumnInfoDao dao;

    @Autowired
    TableService service;

    @Test
    public void contextLoads() {
        List<ColumnInfo> c = dao.queryColumnInfoList("users");
        Assert.assertNotNull(c);
    }

    @Test
    public void handleTable(){
        service.handle("USER");
    }

}
