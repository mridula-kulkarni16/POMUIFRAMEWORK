package com.qa.opencart.test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.SearchResults;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest {
	@BeforeClass
	 public void accSetup() {
		 
		acp=    lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		  }

	
	@Description("accPageTitleTest -- Dev Name: @Naveen Khunteta")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void AccPageTitleTest() throws InterruptedException
	{

		String ActualAccPageTitle= acp.getAccPageTitle();
		Assert.assertEquals( ActualAccPageTitle, AppConstants.ACC_PAGE_TITLE);
	}
	
	@Description("accPageUrlTest -- Dev Name: @Naveen Khunteta")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void AccPageUrlTest()
	{
	
		Assert.assertTrue(acp.getAccPageUrl());
	}
	
	@Description("Acc page search test -- Dev Name: @Naveen Khunteta")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void searchTest()
	{
	
		Assert.assertTrue(acp.isSearchExist());
	}
	
	@Description("Acc page logout link exist test -- Dev Name: @Naveen Khunteta")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void logoutExists()
	{
	
		Assert.assertTrue(acp.isLogoutLinkExist());
	}
	
	@Description("Acc page header test -- Dev Name: @Naveen Khunteta")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority = 5)
	public void AccPageHeadersTest()
	{
	
		ArrayList<String> actheader= acp.getAccSecHeadersList();
		System.out.println(actheader);
		Assert.assertEquals(actheader, AppConstants.ACC_PAGE_SECTIONS_HEADERS);
	}
	
	public Object[][] getProductKey() {
		return new Object[][] {
				{ "Macbook"},
				{ "iMac"},
				{"Samsung"}
				};
	}

	
	
	@Description("Acc page search check test -- Dev Name: @Naveen Khunteta")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductKey", priority = 6)
	public void searchCheckTest(String productKey) {
		searchResults = acp.performSearch(productKey);
		Assert.assertTrue(searchResults.isSearchSuccessful());
	}

	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			      { "Macbook", "MacBook Pro" },
			    { "Macbook", "MacBook Air" },
				{ "iMac", "iMac" },
				{"Samsung", "Samsung SyncMaster 941BW"}	
				};
	}

	
	
	@Description("Acc page product search test -- Dev Name: @Naveen Khunteta")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "getProductData", priority = 7)
	public void searchTest(String searchKey, String mainProductName) {
		searchResults = acp.performSearch(searchKey);
		if (searchResults.isSearchSuccessful()) {
			productInfoPage = searchResults.selectProduct(mainProductName);
			String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductHeader, mainProductName);
		}
	}
	
}
