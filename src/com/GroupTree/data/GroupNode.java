package com.GroupTree.data;

import java.util.ArrayList;
import java.util.List;

public class GroupNode {
	private String id;
	private GroupNode superiorGroup;
//	private String owner;
//	private String created;
//	private String installationData;
//	private String modelDataset;
//	private String termUAcc;
	private List<GroupNode> subGroups = new ArrayList<GroupNode>();
//	private List<ConnectedUser> users;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public GroupNode getSuperiorGroup() {
		return superiorGroup;
	}
	public void setSuperiorGroup(GroupNode superiorGroup) {
		this.superiorGroup = superiorGroup;
	}
	public List<GroupNode> getSubGroups() {
		return subGroups;
	}
	public void setSubGroups(List<GroupNode> subGroups) {
		this.subGroups = subGroups;
	}
	
}
