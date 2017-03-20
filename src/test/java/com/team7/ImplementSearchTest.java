package com.team7;
import org.junit.Test;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ImplementSearchTest extends TestCase {

	@Test
	public void testUpdateFilters() {
		Map <String, String> update = new HashMap<String, String>();
		update.put("Keyword", "=,pointer");
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		Map <String, String> result = searchdisplay.updateFilterValues("Keyword", "=,analysis");
		// updating the search criteria 'keyword' from pointer to analysis
		assertEquals("=,analysis", result.get("Keyword"));
	}
	
	@Test
	public void testDeleteFilters() {
		Map <String, String> delete = new HashMap<String, String>();
		delete.put("Year Published", "2010");
		delete.put("Keyword", "pointer");
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		Map <String, String> result = searchdisplay.deleteFilterValues("Keyword", "pointer");
		
		// deleting the search criteria 'keyword' from the map
		assertEquals(false, result.containsKey("Keyword"));
	}
	
	@Test
	public void testsaveFilters() {
		Map <String, String> save = new HashMap<String, String>();
		save.put("Year Published", "2010");
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		Map <String, String> result = searchdisplay.saveFilterValue("Year Published", "2010");
		
		// checking if year = 2010 in result map
		assertEquals("2010", result.get("Year Published"));
	}
	
	@Test
	public void testSearchValid() {
		Map <String, String> search = new HashMap<String, String>();
		search.put("title", "pointer");
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		List<String> result = searchdisplay.search(search); // result will have the answer of the search function
		
		// first author in the list should be Aki Matsumoto
		assertEquals("Aki Matsumoto", result.get(0));
	}
	
	@Test
	public void testSearchInvalid() {
		Map <String, String> search = new HashMap<String, String>();
		search.put("title", "biyanta"); // search for title with Biyanta and return an empty String
		
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		List<String> result = searchdisplay.search(search); 
		
		// will return an empty list
		assertEquals(new ArrayList<String>(), result);
	}
	
	
	@Test
	public void testDisplay() {
		// result will have the answer of the display function
		ImplementSearchDisplay searchdisplay = new ImplementSearchDisplay();
		String result = searchdisplay.display("Aki Matsumoto , Dong-Soo Han, Takao Tsuda");
		
		// your search is such that these 3 authors should be returned		
		assertEquals("Aki Matsumoto , Dong-Soo Han, Takao Tsuda", result);
		
	}
	
	
	@Test
	public void testSaveAuthorValid() {
		Author a = new Author("Dong-Soo Han", "conf/aina/KimJH08");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		String result = searchDisplay.saveAuthor(a);

		assertEquals("Author saved correctly", result);
	}
	
	
	@Test
	public void testCandidateDetails() {
		Author a = new Author("Dong-Soo Han", "conf/aina/KimJH08");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		String result = searchDisplay.candidateDetails(a);

		assertEquals("Dong-Soo Han,  conf/aina/KimJH08", result);
	}

		
	@Test
	public void testSendEmail() {
		User u = new User("xyz@gmail.com", "pass", "Associate Editor", "OOPSLA");
		ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
		List<String> result  = searchDisplay.sendEmail(u);
		
		List<String> test = Arrays.asList("xyz@gmail.com");
		assertEquals(test, result);
	}
	
}
