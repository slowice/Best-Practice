package xb.hook.interceptor;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class TestController {

    //测试类，测试request interceptor
   //@RequestMapping("/test")
    public String test(){
        return "invoke success";
    }
}
