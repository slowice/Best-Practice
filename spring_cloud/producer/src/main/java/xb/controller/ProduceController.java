package xb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/producer")
public class ProduceController {
    @RequestMapping("/produce")
    public String produce() {
        return "Hello World";
    }
}
