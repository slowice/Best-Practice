package xb.hook.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;
import xb.hook.aop.targetService.WithinTest;

@SpringBootTest
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class TestAop {
    @Autowired
    WithinTest test;

    @Test
    public void test1(){
        test.test();
    }
}
