package com.team7.parseTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.team7.abstractDesignFactory.AbstractParseFactory;
import com.team7.abstractDesignFactory.FactoryProducer;
import com.team7.interfaces.ParseCsvFiles;
import com.team7.parsing.ImplementSchemaDB;

/**
 * The Class ImplementUniAuthorTest.
 */
public class ImplementUniAuthorTest {
	
	/** The csv fac. */
	AbstractParseFactory csvFac = FactoryProducer.getFactory("CSV");
	
	/**
	 * Test parse success.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@Test
	// to test the successful parsing of CSV file
	public void testParseSuccess() throws IOException, SQLException {
		
		File file = new File("input/testUni.csv");
	    FileWriter writer = new FileWriter(file);
	    writer.write("name,dept\n"
	    		+ "Testing,Test Uni");
	    
	    writer.close();
	    
	    ParseCsvFiles p = csvFac.getCsv("input/testUni.csv");
		String result = p.parseCsv();
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from Author where name='Testing'");

		file.delete();
	}
	
	/**
	 * Parses the empty.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@Test
	// to test the parsing of an empty CSV file
	public void parseEmpty() throws IOException, SQLException {
		File file = new File("input/testUni.csv");
		FileWriter writer = new FileWriter(file);
		writer.close();
		
		ParseCsvFiles p = csvFac.getCsv("input/testUni.csv");
		String result = p.parseCsv();
		assertEquals("No data", result);
		file.delete();
	}

}
