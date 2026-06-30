package xb.crud.store;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import xb.common.entity.User;
import xb.crud.store.mybatis.UserMapper;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "complex-crud.persistence", havingValue = "mybatis")
public class MyBatisUserStore implements UserStore {
    private final UserMapper userMapper;

    public MyBatisUserStore(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        if (userMapper.updateById(user) == 0) {
            userMapper.insert(user);
        }
    }

    @Override
    public void saveAll(List<User> users) {
        for (User user : users) {
            save(user);
        }
    }

    @Override
    public void deleteById(String userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(userMapper.findById(userId));
    }
}
