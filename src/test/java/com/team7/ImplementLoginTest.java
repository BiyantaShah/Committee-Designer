package com.team7;

import junit.framework.TestCase;
import org.junit.Test;


public class ImplementLoginTest extends TestCase{

	@Test	
	//Test to check search page open's on login success
	public void testLoginSuccess() {
		
		ImplementLogin login = new ImplementLogin();
		String res = login.login("xyz@gmail.com","123");
		assertEquals("success",res);
		
	}
	
	@Test
	//Test to check Search page doesn't open on login failure
	public void testLoginFailure(){
		
		ImplementLogin login = new ImplementLogin();
		String res = login.login("xyz@gmail.com","123");
		assertEquals("failure",res);

	}
	
	 @Test
	 //Test to verify a valid registered user - should return true
     public void testValidateUserSuccess() {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.validateUser("xyz@gmail.com","123");
		Boolean val = true;
		assertEquals(val,res);
		
	}
    
	 
	 @Test
     //Test to verify a  unregistered user or if details are incorrect - should return false
     public void testValidateUserFailure() {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.validateUser("xyz@gmail.com","132");
		Boolean val = false;
		assertEquals(val,res);
		
	}
	 
	 @Test
     //Test to verify a successful user registration - should return success
     public void testCreateUserSuccess() {
		
		ImplementLogin login = new ImplementLogin();
		String res = login.createUser("xyz@gmail.com","132","Associate Editor","OOPSLA");
		assertEquals("success",res);
		
	}
	 
	 @Test
     //Test to verify a failure case of user registration - should return failure
     public void testCreateUserFailure() {
		
		ImplementLogin login = new ImplementLogin();
		String res = login.createUser("xyz@gmail.com","132","Associate Editor","OOPSLA");
		assertEquals("failure",res);
		
	}
	 
	 @Test
     //Test to verify successful logout - should return success
     public void testLogoutSuccess() {
		
		ImplementLogin login = new ImplementLogin();
		User user = new User("xyz@gmail.com","132","Associate Editor","OOPSLA");
		String res = login.logout(user);
		assertEquals("success",res);
		
	}
	 
	 @Test
     //Test to verify unsuccessful case of logout - should return failure
     public void testLogoutFailure() {
		
		ImplementLogin login = new ImplementLogin();
		User user = new User("xyz@gmail.com","132","Associate Editor","OOPSLA");
		String res = login.logout(user);
		assertEquals("failure",res);
		
	}

}
