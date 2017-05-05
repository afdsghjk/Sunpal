package com.sunpal.test;

import com.sunpal.network.ApkDownloader;
import com.sunpal.network.AppDownloader;

public class Threads {
	
	public static void main(String[] args) {
		AppDownloader myAppDownloader = new ApkDownloader();
		myAppDownloader.downloadViaUrl(
				"http://appdlc.hicloud.com/dl/appdl/application/apk/8e/8e6190835a10411faf816483030b327a/com.chinaums.countryside.1704051857.apk?sign=mw@mw1493966021381",
				"全民惠农.apk", 
				"/Users/tongkefang/Documents/Github/Download");
	}

}
