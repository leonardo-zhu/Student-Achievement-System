package service;


public interface UserService {

    void login();

    void register();

    boolean checkAndTips(String user_name);

    void relogin();

    void clear();
}
