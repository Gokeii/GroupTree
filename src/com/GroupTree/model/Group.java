package com.GroupTree.model;

import java.util.List;

public class Group {	
	private String id;
	private String superiorGroup;
	private String owner;
	private String created;
	private String installationData;
	private String modelDataset;
	private String termUAcc;
	private List<String> subGroups;
	private List<ConnectedUser> users;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSuperiorGroup() {
		return superiorGroup;
	}
	public void setSuperiorGroup(String superiorGroup) {
		this.superiorGroup = superiorGroup;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getInstallationData() {
		return installationData;
	}
	public void setInstallationData(String installationData) {
		this.installationData = installationData;
	}
	public String getModelDataset() {
		return modelDataset;
	}
	public void setModelDataset(String modelDataset) {
		this.modelDataset = modelDataset;
	}
	public String getTermUAcc() {
		return termUAcc;
	}
	public void setTermUAcc(String termUAcc) {
		this.termUAcc = termUAcc;
	}
	public List<String> getSubGroups() {
		return subGroups;
	}
	public void setSubGroups(List<String> subGroups) {
		this.subGroups = subGroups;
	}
	public List<ConnectedUser> getUsers() {
		return users;
	}
	public void setUsers(List<ConnectedUser> users) {
		this.users = users;
	}
}
