package com.team7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;
import org.junit.Test;
import junit.framework.TestCase;

@SuppressWarnings("restriction")
public class ImplementParseDatabaseTest extends TestCase{

	@Test
	public void testParseSuccess() throws JAXBException, SQLException, IOException {
		
		// to test the successful parsing of XML file
	    File file = new File("test.xml");
	    FileWriter writer = new FileWriter(file);
	    writer.write("<?xml version='1.0' encoding='ISO-8859-1'?><dblp><garbage><test>This is a test</test></garbage></dblp>"); 
		writer.close();
	    ImplementParseDatabase parse = new ImplementParseDatabase();
		String result = parse.parseXml(file);
		assertEquals("success", result);
	}
	
	@Test
	public void testParseFailure() throws JAXBException, SQLException, IOException {
		
		// to test the failure of parsing of XML file
	    File file = new File("test.xml");
	    FileWriter writer = new FileWriter(file);
	    writer.write("<?xml version='1.0' encoding='ISO-8859-1'?><dblp><garbage><test>'Negative'</test></garbage></dblp>"); 
		writer.close();
		ImplementParseDatabase parse = new ImplementParseDatabase();
		String result = parse.parseXml(file);
		assertEquals("failure", result);
		
	}
}
