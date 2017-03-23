package com.team7;

import java.sql.SQLException;

public interface Login {
	
	
	/*
	 * Login to the application by validating the username and password
	 */
	public boolean login(String username, String password) throws SQLException;
	
	
	/*
	 * Close the session and logout the user.
	 */
	public String logout(User userObject);
}
