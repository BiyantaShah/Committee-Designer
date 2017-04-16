package com.team7.interfaces;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.team7.queryEngine.SearchParameter;

public interface SearchDisplay {
	
	// sends these parameters to the query builder
	public List<String> search(List<SearchParameter> searchFilter) throws SQLException,IOException;
	
	// gets a list of authors and sends email ID's to all users , 
	// with the list of the final committee
	public String sendEmail(Set<String> authors, String userName) throws SQLException,IOException;

	// details of the Author
	public ResultSet candidateDetails(Set<String> authors) throws SQLException,IOException;
	
	// similar Authors
	public Set<String> similarAuthor(String author) throws SQLException, IOException;
	
}
