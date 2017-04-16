package com.team7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface SchemaDB {

	// Creates database and tables
	public void dbSetUp() throws ClassNotFoundException, SQLException,IOException;

	// Create connection to RDBMS 
	public Connection getConnection() throws IOException;

	//Insert the user object created to database
	public boolean insertData(Object object_name) throws SQLException,IOException;
	
	// Insert the candidate list details into the database.
	public boolean insertIntoCandidateList(String author) throws SQLException,IOException;
	
	// Insert data into favorites list
	public boolean insertIntoFavList(String username, String conference, String author) throws IOException, SQLException;

}
