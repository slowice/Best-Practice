package xb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xb.service.Producer;

@RestController
public class ConsumerController {
    @Autowired
    Producer producer;

    @RequestMapping("/consume")
    public String consume(String message) {
        String str = producer.produce();
        System.out.println(str);
        return "Hello World";
    }
}
