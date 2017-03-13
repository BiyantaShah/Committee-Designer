package com.team7;
import org.junit.Test;

import junit.framework.TestCase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchJunitTest extends TestCase {

	@Test
	public void testUpdateFilters() {
		Map <String, String> update = new HashMap<String, String>();
		update.put("Keyword", "=,pointer");
		
		SearchDisplay searchdisplay = new SearchDisplay();
		Map <String, String> result = searchdisplay.updateFilterValues("Keyword", "=,analysis");
		// updating the search criteria 'keyword' from pointer to analysis
		assertEquals("=,analysis", result.get("Keyword"));
	}
	
	@Test
	public void testDeleteFilters() {
		Map <String, String> delete = new HashMap<String, String>();
		delete.put("Year Published", "2010");
		delete.put("Keyword", "pointer");
		
		SearchDisplay searchdisplay = new SearchDisplay();
		Map <String, String> result = searchdisplay.deleteFilterValues("Keyword", "pointer");
		
		// deleting the search criteria 'keyword' from the map
		assertEquals(false, result.containsKey("Keyword"));
	}
	
	@Test
	public void testsaveFilters() {
		Map <String, String> save = new HashMap<String, String>();
		save.put("Year Published", "2010");
		
		SearchDisplay searchdisplay = new SearchDisplay();
		Map <String, String> result = searchdisplay.saveFilterValue("Year Published", "2010");
		
		// checking if year = 2010 in result map
		assertEquals("2010", result.get("Year Published"));
	}
	
	@Test
	public void testSearchValid() {
		Map <String, String> search = new HashMap<String, String>();
		search.put("title", "pointer");
		
		SearchDisplay searchdisplay = new SearchDisplay();
		List<String> result = searchdisplay.search(search); // result will have the answer of the search function
		
		// first author in the list should be Aki Matsumoto
		assertEquals("Aki Matsumoto", result.get(0));
	}
	
	@Test
	public void testSearchInvalid() {
		Map <String, String> search = new HashMap<String, String>();
		search.put("title", "biyanta"); // search for title with Biyanta and return an empty String
		
		SearchDisplay searchdisplay = new SearchDisplay();
		List<String> result = searchdisplay.search(search); 
		
		// will return an empty list
		assertEquals(new ArrayList<String>(), result);
	}
	
	
	@Test
	public void testDisplay() {
		// result will have the answer of the display function
		SearchDisplay searchdisplay = new SearchDisplay();
		String result = searchdisplay.display("Aki Matsumoto , Dong-Soo Han, Takao Tsuda");
		
		// your search is such that these 3 authors should be returned		
		assertEquals("Aki Matsumoto , Dong-Soo Han, Takao Tsuda", result);
		
	}
	
	@Test
	public void testSaveAuthorValid() {
		Author a = new Author("Aki Matsumoto", new Journal(), new Article());
		SearchDisplay searchdisplay = new SearchDisplay();
		String result = searchdisplay.saveAuthor(a);
		
		assertEquals("Author saved correctly", result);
	}
	
	@Test
	public void testSaveAuthorInvalid() {
		Author a = new Author("Aki Matsumoto", new Article(), new Journal());
		SearchDisplay searchDisplay = new SearchDisplay();
		String result = searchDisplay.saveAuthor(a);
		
		// author should be saved in a particular format, 
		// here instead of article we have journal, which is an incorrect format
		assertNotEquals("Author saved correctly", result);
	}
	
	@Test
	public void testCandidateDetails() {
		Author a = new Author("Roberto Bruni", new Article("Revisiting causality, coalgebraically"), new Journal("Acta Inf."));
		SearchDisplay searchDisplay = new SearchDisplay();
		String result = searchDisplay.candidateDetails(a);
		
		assertEquals("Roberto Bruni Revisiting causality, coalgebraically Acta Inf.", result);
	}
	
	@Test
	public void testSendEmail() {
		User u = new User("Biyanta", "pass", "Associate Editor", "OOPSLA", "xyz@gmail.com");
		SearchDisplay searchDisplay = new SearchDisplay();
		List<String> result  = searchDisplay.sendEmail(u);
		
		List<String> test = Arrays.asList("xyz@gmail.com");
		assertEquals(test, result);
	}
	
}
