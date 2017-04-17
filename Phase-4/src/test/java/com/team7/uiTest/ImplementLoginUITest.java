package com.team7.uiTest;

import org.junit.Test;

import com.team7.ui.ImplementLogin;
import com.team7.ui.LoginUI;

/**
 * The Class ImplementLoginUITest.
 */
public class ImplementLoginUITest {

	/** The login. */
	LoginUI login = new LoginUI();

	/** The log. */
	ImplementLogin log = new ImplementLogin();

	/**
	 * Test login button invalid 1.
	 */
	@Test
	// Tests when username is empty
	public void testLoginButtonInvalid1() {
		login.passwordField.setText("abc");
		login.btnLogin.doClick();
	}

	/**
	 * Test login button invalid 2.
	 */
	@Test
	// Tests when password is empty
	public void testLoginButtonInvalid2() {
		login.userNameField.setText("shahbiyanta@gmail.com");
		login.btnLogin.doClick();
	}

	/**
	 * Test login button invalid 3.
	 */
	@Test
	//Tests when user does not exist
	public void testLoginButtonInvalid3() {
		login.userNameField.setText("shah@gmail.com");
		login.passwordField.setText("123");
		login.btnLogin.doClick();
	}

	/**
	 * Test login button invalid 4.
	 */
	@Test
	// Tests when credentials are not correct
	public void testLoginButtonInvalid4() {
		login.userNameField.setText("xyz@gmail.com");
		login.passwordField.setText("biy");
		login.btnLogin.doClick();
	}

	@Test
	// Testing for when the username and password are correct
	public void testLoginButton() {
		login.userNameField.setText("shahbiyanta@gmail.com");
		login.passwordField.setText("abc");
		login.btnLogin.doClick();
		// logout the user then
		log.logout();
	}

	@Test
	// Testing for when the username and password are correct
	public void testRegisterButton() {
		login.btnNewUserClick.doClick();
	}

}
