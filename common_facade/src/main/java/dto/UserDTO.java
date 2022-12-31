package dto;

import bean.UserRelation;
import lombok.Data;
import bean.User;
import bean.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

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

    public UserRelation getUserRelation() {
        return userRelation;
    }

    public void setUserRelation(UserRelation userRelation) {
        this.userRelation = userRelation;
    }

    public List<UserRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
