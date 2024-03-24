package xb.spring.hook.aop.targetService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WithinTest {
    public void test(){
        log.info("test for within");
    }
}
