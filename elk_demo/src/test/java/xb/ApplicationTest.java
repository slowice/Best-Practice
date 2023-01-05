package xb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest()
class ApplicationTest {

    @Test
    public void test1(){

        log.info("123123");
        System.out.println("123");
    }
}