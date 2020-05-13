package com.DemoTest.Tests;

import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class Utils {

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private RemoteWebDriver driver;
    private String sauceUserName;
    private String sauceAccessKey;
    private String sauceURL;
    private String sauceDC;
    private final String sauceDCWest = "US West 1";;

    public Utils(String user, String accessKey, String DC) {

        if (user !=null && accessKey !=null)
        {
            this.sauceUserName = user;
            this.sauceAccessKey = accessKey;
        }
        if ( DC.isEmpty())
        {
            this.sauceDC = sauceDCWest;
        }

    }

    public WebElement findbyID(String locator)
    {
        WebElement element = null;
        try
        {

             element = driver.findElementById(locator);
            System.out.println("Element found: " +element);

        }
        catch (Exception ex)
        {
            System.out.println("Element found: " +element);
        }

        return element;
    }

}
