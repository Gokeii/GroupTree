package com.GroupTree.dao.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import com.GroupTree.dao.GroupDAO;
import com.GroupTree.model.Group;
import com.GroupTree.utils.MainframeCommandEntry;

public class GroupDAOImpl implements GroupDAO{
	
	public Group getByID(String ID, String username, String password) {
		Group group = new Group();
		try {
			String info = MainframeCommandEntry.enterCommand(username, password, "LU "+ID);
			System.out.println(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return group;
	}

	public List<Group> getAllGroups(String username, String password) {
		List<Group> groups = new LinkedList<Group>();
		try {
			//get all available groups
			String command = "SEARCH CLASS(GROUP)";
			String info = MainframeCommandEntry.enterCommand(username, password, command);
			
			//get 'superior group' attribute of all available groups
			List<String> groupsName = new LinkedList<String>();
			String[] infoLines = info.split("\n");
			for (int i = 2; i < infoLines.length; i++)
				if (!infoLines[i].contains("READY"))
					groupsName.add(infoLines[i].substring(1));
				else break;
			
			String commands = "";
			for (int i = 0; i < groupsName.size(); i++)
				commands += "LG " + groupsName.get(i) + "\n  ";
			
			String infos = MainframeCommandEntry.enterCommand(username, password, commands);
			String[] infosLines = infos.split("\n");
			int i = 1;
			Group group;
			while (i < infosLines.length) {
				if (infosLines[i].contains("READY")) {
					i++;
					continue;
				}
				if (infosLines[i].contains("END"))
					break;
				i++;
				group = new Group();
				
				String[] tmp = infosLines[i].split(" ");
				group.setId(tmp[tmp.length-1]);
				i++;
				tmp = infosLines[i].split(" ");
				for (int j = 0; j < tmp.length; j++)
					if (tmp[j].contains("GROUP")) {
						group.setSuperiorGroup(tmp[j].split("=")[1]);
						break;
					}
				groups.add(group);
				
				while (i < infosLines.length && !infosLines[i].contains("READY")) i++;
				i++;
			}
			
/*			for (int j = 0; j < groups.size(); j++)
				System.out.println(j+": "+groups.get(j).getId()+"parent: "+groups.get(j).getSuperiorGroup());*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		return groups;
	}
}
