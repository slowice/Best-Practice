package xb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 一个复杂的CRUD
 */
@SpringBootApplication
@EntityScan(basePackages = "xb.entity")
//开启声明式事务管理
@EnableTransactionManagement
public class ComplexCRUDApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ComplexCRUDApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 配置Springboot的应用环境
        SpringApplicationBuilder sources = builder.sources(ComplexCRUDApplication.class);
        return sources;
    }
}
