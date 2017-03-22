package com.team7;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class ImplementQueryBuilderTest extends TestCase {

	private static String query1 = "SELECT a.name AS Author, p.title AS Title FROM Author a INNER JOIN Paper p ON a.name = p.author INNER JOIN Conference c ON p.confName = c.name WHERE a.name='Michael Ley' ";
	private static String query2 = "SELECT a.name AS Author, p.title AS Title FROM Author a INNER JOIN Paper p ON a.name = p.author INNER JOIN Conference c ON p.confName = c.name WHERE p.year>2 ";
	private static String query4 = "SELECT a.name AS Author, p.title AS Title FROM Author a INNER JOIN Paper p ON a.name = p.author INNER JOIN Conference c ON p.confName = c.name WHERE p.title LIKE '%Chemistry%' ";
	private static String query5 = "SELECT a.name AS Author, p.title AS Title FROM Author a INNER JOIN Paper p ON a.name = p.author INNER JOIN Conference c ON p.confName = c.name WHERE p.title LIKE '%Chemistry%'  OR p.title LIKE '%Biology%' ";
	private static String query6 = "SELECT a.name AS Author, p.title AS Title FROM Author a INNER JOIN Paper p ON a.name = p.author INNER JOIN Conference c ON p.confName = c.name WHERE a.name='ABC' AND p.title LIKE '%Biology%' ";;
	
  	@Test
	public void testValidAuthorName(){ 		
  		SearchParameter s= new SearchParameter("name", "=" ,"Michael Ley");
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query1, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithSpecialCharacters(){
		
		SearchParameter s= new SearchParameter("name", "=" ,"#Michael Ley");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithNumbers(){
		
		SearchParameter s= new SearchParameter("name", "=" ,"123 Michael Ley");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testValidYearsActive(){
		
		SearchParameter s= new SearchParameter("year", ">" ,"2");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);
		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query2, queryFormed);
	}

	@Test
	public void testInvalidYearsActive(){
		
		SearchParameter s= new SearchParameter("year", ">" ,"-1");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testValidKeyword(){
		
		SearchParameter s= new SearchParameter("keyword", "LIKE" ,"Chemistry");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);
		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query4, queryFormed);
	}
	
	@Test
	public void testDiffFilter(){
		
		SearchParameter s1= new SearchParameter("name", "=" ,"ABC");
		SearchParameter s2= new SearchParameter("keyword", "LIKE" ,"Biology");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
		searchCriteria.add(0,s1);
		searchCriteria.add(1, s2);

		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query6, queryFormed);
	}
	
	@Test
	public void testValidMultipleKeyword(){
		SearchParameter s1= new SearchParameter("keyword", "LIKE" ,"Chemistry");
		SearchParameter s2= new SearchParameter("keyword", "LIKE" ,"Biology");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
		searchCriteria.add(0,s1);
		searchCriteria.add(1, s2);

		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query5, queryFormed);
	}
	
	@Test
	public void testSendQuery() throws SQLException{
		
		SearchParameter s1= new SearchParameter("name", "=" ,"ABC");
		SearchParameter s2= new SearchParameter("keyword", "LIKE" ,"Biology");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		List<String> result = new ImplementQueryBuilder().sendQuery(queryFormed);	
		
		assertEquals(result.get(0),"ABC");
	}
	
	@Test
	public void testMultipleAuthorSearch() throws SQLException{
	
		SearchParameter s1= new SearchParameter("name", "=" ,"ABC");
		SearchParameter s2= new SearchParameter("name", "=" ,"Michael Ley");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
		String queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		List<String> result = new ImplementQueryBuilder().sendQuery(queryFormed);	
		
		assertEquals(result.get(0),"Michael Ley");
	}
}