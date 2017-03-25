package com.team7;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JDialog;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import org.apache.commons.codec.binary.Base64;

public class LoginUI extends JFrame implements Login {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameField;
	private JPasswordField passwordField;

	String userName;
	String password;
	String secretKey = "SECRETKEY";

    static LoginUI frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    frame = new LoginUI();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("MSD PROJECT");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome !");
		lblWelcome.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblWelcome.setBounds(401, 39, 153, 32);
		contentPane.add(lblWelcome);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUsername.setBounds(317, 121, 97, 28);
		contentPane.add(lblUsername);

		userNameField = new JTextField();

		UIManager.put("ToolTip.background", Color.orange);
		UIManager.getLookAndFeelDefaults()
		.put("ToolTip.font", new Font("Lucida Grande", Font.BOLD, 14));

		userNameField.setToolTipText("Please enter registered Email-id");
		userNameField.setBounds(472, 119, 191, 34);
		contentPane.add(userNameField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPassword.setBounds(317, 208, 81, 19);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(472, 201, 191, 34);
		contentPane.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogin.setBounds(408, 282, 117, 34);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// encrypted password from the UI
				userName = userNameField.getText();
				String plainText = new String(passwordField.getPassword());

				if (userName.equals("")) //username should not be empty
					messageShow("Please enter username");
				else if (plainText.equals(""))
					messageShow("Please enter password"); // password should not be empty
				else {
					RegisterUI register;
					try {
						register = new RegisterUI();
						register.setLocationRelativeTo(null);
						register.setVisible(false);
						
						// check if user exists or not and then validate the password.
						if (register.verifyIfUserExists(userName)) {

							if (login(userName, plainText)) {
								
								// let it go to the search page
								//messageShow("Logged In Successfully");
								frame.dispose();
								SearchUI search = new SearchUI();
								search.setLocationRelativeTo(null);								
							}
							else {
								messageShow("Invalid Credentials");
							}
						}
						else {
							messageShow("User does not exist. Please register");
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}		

				}
			}
		});

		JButton btnNewUserClick = new JButton("New User? Click to Register");
		btnNewUserClick.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewUserClick.setBounds(333, 339, 287, 34);
		contentPane.add(btnNewUserClick);
		btnNewUserClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUI register;
				try {
					// go to the register page
					frame.dispose();
					register = new RegisterUI();
					register.setSize(950, 600);
					register.setLocationRelativeTo(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}

	public boolean login(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		if (username != null){
			ImplementSchemaDB db = new ImplementSchemaDB();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select password from user where username = '" +username +"'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String plainText = rs.getString(1);
				String finalVal ="";
				try { // decrypting the password
					byte[] encryptedData = Base64.decodeBase64(plainText);
					SecretKeySpec skeyspec=new SecretKeySpec(secretKey.getBytes(),"Blowfish");
					Cipher cipher=Cipher.getInstance("Blowfish");
					cipher.init(Cipher.DECRYPT_MODE, skeyspec);
					byte[] decrypted=cipher.doFinal(encryptedData);
					finalVal=new String(decrypted);

				} catch (Exception e2) {
					e2.printStackTrace();
				}

				if (finalVal.equals(password)) { // if inserted password and the one from the db
					// is the same then let the user login
					return true;
				}
				else { // if incorrect password then don't let the user enter
					return false;
				}	
			}
		}
		return false; // if user does not exist;
	}

	public void messageShow (String msg) {

		JDialog d = new JDialog(frame, msg, true);
		d.setSize(500, 100);
		d.setLocationRelativeTo(frame);
		d.setVisible(true);

	}

	public String logout(User userObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
