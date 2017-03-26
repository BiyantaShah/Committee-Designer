package com.team7;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class ImplementQueryBuilderTest extends TestCase {

  	@Test
	public void testValidAuthorName(){ 	
  		List<String> query1 = new ArrayList<String>();
  		query1.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.Name='Michael Ley' ");
  		query1.add(1,null);	
  		 
  		SearchParameter s= new SearchParameter("Author Name", "=" ,"Michael Ley", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query1, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithSpecialCharacters(){	
		
		List<String> result = new ArrayList<String>();
		result.add(0,null);
		result.add(1,null);	
  		
		SearchParameter s= new SearchParameter("Author Name", "=" ,"#Michael Ley", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(result, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithNumbers(){		
		List<String> result = new ArrayList<String>();
		result.add(0,null);
		result.add(1,null);	
  		
		SearchParameter s= new SearchParameter("Author Name", "=" ,"123 Michael Ley", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals( result, queryFormed);
	}

	@Test
	public void testValidYearsActive(){	
		
		List<String> query2 = new ArrayList<String>();
  		query2.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.Year>2 ");
  		query2.add(1,null);	

		SearchParameter s= new SearchParameter("Paper Published Year", ">" ,"2", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query2, queryFormed);
	}

	@Test
	public void testInvalidYearsActive(){	
		List<String> result = new ArrayList<String>();
		result.add(0,null);
		result.add(1,null);	

		SearchParameter s= new SearchParameter("Paper Published Year", ">" ,"-1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();	
	
  		searchCriteria.add(0,s);	
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(result, queryFormed);
	}

	@Test
	public void testValidKeyword(){	
		
		List<String> query3 = new ArrayList<String>();
  		query3.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%Chemistry%' ");
  		query3.add(1,null);	

		SearchParameter s= new SearchParameter("Keyword in Title", "LIKE" ,"Chemistry", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query3, queryFormed);
	}
	
	@Test
	public void testDiffFilter(){
		
		List<String> query4 = new ArrayList<String>();
  		query4.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.Name='ABC' AND p.title LIKE '%Biology%' ");
  		query4.add(1,null);	
  		
  		SearchParameter s1= new SearchParameter("Author Name", "=" ,"ABC","AND");
		SearchParameter s2= new SearchParameter("Keyword in Title", "LIKE" ,"Biology", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
		searchCriteria.add(0,s1);
		searchCriteria.add(1, s2);
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query4, queryFormed);
	}
	
	@Test
	public void testValidMultipleKeyword(){
		
		List<String> query5 = new ArrayList<String>();
  		query5.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%Chemistry%' OR p.title LIKE '%Biology%' ");
  		query5.add(1, null);	

		SearchParameter s1= new SearchParameter("Keyword in Title", "LIKE" ,"Chemistry","OR");
		SearchParameter s2= new SearchParameter("Keyword in Title", "LIKE" ,"Biology", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
		searchCriteria.add(0,s1);
		searchCriteria.add(1,s2);
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query5, queryFormed);
	}
	
	@Test
	public void testSendQuery() throws SQLException{	
		SearchParameter s1= new SearchParameter("Author Name", "=" ,"Petra Ludewig","OR");
		SearchParameter s2= new SearchParameter("Keyword in Title", "LIKE" ,"Extension", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
  		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);			
  		assertEquals(result.get(0),"Eric Van Wyk");
	}
	
	@Test
	public void testMultipleAuthorSearch() throws SQLException{
		SearchParameter s1= new SearchParameter("Author Name", "=" ,"Shahar Maoz","OR");
		SearchParameter s2= new SearchParameter("Author Name", "=" ,"Jan Oliver Ringert", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals(result.get(0),"Shahar Maoz");
	}
	
	@Test
	public void testMultipleAuthorInvalidSearch() throws SQLException{
		SearchParameter s1= new SearchParameter("Author Name", "=" ,"ABC","AND");
		SearchParameter s2= new SearchParameter("Author Name", "=" ,"Michael Ley", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals(result.size(),0);
	}
	
	@Test
	public void testFirstUseCase() throws SQLException{    //add another test fetching result from DB
		
		List<String> query6 = new ArrayList<String>();
  		query6.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.ConfName='OOPSLA' AND p.Year=2010 GROUP BY a.name HAVING COUNT(*) >1");
  		query6.add(1,null);	
  		

		SearchParameter s1= new SearchParameter("Conference Name", "=" ,"OOPSLA","AND");
		SearchParameter s2= new SearchParameter("Paper Published Year", "=" ,"2010", "AND");
		SearchParameter s3= new SearchParameter("Count Of Papers", ">" ,"1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		searchCriteria.add(2,s3);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query6, queryFormed);
	}
	
	@Test
	public void testSecondUseCase() throws SQLException{    //add another test fetching result from DB
		List<String> query7 = new ArrayList<String>();
  		query7.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%pointer%' OR p.title LIKE '%analysis%' GROUP BY a.name HAVING COUNT(*) >=1");
  		query7.add(1,null);	
  		

		SearchParameter s1= new SearchParameter("Keyword in Title", "LIKE" ,"pointer","OR");
		SearchParameter s2= new SearchParameter("Keyword in Title", "LIKE" ,"analysis", "AND");
		SearchParameter s3= new SearchParameter("Count Of Papers", ">=" ,"1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		searchCriteria.add(2,s3);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query7, queryFormed);
	}
	
	@Test
	public void testThirdUseCase() throws SQLException{    //add another test fetching result from DB
		SearchParameter s1= new SearchParameter("Keyword in Title", "LIKE" ,"pointer","AND");
		SearchParameter s2= new SearchParameter("Committee Year", "=" ,"2006", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
  		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals(result.get(0),"Amer Diwan");   // add a real author name here
	}
}