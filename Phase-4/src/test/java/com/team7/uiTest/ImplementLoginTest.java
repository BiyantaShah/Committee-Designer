package com.team7.uiTest;

import junit.framework.TestCase;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.team7.ui.ImplementLogin;


/**
 * The Class ImplementLoginTest.
 */
public class ImplementLoginTest extends TestCase{

	
	 /**
 	 * Test login success.
 	 *
 	 * @throws SQLException the SQL exception
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
 	@Test
	 //Test to verify a valid registered user - should return true and enter the search page
     public void testLoginSuccess() throws SQLException, IOException {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.login("sravbiya@gmail.com","132");
		Boolean val = true;
		assertEquals(val,res);  
		
	}  
	 
	 	  
	 /**
 	 * Test wrong password.
 	 *
 	 * @throws SQLException the SQL exception
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
 	@Test
     //Test to verify a if details are incorrect - should return false
     public void testWrongPassword() throws SQLException, IOException {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.login("sravbiya@gmail.com","123");
		Boolean val = false;
		assertEquals(val,res);
		
	}
	 
	 /**
 	 * Test unregistered user.
 	 *
 	 * @throws SQLException the SQL exception
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
 	@Test
     //Test to verify a  unregistered user - should return false
     public void testUnregisteredUser() throws SQLException, IOException {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.login("xyz@hello.com","123");
		Boolean val = false;
		assertEquals(val,res);
		
	}

	 
	 
	 /**
 	 * Test pwd decryption success.
 	 *
 	 * @throws SQLException the SQL exception
 	 */
 	@Test
	 //Test to verify a valid registered user - should return true and enter the search page
     public void testPwdDecryptionSuccess() throws SQLException {
		
		ImplementLogin login = new ImplementLogin();
		String res = login.decryptPassword("oU4jbVTElTw","SECRETKEY");
		assertEquals(res,"123");
		
	} 
	 
	 /**
 	 * Test pwd decryption failure.
 	 *
 	 * @throws SQLException the SQL exception
 	 */
 	@Test
	 //Test to verify a valid registered user - should return true and enter the search page
     public void testPwdDecryptionFailure() throws SQLException {
		
		ImplementLogin login = new ImplementLogin();
		String res = login.decryptPassword("oU4jbVTElTw","");
		assertEquals(res,"failure");
		 
	} 
 	  
	 /**
 	 * Test logout success.
 	 */
 	@Test
     //Test to verify successful logout - should return success
     public void testLogoutSuccess() {
		
		ImplementLogin login = new ImplementLogin();
		String res = null;
		res = login.logout(); 
		assertEquals("success",res);
		
	} 
}
