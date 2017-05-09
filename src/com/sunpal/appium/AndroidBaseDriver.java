package com.sunpal.appium;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;

import com.sunpal.config.GlobalAppConfiguration;
import com.sunpal.config.GlobalDeviceConfiguration;
import com.sunpal.network.ApkDownloader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

public class AndroidBaseDriver {
	
	public AppiumDriver<WebElement> driver;
    private static AppiumDriverLocalService service;
    protected static final Logger LOGGER = Logger.getLogger(AndroidBaseDriver.class);
    protected ApplicationContext applicationContext = new FileSystemXmlApplicationContext("ApplicationContext.xml");

    File classpathRoot = new File(System.getProperty("user.dir"));
    File appDir = new File(classpathRoot, GlobalAppConfiguration.appStoredDir);
    
    @BeforeSuite
    public void downloadApkFile(){
    	(new ApkDownloader()).downloadViaUrl(
    			GlobalAppConfiguration.appDownloadUrl, 
    			GlobalAppConfiguration.appFileName, 
    			GlobalAppConfiguration.appStoredDir);
    }
    
    @BeforeTest
    public void setUp() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
        File app = new File(appDir.getCanonicalPath(), GlobalAppConfiguration.appFileName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName",GlobalDeviceConfiguration.deviceName);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", GlobalAppConfiguration.appPackage);
        capabilities.setCapability("appActivity", GlobalAppConfiguration.appActivity);
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
    }
    
    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
    
    public MobileElement scrollTo(String text){
        return (MobileElement) driver.findElement(MobileBy.
                AndroidUIAutomator("new UiScrollable(new UiSelector()"
                        + ".scrollable(true)).scrollIntoView(resourceId(\"android:id/list\")).scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"))"));
    }
}
