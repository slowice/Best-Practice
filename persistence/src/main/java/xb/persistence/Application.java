package xb.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * sql优化？
 * 1.尽量避免使用子查询。而使用关联查询
 * 2.条件查询中使用in来代替or
 * 3.模糊查询中，%%双百分号包裹的条件无法使用索引。
 * 4.对于where order by的条件建索引
 */
@SpringBootApplication
// @MapperScan({"xb.mybatis.dao"})// 使用了@Mapper注解就不需要添加mapperscan了
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
