package com.team7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class ImplementSchemaDB implements SchemaDB {
			
	public void dbSetUp() throws ClassNotFoundException, SQLException{
		
		   // JDBC driver name and database URL
		   String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   String DB_URL = "jdbc:mysql://localhost?verifyServerCertificate=false&useSSL=true";
		   
		   
		   //Database credentials
		   String userName = "root";
		   String password = "root";
		   
		   Connection conn = null;
		   Statement stmt = null;
	   
		   try{
			      //Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //Open a connection
			      conn = DriverManager.getConnection(DB_URL, userName, password);
			      
			      String sql = null; 
//			      stmt = conn.createStatement();			      
//			      String sql = "DROP DATABASE IF EXISTS DBLP";
//			      stmt.executeUpdate(sql);
//			      System.out.println("Dblp database deleted successfully...");

			      //Execute a query
			      stmt = conn.createStatement();			      
			      sql = "CREATE DATABASE DBLP";
			      stmt.executeUpdate(sql);
			      System.out.println("Dblp database created successfully...");
			   
			      //selecting database created above
				  String connected_db = "jdbc:mysql://localhost/DBLP?verifyServerCertificate=false&useSSL=true";
				  conn = DriverManager.getConnection(connected_db, userName, password);
 			      stmt = conn.createStatement();
 			      
 			      
 			      //creating user table
			      sql = "CREATE TABLE User " +
			            "(id       INTEGER      AUTO_INCREMENT NOT NULL, " +
			            " username VARCHAR(255) UNIQUE, " + 
			            " password VARCHAR(255), " + 
			            " role     VARCHAR(255), " + 
			            " confName VARCHAR(255),"+
			            " PRIMARY  KEY(id))"; 

			      stmt.executeUpdate(sql);
			      System.out.println("Created user table in dblp database...");

			      
				  //creating conference table
			      sql = "CREATE TABLE Conference " +
				        "(id          INTEGER      AUTO_INCREMENT NOT NULL, " +
				        " confKey     VARCHAR(255),"  + 
	     	            " name        TEXT, " + 
				        " confDetail  TEXT,"  +
				        " PRIMARY     KEY(id))"; 

				  stmt.executeUpdate(sql);
				  System.out.println("Created Conference table in dblp database...");

			      			      			      
				  //creating paper table
			      sql = "CREATE TABLE Paper " +
				        "(id           INTEGER   AUTO_INCREMENT NOT NULL, " +
				        " author       VARCHAR(255),"  + 
				        " title        TEXT,"          + 
				        " pages		   VARCHAR(255),"  +
	     	            " year         INTEGER, "      + 
				        " confName     VARCHAR(255),"  +
				        " journalName  VARCHAR(255),"  +
				        " PRIMARY      KEY(id))"; 

				  stmt.executeUpdate(sql);
				  System.out.println("Created Paper table in dblp database...");
				  

			     //creating Author table
			      sql = "CREATE TABLE Author " +
				        "(id        INTEGER      AUTO_INCREMENT NOT NULL, " +
				        " name      VARCHAR(255), " + 
				        " url       TEXT, " + 
				        " PRIMARY   KEY(id))" ;

				  stmt.executeUpdate(sql);
				  System.out.println("Created author table in dblp database...");
				  
				     //creating Committees table
			      sql = "CREATE TABLE Committee " +
				        "(id              INTEGER      AUTO_INCREMENT NOT NULL, " +
				        " confName        VARCHAR(255), " + 
	     	            " year            INTEGER, "      +   
				        " authorName      VARCHAR(255), " + 
				        " role            VARCHAR(255), " + 
				        " PRIMARY         KEY(id))" ;

				  stmt.executeUpdate(sql);
				  System.out.println("Created Committees table in dblp database...");
				  
				  sql = "CREATE TABLE Article " +
					        "(id          INTEGER      AUTO_INCREMENT NOT NULL, " +
						    " author	  VARCHAR(255), " +
					        " title       TEXT, " + 
					        " year        INTEGER, " + 
					        " month       VARCHAR(255), " + 
		     	            " ee          TEXT, "      +   
					        " PRIMARY     KEY(id))" ;

					  stmt.executeUpdate(sql);
					  System.out.println("Created Article table in dblp database...");
								  				 			      
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			      
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			      
			   }
		   conn.close();

	}

	public Connection getConnection() {

		  Connection conn = null;
		  
		  //Database to be connected
		  String connected_db = "jdbc:mysql://localhost/DBLP?useServerPrepStmts=false&rewriteBatchedStatements=true&verifyServerCertificate=false&useSSL=true";
		  
		  //Database credentials
		   String userName = "root";
		   String password = "root";
		 
		try {
			
			  conn = DriverManager.getConnection(connected_db, userName, password);
			  
		    } catch (SQLException e) {
		    	
			  e.printStackTrace();
		}
		return conn;
	}

	public <T> void insertData(Object object_name) throws SQLException {
		
		Connection conn = getConnection();		
		
		try {
			
			if(object_name instanceof User)
				
			{
				PreparedStatement statement_inproceedings = conn.prepareStatement("insert into User(userName,password,role,confName) values(?,?,?,?)");
				statement_inproceedings.setString(1,(((User) object_name).userName));
				statement_inproceedings.setString(2,((User) object_name).password);
				statement_inproceedings.setString(3,  ((User) object_name).role);
				statement_inproceedings.setString(4, ((User) object_name).confName);
     			statement_inproceedings.executeUpdate();
     			
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conn.close();

	}

}
