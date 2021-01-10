package xb.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Component and @Aspect两个注解搭配声明此类为一个切面.光有@Aspect无法被spring侦测到
 *
 * pointcut表达式
 * execution：通用匹配
 * within：限制类型
 * this：限制bean reference是指定类型
 * target：限制target object是指定类型
 * args：限制参数是指定类型
 * @target：限制方法执行的所在类是指定类型
 * @args
 * @within
 * @annotation
 */
@Component
@Aspect
public class AnnotationBasedAOPAspect {
    /**
     * 切面配合advice，达成运行切面对应方法时，触发对应动作/事件
     */
    @Pointcut("execution(* xb.aop.AnnotationBasedAOPService.doOK(..))")// the pointcut expression
    private void pointcut1() {}// the pointcut signature
    @Before("pointcut1()")
    public void advice1(){
        System.out.println("this is advice1,before dook");
    }


//    @Pointcut("execution(* transfer(..))")// the pointcut expression
//    private void pointcut2() {}// the pointcut signature
//
//    @Pointcut("pointcut1() && pointcut2()")
//    private void combinedPointcut(){};
}
