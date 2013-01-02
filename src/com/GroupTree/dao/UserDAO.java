package com.GroupTree.dao;

import com.GroupTree.model.User;

public interface UserDAO {

	public User getByID(String ID, String username, String password);
}
