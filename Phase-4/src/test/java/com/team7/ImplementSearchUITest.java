package com.team7;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

public class ImplementSearchUITest {
	
	UIConstants u  = new UIConstants("shahbiyanta@gmail.com", "Conference Chair", "ECOOP");
	SearchUI search = new SearchUI();
	
	@Test
	// Testing the new button in each row
	public void testNewRowButton() {
		search.NewRowButton_1.doClick();
		search.NewRowButton_2.doClick();
		search.NewRowButton_3.doClick();
		search.NewRowButton_4.doClick();
		search.dispose();
	}
	
	@Test
	// Testing the logout button
	public void testLogoutButton() throws SQLException, IOException {
		search.btnLogout.doClick();
		search.dispose();
	}
	
	@Test
	// Testing the submit button
	public void testSubmitButton() throws SQLException, IOException {
		System.out.println("Here");
		search.btnNewButton.doClick();
		search.dispose();
	}

}
