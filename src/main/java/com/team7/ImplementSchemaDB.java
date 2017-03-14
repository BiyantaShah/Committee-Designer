package com.team7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.jcabi.jdbc.JdbcSession;

public class ImplementSchemaDB implements SchemaDB {
	
//	private static final String PORT = System.getProperty("mysql.port");

	public <T> List<T> objectCreation(String className, String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Connection getConnection() {
//		System.out.println("Connecting to MySQL on: "+PORT);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					String.format(
							"jdbc:mysql://localhost:%s/ProjectTest?user=root",
							3306 //SchemaDBTest.PORT
							)
					);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public <T> void insertData(List<T> object_name) {
		Connection conn = getConnection();
		try {
			new JdbcSession(conn)
			.sql("CREATE TABLE foo (id INT PRIMARY KEY)")
			.execute()
			.sql("INSERT INTO foo (id) VALUES (1112211)")
			.execute()
			.sql("select * from foo")
			.execute()
			.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
