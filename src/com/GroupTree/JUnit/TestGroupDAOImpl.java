package com.GroupTree.JUnit;

import org.junit.Test;

import com.GroupTree.dao.impl.GroupDAOImpl;

public class TestGroupDAOImpl {

	@Test
	public void testGetAllGroups() {
		GroupDAOImpl groupDAO = new GroupDAOImpl();
//		groupDAO.getAllGroups("s992025", "302010");
		String username = "s992025";
		String password = "302010";
		groupDAO.getByID("ADM992", username, password);
	}
}
