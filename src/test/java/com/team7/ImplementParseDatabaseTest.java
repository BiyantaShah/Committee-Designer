package com.team7;

import java.io.File;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;
import org.junit.Test;
import junit.framework.TestCase;

@SuppressWarnings("restriction")
public class ImplementParseDatabaseTest extends TestCase{

	@Test
	public void testParseSuccess() throws JAXBException, SQLException {
		// to test the successful parsing of XML file
		File file = new File("input/positiveTest.xml");
		ImplementParseDatabase parse = new ImplementParseDatabase();
		String result = parse.parseXml(file);
		assertEquals("success", result);

	}
	
	@Test
	public void testParseFailure() throws JAXBException, SQLException {
		// to test the failure of parsing of XML file
		File file = new File("input/negativeTest.xml");
		ImplementParseDatabase parse = new ImplementParseDatabase();
		String result = parse.parseXml(file);
		assertEquals("failure", result);
		
	}

}
