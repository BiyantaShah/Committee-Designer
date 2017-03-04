package team7;

import junit.framework.*;

public class DriverClass {

	/*
	 * This class contains main method.
	 */

	public static void main (String []args) {
		TestSuite suite = new TestSuite(SearchJunit.class);
		TestResult result = new TestResult();
		suite.run(result);
		System.out.println("Number of test cases = " + result.runCount());
//		System.out.println("Start");
	}
}
