package com.sunpal.config;

public class GlobalAppConfiguration {
		
	public static String appFileName = "";
	public static String appPackage = "";
	public static String appActivity = ""; 
	public static String appStoredDir = ""; 
	public static String appDownloadUrl = "";
	
	public void setAppFileName(String str) {  
		appFileName = str;  
    } 
	
	public void setAppStoredDir(String str) {  
		appStoredDir = str;  
    } 
	
	public void setAppPackage(String str) {  
		appPackage = str;  
    }
	
	public void setAppActivity(String str) {  
		appActivity = str;  
    }
	
	public void setAppDownloadUrl(String str) {  
		appDownloadUrl = str;  
    } 
}
