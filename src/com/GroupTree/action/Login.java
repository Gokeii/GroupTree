package com.GroupTree.action;

import com.GroupTree.utils.MainframeLogin;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String message;
	private String stat = null;
	
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
		if(isLogin)
			return "success";
		stat = "wrong";
		message = "User name or password is not right";
		return "fail";
	}
}
