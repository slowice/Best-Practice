package xb.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
/**
 * User关联信息
 */
public class UserRelation implements Serializable {
    private String userId;
    private String addressId;
    private MultipartFile file;
}
