package xb.javascript.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaScriptController {

    @GetMapping(value = "/demo.js", produces = "text/javascript")
    public String demo() {
        return "console.log('javascript_controller_demo loaded');";
    }
}
