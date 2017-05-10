package com.sunpal.appium.script;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.sunpal.appium.AndroidBaseDriver;
import com.sunpal.config.GlobalTestcaseConfiguration;

public class AndroidInternalUpdateTester extends AndroidBaseDriver {
	
	@Test
	public void testAndroidApkUpdate() {
		startApp();
		takeScreenshot();
		
		WebElement el = driver.findElement(By.xpath(".//*[@text='更新']"));
        assertEquals("更新", el.getText());
        el.click();
        driver.manage().timeouts().implicitlyWait(
        		GlobalTestcaseConfiguration.waitTimeout, TimeUnit.MILLISECONDS);
	}

}
