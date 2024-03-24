package xb.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import xb.spring.transaction.TransactionTestService;

@Controller
public class XbTest {
    @Autowired
    TransactionTestService transactionTestService;

    @GetMapping("/test1")
    public void test() throws InterruptedException {
        transactionTestService.testUnRepeatableRead();
    }
}
