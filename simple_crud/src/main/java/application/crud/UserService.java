package application.crud;


import application.bean.User;

public interface UserService {
    void add(User user);
    void delete(String userId);
    void update(User user);
    void query(String userId);
}
