package com.team7.interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Interface SchemaDB.
 */
public interface SchemaDB {

	/**
	 * Db set up.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Creates database and tables
	public void dbSetUp() throws ClassNotFoundException, SQLException,IOException;

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Create connection to RDBMS 
	public Connection getConnection() throws IOException;

	/**
	 * Insert data.
	 *
	 * @param object_name the object name
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Insert the user object created to database
	public boolean insertData(Object object_name) throws SQLException,IOException;
	
	/**
	 * Insert into candidate list.
	 *
	 * @param author the author
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Insert the candidate list details into the database.
	public boolean insertIntoCandidateList(String author) throws SQLException,IOException;
	
	/**
	 * Insert into fav list.
	 *
	 * @param username the username
	 * @param conference the conference
	 * @param author the author
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	// Insert data into favorites list
	public boolean insertIntoFavList(String username, String conference, String author) throws IOException, SQLException;

}
