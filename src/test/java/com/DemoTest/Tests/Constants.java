package com.DemoTest.Tests;

public class Constants {


    /*
    old @ondemand.saucelabs.com:443/wd/hub
    new @ondemand.us-west-1.saucelabs.com/wd/hub

     */
    public static final boolean isDebugFlag = true;
    public static final boolean isNativeApp = false;
    public static final String tag = "SmokeTest";
    public static final String tagdownload = "FileDownloadTest";
    public static final String tagEmulator = "SmokeTest_Emulator";
    public static final String tagSimulator = "SmokeTest_Simulator";
    public static final String tunnelIdentifier = "WebtunnelId";
    public static final String buildfile = "/Users/ianflanagan/Workspace/SeleniumJavaWeb/responsivewebdemo/build";
    public static final int buildNumber = 4;
    public static final String sauceUsername = "<sauceusername>";
    public static final String saucePassword = "<sauceaccesskey>";
    public static final String preRunScriptFile = "https://gist.githubusercontent.com/iflanagan/70102460af806e31a73688e1301fb8b5/raw/35684a8265ba5dd4cc156b4aae6436aca5cc1935/prerunwindows.bat";
    public static final String UPURL = "@ondemand.us-west-1.saucelabs.com/wd/hub";
    public static final String SLURL = "https://" +sauceUsername+ ":" +saucePassword+ "@ondemand.us-west-1.saucelabs.com/wd/hub";
    public static final String appiumVersionAndroid = "1.9.1";
    public static final String appiumVersioniOS = "1.15.0";
    public static final String deviceOrientationLand = "landscape";
    public static final String deviceOrientationportrait = "portrait";

    // test specific stuff
    public static final String sauceDemoTitle = "Swag Labs";
    public static final String sauceDemoURL = "https://www.saucedemo.com";
    public static final String sauceDemoInventoryURL = " https://www.saucedemo.com/inventory.html";
    public static final String demoUsername = "standard_user";
    public static final String demoLockedUsername = "locked_out_user";
    public static final String demoproblemuser = "problem_user";
    public static final String demoperfglitchuser = "performance_glitch_user";
    public static final String demoPassword = "secret_sauce";


}
