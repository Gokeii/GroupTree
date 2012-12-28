package com.GroupTree.utils;

import org.apache.commons.net.ftp.FTPClient;

public class MainframeLogin {
	public static boolean login(String userName, String password) {
		String serverName = "10.60.37.129";
		boolean status = false;
		FTPClient ftp = new FTPClient();		
		// Connect to the server
		try {
			ftp.connect(serverName);
			System.out.println(ftp.getReplyString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Login to the server
		try {
			if ((status = ftp.login(userName, password)) == false)
				System.out.println("Unable to log in to zOS as " + userName);
			System.out.println(ftp.getReplyString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Quit the server
		try {
			ftp.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
