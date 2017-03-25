package com.team7;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface SearchDisplay {
	
	/*
	* sends these parameters to the query builder
	*/
	public List<String> search(List<SearchParameter> searchFilter) throws SQLException;
	
	/*
	* gets a list of email id's to send email to
	*/
	public String sendEmail(Set<String> authors, String userName) throws SQLException;

	/*
	* details of the Author
	*/
	public ResultSet candidateDetails(Set<String> authors) throws SQLException;
}
