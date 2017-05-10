package com.sunpal.appium.script;

import static org.testng.Assert.assertTrue;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.sunpal.appium.AndroidBaseDriver;
import com.sunpal.config.GlobalTestcaseConfiguration;

public class AndroidInternalUpgradeTester extends AndroidBaseDriver {
	
	@Test
	public void testAndroidApkUpgrade() {
		startApp();
		driver.manage().timeouts().implicitlyWait(
        		GlobalTestcaseConfiguration.waitTimeout, TimeUnit.MILLISECONDS);
		
		WebElement el = driver.findElement(By.xpath(".//*[@text='更新']"));
		takeScreenshot();
        el.click();
        
        try {
			Thread.sleep(GlobalTestcaseConfiguration.upgradeWaitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        startApp();
		driver.manage().timeouts().implicitlyWait(
        		GlobalTestcaseConfiguration.waitTimeout, TimeUnit.MILLISECONDS);
		takeScreenshot();
		if (driver.findElementsByXPath(".//*[@text='更新']").isEmpty()) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}

}
