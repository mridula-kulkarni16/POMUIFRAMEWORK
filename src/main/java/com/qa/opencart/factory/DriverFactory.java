package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppError;
import com.qa.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	  public WebDriver driver;
	 public Properties prop;

		private static final Logger LOG = Logger.getLogger(DriverFactory.class);
	 public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	 public static String highlight;
		public OptionsManager optionsManager;
	 
	public WebDriver initDriver(Properties prop) throws InterruptedException {
		
	String browsername= prop.getProperty("browser").toLowerCase();

		System.out.println("Browser name" +browsername);
		LOG.info("browser name is : " + browsername);
		//highlight = prop.getProperty("highlight").trim();
		
		if(browsername.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
				
		}
		else if(browsername.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
			 
		}
		
		else if(browsername.equals("safari"))
		{
			WebDriverManager.safaridriver().setup();
			tlDriver.set(new SafariDriver());
			 
		}
		
		else {
			System.out.println(browsername);
			LOG.error("Please pass the right browser name : " + browsername);
			throw new FrameworkException(AppError.BROWSER_NOT_FOUND);
	} 
	
		 getDriver().manage().deleteAllCookies();
		 getDriver().manage().window().maximize();
		 getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 getDriver().get(prop.getProperty("url"));
		
			 		return getDriver();
		}
	
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	//method is used to initiliaze config properties
	/*
	 * public Properties initProp() { prop = new Properties();
	 * 
	 * try { FileInputStream fp= new FileInputStream(
	 * "C:\\Users\\mridu\\eclfpsepart2\\June2025POMSeriess\\src\\test\\resources\\Config\\config.properties"
	 * );//connection made with files prop.load(fp); System.out.println(prop);
	 * //after connection load all properties of config.properties } catch
	 * (FileNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace();
	 * 
	 * } return prop; }
	 */

	public Properties initProp() {
		prop = new Properties();
		FileInputStream fp = null;

		// mvn clean install -Denv="dev"
		// mvn clean install

		// String envName = System.getenv("env");// stage/uat/qa/dev
		String envName = System.getProperty("env");
		System.out.println("-----> Running test cases on environment: ----->" + envName);
		LOG.info("-----> Running test cases on environment: ----->" + envName);
		
		if (envName == null) {
			System.out.println("No env is given..hence running it on QA env.....");
			try {
				fp = new FileInputStream("C:\\Users\\mridu\\eclipsepart2\\June2025POMSeriess\\src\\test\\resources\\Config\\config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName) {
				case "prod":
					fp = new FileInputStream("C:\\Users\\mridu\\eclipsepart2\\June2025POMSeriess\\src\\test\\resources\\Config\\config.properties");
					break;
				case "dev":
					fp = new FileInputStream("C:\\Users\\mridu\\eclipsepart2\\June2025POMSeriess\\src\\test\\resources\\Config\\dev.config.properties");
					break;
				case "stage":
					fp = new FileInputStream("C:\\Users\\mridu\\eclipsepart2\\June2025POMSeriess\\src\\test\\resources\\Config\\stage.config.properties");
					break;
				case "uat":
					fp = new FileInputStream("C:\\Users\\mridu\\eclipsepart2\\June2025POMSeriess\\src\\test\\resources\\Config\\uat.config.properties");
					break;
				case "qa":
					fp = new FileInputStream("C:\\Users\\mridu\\eclipsepart2\\June2025POMSeriess\\src\\test\\resources\\Config\\qa.config.properties");
					break;

				default:
					System.out.println("please pass the right env name...." + envName);
					throw new FrameworkException(AppError.ENV_NOT_FOUND);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(fp);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	
	public static String getScreenshot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
	
	
	
