package com.sunpal.network;

public interface AppDownloader {
	
	public boolean downloadViaUrl(String urlStr, String fileName, String savePath);

}