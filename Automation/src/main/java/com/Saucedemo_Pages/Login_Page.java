package com.Saucedemo_Pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base_utilities.BaseUtility;

public class Login_Page extends BaseUtility {
	//config file reader code
	String path = System.getProperty("user.dir");
	Properties cp = new Properties();
	public Properties configReader() throws FileNotFoundException, IOException {
		cp.load(new FileInputStream(path+"\\src\\main\\resources\\config.properties"));
		return cp;

	}
	
	protected WebDriver driver;
	BaseUtility bu = new BaseUtility();

	public void LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);


	}
	@FindBy (id = "user-name")
	@CacheLookup
	private WebElement uName;

	@FindBy (id = "password")
	@CacheLookup
	private WebElement pwd;

	@FindBy (id = "login-button")
	@CacheLookup
	private WebElement loginButton;

	//Actions on webelement
	public void setUname(WebDriver driver) throws FileNotFoundException, IOException {
		configReader();
		uName.sendKeys(cp.getProperty("userName1"));
	}
	public void setPwd(WebDriver driver) throws FileNotFoundException, IOException {
		configReader();
		pwd.clear();
		pwd.sendKeys(cp.getProperty("password"));
	}
	public void loginButton(WebDriver driver) {
		loginButton.click();
	}
	public void verifyLogin(WebDriver driver) {
		if(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
			System.out.println("login successful");
		}
	}
}
