package xb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 一个复杂的CRUD
 */
@SpringBootApplication
@EntityScan(basePackages = "xb.entity")
//开启声明式事务管理
@EnableTransactionManagement
//public class ComplexCRUDApplication extends SpringBootServletInitializer {
public class ComplexCRUDApplication {
    static Logger logger = LoggerFactory.getLogger(ComplexCRUDApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ComplexCRUDApplication.class, args);
    }

    //@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // 配置Springboot的应用环境
//        SpringApplicationBuilder sources = builder.sources(ComplexCRUDApplication.class);
//        return sources;
//    }
}
