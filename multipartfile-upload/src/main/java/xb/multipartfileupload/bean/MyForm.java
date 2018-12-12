package xb.multipartfileupload.bean;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MyForm {
    private String name;
    private MultipartFile file;
}
