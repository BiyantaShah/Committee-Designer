package com.team7;


public class UIConstants {
	
	// to maintain the 'session' for the user.
	static String currentUser; 
	static String currentUserRole;
	static String currentUserConf;
	
	public UIConstants(String username, String role, String conference) {
		// TODO Auto-generated constructor stub
		currentUser = username;
		currentUserRole = role;
		currentUserConf = conference;
	}

	//To give authority for final selection of candidates
	static String HighestRole = "Program Chair";
	
	static int width = 1200;
	static int height = 770;

	
	

}
