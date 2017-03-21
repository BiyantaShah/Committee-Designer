package com.team7;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class ImplementQueryBuilderTest extends TestCase {

	private static String query1 = "SELECT a.name, p.title FROM Author a INNER JOIN Paper p ON a.author = p.author INNER JOIN Conference c INNER JOIN ON p.confName = c.name WHERE a.name='ABC'";
	private static String query2 = "SELECT a.name, p.title FROM Author a INNER JOIN Paper p ON a.author = p.author INNER JOIN Conference c INNER JOIN ON p.confName = c.name WHERE p.year>2";
	private static String query4 = "SELECT a.name, p.title FROM Author a INNER JOIN Paper p ON a.author = p.author INNER JOIN Conference c INNER JOIN ON p.confName = c.name WHERE p.title LIKE '%Chemistry%'";
	private static String query5 = "SELECT a.name, p.title FROM Author a INNER JOIN Paper p ON a.author = p.author INNER JOIN Conference c INNER JOIN ON p.confName = c.name WHERE p.title LIKE '%Chemistry%' OR p.title LIKE '%Biology%'";
 
	@Test
	public void testValidAuthorName(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("name", "=,ABC");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		System.out.println("***" + queryFormed );
		assertEquals(query1, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithSpecialCharacters(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("name", "=,ABC#");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithNumbers(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("name", "=,ABC2");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testValidYearsActive(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("year", ">,2");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(query2, queryFormed);
	}

	@Test
	public void testInvalidYearsActive(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("year", ">,-1");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testValidKeyword(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("keyword", "LIKE,Chemistry");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(query4, queryFormed);
	}
	
	@Test
	public void testValidMultipleKeyword(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("keyword", "LIKE,Chemistry,Biology");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(query5, queryFormed);
	}
}