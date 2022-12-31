package xb.crud;


import xb.bean.User;

public interface UserService {
    void add(User user);
    void delete(String userId);
    void update(User user);
    String query(String userId);
}
