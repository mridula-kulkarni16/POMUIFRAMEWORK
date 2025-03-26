package com.qa.opencart.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResults{

	private WebDriver driver;
	private ElementUtil util;

	private By productSearchLayout = By.cssSelector("div.product-layout");

	public SearchResults(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public boolean isSearchSuccessful() {
		//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=product/search&search=macbook");
		List<WebElement> searchList = util.waitForElementsToBeVisible(productSearchLayout,
				AppConstants.DEFAULT_LARGE_TIME_OUT);
		if (searchList.size() > 0) {
			System.out.println("Search is successfully done.....");
			return true;
		}
		return false;
	}

	public ProductInfoPage selectProduct(String mainProductName) {
		
		By mainPrName = By.linkText(mainProductName);
		util.doClick(mainPrName);
		return new ProductInfoPage(driver);
	}

}