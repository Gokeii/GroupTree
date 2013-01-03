package com.GroupTree.JUnit;

import org.junit.Test;

import com.GroupTree.dao.impl.GroupDAOImpl;
import com.GroupTree.dao.impl.UserDAOImpl;

public class TestGroupDAOImpl {

	@Test
	public void testGetAllGroups() {
		GroupDAOImpl groupDAO = new GroupDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
//		groupDAO.getAllGroups("s992025", "302010");
		String username = "s992025";
		String password = "302010";
		if (groupDAO.getByID("KK", username, password) == null) System.out.println("null!");
		if (userDAO.getByID("S992025", username, password) == null) System.out.println("null!");
		
	}
}
