package com.team7.interfaces;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.team7.queryEngine.SearchParameter;

/**
 * The Interface SearchDisplay.
 */
public interface SearchDisplay {
	
	/**
	 * Search.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// sends these parameters to the query builder
	public List<String> search(List<SearchParameter> searchFilter) throws SQLException,IOException;
	
	// gets a list of authors and sends email ID's to all users , 
	/**
	 * Send email.
	 *
	 * @param authors the authors
	 * @param userName the user name
	 * @return the string
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// with the list of the final committee
	public String sendEmail(Set<String> authors, String userName) throws SQLException,IOException;

	/**
	 * Candidate details.
	 *
	 * @param authors the authors
	 * @return the result set
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// details of the Author
	public ResultSet candidateDetails(Set<String> authors) throws SQLException,IOException;
	
	/**
	 * Similar author.
	 *
	 * @param author the author
	 * @return the sets the
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// similar Authors
	public Set<String> similarAuthor(String author) throws SQLException, IOException;
	
}
