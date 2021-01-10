package xb.controller;

import org.jboss.logging.MessageLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import xb.transaction.TransactionTestService;

@Controller
public class XbTest {
    @Autowired
    TransactionTestService transactionTestService;

    @GetMapping("/test1")
    public void test() throws InterruptedException {
        transactionTestService.testUnRepeatableRead();
    }
}
