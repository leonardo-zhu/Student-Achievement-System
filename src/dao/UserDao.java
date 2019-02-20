package dao;

import entity.User;

public interface UserDao {
    boolean checkUser(User user);

    boolean checkUser(String user_name);

    void save(User user);
}
