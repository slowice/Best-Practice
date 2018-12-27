package xb.pagehelperdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.pagehelperdemo.bean.Test;
import xb.pagehelperdemo.mapper.TestMapper;
import xb.pagehelperdemo.service.TestService;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public Test selectOne(Integer id) {
        return testMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Test> selectAll() {
        PageHelper.startPage(2,5);
        return testMapper.selectAll();
    }

    @Override
    public PageInfo<Test> selectAllPaged() {
        PageHelper.startPage(2,5);

        return new PageInfo<Test>(testMapper.selectAll());
    }
}
