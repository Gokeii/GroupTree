package com.GroupTree.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.GroupTree.dao.GroupDAO;
import com.GroupTree.dao.UserDAO;
import com.GroupTree.data.GroupTreeNode;
import com.GroupTree.model.Group;
import com.opensymphony.xwork2.ActionSupport;

public class GroupAndUser extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//DAO
	private UserDAO userDAO;
	private GroupDAO groupDAO;
	//user or group name
	private String name;
	//user name and password
	private String username;
	private String password;
	//ajax return
	private Group group;	
	private List<GroupTreeNode> groupTree;
	
	//setters
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	public void setName(String name) {
		this.name = name;
	}
	//getters
	public Group getGroup(){
		return group;
	}
	public List<GroupTreeNode> getGroupTree() {
		return groupTree;
	}
	
	public String getUserInfo(){
		
		//inputStream = new ByteArrayInputStream((name+"-user").getBytes());
		return "success";
	}
	
	public String getGroupInfo(){
		group = groupDAO.getByID(name, username, password);
		return "success";
	}
	
	public String getTreeInfo(){
		generateTree();
		return "tree";
	}
	
	protected void generateTree(){
		List<Group> groups = groupDAO.getAllGroups(username, password);
		this.groupTree = new ArrayList<GroupTreeNode>();
		for(Group group : groups){
			GroupTreeNode gtn = new GroupTreeNode();
			gtn.setId(group.getId());
			gtn.setName(group.getId());
			gtn.setpId(group.getSuperiorGroup());
			groupTree.add(gtn);
		}
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.username = (String) request.getSession().getAttribute("username");
		this.password = (String) request.getSession().getAttribute("password");
	}

}
