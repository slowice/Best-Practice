package xb.crud;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import xb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    static class OOMObject{
    }
    private static final List<OOMObject> testOOMMap = new ArrayList<>();

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    UserService userService;

    // 健康检查
    @GetMapping("/healthcheck")
    public String root() throws InterruptedException {
        while(true){
            testOOMMap.add(new OOMObject());

            if(false){
                break;
            }
        }
        return String.format("this is %s", applicationName);
    }

    // 添加
    @PostMapping("/crud_add")
    public String add(@RequestBody User user){
        userService.add(user);
        return "add ok";
    }

    // 删除
    @PostMapping("/crud_delete")
    public String delete(@RequestParam String idUser){
        userService.delete(idUser);
        return "delete ok";
    }

    // 修改
    @PostMapping("/crud_update")
    public String update(@RequestBody User user){
        userService.update(user);
        return "update ok";
    }

     // 查询
    @GetMapping("/crud_query")
    public String query(@RequestParam String idUser){
        String name = userService.query(idUser);
        return name;
    }

    // 文件上传
    @PostMapping("/crud_fileupload")
    public String handleFormUpload(@RequestParam("userId")String userId, MultipartFile file) {
        return userService.fileUpload(userId, file);
    }

    @ApiOperation(value = "SpringBoot实现文件下载", notes = "使用缓存流，边读边写")
    @GetMapping("/crud_filedownload")
    @ResponseBody
    public String handleFileDownload(@RequestParam("fileId")String fileId, HttpServletResponse response) throws IOException {
        return userService.fileDownload(fileId, response);
    }
}
