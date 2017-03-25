package com.team7;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ImplementRegisterTest {

	 @Test
     //Test to verify a successful user registration - should return success
     public void testCreateUserSuccess() throws SQLException {
		
		RegisterUI user = new RegisterUI();
		// After inserting delete it, so that the next time you run, it does not fail
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from user where username='xyz@gmail.com'");
		
		boolean res = user.createUser("xyz@gmail.com","132","Associate Editor","OOPSLA");
			
		assertEquals(true,res);		
		
	}
	 
     // CreateUserFailure Test is already included in testVerifyUserExistsFailure. Hence, there is no separate test case for the same 
	 
	 @Test
     //Test to verify a successful user registration - should return success
     public void testVerifyUserExistsSuccess() throws SQLException {
		
		RegisterUI user = new RegisterUI();
		boolean  res = user.verifyIfUserExists("xyz@gmail.com");
		assertEquals(true,res);
		
	}
	 
	 @Test
     //Test to verify a successful user registration - should return success
     public void testVerifyUserExistsFailure() throws SQLException {
		
	    RegisterUI user = new RegisterUI();
	    boolean  res = user.verifyIfUserExists("test@gmail.com");
		assertEquals(false,res);
		
	}

 
	 
	 
	 

}
