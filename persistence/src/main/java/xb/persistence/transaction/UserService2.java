package xb.persistence.transaction;

import xb.entity.User;

public interface UserService2 {
    void updateTransactional(User user) throws Exception;
}
