package com.team7;

import java.util.List;
import java.util.Map;

public interface Search {
	
	/*
	* updates the search parameter
	*/
	public Map<String, String> updateFilterValues(String filterCriteria, String filterValue);
	
	/*
	* deletes the search parameter
	*/
	public Map<String, String> deleteFilterValues(String filterCriteria, String filterValue);

	/*
	* adds the search parameters to a list to create the query together
	*/
	public Map<String, String> saveFilterValue(String filterCriteria, String filterValue);

	/*
	* sends these parameters to the query builder
	*/
	public List<String> search(Map<String, String> searchParam);
	
	/*
	* Displays the results returned by the search function; in proper format
	*/
	public String display(String result);

	/*
	* gets a list of email id's to send email to
	*/
	public List<String> sendEmail(User userObject);

	/*
	* saves the author in the database for future use of the user
	*/
	public String saveAuthor(Author authorObject);

	/*
	* details of the Author
	*/
	public String candidateDetails(Author authorObject);


}
