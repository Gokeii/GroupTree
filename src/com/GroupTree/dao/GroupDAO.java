package com.GroupTree.dao;

import java.util.List;

import com.GroupTree.model.Group;

public interface GroupDAO {
	
	public Group getByID(String ID, String username, String password);
	public List<Group> getAllGroups(String username, String password);
}
