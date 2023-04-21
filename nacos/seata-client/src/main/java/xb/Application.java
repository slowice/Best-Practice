package xb;

import io.seata.spring.annotation.AspectTransactionalInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
    @Bean
    public AspectTransactionalInterceptor aspectTransactionalInterceptor () {
        return new AspectTransactionalInterceptor();
    }

    @Bean
    public Advisor txAdviceAdvisor(AspectTransactionalInterceptor aspectTransactionalInterceptor ) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("配置切点表达式使全局事务拦截器生效");
        return new DefaultPointcutAdvisor(pointcut, aspectTransactionalInterceptor);
    }
}
