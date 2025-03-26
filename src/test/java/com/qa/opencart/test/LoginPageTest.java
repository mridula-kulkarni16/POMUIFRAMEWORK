package com.qa.opencart.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPageClass;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;



public class LoginPageTest extends BaseTest {
	@Test
	@Description("TC_01: login Page Title Test")
	@Severity(SeverityLevel.NORMAL)

	public void LoginPageTitleTest() throws InterruptedException
	{

		Assert.assertTrue(lp.getLoginPageTitle());
	}
	@Test
	@Description("TC_02: login Page url Test")
	@Severity(SeverityLevel.NORMAL)
	public void LoginPageUrlTest()
	{
	
		Assert.assertTrue(lp.getLoginPageUrl());
	}
	
	@Test
	@Description("TC_03: verify forgot pwd link exist on the login page")
	@Severity(SeverityLevel.CRITICAL)
	public void isForgotPwdLinkExistTest() {
		Assert.assertEquals(lp.isForgotPwdLinkExist(), true);
	}
	
	@Test
	@Description("TC_04: verify user is able to login with correct username and password")
	@Severity(SeverityLevel.BLOCKER)
	  
	  public void LoginTest() {
	  
	  
	  
	  
	 acp= lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	 

		Assert.assertTrue(acp.isLogoutLinkExist());
	  }
	 
}



