package com.qa.opencart.test;
import java.util.Map;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductPageTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetup() {
		acp = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		searchResults = acp.performSearch("Macbook");
		productInfoPage = searchResults.selectProduct("MacBook Pro");
		String actProdHeader = productInfoPage.getProductHeader("MacBook Pro");
		Assert.assertEquals(actProdHeader, "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {
				{ "Macbook", "MacBook Pro" , AppConstants.MACBOOK_PRO_IMAGES_COUNT},
				{ "Macbook", "MacBook Air" , AppConstants.MACBOOK_AIR_IMAGES_COUNT},
				{ "iMac", "iMac" , AppConstants.IMAC_IMAGES_COUNT},
				};
	}

	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchKey, String mainProductName, int ImagesCount) {
		searchResults = acp.performSearch(searchKey);
		productInfoPage = searchResults.selectProduct(mainProductName);
		int actProductImages = productInfoPage.getProductImagesCount();
		System.out.println("actual product images : " + actProductImages);
		Assert.assertEquals(actProductImages, ImagesCount);
	}
	
	
	@Test
	public void productMetaDataTest() {
		searchResults = acp.performSearch("Macbook");
		productInfoPage = searchResults.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
	}
}