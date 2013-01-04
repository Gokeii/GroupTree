package com.GroupTree.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

public class JCLGenerator {
	public static FileInputStream JCLGenerate(String username, String command) throws IOException {
		String path = System.getProperty("user.dir") + "/WebContent/jcl/";
		String templatePath = null;
		try {
			templatePath = JCLGenerator.class.getResource("../jcl/").toURI().getPath()+"template.jcl";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(templatePath);
		File template = new File(templatePath);
		File newFile = new File(new File(path), username + ".jcl");
		FileUtils.copyFile(template, newFile);
		RandomAccessFile raf = new RandomAccessFile(newFile, "rw");

		String line = null;
		int nowLength = 0;
		while ((line = raf.readLine()) != null) {
			if (line.contains("username") || line.contains("command")) {
				raf.seek(nowLength);
				if (line.contains("username"))
					line = line.replace("username", username.toUpperCase());
				else 
					line = line.replace("command                 ", command.toUpperCase());
				raf.writeBytes(line);
				raf.seek(0);
				nowLength = 0;
				continue;
			}
			nowLength += line.length() + 2;
		}
		raf.close();
		FileInputStream fis = new FileInputStream(newFile);
		return fis;
	}		
	
	
	
	
	public static FileInputStream JCLSubmit(String username) throws IOException {
		String path = System.getProperty("user.dir") + "/WebContent/jcl/";
		String templatePath = null;
		try {
			templatePath = JCLGenerator.class.getResource("../jcl/").toURI().getPath()+"DSMON.jcl";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(templatePath);
		File template = new File(templatePath);
		File newFile = new File(new File(path), username + ".jcl");
		FileUtils.copyFile(template, newFile);
		FileInputStream fis = new FileInputStream(newFile);
		return fis;
	}
}
