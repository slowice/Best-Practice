package xb.ioc.applicationContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xb.ApplicationTest;

public class ApplicationContextSomeTest extends ApplicationTest {
    @Autowired
    ApplicationContextSome applicationContextSome;
    @Test
    public void test1() {
        applicationContextSome.test();
    }
}