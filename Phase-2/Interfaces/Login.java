package team7;

public interface Login {
	
	/*
	 * Create a new user, if not present
	 */
	public void createUser (Object userObject); //This will be an object of user class
	
	/*
	 * Login to the application
	 */
	public void login(String username, String password);
	
	/*
	 * Validates the username and password
	 */
	public boolean validateUser(String username, String password);	
	
	/*
	 * Close the session and logout the user.
	 */
<<<<<<< HEAD
	public void logout(User userObject);
=======
	public void logout (Object userObject); //This will be an object of user class
>>>>>>> 7dd3a2f284a24edb0c8f6aac2ee65fed8870f226
}
