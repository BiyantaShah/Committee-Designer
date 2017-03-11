package com.team7;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class ImplementQueryBuilderTest extends TestCase {

	private static String query1 = "SELECT AuthorName, PapersPublished FROM TABLE_NAME WHERE AuthorName='ABC'";
	private static String query2 = "SELECT AuthorName, PapersPublished FROM TABLE_NAME WHERE YearsActive>2";
	private static String query3 = "SELECT AuthorName, PapersPublished FROM TABLE_NAME WHERE Region='Boston'";
	private static String query4 = "SELECT AuthorName, PapersPublished FROM TABLE_NAME WHERE Papers LIKE '%Chemistry%'";
	private static String query5 = "SELECT AuthorName, PapersPublished FROM TABLE_NAME WHERE Papers LIKE '%Chemistry%' OR Papers LIKE '%Biology%'";

	
	@Test
	public void testValidAuthorName(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("AuthorName", "=,ABC");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(query1, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithSpecialCharacters(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("AuthorName", "=,ABC#");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithNumbers(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("AuthorName", "=,ABC2");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testValidYearsActive(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("YearsActive", ">,2");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(query2, queryFormed);
	}

	@Test
	public void testInvalidYearsActive(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("YearsActive", ">,-1");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testValidRegion(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("Region", "=,Boston");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(query3, queryFormed);
	}

	@Test
	public void testInvalidRegionWithSpecialCharacter(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("Region", "=,Boston#");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testInvalidRegionWithNumbers(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("Region", "=,Boston123");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(null, queryFormed);
	}

	@Test
	public void testValidKeyword(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("Keyword", "LIKE,Chemistry");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(query4, queryFormed);
	}
	
	@Test
	public void testValidMultipleKeyword(){
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("Keyword", "LIKE,Chemistry,Biology");

		String queryFormed = new ImplementQueryBuilder().createQuery(searchParam);
		assertEquals(query5, queryFormed);
	}
}