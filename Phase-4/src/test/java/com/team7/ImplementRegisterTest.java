package com.team7;
 
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ImplementRegisterTest { 

	@Test
	//Test to verify a successful user registration - should return true
	public void testCreateUserSuccess() throws Exception {

		ImplementRegister user = new ImplementRegister();
		// After inserting delete it, so that the next time you run, it does not fail
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from User where username='sravbiya@gmail.com'");

		String res = user.createUser("sravbiya@gmail.com","132","Associate Editor","OOPSLA");

		assertEquals("true",res);		

	}  
	
	@Test
	//Test to verify a successful user registration - should return exists
	public void testUserExistsSuccess() throws Exception {

		ImplementRegister user = new ImplementRegister();
		String  res = user.createUser("sravbiya@gmail.com","132","Associate Editor","OOPSLA");
		assertEquals("exists",res);

	}
	
	@Test
	//Test to successful encryption of password
	public void testPwdEncryptionSuccess() throws SQLException {

		ImplementRegister user = new ImplementRegister();
		String res;
			res = user.encryptPassword("123","SECRETKEY");
			int len = res.length();
		    assertEquals(len,13);
	}
	
	
	@Test
	public void testPwdEncryptionFailure() throws Exception {
	    
		ImplementRegister user = new ImplementRegister();		
		assertEquals("failure",user.encryptPassword("123",""));	
		
	}  
	  
	
	@Test
	//Test to verify a unsuccessful user registration - should return invalid email
	// When email ID does not contain "@"
	public void testCreateUserFailure() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("sravbiya.com","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}

	@Test
	//Test to verify a unsuccessful user registration - should return invalid email
	// When email ID does not contain .com or .edu as the domain
	public void testCreateUserFailure1() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("sravbiya@gmail@.gcom","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}

	@Test
	//Test to verify a unsuccessful user registration - should return invalid email
	// When email ID contain 2 "@"
	public void testCreateUserFailure2() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("sravbiya@gmail@com","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}

	@Test
	//Test to verify a unsuccessful user registration - should return invalid email
	// When email ID does contain domain name
	public void testCreateUserFailure3() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("mno@.com","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}
	
	@Test
	//Test to verify a unsuccessful user registration - should return invalid email
	// When contains multiple userNames
	public void testCreateUserFailure4() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("bsha@gmail.com,bs1@gmail.edu","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}


	@Test
	//Test to verify a successful user registration - should return success
	public void testVerifyUserExistsFailure() throws SQLException, IOException {

		ImplementRegister user = new ImplementRegister();
		boolean  res = user.verifyIfUserExists("bs@gmail.com");
		assertEquals(false,res);

	}
}
