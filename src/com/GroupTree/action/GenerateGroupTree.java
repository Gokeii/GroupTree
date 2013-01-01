package com.GroupTree.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.GroupTree.dao.GroupDAO;
import com.GroupTree.data.GroupNode;
import com.GroupTree.model.Group;
import com.opensymphony.xwork2.ActionSupport;

public class GenerateGroupTree extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GroupDAO groupDAO;
	private String username;
	private String password;
	private List<GroupNode> root = new ArrayList<GroupNode>();
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<GroupNode> getRoot(){
		return root;
	}
	
	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
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
//		result = "\n{ id:1, pId:0, name:\"父节点1 - 展开\", open:true},\n" +
//			"{ id:11, pId:1, name:\"父节点11 - 折叠\"},\n" +
//			"{ id:111, pId:11, name:\"叶子节点111\"},\n" +
//			"{ id:112, pId:11, name:\"叶子节点112\"},\n" +
//			"{ id:113, pId:11, name:\"叶子节点113\"},\n" +
//			"{ id:114, pId:11, name:\"叶子节点114\"},\n" +
//			"{ id:12, pId:1, name:\"父节点12 - 折叠\"},\n" +
//			"{ id:121, pId:12, name:\"叶子节点121\"},\n" +
//			"{ id:122, pId:12, name:\"叶子节点122\"},\n" +
//			"{ id:123, pId:12, name:\"叶子节点123\"},\n" +
//			"{ id:124, pId:12, name:\"叶子节点124\"},\n" +
//			"{ id:13, pId:1, name:\"父节点13 - 没有子节点\", isParent:true},\n" +
//			"{ id:2, pId:0, name:\"父节点2 - 折叠\"},\n" +
//			"{ id:21, pId:2, name:\"父节点21 - 展开\", open:true},\n" +
//			"{ id:211, pId:21, name:\"叶子节点211\"},\n" +
//			"{ id:212, pId:21, name:\"叶子节点212\"},\n" +
//			"{ id:213, pId:21, name:\"叶子节点213\"},\n" +
//			"{ id:214, pId:21, name:\"叶子节点214\"},\n" +
//			"{ id:22, pId:2, name:\"父节点22 - 折叠\"},\n" +
//			"{ id:221, pId:22, name:\"叶子节点221\"},\n" +
//			"{ id:222, pId:22, name:\"叶子节点222\"},\n" +
//			"{ id:223, pId:22, name:\"叶子节点223\"},\n" +
//			"{ id:224, pId:22, name:\"叶子节点224\"},\n" +
//			"{ id:23, pId:2, name:\"父节点23 - 折叠\"},\n" +
//			"{ id:231, pId:23, name:\"叶子节点231\"},\n" +
//			"{ id:232, pId:23, name:\"叶子节点232\"},\n" +
//			"{ id:233, pId:23, name:\"叶子节点233\"},\n" +
//			"{ id:234, pId:23, name:\"叶子节点234\"},\n" +
//			"{ id:3, pId:0, name:\"父节点3 - 没有子节点\", isParent:true},\n" +
//			"{ id:33333, pId:1, name:\"shishi\"}\n";
		generateResult();
		return "success";
	}
	protected void generateResult(){
		List<Group> groups = groupDAO.getAllGroups(username, password);
		result="\n";
		for(Group group : groups){
			result += "{ id:"+group.getId()+", pId:"+group.getSuperiorGroup()+", name:'"+group.getId()+"', isParent:true},\n";
		}
		//result += "\b";
	}
	public void generate(){
		List<Group> groups = groupDAO.getAllGroups(username, password);
		
		
		Map<String, GroupNode> map = new HashMap<String, GroupNode>();
		for(Group group : groups){
			GroupNode groupNode = new GroupNode();
			groupNode.setId(group.getId());
			map.put(groupNode.getId(), groupNode);
		}
		
		for(Group group : groups){
			GroupNode groupNode = map.get(group.getId());
			GroupNode superiorNode = map.get(group.getSuperiorGroup());
			if(superiorNode != null){
				superiorNode.getSubGroups().add(groupNode);
				groupNode.setSuperiorGroup(superiorNode);
			}
			else{
				root.add(groupNode);
			}
		}
		
	}
	
	
	
}
