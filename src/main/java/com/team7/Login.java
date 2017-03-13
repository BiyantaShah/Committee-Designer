package com.team7;

public interface Login {
	
	/*
	 * Create a new user, if not present
	 */
	public String createUser(String userName, String password, String role, String confName);
	
	/*
	 * Login to the application
	 */
	public String login(String username, String password);
	
	/*
	 * Validates the username and password
	 */
	public boolean validateUser(String username, String password);	
	
	/*
	 * Close the session and logout the user.
	 */
	public String logout(User userObject);
}
