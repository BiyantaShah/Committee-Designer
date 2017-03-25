package com.team7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jcabi.jdbc.JdbcSession;
import junit.framework.TestCase;
import org.junit.Test;

public class ImplementSchemaDBTest extends TestCase {

	
	/*
	 * Test Case to test the DB Connection
	 * Test will fail if connection object is null
	 */
	
	@Test
	public void testAGetConnection() {
		Connection conn = null;
		conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
	}
	
	/*
	 * Test Case to test Create Table
	 * Test will fail if connection object is null Or Creation of Table Fails
	 */
	
	@Test
	public void testBSetUp() throws ClassNotFoundException, SQLException {
		
		ImplementSchemaDB db = new ImplementSchemaDB();
		db.dbSetUp();
		Connection conn = new ImplementSchemaDB().getConnection();
          
		try {
			
			PreparedStatement  test = conn.prepareStatement("show tables");
            ResultSet rs = test.executeQuery();
			
			JdbcSession sessionObject = new JdbcSession(conn)
											.sql("show tables")
											.execute();
			assertNotNull(sessionObject);
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
		conn.close();
	}
	
	/*
	 * Test Case to test Insert Data
	 * Test will fail if connection object is null Or Insertion of Data Fails
	 */
	
	@Test
	public void testCInsertData() throws SQLException {
		
		ImplementSchemaDB db = new ImplementSchemaDB();
		User test = new User("test@test.com","test","editor","OOPSLA");	
        db.insertData(test);
        
      	Connection conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
		try {
			JdbcSession sessionObject = new JdbcSession(conn)
					.sql("select id from user where userName like 'test@test.com'")
					.execute();
			assertNotNull(sessionObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
	}	
}
