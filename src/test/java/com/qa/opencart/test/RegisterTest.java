package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterTest extends BaseTest{
	
	
	@BeforeClass
	public void regSetup() {
		regPage = lp.navigateToRegisterPage();
	}
	
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automationtest"+random.nextInt(10000)+"@gmail.com";
		return email;
	}
	
	
	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String firstname, String lastname,  String email, String telephone, String password, String subscribe) {
				
		String actSuccMessg = regPage.userRegister(firstname, lastname, getRandomEmail(), telephone, password, subscribe);
		Assert.assertEquals(actSuccMessg, AppConstants.ACC_CREATE_SUCC_MESSG);
	}
	
	

}