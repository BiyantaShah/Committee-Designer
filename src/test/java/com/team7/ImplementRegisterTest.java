package com.team7;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class ImplementRegisterTest {

	 @Test
     //Test to verify a successful user registration - should return success
     public void testCreateUserSuccess() throws SQLException {
		
		RegisterUI user = new RegisterUI();
		boolean res = user.createUser("xyz@gmail.com","132","Associate Editor","OOPSLA");
		assertEquals(true,res);
		
	}
	 
     // CreateUserFailure Test is already included in testVerifyUserExistsFailure. Hence, there is no separate test case for the same 
	 
	 @Test
     //Test to verify a successful user registration - should return success
     public void testVerifyUserExistsSuccess() throws SQLException {
		
		RegisterUI user = new RegisterUI();
		boolean  res = user.veryifyIfUserExists("xyz@gmail.com");
		assertEquals(true,res);
		
	}
	 
	 @Test
     //Test to verify a successful user registration - should return success
     public void testVerifyUserExistsFailure() throws SQLException {
		
	    RegisterUI user = new RegisterUI();
	    boolean  res = user.veryifyIfUserExists("test@gmail.com");
		assertEquals(false,res);
		
	}

 
	 
	 
	 

}
