package xb.crud;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import xb.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationContext ctx;

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void addBatch(List<User> userList) {
        userRepository.saveAll(userList);
    }

    @Override
    public void delete(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public String query(String userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        String name = userOpt.map(user -> user.getName()).orElse("查无此人");
        System.out.println(name);
        return name;
    }

    // 默认单文件1mb,单请求10mb限制 spring.servlet.multipart.max-file-size=-1不限制
    @Override
    public String fileUpload(String userId, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 文件名称
                String fileName = file.getName();
                InputStream inputStream = file.getInputStream();
                try {
                    // 文件大小
                    int fileSize = inputStream.available();
                    // store the bytes somewhere
                    byte[] bytes = file.getBytes();
                    //String classpathUrl = "classpath:"+"backup-"+fileName;
                    String path = "./"+fileName;
                    Path p = Paths.get(path);
                    try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))) {
                        out.write(bytes, 0, bytes.length);
                    } catch (IOException x) {
                        System.err.println(x);
                    }
                } catch (Exception e) {
                    // log
                    e.printStackTrace();
                } finally {
                    IOUtils.closeQuietly(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "uploadSuccess";
        }
        return "uploadFailure";
    }

    @Override
    public String fileDownload(String fileId, HttpServletResponse response) throws IOException {
        // load resource
        Resource resource = ctx.getResource("classpath:application.yml");
        if (!resource.exists()) {
            // log&ex
        }
        // output resource
        String filename = resource.getFilename();
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        try {
            inputStream = resource.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            FileCopyUtils.copy(bufferedInputStream, bufferedOutputStream);
        }catch(Exception e){
            // log&ex
        }finally {
            if(null!=inputStream){
                inputStream.close();
            }
            if(null!=bufferedInputStream){
                bufferedInputStream.close();
            }
            if(null!=bufferedOutputStream){
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
        }
        return null;
    }
}
