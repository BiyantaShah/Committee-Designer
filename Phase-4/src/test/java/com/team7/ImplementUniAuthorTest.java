package com.team7;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ImplementUniAuthorTest {
	
	@Test
	public void testParseSuccess() throws IOException, SQLException {
		
		File file = new File("test.csv");
	    FileWriter writer = new FileWriter(file);
	    writer.write("name,dept\n"
	    		+ "Testing,Test Uni");
	    
	    writer.close();
	    ImplementUniversityAuthorData parse = new ImplementUniversityAuthorData();
		String result = parse.ParseFiles(file);
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from Author where name='Testing'");

		file.delete();
	}
	
	@Test
	public void parseEmpty() throws IOException, SQLException {
		File file = new File("test.csv");
		FileWriter writer = new FileWriter(file);
		writer.close();
		
		ImplementUniversityAuthorData parse = new ImplementUniversityAuthorData();
		String result = parse.ParseFiles(file);
		assertEquals("No data", result);
		file.delete();
	}

}
