package view;

import service.UserService;
import service.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register {
    public static JFrame frame;
    public static JTextField user_name;
    public static JPanel tips;
    public static JLabel name_tips;
    public static JPasswordField password;
    public static JPasswordField repassword;
    private JButton registerButton;
    private JLabel support1;
    private JLabel support2;
    private JLabel support3;
    private JLabel error1;
    private JLabel error2;

    UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register window = new Register();
//					window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Register() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 765, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setFocusable(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.requestFocus();
            }
        });

        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setIcon(new ImageIcon(Register.class.getResource("/view/image/register1.png")));
        lblNewLabel.setBounds(0, 84, 768, 7);
        frame.getContentPane().add(lblNewLabel);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Register.class.getResource("/view/image/register2.png")));
        label.setBounds(0, 0, 155, 91);
        frame.getContentPane().add(label);

        JLabel label_1 = new JLabel();
        label_1.setIcon(new ImageIcon(Register.class.getResource("/view/image/register3.png")));
        label_1.setBounds(526, 34, 124, 57);
        frame.getContentPane().add(label_1);

        final JButton relogin = new JButton();
        relogin.setIcon(new ImageIcon(Register.class.getResource("/view/image/register4.png")));
        relogin.setBounds(642, 48, 91, 30);
        relogin.setBorder(null);
        relogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        relogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userService.relogin();
            }
        });
        frame.getContentPane().add(relogin);

        JLabel label_2 = new JLabel("  用 户 名");
        label_2.setForeground(Color.GRAY);
        label_2.setHorizontalAlignment(SwingConstants.LEFT);
        label_2.setFont(new Font("幼圆", Font.PLAIN, 25));
        label_2.setBounds(90, 199, 213, 70);
        label_2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY));
        frame.getContentPane().add(label_2);

        user_name = new JTextField();
        user_name.setForeground(Color.GRAY);
        user_name.setFont(new Font("幼圆", Font.PLAIN, 25));
        user_name.setBounds(303, 199, 356, 70);
        user_name.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
        user_name.setColumns(10);
        user_name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                support1.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                String user_name = Register.user_name.getText().trim();
                if (user_name.equals("")) {
                    error1.setVisible(true);
                    support1.setVisible(false);
                    registerButton.setEnabled(false);
                } else {
                    boolean isExist = userService.checkAndTips(user_name);
                    if (isExist){
                        Register.user_name.requestFocus();
                        registerButton.setEnabled(false);
                    }
                    else {
                        error1.setVisible(false);
                        support1.setVisible(false);
                        registerButton.setEnabled(true);
                    }

                }
            }
        });
        frame.getContentPane().add(user_name);

        tips = new JPanel();
        tips.setBounds(90, 269, 569, 109);
