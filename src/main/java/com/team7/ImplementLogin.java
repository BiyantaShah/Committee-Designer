package com.team7;

public class ImplementLogin implements Login {
	
	public ImplementLogin(){
	}

	public String createUser(String userName, String password, String role, String confName) {
		// TODO Auto-generated method stub
		
		User newUser = new User(userName, password, role, confName);
		
		//send this newUser to DB to add the info to user table
		
		return null;
	}

	public String login(String username, String password) {
		// TODO Auto-generated method stub
						
	     return null;
	}

	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public String logout(User userObject) {
		// TODO Auto-generated method stub
		return null;
		
	}
}
