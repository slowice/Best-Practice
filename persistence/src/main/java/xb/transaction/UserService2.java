package xb.transaction;

import bean.User;

public interface UserService2 {
    void updateTransactional(User user) throws Exception;
}
