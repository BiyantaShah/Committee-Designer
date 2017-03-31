package com.team7;

import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.List;

public class DblpTest {
	
	List<Paper> inproceedings;
	List<Conference> proceedings;
	List<AuthorDetails> www;
	List<Article> article;
	
    @Test
    public void testSetInProceedings() throws NoSuchFieldException, IllegalAccessException {
        //given
        final dblp testObject = new dblp();

        //when
        testObject.setInproceedings(inproceedings);

        //then
        final Field field = testObject.getClass().getDeclaredField("inproceedings");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), inproceedings);
    }
    
    @Test
    public void testSetProceedings() throws NoSuchFieldException, IllegalAccessException {
        //given
        final dblp testObject = new dblp();

        //when
        testObject.setProceedings(proceedings);

        //then
        final Field field = testObject.getClass().getDeclaredField("proceedings");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), proceedings);
    }
    
    @Test
    public void testSetArticle() throws NoSuchFieldException, IllegalAccessException {
        //given
        final dblp testObject = new dblp();

        //when
        testObject.setArticle(article);

        //then
        final Field field = testObject.getClass().getDeclaredField("article");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), article);
    }
    
    @Test
    public void testSetWww() throws NoSuchFieldException, IllegalAccessException {
        //given
        final dblp testObject = new dblp();

        //when
        testObject.setWww(www);

        //then
        final Field field = testObject.getClass().getDeclaredField("www");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), www);
    }
}
