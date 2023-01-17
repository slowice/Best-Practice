package xb.crud;

import org.springframework.web.multipart.MultipartFile;
import xb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

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

    @Override
    public String fileUpload(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // store the bytes somewhere
            return "uploadSuccess";
        }
        return "uploadFailure";
    }
}
