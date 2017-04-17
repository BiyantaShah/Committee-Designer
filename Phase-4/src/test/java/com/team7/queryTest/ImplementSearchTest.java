package com.team7.queryTest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.team7.queryEngine.ImplementSearchDisplay;
import com.team7.queryEngine.SearchParameter;

import junit.framework.TestCase;

public class ImplementSearchTest extends TestCase {
	
	
	// Testing the final query result
	@Test
	public void testSearchValid() throws SQLException, IOException {
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		List<SearchParameter> searchParameterList = new ArrayList<SearchParameter>();
		SearchParameter s = new SearchParameter("Name", "=","Shahar Maoz",null);
		searchParameterList.add(0,s);
		List<String> result = searchdisplay.search(searchParameterList); 
		assertEquals("Shahar Maoz", result.get(0));

	} 
		
	// Valid candidate details extracted
	@Test
	public void testCandidateDetails() throws SQLException, IOException {   
		Set<String> authorList = new HashSet<String>();
		authorList.add("Amer Diwan");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		ResultSet res = searchDisplay.candidateDetails(authorList);
		
		String author = null;
		while (res.next()) {
			author = res.getString("Author");
		}

		assertEquals("Amer Diwan", author);
	} 
	
	
	@Test
	// Testing the similar authors function
	public void testSimAuth() throws SQLException, IOException {
		Set<String> simAuth = new ImplementSearchDisplay().similarAuthor("Aniruddha S. Gokhale");
		assertEquals(7, simAuth.size());
		
	}

	
	// Email sent successfully
	@Test
	public void testSendEmailValid() throws SQLException, IOException {
		Set<String> authors = new HashSet<String>();
		authors.add("Roger King");
		authors.add("Petra Ludewig");
		
		String username="shahbiyanta@gmail.com";
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		String res = searchDisplay.sendEmail(authors, username);
				
		assertEquals("success", res);
	}
	

}