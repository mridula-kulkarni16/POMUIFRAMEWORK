package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;


	public class RegisterPage {

		private WebDriver driver;
		private ElementUtil util;

		private By firstname = By.id("input-firstname");
		private By lastname = By.id("input-lastname");
		private By email = By.id("input-email");
		private By telephone = By.id("input-telephone");
		private By password = By.id("input-password");
		private By confirmpassword = By.id("input-confirm");

		private By agreeCheckBox = By.name("agree");
		private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
		private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1' and @type='radio']");
		private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0' and @type='radio']");

		private By registerSuccessMesg = By.cssSelector("div#content h1");
		private By logoutLink = By.linkText("Logout");
		private By registerLink = By.linkText("Register");

		public RegisterPage(WebDriver driver) {
			this.driver = driver;
			util = new ElementUtil(driver);
		}

		/**
		 * user registration
		 * @param firstName
		 * @param lastName
		 * @param email
		 * @param telephone
		 * @param password
		 * @param subscribe
		 */
		public String userRegister(String firstname, String lastname, 
						String email, String telephone, String password, String subscribe) {

			
			util.doSendKeysWithVisibleElement(this.firstname, AppConstants.DEFAULT_TIME_OUT, firstname);
			util.doSendKeys(this.lastname, lastname);
			util.doSendKeys(this.email, email);
			util.doSendKeys(this.telephone, telephone);
			util.doSendKeys(this.password, password);
			util.doSendKeys(this.confirmpassword, password);

			
				if(subscribe.equalsIgnoreCase("yes")) {
					util.doClick(this.subscribeYes);
				}
				else {
					util.doClick(this.subscribeNo);
				}
			
			util.doClick(this.agreeCheckBox);
			util.doClick(this.continueButton);
			
			String succMesg = util.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_TIME_OUT).getText();
			System.out.println("Success Messsggggg=====>" + succMesg);
		    util.doClick(logoutLink);
			util.doClick(registerLink);
			
			
			return succMesg;
			
		}

	}