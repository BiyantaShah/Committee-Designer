package team7;

import java.util.Map;

public interface Search {
	
	/*
	* saves the search parameter
	*/
	public void saveFilterValues (String filterCriteria);

	/*
	* adds the search parameters to a list to create the query together
	*/
	public Map<String, String> addFilterValue (String filterCriteria, String filterValue);

	/*
	* sends these parameters to the query builder
	*/
	public void search(Map<String, String> searchParam);
	
	/*
	* Displays the results returned by the search function; in proper format
	*/
	public void display(String result);

	/*
	* sends email to the author selected
	*/
	public void sendEmail(Object authorObject); //This will be an object of author class

	/*
	* saves the author in the database for future use of the user
	*/
	public void saveAuthor (Object authorObject); //This will be an object of author class

	/*
	* details of the Author
	*/
	public void candidateDetails(Object authorObject); //This will be an object of author class


}
