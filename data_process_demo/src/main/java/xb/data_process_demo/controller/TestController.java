package xb.data_process_demo.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xb.data_process_demo.bean.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    /**
     * 字符串数组传递
     * @param data
     * @return
     */
    @RequestMapping("/simpleArr")
    @ResponseBody
    public String[] simpleArr(@RequestParam String[] data){
        System.out.println(data);
        return data;
    }

    /**
     * 对象数组传递
     * @param persons
     * @return
     * @throws IOException
     */
    @PostMapping("/objArr")
    @ResponseBody
    public String objArr(@RequestParam String persons) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,Person.class);
        List<Person> personList = mapper.readValue(persons,javaType);
        System.out.println(personList.size());
        return "123";
    }

}
