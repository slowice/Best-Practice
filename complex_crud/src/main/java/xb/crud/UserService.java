package xb.crud;


import org.springframework.web.multipart.MultipartFile;
import xb.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {
    void add(User user);
    void addBatch(List<User> userList);
    void delete(String userId);
    void update(User user);
    String query(String userId);
    String fileUpload(String userId, MultipartFile file);

    String fileDownload(String fileId, HttpServletResponse response) throws IOException;
}
