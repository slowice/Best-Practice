package xb.common.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import xb.common.entity.User;
import xb.common.entity.UserRelation;
import xb.common.entity.UserRole;

import java.util.List;

@Data
@Slf4j
/**
 * User传输类，除了包含User表元数据以外，还包含其它关联数据
 */
public class UserDTO extends User {
    public UserDTO(){
        super();
    }

    public UserDTO(String idUser,String name){
        log.info("******************UserDto id is{}",idUser);
        log.info("******************UserDto name is{}",name);
    }

    private UserRelation userRelation;

    private List<UserRole> roleList;

    private List<User> userList;

    private MultipartFile file;

    private List<MultipartFile>  files;
}
