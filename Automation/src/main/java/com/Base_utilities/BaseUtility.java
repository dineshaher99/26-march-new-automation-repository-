package com.Base_utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUtility {
	

	static WebDriver driver;
	@Test
	public WebDriver startUp()  {
		
		String path = System.getProperty("user.dir");
		Properties cp = new Properties();
		try {
		cp.load(new FileInputStream(path+"\\src\\main\\resources\\config.properties"));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browserName = cp.getProperty("browserName");
		String url = cp.getProperty("url");
		switch(browserName) {
		case ("chrome"): 
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case ("firefox"): 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case ("edge"): 
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case ("ie"): 
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		case ("safari"): 
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get(url);
		return driver;
	}
	
	public void tearDown() {
		driver.quit();
	}
	
	public void handleDemoQALaunch() {
		try {
			driver.findElement(By.id("details-button")).click();
			driver.findElement(By.id("proceed-link")).click();
		}catch(Exception e) {
			System.out.println("URL launched directly");
		}
	}
	
	public void scrollTillElement(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ele);
	}
	
	public void clickByJS(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		js.executeScript("arguments[0].click();", ele);
	}
	
	public void waitForFrameLoad(WebDriver driver, WebElement frameEle) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
//		WebDriverWait wt = new WebDriverWait(driver, 5);
		wt.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameEle));
	}
	
	public String getTitle(){
		String title = driver.getTitle();
		System.out.println(title);
		return title;
	}
	
	public String getCurrentUrl() {
		String cuUrl = driver.getCurrentUrl();
		System.out.println(cuUrl);
		return cuUrl;
	}
}
