package xb.persistence.transaction;


import xb.common.entity.User;

import java.util.List;

public interface UserService {
    void proganationTest(List<User> user) throws Exception;
    void updateTransactional(User user) throws Exception;
}
