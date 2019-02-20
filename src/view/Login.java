package view;

import service.UserService;
import service.UserServiceImpl;
import utils.RemovePasswordTips;
import utils.RemoveTextTips;

import java.awt.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	public static JFrame frame;
	public static JTextField user_name;
	public static JPasswordField password;
	public static JLabel tips;

	UserService userService = new UserServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 565, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("登录");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(true);
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("欢迎登录");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(0, 13, 190, 55);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/view/image/login1.png")));
		lblNewLabel_2.setBounds(95, 81, 370, 90);
		frame.getContentPane().add(lblNewLabel_2);

		JButton register_Button = new JButton("");
		register_Button.setIcon(new ImageIcon(Login.class.getResource("/view/image/register.png")));
		register_Button.setBounds(293, 548, 55, 36);
		register_Button.setBorder(null);
		register_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		register_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				new Register();
			}
		});
		frame.getContentPane().add(register_Button);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/view/image/login3.png")));
		lblNewLabel_3.setBounds(0, 528, 547, 75);
		frame.getContentPane().add(lblNewLabel_3);

		JButton loginButton = new JButton();
		loginButton.setIcon(new ImageIcon(Login.class.getResource("/view/image/login2.png")));
		loginButton.setBounds(86, 421, 390, 44);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userService.login();
			}
		});
		frame.getContentPane().add(loginButton);

		JLabel label_1 = new JLabel();
		label_1.setBounds(86, 316, 12, 63);
		label_1.setBorder(BorderFactory.createMatteBorder(1,1,1,0,Color.GRAY));
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel();
		label_2.setBorder(BorderFactory.createMatteBorder(1,1,1,0,Color.GRAY));
		label_2.setBounds(86, 199, 12, 63);
		frame.getContentPane().add(label_2);

		user_name = new JTextField();
		user_name.setFont(new Font("幼圆", Font.PLAIN, 30));
		user_name.setForeground(Color.GRAY);
		user_name.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.GRAY));
		user_name.setText("用户名");
		user_name.setBounds(98, 199, 381, 63);
		user_name.setColumns(10);
		user_name.addFocusListener(new RemoveTextTips("用户名",user_name));
		frame.getContentPane().add(user_name);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(98, 316, 381, 63);
		frame.getContentPane().add(layeredPane);

		password = new JPasswordField();
		password.setBounds(0, 0, 381, 63);
		layeredPane.add(password,new Integer(0));
		password.setFont(new Font("宋体", Font.PLAIN, 40));
		password.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.GRAY));
		password.addFocusListener(new RemovePasswordTips());

		tips = new JLabel();
		tips.setBounds(4, 4, 67, 55);
		layeredPane.add(tips,new Integer(1));
		tips.setHorizontalAlignment(SwingConstants.CENTER);
		tips.setIcon(new ImageIcon(Login.class.getResource("/view/image/password.png")));
	}
}
