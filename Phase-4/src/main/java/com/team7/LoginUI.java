package com.team7;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPasswordField;

// LoginUI window
public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public JPanel contentPane;
	public JTextField userNameField;
	public JPasswordField passwordField;
	public JButton btnLogin;
	public JButton btnNewUserClick; 

	public String userName;	
	public String password;

	static LoginUI frame; 


	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		System.setProperty("java.awt.headless", "true");
		// Below was used to create database ,extract xml and insert data into tables

//		File file = new File("input/dblp.xml");
//		File comData = new File("input/committees/");
//		File uniData = new File("input/generate-author-info.csv");
//		File countryData = new File("input/country-info.csv");	
//		File affData = new File("input/faculty-affiliations.csv");
//		File hPageData = new File("input/homepages.csv");
//
//		// Parsing the xml and csv to create objects
//		ImplementParseDatabase parse = new ImplementParseDatabase();
//		ImplementSchemaDB db=new ImplementSchemaDB();
//		ImplementCommittees com = new ImplementCommittees();
//		ImplementUniversityAuthorData uni = new ImplementUniversityAuthorData();
//		ImplementUniCountryData country = new ImplementUniCountryData();
//		ImplementAuthorAffData aff = new ImplementAuthorAffData();
//		ImplementHomePageData hPage = new ImplementHomePageData();
//
//		try {
//			
//			db.dbSetUp();   //set up initial database
//			parse.parseXml(file);	//parse xml data
//			com.ParseFiles(comData); //parse committee data
//			uni.ParseFiles(uniData); // parsing university data
//			country.ParseFiles(countryData); // parsing universities and their country
//			aff.ParseFiles(affData); // parsing the affiliated universities data
//			hPage.ParseFiles(hPageData); // parsing homepage data
//
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}


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

	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {

		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setSize(UIConstants.width, UIConstants.height);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("WELCOME !");
		lblWelcome.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblWelcome.setBounds(551, 65, 153, 32);
		contentPane.add(lblWelcome); 

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUsername.setBounds(437, 151, 97, 28);
		contentPane.add(lblUsername);

		userNameField = new JTextField();

		UIManager.put("ToolTip.background", Color.orange);
		UIManager.getLookAndFeelDefaults()
		.put("ToolTip.font", new Font("Lucida Grande", Font.BOLD, 14));

		userNameField.setToolTipText("Please enter registered Email-id");
		userNameField.setBounds(592, 149, 191, 34);
		contentPane.add(userNameField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPassword.setBounds(437, 228, 81, 19);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(592, 221, 191, 34);
		contentPane.add(passwordField);

		btnLogin = new JButton("Login"); 
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogin.setBounds(558, 312, 117, 34);
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
						try {
							//including an escape character if string contains '
							if(userName.contains("'")){
								userName = userName.replaceAll("'","\\\\'");
							}
							if (register.verifyIfUserExists(userName)) {
								ImplementLogin login = new ImplementLogin();
								if (login.login(userName, plainText)) {

							      // let it go to the search page if login is successful
									dispose();
									SearchUI search = new SearchUI();
									search.setVisible(true);
									setSize(UIConstants.width, UIConstants.height);
									search.setLocationRelativeTo(null);

								}
								else {
									messageShow("Invalid Credentials: Username and password don't match");
								}
							}
							else {
								messageShow("User does not exist. Please register");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
					}		

				}
			}
		}
				);

		btnNewUserClick = new JButton("New User? Click to Register");
		btnNewUserClick.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewUserClick.setBounds(483, 369, 287, 34);
		contentPane.add(btnNewUserClick);
		btnNewUserClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUI register;
				// go to the register page
				dispose();
				register = new RegisterUI();
				register.setVisible(true);
				setSize(UIConstants.width, UIConstants.height);
				register.setLocationRelativeTo(null);
			}
		});
	} 

	public void messageShow (String msg) {

		final JDialog d = new JDialog(frame, msg, true);
		d.setSize(500, 100);
		d.setLocationRelativeTo(frame);


		d.addWindowListener(null);
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Timer timer = new Timer(3000, new ActionListener() { // 3 sec
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
				d.dispose();
			}
		});

		timer.start();
		d.setVisible(true);
	}

}
