package com.team7;

import junit.framework.TestCase;

import java.sql.SQLException;

import org.junit.Test;


public class ImplementLoginTest extends TestCase{

	
	 @Test
	 //Test to verify a valid registered user - should return true and enter the search page
     public void testLoginSuccess() throws SQLException {
		
		LoginUI login = new LoginUI();
		Boolean res = login.login("xyz@gmail.com","123");
		Boolean val = true;
		assertEquals(val,res);
		
	}
    
	 
	 @Test
     //Test to verify a  unregistered user or if details are incorrect - should return false
     public void testLoginFailure() throws SQLException {
		
		 LoginUI login = new LoginUI();
		Boolean res = login.login("xyz@gmail.com","132");
		Boolean val = false;
		assertEquals(val,res);
		
	}
	 
	 @Test
     //Test to verify successful logout - should return success
     public void testLogoutSuccess() {
		
		 LoginUI login = new LoginUI();
		User user = new User("xyz@gmail.com","132","Associate Editor","OOPSLA");
		String res = login.logout(user);
		assertEquals("success",res);
		
	}
	 
	 @Test
     //Test to verify unsuccessful case of logout - should return failure
     public void testLogoutFailure() {
		
		 LoginUI login = new LoginUI();
		User user = new User("xyz@gmail.com","132","Associate Editor","OOPSLA");
		String res = login.logout(user);
		assertEquals("failure",res);
		
	}

}
