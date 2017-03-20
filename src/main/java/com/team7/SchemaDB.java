package com.team7;

import java.sql.Connection;
import java.sql.SQLException;

public interface SchemaDB {

	
	/* 
	 * Creates database and tables
	 */
	public void dbSetUp() throws ClassNotFoundException, SQLException;
	
	/*
	 * Create connection to RDBMS 
	 */
	public Connection getConnection();

	/*
	 * Insert the objects created to Database
	 *
	 */
	public <T> void insertData(Object object_name) throws SQLException;
	
}
