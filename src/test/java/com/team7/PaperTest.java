package com.team7;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.Test;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

public class PaperTest {
	private String title = "testTitle";
	private String pages = "testPages";
	private int year = 1234;
	private String key = "testKey";
	
	ArrayList<String> author = new ArrayList<String>();
	
	@Test
	public void testGetAuthor() {
		author.add("testAuthor");
		Paper testObject = new Paper();
		testObject.setAuthor(author);
		ArrayList<String> authorReturned = (ArrayList<String>) testObject.getAuthor();
		assertEquals(author, authorReturned);
		assertNotNull(authorReturned);
	}
	
    @Test
    public void testSetAuthor() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Paper testObject = new Paper();

        //when
        testObject.setAuthor(author);

        //then
        final Field field = testObject.getClass().getDeclaredField("author");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), author);
    }
   
    @Test
    public void testSetTitle() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Paper testObject = new Paper();

        //when
        testObject.setTitle(title);

        //then
        final Field field = testObject.getClass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), title);
    }
    
	@Test
	public void testGetTitle() {
		
		Paper testObject = new Paper();
		testObject.setTitle(title);
		String titleReturned = testObject.getTitle();
		assertEquals(title, titleReturned);
		assertNotNull(titleReturned);
	}

    @Test
    public void testSetPages() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Paper testObject = new Paper();

        //when
        testObject.setPages(pages);

        //then
        final Field field = testObject.getClass().getDeclaredField("pages");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), pages);
    }
    
	@Test
	public void testGetPages() {
		
		Paper testObject = new Paper();
		testObject.setPages(pages);
		String pagesReturned = testObject.getPages();
		assertEquals(pages, pagesReturned);
		assertNotNull(pagesReturned);
	}
    
    @Test
    public void testSetYear() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Paper testObject = new Paper();

        //when
        testObject.setYear(year);

        //then
        final Field field = testObject.getClass().getDeclaredField("year");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), year);
    }
    
	@Test
	public void testGetYear() {
		
		Paper testObject = new Paper();
		testObject.setYear(year);
		int yearReturned = testObject.getYear();
		assertEquals(year, yearReturned);
		assertNotNull(yearReturned);
	}
    
    @Test
    public void testSetKey() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Paper testObject = new Paper();

        //when
        testObject.setKey(key);

        //then
        final Field field = testObject.getClass().getDeclaredField("key");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), key);
    }
    
	@Test
	public void testGetKey() {
		
		Paper testObject = new Paper();
		testObject.setKey(key);
		String keyReturned = testObject.getKey();
		assertEquals(key, keyReturned);
		assertNotNull(keyReturned);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testToString() {
		
		Paper testObject = new Paper();
		testObject.setAuthor(author);
		testObject.setTitle(title);
		testObject.setPages(pages);
		testObject.setYear(year);
		String createdString = testObject.toString();
		assertThat(createdString, containsString(title));
		assertNotNull(createdString);
	}
	
}
