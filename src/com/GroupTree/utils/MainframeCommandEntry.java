package com.GroupTree.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.commons.net.ftp.FTPClient;

public class MainframeCommandEntry {

	public static String enterCommand(String username, String password,
			String command) throws IOException {
		String serverName = "10.60.37.129";
		String jobID = "";
		FTPClient ftp = new FTPClient();

		// Connect to the server
		ftp.connect(serverName);
		System.out.println(ftp.getReplyString());

		// Login to the server
		if (ftp.login(username, password) == false) {
			System.out.println("Unable to log in to " + "zOS" + " as "
					+ username);
			return null;
		}
		System.out.println(ftp.getReplyString());

		// Tell server that the file will have JCL records
		if (ftp.sendSiteCommand("quote site filetype=jes") == true)
			System.out.println("FTP is now in job entry mode");
		else
			System.err.println("Failed to switch to job entry mode");

		// Submit the job from the text file
		
		FileInputStream inputStream;
		System.out.println(command);
		if(!command.equals("SEARCH CLASS(GROUP)"))
			{inputStream = JCLGenerator.JCLGenerate(username,command);}
		else
			{inputStream = JCLGenerator.JCLSubmit(username);}
		ftp.storeFile(serverName, inputStream);

		String replyText = ftp.getReplyString();
		System.out.println(replyText);
		if (replyText.startsWith("250-It is known to JES as ")) {
			jobID = replyText.substring(26, 34);
			System.out.println("Submitted job " + username + ".jcl" + " as "
					+ jobID);
		} else {
			System.out.println("Submitted job");
		}

		// get job output file(s)
		InputStream is = ftp.retrieveFileStream(jobID);
		BufferedReader br = new BufferedReader(new InputStreamReader((is)));
		String line = null;
		String info = "";
		while ((line = br.readLine()) != null)
			info += line + "\n";
		is.close();
		br.close();
		ftp.completePendingCommand();
		
		// Quit the server
		try {
			ftp.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;
	}
}
