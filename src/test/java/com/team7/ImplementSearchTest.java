package com.team7;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import junit.framework.TestCase;

public class ImplementSearchTest extends TestCase {
	
	
	@Test
	public void testSearchValid() throws SQLException {
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		List<SearchParameter> searchParameterList = new ArrayList<SearchParameter>();
		SearchParameter s = new SearchParameter("Name", "=","Michael Ley",null);
		searchParameterList.add(0,s);
		List<String> result = searchdisplay.search(searchParameterList); // result will have the answer of the search function		
		assertEquals("Michael Ley", result.get(0));

	}
	
	
	@Test
	public void testCandidateDetails() throws SQLException {   //add valid author names to this test
		Set<String> authorList = new HashSet<String>();
		authorList.add("Amer Diwan");
		authorList.add("Roger King");
		authorList.add("Petra Ludewig");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		ResultSet res = searchDisplay.candidateDetails(authorList);
		
		String author = null;
		while (res.next()) {
			author = res.getString("Author");
		}

		assertEquals("Amer Diwan", author);
	}

		
	@Test
	public void testSendEmailValid() throws SQLException {
		Set<String> authors = new HashSet<String>();
		authors.add("Roger King");
		authors.add("Petra Ludewig");
		
		String username="shahbiyanta@gmail.com";
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		String res = searchDisplay.sendEmail(authors, username);
				
		assertEquals("success", res);
	}
	
	@Test
	public void testSendEmailInValid() throws SQLException {
		Set<String> authors = new HashSet<String>();
		authors.add("Roger King");
		authors.add("Petra Ludewig");
		
		String username="abc@abc.com";
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		String res = searchDisplay.sendEmail(authors, username);
				
		assertEquals("failure", res);
	}

}
