package xb.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAspectJAutoProxy
public class AOPTest {
    @Autowired
    AnnotationBasedAOPService annotationBasedAOPService;

    @Test
    public void basicTest(){
        annotationBasedAOPService.doOK();
    }

}
