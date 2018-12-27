package xb.pagehelperdemo.service;

import com.github.pagehelper.PageInfo;
import xb.pagehelperdemo.bean.Test;

import java.util.List;

public interface TestService {
    Test selectOne(Integer id);
    List<Test> selectAll();
    PageInfo<Test> selectAllPaged();
}
