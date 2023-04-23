package xb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xb.iservice.XbService;

@RestController
public class XbController {
    @Autowired
    XbService xbService;
    @RequestMapping("/doJeknins")
    public String doJenkins(@RequestParam String code){
        String result = xbService.doJenkins(code);
        return result;
    }
}
