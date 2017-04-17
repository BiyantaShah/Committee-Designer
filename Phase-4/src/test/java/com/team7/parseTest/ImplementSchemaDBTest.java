package com.team7.parseTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcabi.jdbc.JdbcSession;
import com.team7.parsing.Author;
import com.team7.parsing.ImplementSchemaDB;
import com.team7.parsing.User;
import com.team7.ui.UIConstants;

import junit.framework.TestCase;
import org.junit.Test;

public class ImplementSchemaDBTest extends TestCase {

	UIConstants u  = new UIConstants("shah.bi@shah.bi", "Program Chair", "ECOOP");
	//Test Case to test the DB Connection
	//Test will fail if connection object is null
	@Test
	public void testAGetConnection() throws IOException {
		Connection conn = null;
		conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
	}

	//Test Case to test Create Table
	// Test will fail if connection object is null Or Creation of Table Fails
	@Test
	public void testBSetUp() throws ClassNotFoundException, SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		db.dbSetUp();
		Connection conn = new ImplementSchemaDB().getConnection();
		PreparedStatement  test = conn.prepareStatement("show tables");
     	test.executeQuery();
     	
     	boolean res = true;
     	if (conn.equals(null)) {
     		res = false;
     	}
     	
     	assertTrue(res);
	}

	//Test Case to test Insert Data
	//Test will fail if connection object is null Or Insertion of Data Fails
	@Test
	public void testInsertData() throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = new ImplementSchemaDB().getConnection();
		Statement stmt = conn.createStatement();
		// delete the user before inserting it every time
		stmt.executeUpdate("delete from User where username='test@test.com'");		
		User test = new User("test@test.com","test","editor","OOPSLA");	
		boolean res = db.insertData(test);

		assertTrue(res);
		
	}
	
	//Test Case to check to verify object other than user during insertion
	@Test
	public void testInsertData2() throws SQLException, IOException {
	    Author auth = new Author("Test", "testKey");
		ImplementSchemaDB db = new ImplementSchemaDB();
		boolean res = db.insertData(auth);
		assertEquals(false, res);
	}
	
	//Test Case to verify exception if data is not valid
	@Test(expected = SQLException.class)
	public void testExpectedException() throws SQLException, IOException {
			ImplementSchemaDB db = new ImplementSchemaDB();
			User test = new User();	
			db.insertData(test);	  
	} 
	
	//Test Case to test Insert Data into candidate list
	//Test will fail if connection object is null Or Insertion of Data Fails
	@Test
	public void testInsertCandidateListData() throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();

		boolean res = db.insertIntoCandidateList("Biyanta Shah");
		
		assertTrue(res);
		
		// delete the author
		res = db.updateCandidateList("Biyanta Shah", UIConstants.currentUserConf);
		
		assertTrue(res);
	} 
	
	//Test Case to test Insert Data into favorites list
	//Test will fail if connection object is null Or Insertion of Data Fails
	@Test
	public void testInsertUpdateFavListData() throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();

		// insert into fav list
		boolean res = db.insertIntoFavList(UIConstants.currentUser, UIConstants.currentUserConf, "Biyanta Shah");

		assertTrue(res);
		
		// delete the author 
		res = db.updateFavList(UIConstants.currentUser, "Biyanta Shah");

		assertTrue(res);
	}
	
	
}
