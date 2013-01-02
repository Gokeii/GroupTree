package com.GroupTree.utils;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class MainframeLogin {
	public static boolean login(String userName, String password) {
		String serverName = "10.60.37.129";
		boolean status = false;
		FTPClient ftp = new FTPClient();

		try {
			// Connect to the server
			ftp.connect(serverName);
			System.out.println(ftp.getReplyString());
		
			// Login to the server
			if ((status = ftp.login(userName, password)) == false)
				System.out.println("Unable to log in to zOS as " + userName);
			System.out.println(ftp.getReplyString());
		
			// Quit the server
			ftp.quit();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return status;
	}
}
