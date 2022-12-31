package xb.crud;

import xb.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
