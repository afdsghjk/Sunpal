package com.sunpal.config;

public class GlobalTestcaseConfiguration {
		
	public static String loginUsername = "";
	public static String loginPassword = "";
	public static int waitTimeout = 0; 
	public static String screenshotPath = "";
	public static int upgradeWaitTime = 0;
	
	public void setLoginUsername(String str) {  
		loginUsername = str;  
    } 
	
	public void setLoginPassword(String str) {  
		loginPassword = str;  
    }
	
	public void setWaitTimeout(int integer) {  
		waitTimeout = integer;  
    }
	
	public void setScreenshotPath(String str) {  
		screenshotPath = str;  
    }
	
	public void setUpgradeWaitTime(int integer) {  
		upgradeWaitTime = integer;  
    }
}
