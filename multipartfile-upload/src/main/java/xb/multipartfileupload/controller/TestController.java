package xb.multipartfileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xb.multipartfileupload.bean.MyForm;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  默认单个文件最大1MB,单次请求最大10MB
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public String testPage(){
        return "test";
    }

    @PostMapping("/upload1")
    @ResponseBody
    public String uploadFiles1(@RequestParam("name")String name, @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("hello "+name);
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            return "upload success!";
        }
        return "upload failed!";
    }

    @PostMapping("/upload2")
    @ResponseBody
    public String uploadFiles2(MyForm form) throws IOException {
        String name = form.getName();
        MultipartFile file = form.getFile();
        System.out.println("hello "+name);
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            return "upload success!";
        }
        return "upload failed!";
    }

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        Collection<List<String>> values = map.values();
        for(List<String> obj : values){
            System.out.println(obj);
        }
    }
}
