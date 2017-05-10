package com.sunpal.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandRunner {
	
	public static void runCmdCommand(String cmd) throws IOException {
		String outputStr;
		Process process = Runtime.getRuntime().exec("cmd /c " + cmd);
		BufferedReader bufferedReader = new BufferedReader( 
				new InputStreamReader(process.getInputStream())); 
		while ( (outputStr=bufferedReader.readLine()) != null) 
				System.out.println(outputStr); 
	}

}
