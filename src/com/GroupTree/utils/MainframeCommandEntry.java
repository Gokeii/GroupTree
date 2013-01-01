package com.GroupTree.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTPClient;

public class MainframeCommandEntry {
	
	public static String enterCommand(String username, String password, String command) throws IOException {
		String serverName = "10.60.37.129";
		String jobID = "";
		FTPClient ftp = new FTPClient();
		
		// Connect to the server
		ftp.connect(serverName);
		String replyText = ftp.getReplyString();
		System.out.println(replyText);
		
		// Login to the server
		if (ftp.login(username, password) == false) {
			System.out.println("Unable to log in to " + "zOS" + " as "	+ username);
			return null;
		}
		replyText = ftp.getReplyString();
		System.out.println(replyText);
		
		// Tell server that the file will have JCL records
		if (ftp.sendSiteCommand("quote site filetype=jes") == true)
			// place the FTP session into job entry mode
			System.out.println("FTP is now in job entry mode");
		else
			System.err.println("Failed to switch to job entry mode");
		
		// Submit the job from the text file
		FileInputStream inputStream = JCLGenerator.JCLGenerate(username, command);
		ftp.storeFile(serverName, inputStream);
		replyText = ftp.getReplyString();
		System.out.println(replyText);
		if (replyText.startsWith("250-It is known to JES as ")) {
			jobID = replyText.substring(26, 34);
			System.out.println("Submitted job " + username + ".jcl" + " as " + jobID);
		} else {
			System.out.println("Submitted job");
		}
		
		//get job output file(s)
		String sRemoteFilename = jobID;
		InputStream is = ftp.retrieveFileStream(sRemoteFilename);
		BufferedReader br = new BufferedReader(new InputStreamReader((is)));
		String sFileText = "";
		boolean bContinue = true;
		while (bContinue) {
			String sLine = br.readLine();
			if (sLine != null)
				sFileText += sLine;
			else {
				bContinue = false;
				is.close();
				br.close();
			}
		}
		ftp.completePendingCommand();
		System.out.println("-------------------------");
		System.out.println(sFileText);
		
		// Quit the server
		try {
			ftp.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sFileText;
	}	
}
