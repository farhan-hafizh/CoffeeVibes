package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.LoginController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginPage {

	private static JFrame frame;


	private JTextField username;
	private JPasswordField password;
	
	public static JFrame getFrame() {
		return frame;
	}
	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(48, 66, 103, 16);
		frame.getContentPane().add(lblUsername);
		
		username = new JTextField();
		username.setBounds(169, 66, 148, 22);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(48, 111, 89, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController.Login();
			}
		});
		btnLogin.setBounds(90, 167, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(221, 167, 97, 25);
		frame.getContentPane().add(btnReset);
		
		password = new JPasswordField();
		password.setBounds(169, 108, 148, 23);
		frame.getContentPane().add(password);
		frame.setVisible(true);
	}
}
