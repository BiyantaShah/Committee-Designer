package com.team7;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ImplementHomePageTest {
	
	@Test
	public void testParseSuccess() throws IOException, SQLException {
		
		File file = new File("test.csv");
	    FileWriter writer = new FileWriter(file);
	    writer.write("name,homepage\n"
	    		+ "Test page,Test page");
	    
	    writer.close();
	    ImplementHomePageData parse = new ImplementHomePageData();
		String result = parse.ParseFiles(file);
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from Author where name='Test page'");

		file.delete();
	}
	
	@Test
	public void parseEmpty() throws IOException, SQLException {
		File file = new File("test.csv");
		FileWriter writer = new FileWriter(file);
		writer.close();
		
		ImplementHomePageData parse = new ImplementHomePageData();
		String result = parse.ParseFiles(file);
		assertEquals("No data", result);
		file.delete();
	}

}
