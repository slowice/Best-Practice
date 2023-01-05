package xb;

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
public class ComplexCRUDApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComplexCRUDApplication.class, args);
    }
}
