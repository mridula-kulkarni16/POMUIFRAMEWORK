package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;



public class AccountsPage {

	private WebDriver driver;
	private ElementUtil util;

	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	
	private By searchIcon = By.cssSelector("div#search button");
	private By accSecHeaders = By.cssSelector("div#content h2");
	private By acc = By.cssSelector("div#content h1");


	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	@Step("getAccPageTitle......")
	public String getAccPageTitle() {
		String title = util.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_TITLE);
		System.out.println("Acc page title : " + title);
		return title;
	}
	@Step("getAccPageUrl.....")
	public boolean getAccPageUrl() {
		String url = util.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_URL_PARAM);
		System.out.println("Acc page url : " + url);
		if (url.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}

	@Step("isLogoutLinkExist.....")
	public boolean isLogoutLinkExist() {
		return util.doEleIsDisplayed(logoutLink);
	}

	@Step("isSearchExist.....")
	public boolean isSearchExist() {
		return util.doEleIsDisplayed(search);
	}

	@Step("performSearch.....{0}")
	public SearchResults performSearch(String ProductKey) {
		System.out.println("ProductKey is : " + ProductKey);
		if (isSearchExist()) {
			
			util.doSendKeys(search, ProductKey);
			util.doClick(searchIcon);
			return new SearchResults(driver);
		} else {
			System.out.println("search field is not present on the page.....");
			return null;
		}
	}

	@Step("getAccSecHeadersList.....")
	public ArrayList<String> getAccSecHeadersList() {
		List<WebElement> secList = util.waitForElementsToBeVisible(accSecHeaders,
				AppConstants.DEFAULT_LARGE_TIME_OUT);
		System.out.println("total seactions headers: " + secList.size());
		ArrayList<String> actSecTextList = new ArrayList<String>();
		for (WebElement e : secList) {
			String text = e.getText();
			actSecTextList.add(text);
		}
		return actSecTextList;
	}

}