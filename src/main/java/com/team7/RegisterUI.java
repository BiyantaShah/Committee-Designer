package com.team7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class RegisterUI extends JFrame implements Register {

	private JPanel contentPane;
	private JTextField UsernameTField;
	private JPasswordField passwordField;

	String userName;
	String password;
	String role;
	String confName;
	String secretKey = "SECRETKEY";
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	static RegisterUI frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame = new RegisterUI();
					frame.setVisible(true);
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
					frame.setTitle("Register");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public RegisterUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -28, 933, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsername.setBounds(257, 86, 97, 28);
		contentPane.add(lblUsername);

		UsernameTField = new JTextField(10);

		UIManager.put("ToolTip.background", Color.orange);
		UIManager.getLookAndFeelDefaults()
		.put("ToolTip.font", new Font("Arial", Font.BOLD, 14));


		UsernameTField.setToolTipText("Please enter valid Email-id");

		UsernameTField.setBounds(521, 83, 191, 34);
		contentPane.add(UsernameTField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPassword.setBounds(257, 180, 81, 19);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(521, 165, 191, 34);
		contentPane.add(passwordField);


		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Calibri", Font.BOLD, 20));
		lblRole.setBounds(257, 264, 56, 16);
		contentPane.add(lblRole);
		String[] roleList = {"Conference Chair","General Chair","Member for External Review Committee","Program Chair"};

		@SuppressWarnings("rawtypes")
		final JComboBox role_combo = new JComboBox(roleList);
		role = roleList[0];//setting initial value
		role_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				role = String.valueOf(role_combo.getSelectedItem());

			}
		});
		role_combo.setBounds(521, 246, 191, 34);
		contentPane.add(role_combo);


		JLabel lblConference = new JLabel("Conference");
		lblConference.setFont(new Font("Calibri", Font.BOLD, 20));
		lblConference.setBounds(257, 330, 97, 28);
		contentPane.add(lblConference);
		String[] confList = { "ASE", "ECOOP","ESOP","FSE", "ICFP","ICSE","ISMM","ISSTA","OOPSLA","PLDI","POPL","PPOPP"};

		@SuppressWarnings("rawtypes")
		final JComboBox conf_combo = new JComboBox(confList);
		confName = confList[0];  //setting initial value
		conf_combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				confName = String.valueOf(conf_combo.getSelectedItem());

			}
		});
		conf_combo.setBounds(521, 324, 191, 34);
		contentPane.add(conf_combo);


		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Calibri", Font.BOLD, 20));
		btnRegister.setBounds(394, 451, 114, 34);
		contentPane.add(btnRegister);
		btnRegister.addActionListener(new ActionListener()
		{

			boolean res;

			public void actionPerformed(ActionEvent e)
			{

				userName = UsernameTField.getText();
				String plainPwd = new String(passwordField.getPassword());


				if(userName.equals("")){

					errorMessage("Please enter  user name");


				} else if(plainPwd.equals("")){

					errorMessage("Please enter password");

				} else{
					//no duplicate entry  - encrypt password

					try {

						SecretKeySpec skeyspec=new SecretKeySpec(secretKey.getBytes(),"Blowfish");
						Cipher cipher = Cipher.getInstance("Blowfish");
						cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
						byte[] encrypted=cipher.doFinal(plainPwd.getBytes());
						password=new String(encrypted);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						//insert data into table
						res = createUser(userName,password,role,confName);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 

					if(res == true){
						//connect to search page
						errorMessage("registered successfully");
						frame.dispose();	

					}
				}}});
	}


	public void errorMessage(String msg){

		JDialog d = new JDialog(frame, msg, true);
		d.setSize(300, 50);
		d.setLocationRelativeTo(frame);
		d.setVisible(true);
		//frame.dispose();

	}

	public boolean createUser(String userName, String password, String role, String confName) throws SQLException {

		if(veryifyIfUserExists(userName))
		{			
			errorMessage("Username already exists");

		}else{

			User user = new User(userName,password,role,confName);
			ImplementSchemaDB db= new ImplementSchemaDB();
			boolean res = db.insertData(user);

			if(res == true){

				return true;

			}
		}
		return false;

	}

	public boolean veryifyIfUserExists(String userName) throws SQLException {

		if(userName != null)
		{
			ImplementSchemaDB db = new ImplementSchemaDB();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "Select count(*) from user where username = " + "'" +userName+ "'"; 

			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next()){

				if(rs.getInt(1) > 0){
					System.out.println(rs.getString(1));
					return true; 
				}
			}
		}

		return false;
	}
}
