package xb.spring.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
