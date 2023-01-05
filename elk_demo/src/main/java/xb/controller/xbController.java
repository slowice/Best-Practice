package xb.controller;



import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class xbController {
    private static final Logger logger = LoggerFactory.getLogger(xbController.class);
    @RequestMapping("/test")
    public void test(){
        logger.info(JSONObject.toJSONString(JSONObject.toJSON("123123")));
    }
}
