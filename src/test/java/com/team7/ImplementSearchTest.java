package com.team7;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	/*@Test
 	public void testSaveAuthorValid() {
		Author a = new Author("Dong-Soo Han", "conf/aina/KimJH08");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		String result = searchDisplay.saveAuthor(a);

		assertEquals("Author saved correctly", result);
	}*/
	
	
	@Test
	public void testCandidateDetails() throws SQLException {   //add valid author names to this test
		List<String> authorList = new ArrayList<String>();
		authorList.add("ABC");
		authorList.add("XYZ");
		authorList.add("PQR");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		List<String> result = searchDisplay.candidateDetails(authorList);

		//assertEquals();
	}

		
	/*@Test
	public void testSendEmail() {
		User u = new User("xyz@gmail.com", "pass", "Associate Editor", "OOPSLA");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		List<String> result  = searchDisplay.sendEmail(u);
		
		List<String> test = Arrays.asList("xyz@gmail.com");
		assertEquals(test, result);
	}*/

}
