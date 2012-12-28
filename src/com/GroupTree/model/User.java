package com.GroupTree.model;

import java.util.List;

public class User {
	private String id;
	private String name;
	private String created;
	private String defaultGroup;
	private String passDate;
	private int passInterval;
	private String phraseDate;
	private String attributes;
	private String revokeDate;
	private String resumeDate;
	private String lastAccess;
	private String classAuthorization;
	private String installationData;
	private String modelName;
	private String logonAllowedDays;
	private String logonAllowedTime;
	private List<ConnectedGroup> connectedGroups;
	private String securityLevel;
	private String categoryAuthorization;
	private String securityLabel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getDefaultGroup() {
		return defaultGroup;
	}
	public void setDefaultGroup(String defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
	public String getPassDate() {
		return passDate;
	}
	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}
	public int getPassInterval() {
		return passInterval;
	}
	public void setPassInterval(int passInterval) {
		this.passInterval = passInterval;
	}
	public String getPhraseDate() {
		return phraseDate;
	}
	public void setPhraseDate(String phraseDate) {
		this.phraseDate = phraseDate;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
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
	public String getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}
	public String getClassAuthorization() {
		return classAuthorization;
	}
	public void setClassAuthorization(String classAuthorization) {
		this.classAuthorization = classAuthorization;
	}
	public String getInstallationData() {
		return installationData;
	}
	public void setInstallationData(String installationData) {
		this.installationData = installationData;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getLogonAllowedDays() {
		return logonAllowedDays;
	}
	public void setLogonAllowedDays(String logonAllowedDays) {
		this.logonAllowedDays = logonAllowedDays;
	}
	public String getLogonAllowedTime() {
		return logonAllowedTime;
	}
	public void setLogonAllowedTime(String logonAllowedTime) {
		this.logonAllowedTime = logonAllowedTime;
	}
	public List<ConnectedGroup> getConnectedGroups() {
		return connectedGroups;
	}
	public void setConnectedGroups(List<ConnectedGroup> connectedGroups) {
		this.connectedGroups = connectedGroups;
	}
	public String getSecurityLevel() {
		return securityLevel;
	}
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	public String getCategoryAuthorization() {
		return categoryAuthorization;
	}
	public void setCategoryAuthorization(String categoryAuthorization) {
		this.categoryAuthorization = categoryAuthorization;
	}
	public String getSecurityLabel() {
		return securityLabel;
	}
	public void setSecurityLabel(String securityLabel) {
		this.securityLabel = securityLabel;
	}
}
