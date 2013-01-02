package com.GroupTree.model;

public class ConnectedGroup {
	private String id;
	private String auth;
	private String connectOwner;
	private String connectDate;
	private String connects;
	private String uAcc;
	private String lastConnect;
	private String connectAttributes;
	private String revokeDate;
	private String resumeDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getConnectOwner() {
		return connectOwner;
	}
	public void setConnectOwner(String connectOwner) {
		this.connectOwner = connectOwner;
	}
	public String getConnectDate() {
		return connectDate;
	}
	public void setConnectDate(String connectDate) {
		this.connectDate = connectDate;
	}
	public String getConnects() {
		return connects;
	}
	public void setConnects(String connects) {
		this.connects = connects;
	}
	public String getuAcc() {
		return uAcc;
	}
	public void setuAcc(String uAcc) {
		this.uAcc = uAcc;
	}
	public String getLastConnect() {
		return lastConnect;
	}
	public void setLastConnect(String lastConnect) {
		this.lastConnect = lastConnect;
	}
	public String getConnectAttributes() {
		return connectAttributes;
	}
	public void setConnectAttributes(String connectAttributes) {
		this.connectAttributes = connectAttributes;
	}
	public String getRevokeDate() {
		return revokeDate;
	}
	public void setRevokeDate(String revokeDate) {
		this.revokeDate = revokeDate;
	}
	public String getResumeDate() {
		return resumeDate;
	}
	public void setResumeDate(String resumeDate) {
		this.resumeDate = resumeDate;
	}
}
