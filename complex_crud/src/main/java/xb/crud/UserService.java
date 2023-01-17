package xb.crud;


import org.springframework.web.multipart.MultipartFile;
import xb.entity.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void addBatch(List<User> userList);
    void delete(String userId);
    void update(User user);
    String query(String userId);
    String fileUpload(MultipartFile file);
}
