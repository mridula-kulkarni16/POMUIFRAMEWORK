package com.qa.opencart.base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPageClass;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResults;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
   public DriverFactory df;
	public WebDriver driver;

  public Properties prop;
  public AccountsPage acp;
  public SearchResults searchResults;
 public LoginPageClass lp;
 public RegisterPage regPage;
 public ProductInfoPage productInfoPage;
 @Parameters({"url"})
	@BeforeTest
	public void setup() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		df= new DriverFactory();
		prop=df.initProp();
		driver=df.initDriver(prop);
		lp = new LoginPageClass(driver);
	
	
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
		
	}
}
