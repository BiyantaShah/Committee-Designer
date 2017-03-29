package com.team7;

import junit.framework.TestCase;

import java.sql.SQLException;

import org.junit.Test;


public class ImplementLoginTest extends TestCase{

	
	 @Test
	 //Test to verify a valid registered user - should return true and enter the search page
     public void testLoginSuccess() throws SQLException {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.login("xyz@gmail.com","132");
		Boolean val = true;
		assertEquals(val,res);
		
	}
    
	 
	 @Test
     //Test to verify a  unregistered user or if details are incorrect - should return false
     public void testLoginFailure() throws SQLException {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.login("xyz@gmail.com","123");
		Boolean val = false;
		assertEquals(val,res);
		
	}
	 
	 @Test
     //Test to verify successful logout - should return success
     public void testLogoutSuccess() {
		
		ImplementLogin login = new ImplementLogin();
		String res = null;
		login.logout();
		res = (LoginUI.currentUser == null) ? "success" : "failure";
		assertEquals("success",res);
		
	}
	 
	 @Test
     //Test to verify successful logout - should return success
     public void testLogoutFailure() throws SQLException {
		
		ImplementLogin login = new ImplementLogin();
		String res = null;
		login.login("xyz@gmail.com","132");
		res = (LoginUI.currentUser == null) ? "success" : "failure";
		assertEquals("failure",res);
		
	}
	 

}