//        tips.setBorder(BorderFactory.createLineBorder(new Color(255,153,136)));
        tips.setLayout(null);
        tips.setVisible(false);
        frame.getContentPane().add(tips);


        JLabel label_tips = new JLabel();
        label_tips.setOpaque(true);
        label_tips.setBackground(new Color(253, 242, 239));
        label_tips.setHorizontalAlignment(SwingConstants.CENTER);
        label_tips.setIcon(new ImageIcon(Register.class.getResource("/view/image/register_tips.png")));
        label_tips.setBorder(BorderFactory.createMatteBorder(0,1,0,1,new Color(255,153,136)));
        label_tips.setBounds(0, 0, 569, 53);
        tips.add(label_tips);

        name_tips = new JLabel();
        name_tips.setOpaque(true);
        name_tips.setForeground(Color.DARK_GRAY);
        name_tips.setHorizontalAlignment(SwingConstants.CENTER);
        name_tips.setFont(new Font("幼圆", Font.BOLD, 20));
        name_tips.setBounds(0, 53, 569, 55);
        name_tips.setBackground(new Color(255, 234, 229));
        name_tips.setBorder(BorderFactory.createMatteBorder(0,1,1,1,new Color(255,153,136)));
        name_tips.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                user_name.setText(name_tips.getText());
                tips.setVisible(false);
            }
        });
        name_tips.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tips.add(name_tips);

        support1 = new JLabel();
        support1.setIcon(new ImageIcon(Register.class.getResource("/view/image/support1.png")));
        support1.setBounds(90, 269, 568, 39);
        support1.setVisible(false);
        frame.getContentPane().add(support1);

        error1 = new JLabel();
        error1.setIcon(new ImageIcon(Register.class.getResource("/view/image/error1.png")));
        error1.setBounds(90, 269, 568, 39);
        error1.setVisible(false);
        frame.getContentPane().add(error1);

        JLabel label_3 = new JLabel("  设 置 密 码");
        label_3.setForeground(Color.GRAY);
        label_3.setHorizontalAlignment(SwingConstants.LEFT);
        label_3.setFont(new Font("幼圆", Font.PLAIN, 25));
        label_3.setBounds(90, 308, 213, 70);
        label_3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY));
        frame.getContentPane().add(label_3);

        password = new JPasswordField();
        password.setFont(new Font("宋体", Font.PLAIN, 25));
        password.setBounds(303, 308, 356, 70);
        password.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
        password.setColumns(10);
        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                support2.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                support2.setVisible(false);
            }
        });
        frame.getContentPane().add(password);

        support2 = new JLabel();
        support2.setIcon(new ImageIcon(Register.class.getResource("/view/image/support2.png")));
        support2.setBounds(90, 376, 569, 49);
        support2.setVisible(false);
        frame.getContentPane().add(support2);

        JLabel label_4 = new JLabel("  确 认 密 码");
        label_4.setHorizontalAlignment(SwingConstants.LEFT);
        label_4.setFont(new Font("幼圆", Font.PLAIN, 25));
        label_4.setForeground(Color.GRAY);
        label_4.setBounds(90, 426, 213, 70);
        label_4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY));
        frame.getContentPane().add(label_4);

        repassword = new JPasswordField();
        repassword.setFont(new Font("宋体", Font.PLAIN, 25));
        repassword.setBounds(303, 426, 356, 70);
        repassword.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
        repassword.setColumns(10);
        repassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                support3.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                String password = String.valueOf(Register.password.getPassword());
                String repassword = String.valueOf(Register.repassword.getPassword());
                if (!repassword.equals(password)) {
                    error2.setVisible(true);
                    support3.setVisible(false);
                    registerButton.setEnabled(false);
                } else {
                    error2.setVisible(false);
                    support3.setVisible(false);
                    registerButton.setEnabled(true);
                }


            }
        });
        frame.getContentPane().add(repassword);

        support3 = new JLabel();
        support3.setIcon(new ImageIcon(Register.class.getResource("/view/image/support3.png")));
        support3.setBounds(90, 495, 569, 39);
        support3.setVisible(false);
        frame.getContentPane().add(support3);

        error2 = new JLabel();
        error2.setIcon(new ImageIcon(Register.class.getResource("/view/image/error2.png")));
        error2.setBounds(90, 495, 569, 39);
        error2.setVisible(false);
        frame.getContentPane().add(error2);


        JLabel lblTian = new JLabel("填写用户信息");
        lblTian.setFont(new Font("幼圆", Font.BOLD, 22));
        lblTian.setHorizontalAlignment(SwingConstants.CENTER);
        lblTian.setForeground(new Color(50, 205, 50));
        lblTian.setBounds(236, 104, 273, 65);
        frame.getContentPane().add(lblTian);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(226, 35, 26));
        panel.setBounds(90, 548, 569, 70);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        registerButton = new JButton("立即注册");
        registerButton.setBounds(0, 0, 569, 70);
        panel.add(registerButton);
        registerButton.setFont(new Font("宋体", Font.BOLD, 25));
        registerButton.setForeground(Color.WHITE);
        registerButton.setContentAreaFilled(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(password.getPassword().length != 0 && repassword.getPassword().length != 0){
                    userService.register();
                }
            }
        });
    }
}
