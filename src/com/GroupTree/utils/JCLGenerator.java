package com.GroupTree.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class JCLGenerator {
	public static FileInputStream JCLGenerate(String username, String command) throws IOException {
		String path = ServletActionContext.getServletContext().getRealPath("/jcl/");
		String templatePath = JCLGenerator.class.getResource("../jcl/").getPath()+"template.jcl";
		File template = new File(templatePath);
		File newFile = new File(new File(path), username + ".jcl");
		FileUtils.copyFile(template, newFile);
		RandomAccessFile raf = new RandomAccessFile(newFile, "rw");

		String line = null;
		int nowLength = 0;
		while ((line = raf.readLine()) != null) {
			if (line.contains("username")) {
				raf.seek(nowLength);
				line = line.replace("username", username);
				raf.writeBytes(line);
			}
			if (line.contains("command")) {
				raf.seek(nowLength);
				line = line.replace("command", command);
				raf.writeBytes(line);
			}
			nowLength += line.length() + 2;
		}
		
		raf.seek(0);
		raf.close();

		FileInputStream fis = new FileInputStream(newFile);
		return fis;
	}
}
