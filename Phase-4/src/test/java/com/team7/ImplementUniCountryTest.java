package com.team7;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ImplementUniCountryTest {
	
	@Test
	// to test the successful parsing of CSV file
	public void testParseSuccess() throws IOException, SQLException {
		
		File file = new File("test.xml");
	    FileWriter writer = new FileWriter(file);
	    writer.write("institution,region\n"
	    		+ "Test Country,Test Country");
	    
	    writer.close();
	    ImplementUniCountryData parse = new ImplementUniCountryData();
		String result = parse.ParseFiles(file);
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from Author where name='Test Country'");

		file.delete();
	}
	
	@Test
	// to test the parsing of an empty CSV file
	public void parseEmpty() throws IOException, SQLException {
		File file = new File("test.csv");
		FileWriter writer = new FileWriter(file);
		writer.close();
		
		ImplementUniCountryData parse = new ImplementUniCountryData();
		String result = parse.ParseFiles(file);
		assertEquals("No data", result);
		file.delete();
	}

}
