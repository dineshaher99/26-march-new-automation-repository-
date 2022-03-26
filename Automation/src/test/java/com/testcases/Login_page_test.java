package com.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;
import com.Saucedemo_Pages.Login_Page;

public class Login_page_test extends Login_Page {
	@Test
	public void InvalidLogin() throws FileNotFoundException, IOException {
		startUp();
		setUname(driver);
		setPwd(driver);
		loginButton(driver);
		verifyLogin(driver);
		
	}

}
