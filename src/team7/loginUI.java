package team7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class loginUI {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblusername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginUI window = new loginUI();
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
	public loginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 12));
		frame.setBounds(100, 100, 576, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Welcome");
				lblusername.setText("hello");
			}
		});
		btnLogin.setBounds(195, 201, 97, 25);
		frame.getContentPane().add(btnLogin);
		
	    lblusername = new JLabel("Username");
		lblusername.setFont(new Font("Calibri", Font.BOLD, 14));
		lblusername.setBounds(121, 95, 78, 25);
		frame.getContentPane().add(lblusername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPassword.setBounds(120, 143, 79, 16);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(223, 140, 116, 22);
		frame.getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(223, 94, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
