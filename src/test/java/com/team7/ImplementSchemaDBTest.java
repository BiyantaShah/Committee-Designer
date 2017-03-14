package com.team7;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.runners.MethodSorters;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.After;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void testBCreateTable() {
		Connection conn = null;
		conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
		try {
			JdbcSession sessionObject = new JdbcSession(conn)
					.sql("CREATE TABLE foo (id INT PRIMARY KEY)")
					.execute();
			assertNotNull(sessionObject);
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * Test Case to test Insert Data
	 * Test will fail if connection object is null Or Insertion of Data Fails
	 */
	
	@Test
	public void testCInsertData() {
		Connection conn = null;
		conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
		try {
			JdbcSession sessionObject = new JdbcSession(conn)
					.sql("INSERT INTO foo (id) VALUES (1112211)")
					.execute();
			assertNotNull(sessionObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Test Case to test getting data
	 * Test will fail if connection object is null Or data is not fetched correctly
	 */
	
	@Test
	public void testDGetData() {
		Connection conn = null;
		conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
		System.out.println("Getting Data");
		try {
			String valueFromDB = new JdbcSession(conn)
					.sql("select * from foo")
					.select(new SingleOutcome<String>(String.class));
			System.out.println(valueFromDB);
			assertEquals(valueFromDB, "1112211");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Delete Test Table after running tests
	 * Test will fail if connection object is null
	 * Deletes Table on success
	 */
	
	@After
	public void testEDeleteTable() {
		Connection conn = null;
		conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
		try {
			JdbcSession sessionObject = new JdbcSession(conn)
			.sql("DROP table foo")
			.execute();			
			assertNotNull(sessionObject);			
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}
}
	
	/*
	 * The following code is for testing purpose and would needed in future to test some issues.
	 * Don't delete
	 */
	
//	
//	@Test
//	public void testInsertData() {
//		System.out.println("********"+PORT);
//		Connection conn;
//		try {
//			conn = DriverManager.getConnection(
//					String.format(
//							"jdbc:mysql://localhost:%s/ProjectTest?user=root",
//							3306 //SchemaDBTest.PORT
//							)
//					);
//			new JdbcSession(conn)
//			.autocommit(false)
//			.sql("CREATE TABLE foo (id INT PRIMARY KEY)")
//			.execute()
//			.sql("INSERT INTO foo (id) VALUES (1112211)")
//			.execute()
//			.sql("select * from foo")
//			.execute()
//			.sql("DROP table foo")
//			.execute()
//			.commit();
//			assertEquals("Database Connection okay",dbStr);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
////	@Rule
////	public final ExpectedException exception = ExpectedException.none();	
//
//	@Test(expected = CommunicationsException.class)
//	public void testGetConnectionInvalidPath() {
//		System.out.println("********"+PORT);
//		Connection conn;
//		try {
////			exception.expect(CommunicationsException.class);
//			conn = DriverManager.getConnection(
//					String.format(
//							"jdbc:mysql://blahblah",
//							3306 //SchemaDBTest.PORT
//							)
//					);
////			exception.expect(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException.class);
//			new JdbcSession(conn);
//			
//		} catch (SQLException e) {
////			exception.expect(CommunicationsException.class);
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


/*
 * #%L
 * MariaDB4j
 * %%
 * Copyright (C) 2012 - 2014 Michael Vorburger
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.List;
//
//import org.apache.commons.dbutils.DbUtils;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.ColumnListHandler;
//import org.junit.Assert;
//import org.junit.Test;
//
//import ch.vorburger.mariadb4j.DB;
//import ch.vorburger.mariadb4j.DBConfigurationBuilder;
//
///**
// * Tests the functioning of MariaDB4j Sample / Tutorial illustrating how to use MariaDB4j.
// * 
// * @author Michael Vorburger
// * @author Michael Seaton
// */
//public class ImplementSchemaDBTest {
//
//    @Test
//    public void testEmbeddedMariaDB4j() throws Exception {
////        DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
////        config.setPort(0); // 0 => autom. detect free port
////        DB db = DB.newEmbeddedDB(config.build());
////        db.start();
////
////        String dbName = "mariaDB4jTest"; // or just "test"
////        if (!dbName.equals("test")) {
////            // mysqld out-of-the-box already has a DB named "test"
////            // in case we need another DB, here's how to create it first
////            db.createDB(dbName);
////        }
//
//        Connection conn = null;
//        try {
//        	DB database = DB.newEmbeddedDB(3306);
//        	database.start();
//        	conn = DriverManager.getConnection("jdbc:mysql://localhost/ProjectTest", "root", "");
////            conn = DriverManager.getConnection("jdbc:mysql://localhost/ProjectTest", "root", "");
//            QueryRunner qr = new QueryRunner();
//
//            // Should be able to create a new table
//            qr.update(conn, "CREATE TABLE hello(world VARCHAR(100))");
//
//            // Should be able to insert into a table
//            qr.update(conn, "INSERT INTO hello VALUES ('Hello, world')");
//
//            // Should be able to select from a table
//            List<String> results = qr.query(conn, "SELECT * FROM hello",
//                    new ColumnListHandler<String>());
//            Assert.assertEquals(1, results.size());
//            Assert.assertEquals("Hello, world", results.get(0));
//
//            // Should be able to source a SQL file
////            db.source("ch/vorburger/mariadb4j/testSourceFile.sql", "root", null, dbName);
////            results = qr.query(conn, "SELECT * FROM hello", new ColumnListHandler<String>());
////            Assert.assertEquals(3, results.size());
////            Assert.assertEquals("Hello, world", results.get(0));
////            Assert.assertEquals("Bonjour, monde", results.get(1));
////            Assert.assertEquals("Hola, mundo", results.get(2));
//        } finally {
//            DbUtils.closeQuietly(conn);
//        }
//    }
//
//}
