package com.sunpal.appium.script;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.sunpal.appium.AndroidBaseDriver;
import com.sunpal.config.GlobalTestcaseConfiguration;

public class AndroidOnlineUpgradeTester extends AndroidBaseDriver {
	
	@Test
	public void testAndroidOnlineUpgrade() throws Exception {
		downloadApkFile();
		installAppViaAdb();
		startApp();
		driver.manage().timeouts().implicitlyWait(
        		GlobalTestcaseConfiguration.waitTimeout, TimeUnit.MILLISECONDS);
		takeScreenshot();
		if (driver.findElementsByXPath(".//*[@text='¸üÐÂ']").isEmpty()) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}

}
