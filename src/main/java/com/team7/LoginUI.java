package com.team7;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPasswordField;

// LoginUI window
public class LoginUI extends JFrame {

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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws JAXBException 
	 */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, JAXBException, IOException {

    	System.out.println("Start");

    	File file = new File("input/dblp.xml");
    	File comData = new File("input/committees/");
    	// Parsing the xml to create objects
    	ImplementParseDatabase parse = new ImplementParseDatabase();
    	ImplementSchemaDB db=new ImplementSchemaDB();;
    	ImplementCommittees com = new ImplementCommittees();

		try {
			
//	    	db.dbSetUp();   //set up initial database
//	    	parse.parseXml(file);	//parse xml data
//	    	com.ParseFiles(comData); //parse committee data

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				frame= new LoginUI();
    				frame.setVisible(true);
    				frame.setLocationRelativeTo(null);
    				frame.setTitle("MSD PROJECT");
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    	
    	System.out.println("End");
    }

	/**
	 * Create the frame.
	 */
	public LoginUI() {
		
		setSize(950, 600);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("WELCOME !");
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
					try {
						ImplementRegister register = new ImplementRegister();
						// check if user exists or not and then validate the password.
						if (register.verifyIfUserExists(userName)) {
							ImplementLogin login = new ImplementLogin();
							if (login.login(userName, plainText)) {
								
								// let it go to the search page if login is successful
								dispose();
								SearchUI search = new SearchUI(userName);
								search.setSize(950, 600);
								search.setLocationRelativeTo(null);
								
							}
							else {
								messageShow("Invalid Credentials: Username and password don't match");
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
					dispose();
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

	public void messageShow (String msg) {

		JDialog d = new JDialog(frame, msg, true);
		d.setSize(500, 100);
		d.setLocationRelativeTo(frame);
		d.setVisible(true);

	}

}
