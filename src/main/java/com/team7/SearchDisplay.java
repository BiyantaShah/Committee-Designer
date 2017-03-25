package com.team7;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface SearchDisplay {
	
	/*
	* updates the search parameter
	*/
	//public List<SearchParameter> updateFilterValues(int index, String filterCriteria, String filterComparator, String filterValue, String filterJoin);
	
	/*
	* deletes the search parameter
	*/
	//public List<SearchParameter> deleteFilterValues(int index);

	/*
	* adds the search parameters to a list to create the query together
	*/
	//public List<SearchParameter> saveFilterValue(String filterCriteria, String filterComparator, String filterValue, String filterJoin);

	/*
	* sends these parameters to the query builder
	*/
	public List<String> search(List<SearchParameter> searchFilter) throws SQLException;
	
	/*
	* Displays the results returned by the search function; in proper format
	*/
	//public String display(String result);

	/*
	* gets a list of email id's to send email to
	*/
	public void sendEmail(Set<String> authors, String userName) throws SQLException;

	/*
	* saves the author in the database for future use of the user
	*/
	public String saveAuthor(Set<String> saveAuthors);

	/*
	* details of the Author
	*/
	public List<String> candidateDetails(List<String> authors) throws SQLException;
}
