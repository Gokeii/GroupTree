package com.GroupTree.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class GetGroupOrUserInfo extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GetGroupOrUserInfo(){
		System.out.println("construct");
	}
	
	private String name;
	
	private InputStream inputStream;
	
	public void setName(String name) {
		this.name = name;
	}
	public InputStream getInputStream(){
		return inputStream;
	}
	public String getUserInfo(){
		
		inputStream = new ByteArrayInputStream((name+"-user").getBytes());
		return "success";
	}
	
	public String getGroupInfo(){
		
		inputStream = new ByteArrayInputStream((name+"-group").getBytes());
		return "success";
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println(request.getRequestURL());
	}

}
