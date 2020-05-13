package com.DemoTest.Tests;

import com.DemoTest.Pages.WebDemoPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

public class WebDemoTest extends TestBase {

	@Parameters({ "browser", "version", "os"  })
	@Test
	public void LoginDesktopParameterExample(String browser, String version, String os, Method method)  throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		System.out.println("\nStarting Login Function for Parameters example");
		// add boolean variable
		Boolean value = true;

		// Added comment to sync file

		System.out.println("\nCreate an instance of the driver");
		//create webdriver session
		this.createDriverNew(browser, version, os, method.getName());
		WebDriver driver = this.getWebDriver();

		this.annotate("Visiting sauce labs demo page...");
		WebDemoPage page = WebDemoPage.visitPage(driver);

		System.out.println("\nStarting login function");
		this.annotate("Login Test..");
		//  Boolean returnvalue = page.Login("standard_user", "secret_sauce");
		Boolean returnvalue = page.Login(Constants.demoUsername, Constants.demoPassword);

		this.annotate("Asserting the test: Login: result");
		Assert.assertEquals(value,returnvalue);

	}

	 @Test(dataProvider = "Browsers")
	    public void IanTestLoginDesktopWeb(String browser, String version, String os, Method method)  throws MalformedURLException, InvalidElementStateException, UnexpectedException {

		 System.out.println("\nStarting Login Function for Dataprovider: Browsers");
		 // add boolean variable 
	    	Boolean value = true;
	    	
	    	// Added comment to sync file 

	    	 System.out.println("\nCreate an instance of the driver");
	        //create webdriver session
	        this.createDriver(browser, version, os, method.getName());
	        WebDriver driver = this.getWebDriver();

	        this.annotate("Visiting sauce labs demo page...");
	        WebDemoPage page = WebDemoPage.visitPage(driver);

	        System.out.println("\nStarting login function");
	        this.annotate("Login Test..");
	      //  Boolean returnvalue = page.Login("standard_user", "secret_sauce");
	         Boolean returnvalue = page.Login(Constants.demoUsername, Constants.demoPassword);
	        
	        this.annotate("Asserting the test: Login: result");
	        Assert.assertEquals(value,returnvalue);
	     
	    }

	@Test(dataProvider = "Emulators")
	public void pageValidationEmulator(String DeviceName, String orientation, String browser, String os, String platformName, Method methodName)  throws MalformedURLException, UnexpectedException {

		System.out.println("\nStarting Login Function for Dataprovider Emulators");
		// add boolean variable
		Boolean value = true;

		// Added comment to sync file

		System.out.println("\nCreate an instance of the driver");
		//create webdriver session
		this.createDriverEmulator(DeviceName, orientation,browser, os, platformName, methodName.getName());
		WebDriver driver = this.getWebDriver();

		this.annotate("Visiting sauce labs demo page...");
		WebDemoPage page = WebDemoPage.visitPage(driver);

		System.out.println("\nStarting login function");
		this.annotate("Validate Page Loaded Test");
		Boolean returnvalue = page.getTitle(driver, Constants.sauceDemoTitle);

		System.out.println("\nStarting login function");
	//	this.annotate("Login Test..");
		//  Boolean returnvalue = page.Login("standard_user", "secret_sauce");
	//	Boolean returnvalue = page.Login(Constants.demoUsername, Constants.demoPassword);

		this.annotate("Asserting the test: Page Load: result");
		Assert.assertEquals(value,returnvalue);

	}

	@Test(dataProvider = "Simulators")
	public void pageValidationSimulator(String DeviceName, String orientation, String browser, String os, String platformName, Method methodName)  throws MalformedURLException, UnexpectedException {

		Boolean value = true;

		// Added comment to sync file

		System.out.println("\nCreate an instance of the Simulator driver");
		//create webdriver session
		this.createDriverSimulator(DeviceName, orientation,browser, os, platformName, methodName.getName());
		WebDriver driver = this.getWebDriver();

		this.annotate("Visiting sauce labs demo page...");
		WebDemoPage page = WebDemoPage.visitPage(driver);

		System.out.println("\nStarting login function");
		this.annotate("Validate Page Loaded Test");
		Boolean returnvalue = page.getTitle(driver, Constants.sauceDemoTitle);

		System.out.println("\nStarting login function");
		//  this.annotate("Login Test..");
		//  Boolean returnvalue = page.Login("standard_user", "secret_sauce");
		//  Boolean returnvalue = page.Login(Constants.demoUsername, Constants.demoPassword);

		this.annotate("Asserting the test: Page Load: result");
		Assert.assertEquals(value,returnvalue);

	}

	@Parameters({ "platformName", "platformVersion", "deviceName"  })
	@Test
	public void pageValidationRDC(String platformName, String deviceName, Method methodName)  throws MalformedURLException, UnexpectedException {

		Boolean value = true;
		Boolean returnvalue = false;

		// Added comment to sync file

		System.out.println("\nCreate an instance of the RDC driver");
		//create webdriver session
		AndroidDriver driver = createDriverRDC(platformName, deviceName, methodName.getName());

		/* Get the elements. */
		this.annotate("Capturing Objects");
		MobileElement buttonTwo = (MobileElement)(driver.findElement(By.id("net.ludeke.calculator:id/digit2")));
		MobileElement buttonPlus = (MobileElement)(driver.findElement(By.id("net.ludeke.calculator:id/plus")));
		MobileElement buttonEquals = (MobileElement)(driver.findElement(By.id("net.ludeke.calculator:id/equal")));
		MobileElement resultField = (MobileElement)(driver.findElement(By.xpath("//android.widget.EditText[1]")));

		/* Add two and two. */
		this.annotate("Click click");
		buttonTwo.click();
		buttonPlus.click();
		buttonTwo.click();
		driver.getScreenshotAs(OutputType.FILE);
		buttonEquals.click();
		driver.getScreenshotAs(OutputType.FILE);



		/* Check if within given time the correct result appears in the designated field. */
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElement(resultField, "4"));

		this.annotate("Asserting the test: Page Load: result");
		Assert.assertEquals(value,returnvalue);

	}
	    
	 @Test(dataProvider = "Browsers")
	    public void LoginLockedoutUser(String browser, String version, String os, Method method)
	            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

	    	Boolean value = true;

	        //create webdriver session
	        this.createDriver(browser, version, os, method.getName());
	        WebDriver driver = this.getWebDriver();

	        this.annotate("Visiting sauce labs demo page...");
	        WebDemoPage page = WebDemoPage.visitPage(driver);


	         //
	        this.annotate("Login LoginLockedoutUser user Test..");
	        Boolean returnedvaluelockedout = page.LoginLockedoutUser(Constants.demoLockedUsername, Constants.demoPassword);

	        this.annotate("Asserting the test: Login LoginLockedoutUser user Test: result");
	        Assert.assertEquals(value, returnedvaluelockedout);


	    }

	 
	@Test(dataProvider = "Browsers")
	    public void LoginPerfGlitchUser(String browser, String version, String os, Method method)
	            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

	    	Boolean value = true;

	        //create webdriver session
	        this.createDriver(browser, version, os, method.getName());
	        WebDriver driver = this.getWebDriver();

	        this.annotate("Visiting sauce labs demo page...");
	        WebDemoPage page = WebDemoPage.visitPage(driver);


	         //
	        this.annotate("Login Performance Glitch user Test..");
	        Boolean returnedvalueglitch = page.LoginPerfGlitchUser(Constants.demoperfglitchuser, Constants.demoPassword);

	        this.annotate("Asserting the test: Login Performance Glitch user Test: result");
	        Assert.assertEquals(value,returnedvalueglitch);


	    }
	

	@Test(dataProvider = "Browsers")
	    public void LoginProblemUser(String browser, String version, String os, Method method)
	            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

	    	Boolean value = true;

	        //create webdriver session
	        this.createDriver(browser, version, os, method.getName());
	        WebDriver driver = this.getWebDriver();

	        this.annotate("Visiting sauce labs demo page...");
	        WebDemoPage page = WebDemoPage.visitPage(driver);


	         //
	        this.annotate("Login ProblemUser user Test..");
	        Boolean returnedvaluelockedout = page. LoginProblemUser(Constants.demoproblemuser, Constants.demoPassword);

	        this.annotate("Asserting the test: Login ProblemUser user Test: result");
	        Assert.assertEquals(value, returnedvaluelockedout);


	    }

}
