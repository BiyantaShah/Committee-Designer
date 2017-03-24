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
	public void testsaveFilters() {
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		searchdisplay.clearFilterMap();
		List<SearchParameter> result = searchdisplay.saveFilterValue("Name", "=", "Michael Ley", null);			
		// checking if year = 2010 in result map
		assertEquals("Michael Ley", result.get(0).getSearchValue());
	}
	
	@Test
	public void testUpdateFilters() {
		
		SearchParameter newCriteria = new SearchParameter("Keyword", "LIKE","Biology","AND");
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		searchdisplay.clearFilterMap();
		searchdisplay.saveFilterValue("Keyword", "LIKE","Chemistry",null);	
		List<SearchParameter> result = searchdisplay.updateFilterValues(0, newCriteria);
		assertEquals("Biology", result.get(0).getSearchValue());		
	}
	
	@Test
	public void testDeleteFilters() {
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		searchdisplay.clearFilterMap();
		searchdisplay.saveFilterValue("Name", "=","Michael Ley","AND");	
		searchdisplay.saveFilterValue("Keyword", "LIKE","Chemistry",null);	
		List<SearchParameter> result = searchdisplay.deleteFilterValues(1);
		// deleting the search criteria 'keyword' from the map
		assertEquals(false, result.contains(new SearchParameter("keyword", "LIKE","Chemistry",null)));
	}
	
	// The following test cases need  to added
	
	/*@Test
	public void testSearchValid() throws SQLException {
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		searchdisplay.clearFilterMap();
		List<SearchParameter> search = searchdisplay.saveFilterValue("Name", "=","Michael Ley",null);

		List<String> result = searchdisplay.search(search); // result will have the answer of the search function
		
		assertEquals("Michael Ley", result.get(0));
		//searchdisplay.clearFilterMap();
	}*/
	
	/*@Test
	public void testDisplay() {
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		searchdisplay.clearFilterMap();
		List<SearchParameter> filter = searchdisplay.saveFilterValue("name", "=", "Michael Ley",null);	
		
		String result = searchdisplay.display("Aki Matsumoto , Dong-Soo Han, Takao Tsuda");
		
		// your search is such that these 3 authors should be returned		
		assertEquals("Aki Matsumoto , Dong-Soo Han, Takao Tsuda", result);
	}*/
	
	/*@Test
 	public void testSaveAuthorValid() {
		Author a = new Author("Dong-Soo Han", "conf/aina/KimJH08");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		String result = searchDisplay.saveAuthor(a);

		assertEquals("Author saved correctly", result);
	}*/
	
	
/*	@Test
	public void testCandidateDetails() {
		Author a = new Author("Dong-Soo Han", "conf/aina/KimJH08");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		String result = searchDisplay.candidateDetails(a);

		assertEquals("Dong-Soo Han,  conf/aina/KimJH08", result);
	}*/

		
	/*@Test
	public void testSendEmail() {
		User u = new User("xyz@gmail.com", "pass", "Associate Editor", "OOPSLA");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		List<String> result  = searchDisplay.sendEmail(u);
		
		List<String> test = Arrays.asList("xyz@gmail.com");
		assertEquals(test, result);
	}*/

}
