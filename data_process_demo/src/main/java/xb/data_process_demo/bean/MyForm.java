package xb.data_process_demo.bean;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MyForm {
    private String name;
    private MultipartFile file;//默认单个文件最大1MB,单次请求最大10MB
}
