package com.team7;


public class UIConstants {
	
	// to maintain the 'session' for the user.
	static String currentUser; 
	static String currentuserRole;
	
	public UIConstants(String name,String role){
		currentUser = name;
		currentuserRole = role;
		System.out.println("hey"+currentUser);
		System.out.println("hi"+currentuserRole);		
	}

	//To give authority for final selection of candidates
	static String HighestRole = "Program Chair";
	
	static int width = 1200;
	static int height = 750;
	
	

}
