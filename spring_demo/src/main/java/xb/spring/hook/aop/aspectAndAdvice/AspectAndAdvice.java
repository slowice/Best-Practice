package xb.spring.hook.aop.aspectAndAdvice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 测试切面
 * 注意用@Aspect修饰类
 */
@Aspect
@Component
@Slf4j
public class AspectAndAdvice{

    @Pointcut(value = "within(xb.spring.hook.aop.targetService.WithinTest)")
    public void withinPointcut(){}
    @Before("withinPointcut()")
    public void withinTest(){
        log.info("before within");
    }
}