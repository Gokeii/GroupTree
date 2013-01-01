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
//		result = "\n{ id:1, pId:0, name:\"���ڵ�1 - չ��\", open:true},\n" +
//			"{ id:11, pId:1, name:\"���ڵ�11 - �۵�\"},\n" +
//			"{ id:111, pId:11, name:\"Ҷ�ӽڵ�111\"},\n" +
//			"{ id:112, pId:11, name:\"Ҷ�ӽڵ�112\"},\n" +
//			"{ id:113, pId:11, name:\"Ҷ�ӽڵ�113\"},\n" +
//			"{ id:114, pId:11, name:\"Ҷ�ӽڵ�114\"},\n" +
//			"{ id:12, pId:1, name:\"���ڵ�12 - �۵�\"},\n" +
//			"{ id:121, pId:12, name:\"Ҷ�ӽڵ�121\"},\n" +
//			"{ id:122, pId:12, name:\"Ҷ�ӽڵ�122\"},\n" +
//			"{ id:123, pId:12, name:\"Ҷ�ӽڵ�123\"},\n" +
//			"{ id:124, pId:12, name:\"Ҷ�ӽڵ�124\"},\n" +
//			"{ id:13, pId:1, name:\"���ڵ�13 - û���ӽڵ�\", isParent:true},\n" +
//			"{ id:2, pId:0, name:\"���ڵ�2 - �۵�\"},\n" +
//			"{ id:21, pId:2, name:\"���ڵ�21 - չ��\", open:true},\n" +
//			"{ id:211, pId:21, name:\"Ҷ�ӽڵ�211\"},\n" +
//			"{ id:212, pId:21, name:\"Ҷ�ӽڵ�212\"},\n" +
//			"{ id:213, pId:21, name:\"Ҷ�ӽڵ�213\"},\n" +
//			"{ id:214, pId:21, name:\"Ҷ�ӽڵ�214\"},\n" +
//			"{ id:22, pId:2, name:\"���ڵ�22 - �۵�\"},\n" +
//			"{ id:221, pId:22, name:\"Ҷ�ӽڵ�221\"},\n" +
//			"{ id:222, pId:22, name:\"Ҷ�ӽڵ�222\"},\n" +
//			"{ id:223, pId:22, name:\"Ҷ�ӽڵ�223\"},\n" +
//			"{ id:224, pId:22, name:\"Ҷ�ӽڵ�224\"},\n" +
//			"{ id:23, pId:2, name:\"���ڵ�23 - �۵�\"},\n" +
//			"{ id:231, pId:23, name:\"Ҷ�ӽڵ�231\"},\n" +
//			"{ id:232, pId:23, name:\"Ҷ�ӽڵ�232\"},\n" +
//			"{ id:233, pId:23, name:\"Ҷ�ӽڵ�233\"},\n" +
//			"{ id:234, pId:23, name:\"Ҷ�ӽڵ�234\"},\n" +
//			"{ id:3, pId:0, name:\"���ڵ�3 - û���ӽڵ�\", isParent:true},\n" +
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
