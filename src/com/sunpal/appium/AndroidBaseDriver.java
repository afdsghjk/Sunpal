package com.sunpal.appium;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.AfterTest;
import org.testng.log4testng.Logger;

import com.sunpal.config.GlobalAppConfiguration;
import com.sunpal.config.GlobalDeviceConfiguration;
import com.sunpal.config.GlobalTestcaseConfiguration;
import com.sunpal.network.ApkDownloader;
import com.sunpal.tools.CommandRunner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

public class AndroidBaseDriver implements IAndroidBaseDriver {
	
	public AppiumDriver<WebElement> driver;
    private static AppiumDriverLocalService service;
    protected static final Logger LOGGER = Logger.getLogger(AndroidBaseDriver.class);
    protected ApplicationContext applicationContext = 
    		new FileSystemXmlApplicationContext("ApplicationContext.xml");

    File classpathRoot = new File(System.getProperty("user.dir"));
    File appDir = new File(classpathRoot, GlobalAppConfiguration.appStoredDir);
    
    public void downloadApkFile(){
    	(new ApkDownloader()).downloadViaUrl(
    			GlobalAppConfiguration.appDownloadUrl, 
    			GlobalAppConfiguration.appFileName, 
    			GlobalAppConfiguration.appStoredDir);
    }
    
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
    
    public boolean takeScreenshot() {
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		File targetFile=new File((new StringBuilder(System.getProperty("user.dir"))).append(GlobalTestcaseConfiguration.screenshotPath).append((new SimpleDateFormat ("yyyyMMddHHmmssSSS")).format(new Date())).append(".png").toString());
		
	    try {
			FileUtils.copyFile(srcFile, targetFile);
			return true;
		} 
	    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
    
    public void startApp() {
    	service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName",GlobalDeviceConfiguration.deviceName);
        capabilities.setCapability("appPackage", GlobalAppConfiguration.appPackage);
        capabilities.setCapability("appActivity", GlobalAppConfiguration.appActivity);
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
    }
    
    public void installAppViaAdb() throws Exception {
    	File app = new File(appDir.getCanonicalPath(), GlobalAppConfiguration.appFileName);
    	if (!app.exists()) {
    		assertTrue(false);
    	}
    	CommandRunner.runCmdCommand("adb install -r " + app.getAbsolutePath());
    }
}
