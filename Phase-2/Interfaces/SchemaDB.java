package team7;

import java.util.List;

public interface SchemaDB {

	/*
	 * Creates objects based on the incoming data and returns them
	 * Objects will be of type publications, journal, author, article 
	 */
	public <T> List<T> objectCreation(String className, String attributeName);
	
	/*
	 * Create connection to RDBMS 
	 */
	public Connection getConnection();

	/*
	 * Insert the objects created to Database
	 */
	public <T> void insertData(List<T> object_name);
	
}
