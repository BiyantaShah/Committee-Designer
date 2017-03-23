package com.team7;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface QueryBuilder {

	/*
	 * createQuery method receives a key-value pair for search parameters
	 * Returns a string query
	 */
	//public String createQuery(Map<String, String> searchParam);
	
	/*
	 * validate the query and look for evil inputs for enhanced security
	 */
	//public boolean validateQuery(Map<String, String>  searchParam);
	
	/*
	 * send the query to Database
	 */
	//public List<String> sendQuery(String searchQuery) throws SQLException;  //This object will be of type ResultSet
	
	
     public String createQuery(List<SearchParameter> searchParam);
	
	/*
	 * validate the query and look for evil inputs for enhanced security
	 */
	public boolean validateQuery(List<SearchParameter> searchParam);
	
	/*
	 * send the query to Database
	 */
	public List<String> sendQuery(String searchQuery) throws SQLException;  //This object will be of type ResultSet
      
           
}
