package com.GroupTree.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.GroupTree.utils.MainframeLogin;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String message;
	private String stat = null;
	private HttpServletRequest servletRequest;
	

	public String getMessage(){
		return message;
	}
	
	public String getStat(){
		return stat;
	}
	
	
	
	
	
	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String execute(){
		boolean isLogin = MainframeLogin.login(username, password);
		if(isLogin){
			this.servletRequest.getSession().setAttribute("username", username);
			this.servletRequest.getSession().setAttribute("password", password);
			return "success";
		}
		return "failToLogin";
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}


}
