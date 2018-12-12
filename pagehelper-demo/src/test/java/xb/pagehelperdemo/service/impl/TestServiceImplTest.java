package xb.pagehelperdemo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xb.pagehelperdemo.PageHelperDemoApplicationTests;
import xb.pagehelperdemo.service.TestService;

import java.util.List;

import static org.junit.Assert.*;

public class TestServiceImplTest extends PageHelperDemoApplicationTests {
    @Autowired
    private TestService testService;
    @Test
    public void selectOne() {
        xb.pagehelperdemo.bean.Test test = testService.selectOne(1);
        Assert.assertNotNull(test);
    }

    @Test
    public void selectAll(){
        List<xb.pagehelperdemo.bean.Test> testList = testService.selectAll();
        Assert.assertNotNull(testList);
    }
}