package utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class RemoveTextTips implements FocusListener{
	private String mHintText;
    private JTextField mTextField;

    public RemoveTextTips(String hintText, JTextField textField) {
        this.mHintText = hintText;
        this.mTextField = textField;
        textField.setForeground(Color.GRAY);
    }

    public void focusGained(FocusEvent e) {
        String temp = mTextField.getText();
        if(temp.equals(mHintText)){
            mTextField.setText("");
        }
    }

    public void focusLost(FocusEvent e) {
        String temp = mTextField.getText();
        if(temp.trim().equals("")){
            mTextField.setText(mHintText);
        }
    }
}
