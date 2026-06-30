package xb.crud.store;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import xb.common.entity.User;
import xb.crud.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "complex-crud.persistence", havingValue = "jpa", matchIfMissing = true)
public class JpaUserStore implements UserStore {
    private final UserRepository userRepository;

    public JpaUserStore(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }
}
