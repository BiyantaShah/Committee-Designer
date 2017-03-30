package com.team7;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import junit.framework.TestCase;

public class ImplementCommitteesTest extends TestCase {

	// Test case for successful parsing
	@Test
	public void testParseSuccess() throws IOException, SQLException {
		
		File test = new File("input/committees");
	    test.mkdirs();
	    
	    File file1 = new File("input/committees/success1234-pc");	    
	    FileWriter writer = new FileWriter(file1);
	    writer.write("P:test\ntest2"); 
	    writer.close();
	    
	    File file2 = new File("input/committees/success5678-pc");	    
	    FileWriter writer2 = new FileWriter(file2);
	    writer2.write("E:test3\ntest4"); 	    
		writer2.close();
		
		ImplementCommittees com = new ImplementCommittees();
		String result = com.ParseFiles(test);
		assertEquals("success", result);
		
		FileUtils.deleteDirectory(test);		
	}
	
	// Test case for unsuccessful parsing
	@Test
	public void testParseFailure() throws IOException, SQLException {
		
		File test = new File("input/committees");
		test.mkdirs();
		
	    File file1 = new File("input/committees/fail1234-pc");	    
	    FileWriter writer = new FileWriter(file1);
	    writer.write("TestFailure"); 
	    writer.close();
		
		ImplementCommittees com = new ImplementCommittees();
		String result = com.ParseFiles(test);
		assertEquals("failure", result);
		FileUtils.deleteDirectory(test);		
		
	}

	
	
}
