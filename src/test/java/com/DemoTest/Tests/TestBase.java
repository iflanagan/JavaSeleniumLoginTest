package com.DemoTest.Tests;

import io.appium.java_client.android.AndroidDriver;

import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

public  class TestBase  {


    // added build tag

    private static final String  targetEnvironment = null;
    private static final int sauce = 0;
	public String buildTag = System.getenv("BUILD_TAG");
    public static String username = System.getenv("SAUCE_USERNAME");
    public static String accesskey = System.getenv("SAUCE_ACCESS_KEY");
    public static final String sauceURL = "https://" +username+ ":" +accesskey+ "@ondemand.us-west-1.saucelabs.com/wd/hub";

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
    private ThreadLocal<AndroidDriver> driver = new ThreadLocal<AndroidDriver>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod
     * @return Two dimensional array of objects with browser, version, and platform information
     */
    @DataProvider(name = "Browsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{

        	 // windows 10 
        	 
        	 new Object[]{"chrome", "latest", "Windows 10"},

     			 // windows 8.1
                new Object[]{"MicrosoftEdge", "latest", "Windows 10"},
                new Object[]{"internet explorer", "latest", "Windows 8.1"},
     			new Object[]{"firefox", "latest", "Windows 8.1"},
     			new Object[]{"chrome", "latest", "Windows 8.1"},
     			new Object[]{"firefox", "latest -1", "Windows 8.1"},
     			new Object[]{"chrome", "latest -1", "Windows 8.1"},
     
     			
     			new Object[]{"safari", "latest", "macOS 10.14"}, //12.0 safari version
     			
     			
     			
     			// windows 8
     			new Object[]{"chrome", "latest", "Windows 8"},
     			
     			// windows 7
     		
     			new Object[]{"firefox", "latest", "Windows 7"},
     			new Object[]{"chrome", "latest", "Windows 7"},
                
        };
    }

    @DataProvider(name = "Emulators", parallel = true)
       	    public static Object[][] sauceBrowserDataProviderEmulator(Method testMethod) {
       	        return new Object[][]{

       	                //
       	        	
       	         new Object[]{"Android GoogleAPI Emulator", "portrait", "Chrome", "8.0", "Android"},
             //    new Object[]{" Samsung Galaxy Tab A 10 GoogleAPI Emulator", "landscape", "Chrome", "8.1", "Android"},
       	             
       	        };
       	    }

    @DataProvider(name = "Simulators", parallel = true)
    public static Object[][] sauceBrowserDataProviderSimulators(Method testMethod) {
        return new Object[][]{

                new Object[]{"iPhone XS Simulator", "portrait", "Safari", "13.2", "iOS"},

        };
    }

    @DataProvider(name = "Devices", parallel = true)
    public static Object[][] sauceBrowserDataProviderRDC(Method testMethod) {
        return new Object[][]{
                //Verify that your account has access to the devices below
                new Object[]{"iOS", "iPhone 7", "10"},
                new Object[]{"iOS", "iPhone SE", ""}
        };
    }

    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part of the test run.
     * @param os Represents the operating system to be used as part of the test run.
     * @param methodName Represents the name of the test case that will be used to identify the test on Sauce.
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */
    protected void createDriverSimulator(String DeviceName, String orientation, String browser, String os, String platformName, String methodName)  throws MalformedURLException, UnexpectedException {

            DesiredCapabilities caps = new DesiredCapabilities();
            // set desired capabilities to launch appropriate browser on Sauce
            caps.setCapability("deviceName",DeviceName);
            caps.setCapability("deviceOrientation", orientation);
            caps.setCapability("browserName", browser);
            caps.setCapability("platformVersion",os);
            caps.setCapability("name", methodName);
            caps.setCapability("tage", Constants.tagSimulator);

        if (buildTag != null) {
            caps.setCapability("build", buildTag);
        }

            webDriver.set(new RemoteWebDriver(new URL(sauceURL), caps));

        // set current sessionId
            String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
            sessionId.set(id);
    }

    protected void createDriverEmulator(String DeviceName, String orientation, String browser, String os, String platformName, String methodName)  throws MalformedURLException, UnexpectedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        // set desired capabilities to launch appropriate browser on Sauce
        caps.setCapability("deviceName",DeviceName);
        caps.setCapability("deviceOrientation", orientation);
        caps.setCapability("browserName", browser);
        caps.setCapability("platformVersion",os);
        caps.setCapability("name", methodName);
        caps.setCapability("tage", Constants.tagEmulator);

        if (buildTag != null) {
            caps.setCapability("build", buildTag);
        }

        // Launch remote browser and set it as the current thread
        //       webDriver.set(new RemoteWebDriver(new URL("https://" +Constants.sauceUsername+ ":" +Constants.saucePassword+ "@ondemand.saucelabs.com:443/wd/hub"), capabilities));
        webDriver.set(new RemoteWebDriver(new URL(sauceURL), caps));

        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
    }


    protected void createDriverNew(String browser, String version, String os, String methodName)  throws MalformedURLException, UnexpectedException {


               JSONObject obj = new JSONObject();
               obj.put("executable",Constants.preRunScriptFile);
               obj.put("background","false");

              ChromeOptions chromeOpts = new ChromeOptions();
              chromeOpts.setExperimentalOption("w3c", true);

              MutableCapabilities sauceOpts = new MutableCapabilities();
              sauceOpts.setCapability("seleniumVersion", "3.11.0");
              sauceOpts.setCapability("user", username);
              sauceOpts.setCapability("accessKey", accesskey);
              sauceOpts.setCapability("tag", Constants.tag);
              sauceOpts.setCapability("name", methodName);
           //   sauceOpts.setCapability("build", 1);
              sauceOpts.setCapability("prerun", obj);

        if (buildTag != null) {
            sauceOpts.setCapability("build", buildTag);
        }

              MutableCapabilities caps = new MutableCapabilities();
              caps.setCapability(ChromeOptions.CAPABILITY,  chromeOpts);
              caps.setCapability("browserName", browser);
              caps.setCapability("platformName", os);
              caps.setCapability("browserVersion", version);
              caps.setCapability("sauce:options", sauceOpts);

              URL url = new URL(sauceURL);
              RemoteWebDriver driver = new RemoteWebDriver(url, caps);

              String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
              sessionId.set(id);

/*
               DesiredCapabilities capabilities = new DesiredCapabilities();
                // set desired capabilities to launch appropriate browser on Sauce
                capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
                capabilities.setCapability(CapabilityType.VERSION, version);
                capabilities.setCapability(CapabilityType.PLATFORM, os);
                capabilities.setCapability("name", methodName);
                capabilities.setCapability("extendedDebugging", Constants.isDebugFlag);
                capabilities.setCapability("tags", Constants.tag);
                capabilities.setCapability("build", Constants.buildNumber);
                      capabilities.setCapability("TunnelIdentifier", Constants.tunnelIdentifier);
                capabilities.setCapability("prerun", obj);

        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        webDriver.set(new RemoteWebDriver(new URL(sauceURL), capabilities));

        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);*/
    }

    protected AndroidDriver createDriver(String browser, String version, String os, String methodName)  throws MalformedURLException, UnexpectedException {

        JSONObject obj = new JSONObject();
        obj.put("executable",Constants.preRunScriptFile);
        obj.put("background","false");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        // set desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("extendedDebugging", Constants.isDebugFlag);
        capabilities.setCapability("tags", Constants.tag);
     //   capabilities.setCapability("build", Constants.buildNumber);
  //      capabilities.setCapability("TunnelIdentifier", Constants.tunnelIdentifier);
        capabilities.setCapability("prerun", obj);

        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        // Launch remote browser and set it as the current thread
 //       webDriver.set(new RemoteWebDriver(new URL("https://" +Constants.sauceUsername+ ":" +Constants.saucePassword+ "@ondemand.saucelabs.com:443/wd/hub"), capabilities));
        webDriver.set(new RemoteWebDriver(new URL(sauceURL), capabilities));
            
        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
        return null;
    }

    protected AndroidDriver createDriverRDC(String platformName,String deviceName, String methodName)
            throws MalformedURLException, UnexpectedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName);
       // capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("name",  methodName);
        capabilities.setCapability("app","sauce-storage:Calculator_2.0.apk");

       // RemoteWebDriver rdcDriver  = new RemoteWebDriver(new URL(sauceURL), capabilities);
        driver.set(new AndroidDriver<WebElement>(new URL(sauceURL), capabilities));

        return driver.get();

       /* AppiumDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("name",  methodName);
        capabilities.setCapability("app","sauce-storage:Calculator_2.0.apk");

        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }*/
/*
        if (platformName.equals("iOS"))
        {
           // driver = new IOSDriver(sauceURL, capabilities);
            driver = new RemoteWebDriver(sauceURL, capabilities);
        }

        {
            driver = new AndroidDriver(sauceURL, capabilities);
        }*/
/*
      RemoteWebDriver rdcDriver  = new RemoteWebDriver(new URL(sauceURL), capabilities); // new URL(sauceURL), caps
        webDriver.set(new RemoteWebDriver(new URL(sauceURL), capabilities));
        webDriver.set(new RemoteWebDriver(new URL(sauceURL), capabilities));

        String id = webDriver.getSessionId().toString();
       String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);*/




    }

    public static void setSauceContext(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }

    public static void setSauceLabsLogging(WebDriver driver, String command)
    {
        // command = sauce: disable log TOOENABLE = sauce: enable log
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command);

    }


    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        webDriver.get().quit();
    }


    public static void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }
}
