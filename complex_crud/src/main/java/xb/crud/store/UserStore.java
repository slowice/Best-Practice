package xb.crud.store;

import xb.common.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserStore {
    void save(User user);

    void saveAll(List<User> users);

    void deleteById(String userId);

    Optional<User> findById(String userId);
}
