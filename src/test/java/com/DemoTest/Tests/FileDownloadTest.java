package com.DemoTest.Tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FileDownloadTest {

    private static RemoteWebDriver driver;
    private static ThreadLocal<String> sessionId = new ThreadLocal<String>();


    public static <var> void main(String[] args) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        // set desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.VERSION, "latest");
        capabilities.setCapability(CapabilityType.PLATFORM, "Windows 10");
        capabilities.setCapability("name", "filedownload test");
      //  capabilities.setCapability("extendedDebugging", Constants.isDebugFlag);
        capabilities.setCapability("tags", Constants.tagdownload);
        capabilities.setCapability("build", Constants.buildNumber);
        //      capabilities.setCapability("TunnelIdentifier", Constants.tunnelIdentifier);
     //   capabilities.setCapability("prerun", obj);

        driver = new RemoteWebDriver(new URL(Constants.SLURL), capabilities);
        driver.setFileDetector(new LocalFileDetector());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://www.iconfinder.com/icons/511559/document_download_file_files_pdf_icon");
        WebElement element = driver.findElementByCssSelector("#png-512 > a.btn.btn-block.btn-primary");
        element.click();

        System.out.println("Checking the directory location now.");

        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        for (int i=0; i < tabs.size(); i++)
        {
            System.out.println("Window Handle" +i+  " is: " +tabs.get(i));
        }
        System.out.println("Switch to new Window");
        driver.switchTo().window(tabs.get(1));
        System.out.println("Opening downloads URL now");
        driver.get("chrome://downloads");

       JavascriptExecutor js = (JavascriptExecutor) driver;

       // js.executeScript("document.querySelector(\"body > downloads-manager\").shadowRoot.querySelector(\"#frb1\").shadowRoot.querySelector(\"#file-link\")

        System.out.println("Getting atribute for element");
        String downLoadLink = null;

      //  const docs = document.querySelector('downloads-manager').shadowRoot.querySelector('#downloads-list').getElementsByTagName('downloads-item');

        try
        {
            downLoadLink = (String) js.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#frb1').shadowRoot.querySelector('#file-link').text;)");

        }
        catch (Exception ex)
        {
            System.out.println("Can't Get atribute for element" +ex.toString());
        }


       //   String downLoadLink = (String) js.executeScript("return document.querySelector('body > downloads-manager').shadowRoot.querySelector('#frb1').shadowRoot.querySelector('#file-link').text;)");
     //   String downLoadLink = (String) js.executeScript("return document.querySelector("+body+").shadowRoot.querySelector("+query+").shadowRoot.querySelector("+selector+").text;)");
        System.out.println(downLoadLink);


        System.out.println("Switch to the parent Window");
        driver.switchTo().window(tabs.get(0));

      // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"file-link\"]")));
     //   wait.until(ExpectedConditions.jsReturnsValue(downLoadLink));


      //  String downLoadLink =  js.executeScript("return document.querySelector('body > downloads-manager').shadowRoot.querySelector('#frb0').shadowRoot.querySelector('#url')").toString();


      //  System.out.println(downLoadLink);
      //  var downloadlink = js.executeScript( "return document.querySelector(\"body > downloads-manager\").shadowRoot.querySelector(\"#frb0\").shadowRoot.querySelector(\"#url\"));


       /* if (sourceHtml.contains("https://www.iconfinder.com/icons/511559/download/png/512"))
        {
            System.out.println("Downloaded File Passed");
        } else
        {
            System.out.println("Downloaded File Failed");
        }

        System.out.println(sourceHtml);*/

     //   String fileDownloadLink= driver.findElement(By.xpath("//a[@href='https://www.iconfinder.com/icons/511559/download/png/512']")).getAttribute("href");
     //   System.out.println(fileDownloadLink);


        //    String result  = driver.findElementByClassName("#file-link").getAttribute("#file-link");
    //    System.out.println("Result is " +result);


    //    checkifFileExists("C:\\Users\\Administrator\\Downloads\\confinder_1-02_511559.png");

        driver.close();
        driver.quit();

        // set current sessionId

     //   String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
     //   sessionId.set(id);



    }

    private static boolean checkifFileExists(String filename)
    {
        // C:\Users\Administrator\Downloads
        boolean fileExist = false;

        File myfile = new File(filename);

        if (myfile.exists()) {

            System.out.println("File" +filename+ "exists");
            fileExist = true;

        } else {

            System.out.println("File " +filename+ "not found!");
        }

        return fileExist;
    }

}
