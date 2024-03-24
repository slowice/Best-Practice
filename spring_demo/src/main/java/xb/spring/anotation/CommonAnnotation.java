package xb.spring.anotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonAnnotation {
    //todo
    //RequestBody https://blog.csdn.net/originations/article/details/89492884
    @RequestMapping("/testCommonAnnotation")
    public String testCommonAnnotation(){
        return null;
    }
}
