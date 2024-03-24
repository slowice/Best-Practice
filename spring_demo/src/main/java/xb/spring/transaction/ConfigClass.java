package xb.spring.transaction;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:transaction/transactionTest.xml")
public class ConfigClass {
}
