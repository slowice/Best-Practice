package xb.data_process_demo.data_access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xb.data_process_demo.bean.ClassRoom;
import xb.data_process_demo.bean.MyForm;
import xb.data_process_demo.bean.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class DataAccessConroller {

    private static final ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    /**
     * 综合表单数据传递
     */
    @RequestMapping("/formHandler")
    public String formHandler(MyForm myForm) throws IOException {
        String name = myForm.getName();
        MultipartFile file = myForm.getFile();
        System.out.println("hello "+name);
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            return "redirect";
        }
        return "upload failed!";
    }

    /**
     * 字符串数组传递
     * 注意参数名称要和前端一致
     */
    @RequestMapping("/simpleArr")
    @ResponseBody
    public String[] simpleArr(@RequestParam String[] data) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString(data));
        return data;
    }

    /**
     * 对象数组传递
     */
    @PostMapping("/objArr")
    @ResponseBody
    public String objArr(@RequestParam String user) throws IOException {
        System.out.println(user);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Student.class);
        List<Student> studentList = mapper.readValue(user,javaType);
        for(Student u: studentList){
            System.out.println(mapper.writeValueAsString(u));
        }
        return null;
    }

    /**
     * 复选框数据传递
     */
    @RequestMapping(value = "/checkBox",method = RequestMethod.POST)
    @ResponseBody
    public String checkBox(@RequestParam String[] data) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString(data));
        return null;
    }

    /**
     * 嵌套对象的序列化与反序列化
     */
    @PostMapping("/objFromForm")
    public String redirect(@RequestParam String _classRoom) throws IOException {
        System.out.println(_classRoom);
        ClassRoom classRoom = mapper.readValue(_classRoom,ClassRoom.class);
        System.out.println(classRoom.getName());
        for(Student s:classRoom.getStudent()){
            System.out.println(s.getName());
        }
        return "redirect";
    }
}
