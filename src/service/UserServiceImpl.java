package service;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import utils.AddId;
import utils.RandomName;
import view.Login;
import view.Main;
import view.Register;

import javax.swing.*;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    public void login() {
        User user = new User();
        String user_name = Login.user_name.getText().trim();
        String password = String.valueOf(Login.password.getPassword());

        user.setUser_name(user_name);
        user.setPassword(password);

        boolean isExist = userDao.checkUser(user);
        if (isExist) {
//            JOptionPane.showMessageDialog(null, "登录成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
            if (!user_name.equals("admin")) {
                new Main();
                Main.failedScore.setVisible(false);
                Main.failedStudent.setVisible(false);
                Main.addButton.setVisible(false);
                Main.deleteButton.setVisible(false);
                Main.editButton.setVisible(false);
                Main.saveButton.setVisible(false);
                Main.comboBox.setEnabled(false);

                Login.frame.dispose();
            } else {
                new Main();
                Login.frame.dispose();
            }

        } else if (user_name.equals("用户名") || password.equals("")) {
            if (user_name.equals("用户名") && !password.equals("")) {
                JOptionPane.showMessageDialog(null, "用户名为空！", "提示消息", JOptionPane.ERROR_MESSAGE);
                clear();
            } else if (!user_name.equals("用户名") && password.equals("")) {
                JOptionPane.showMessageDialog(null, "密码为空！", "提示消息", JOptionPane.ERROR_MESSAGE);
                clear();
            } else
                JOptionPane.showMessageDialog(null, "请输入用户名和密码！", "提示消息", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "用户名或者密码错误！\n请重新输入！", "提示消息", JOptionPane.ERROR_MESSAGE);
            clear();
        }

    }

    public void register() {
        String user_name = Register.user_name.getText().trim();
        String password = String.valueOf(Register.password.getPassword()).trim();

        User user = new User();
        user.setUser_name(user_name);
        user.setPassword(password);

        String id = AddId.formatId();
        user.setId(id);
        userDao.save(user);
        JOptionPane.showMessageDialog(null, "注册成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
        Register.frame.dispose();
        Login.frame.setVisible(true);

    }

    @Override
    public boolean checkAndTips(String user_name) {
        boolean isExist = userDao.checkUser(user_name);
        if (isExist) {
            Register.tips.setVisible(true);
            Register.name_tips.setText(user_name + RandomName.random());
            return true;
        } else {
            Register.tips.setVisible(false);
            return false;
        }

    }

    public void relogin() {
        Register.frame.dispose();
        Login.frame.setVisible(true);
    }

    public void clear() {
        Login.user_name.setText("");
        Login.password.setText("");
    }


}
