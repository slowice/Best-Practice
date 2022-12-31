package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "bean")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
