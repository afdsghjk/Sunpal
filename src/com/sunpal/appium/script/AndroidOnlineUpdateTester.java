package com.sunpal.appium.script;

import org.testng.annotations.Test;

import com.sunpal.appium.AndroidBaseDriver;

public class AndroidOnlineUpdateTester extends AndroidBaseDriver {
	
	@Test
	public void testAndroidOnlineUpdate() throws Exception {
		downloadApkFile();
		setUp();
		takeScreenshot();
	}

}
