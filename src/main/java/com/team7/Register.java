package com.team7;

import java.sql.SQLException;

public interface Register {
	
	/*
	 * Create a new user, if not present
	 */
	public boolean createUser(String userName, String password, String role, String confName) throws SQLException;

	/*
	 * Check if user already exists
	 */
	public boolean veryifyIfUserExists(String userName) throws SQLException;

}
