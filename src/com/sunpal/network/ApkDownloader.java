package com.sunpal.network;

import java.io.IOException;

public class ApkDownloader implements AppDownloader {

	public boolean downloadViaUrl(String urlStr, String fileName,  String savePath) {	

		try {
			HttpDownloadUtility.downloadFile(urlStr, fileName, savePath);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return false;
	}

}
