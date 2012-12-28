package com.GroupTree.model;

public class ConnectedUser {
	private String id;
	private String access;
	private String accessCount;
	private String universalAccess;
	private String connectAttributes;
	private String revokeDate;
	private String resumeDate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(String accessCount) {
		this.accessCount = accessCount;
	}
	public String getUniversalAccess() {
		return universalAccess;
	}
	public void setUniversalAccess(String universalAccess) {
		this.universalAccess = universalAccess;
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