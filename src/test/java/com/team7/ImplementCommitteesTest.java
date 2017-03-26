package com.team7;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import junit.framework.TestCase;

public class ImplementCommitteesTest extends TestCase {

	// Test case for successful parsing
	@Test
	public void testParseSuccess() throws IOException, SQLException {
		
		File path = new File("input/TestCommitteeSuccess/");
		ImplementCommittees com = new ImplementCommittees();
		String result = com.ParseFiles(path);
		assertEquals("success", result);
		
	}
	
	// Test case for unsuccessful parsing
	@Test
	public void testParseFailure() throws IOException, SQLException {
		
		File path = new File("input/TestCommitteeFailure/");
		ImplementCommittees com = new ImplementCommittees();
		String result = com.ParseFiles(path);
		assertEquals("failure", result);
		
	}

	
	
}
