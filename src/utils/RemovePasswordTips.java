package utils;

import view.Login;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RemovePasswordTips implements FocusListener {
    @Override
    public void focusGained(FocusEvent e) {
            Login.tips.setVisible(false);
    }

    @Override
    public void focusLost(FocusEvent e) {
        String password= String.valueOf(Login.password.getPassword()).trim();
        if (password.equals("") || password.length() == 0) {
            Login.tips.setVisible(true);
        }
    }
}
