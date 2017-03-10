package com.team7;
import org.junit.Test;

import junit.framework.TestCase;

public class SearchJunitTest extends TestCase {

	@Test
	public void testAdd1() {
		//count the number of test cases
		System.out.println("No of Test Case = "+ this.countTestCases());

		//test getName 
		String name = this.getName();
		System.out.println("Test Case Name = "+ name);

		//test setName
		this.setName("testNewAdd");
		String newName = this.getName();
		System.out.println("Updated Test Case Name = "+ newName);
	}
	
	@Test
	public void testAdd2() {
		
		System.out.println("Inside testAdd");
		//test data
		int num = 5;
		String str = "Junit is working fine";

		//check for equality
		assertEquals("Junit is working fine", str);

		//check for false condition
		assertFalse(num > 6);

		//check for not null value
		assertNotNull(str);
	}


}
