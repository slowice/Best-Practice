package xb.aopTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xb.ApplicationTest;
import xb.aop.AnnotationBasedAOPService;

public class AopTest extends ApplicationTest {
    @Autowired
    AnnotationBasedAOPService annotationBasedAOPService;

    @Test
    public void Test1(){
        annotationBasedAOPService.doOK();
    }
}
