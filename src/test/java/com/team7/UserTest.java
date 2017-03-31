package com.team7;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

public class UserTest {
	
	String userName = "testUser";
	String testPassword = "testpass";
	String testRole = "testrole";
	String testConf = "conference";
	
    @Test
    public void testSetUserName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User testObject = new User();

        //when
        testObject.setUserName(userName);

        //then
        final Field field = testObject.getClass().getDeclaredField("userName");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), userName);
    }
    
	@Test
	public void testGetUserName() {
		User testObject = Mockito.mock(User.class);
		
		//Return using when and thenReturn
		when(testObject.getUserName()).thenReturn("testUser");
		
		assertEquals(testObject.getUserName(), userName);
	}
	
    @Test
    public void testSetPassword() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User testObject = new User();

        //when
        testObject.setPassword(testPassword);

        //then
        final Field field = testObject.getClass().getDeclaredField("password");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), testPassword);
    }
    
	@Test
	public void testGetPassword() {
		User testObject = Mockito.mock(User.class);
		
		//Return using when and thenReturn
		when(testObject.getPassword()).thenReturn("testpass");
		
		assertEquals(testObject.getPassword(), testPassword);
	}
    
    @Test
    public void testSetRole() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User testObject = new User();

        //when
        testObject.setRole(testRole);

        //then
        final Field field = testObject.getClass().getDeclaredField("role");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), testRole);
    }
    
	@Test
	public void testGetRole() {
		User testObject = Mockito.mock(User.class);
		
		//Return using when and thenReturn
		when(testObject.getRole()).thenReturn("testrole");
		
		assertEquals(testObject.getRole(), testRole);
	}
    
    @Test
    public void testSetConference() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User testObject = new User();

        //when
        testObject.setConfName(testConf);

        //then
        final Field field = testObject.getClass().getDeclaredField("confName");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testObject), testConf);
    }
    
	@Test
	public void testGetConfName() {
		User testObject = Mockito.mock(User.class);
		
		//Return using when and thenReturn
		when(testObject.getConfName()).thenReturn("conference");
		
		assertEquals(testObject.getConfName(), testConf);
	}
}
