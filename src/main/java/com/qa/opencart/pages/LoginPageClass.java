package com.qa.opencart.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;


public class LoginPageClass {
	
	  private WebDriver driver;
	  private ElementUtil util;
	
	// 1. By locator:
	     private By email = By.id("input-email");
		 private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private  By logo = By.cssSelector("img.img-responsive");
	public By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	public LoginPageClass(WebDriver driver) {
		this.driver=driver;
		util= new ElementUtil(driver);
	}
	@Step("Waiting for login page title and fetching the title")
	public boolean getLoginPageTitle() throws InterruptedException
	{ driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		String title = util.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);

		//String title= driver.getTitle();
		System.out.println(title);
		
		return true;
		
	}
	

	
	@Step("Waiting for login page url and fetching the url")
	public boolean getLoginPageUrl() {
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		String url = util.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		System.out.println(url);
		if (url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
			
	
	/*
	 * public boolean getLoginPageUrl() { String url= driver.getCurrentUrl();
	 * System.out.println(url); if(url.contains("route=account/login")) { return
	 * true; } else { return false;
	 * 
	 * } }
	 */
	@Step("checking forgot pwd link is displayed on login page")
	public boolean isForgotPwdLinkExist() {
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		return util.doEleIsDisplayed(forgotPwdLink);
	}
		
		
		
			
		/*
		 * public void doLogin(String Username, String passworduser) {
		 * System.out.println("User credentials are");
		 * 
		 * driver.findElement(email).sendKeys(Username);
		 * driver.findElement(password).sendKeys(passworduser);
		 * driver.findElement(loginBtn).click();
		 * 
		 * }
		 * 
		 */	
	
	@Step("navigating to register page")
	    public AccountsPage doLogin(String Username, String Password) {
			//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			 
				driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
				
				/*
				 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				 * 
				 * driver.findElement(By.id("input-email")).clear();
				 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")
				 * )).click();
				 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")
				 * )).sendKeys(Username);
				 * 
				 * System.out.println(Username);
				 * driver.findElement(By.id("input-password")).clear();
				 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
				 * "input-password"))).click();
				 * //driver.findElement(By.id("input-password")).sendKeys(Password);
				 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
				 * "input-password"))).sendKeys(Password); System.out.println(Password);
				 */
		
			
			util.doSendKeysWithWait(email, AppConstants.DEFAULT_LARGE_TIME_OUT, Username);
			System.out.println(Username);
		    util.doSendKeys(password, Password);
		    System.out.println(Password);
		    util.doClick(loginBtn);
//				waitForElementPresence(email, 10).sendKeys("test@gmail.com");
		
				return new AccountsPage(driver);
		 }			
	
		
			public RegisterPage navigateToRegisterPage() {
				driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
				
				System.out.println("navigating to register page.....");
				util.doClick(registerLink);
				driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
				
				return new RegisterPage(driver);
			}

	
			}