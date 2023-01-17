package xb.crud;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import xb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String root(){
        return String.format("this is %s", applicationName);
    }

    @PostMapping("/crud_add")
    public String add(@RequestBody User user){
        userService.add(user);
        return "add ok";
    }

    @PostMapping("/crud_delete")
    public String delete(@RequestParam String idUser){
        userService.delete(idUser);
        return "delete ok";
    }

    @PostMapping("/crud_update")
    public String update(@RequestBody User user){
        userService.update(user);
        return "update ok";
    }

    @GetMapping("/crud_query")
    public String query(@RequestParam String idUser){
        String name = userService.query(idUser);
        return name;
    }

    @PostMapping("/crud_fileupload")
    public String handleFormUpload(@RequestParam("file") MultipartFile file) {
        return userService.fileUpload(file);
    }
}
