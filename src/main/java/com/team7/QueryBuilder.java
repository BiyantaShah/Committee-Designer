package com.team7;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryBuilder {

	/*
	 * createQuery method receives a key-value pair for search parameters
	 * Returns a string query
	 */
	public List<String> createQuery(List<SearchParameter> searchParam);
	
	/*
	 * validate the query and look for evil inputs for enhanced security
	 */	
	public boolean validateQuery(List<SearchParameter> searchParam);
	
	/*
	 * send the query to Database
	 */
	public ResultSet sendQuery(String searchQuery) throws SQLException;  //This object will be of type ResultSet
}
