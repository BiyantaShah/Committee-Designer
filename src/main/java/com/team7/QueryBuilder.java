package com.team7;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
	public List<String> sendQuery(List<String> searchQuery) throws SQLException;  //This object will be of type ResultSet
}
