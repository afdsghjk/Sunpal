package com.sunpal.appium;

import io.appium.java_client.MobileElement;

public interface IAndroidBaseDriver {
	
	public void downloadApkFile();
	public void setUp() throws Exception;
	public void tearDown() throws Exception;
	public MobileElement scrollTo(String text);
	public boolean takeScreenshot();
	public void startApp();

}
