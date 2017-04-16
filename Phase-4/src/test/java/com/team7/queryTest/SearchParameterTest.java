package com.team7.queryTest;

import static org.junit.Assert.*;
import org.junit.Test;

import com.team7.queryEngine.SearchParameter;

public class SearchParameterTest {
	
	SearchParameter test = new SearchParameter();
	String testFilter = "filter";
	String testComparator = "comparator";
	String testValue = "value";
	String joinFilter = "joinFilterValue";
	
	@Test
	public void testSetSearchFilter() {
		String searchFilter = test.setSearchFilter(testFilter);
		assertEquals("filter", searchFilter);
		assertNotNull(searchFilter);
	}
	
	@Test
	public void testSearchComparator() {
		String searchComparator = test.setSearchComparator(testComparator);
		assertEquals("comparator", searchComparator);
		assertNotNull(searchComparator);
	}
	
	@Test
	public void testSetSearchValue() {
		String searchValue = test.setSearchValue(testValue);
		assertEquals("value", searchValue);
		assertNotNull(searchValue);
	}
	
	@Test
	public void testSetJoinFilter() {
		String searchJoinFilter = test.setJoinFilter(joinFilter);
		assertEquals("joinFilterValue", searchJoinFilter);
		assertNotNull(searchJoinFilter);
	}
}
