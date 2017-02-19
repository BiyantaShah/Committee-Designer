package team7;

import java.util.Map;

public interface QueryBuilder {

	/*
	 * createQuery method receives a key-value pair for search parameters
	 * Returns a string query
	 */
	public String createQuery(Map<String, String> searchParam);
	
	/*
	 * validate the query and look for evil inputs for enhanced security
	 */
	
	public boolean validateQuery(String searchQuery);
	
	/*
	 * send the query to Database
	 */
	public void sendQuery(String searchQuery);
}
