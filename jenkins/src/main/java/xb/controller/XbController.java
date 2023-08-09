package xb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xb.iservice.JenkinsHelper;

import java.io.IOException;

@RestController
public class XbController {
    @Autowired
    JenkinsHelper jenkinsHelper;

    @RequestMapping("/doJenkins")
    public String doJenkins(@RequestParam String code){
        String result = null;
        try {
            result = jenkinsHelper.doJenkins(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
