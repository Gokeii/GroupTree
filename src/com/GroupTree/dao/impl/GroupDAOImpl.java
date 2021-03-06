package com.GroupTree.dao.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.GroupTree.dao.GroupDAO;
import com.GroupTree.model.ConnectedUser;
import com.GroupTree.model.Group;
import com.GroupTree.utils.MainframeCommandEntry;

public class GroupDAOImpl implements GroupDAO{
	
	public Group getByID(String ID, String username, String password) {
		Group group = new Group();
		try {
			String info = MainframeCommandEntry.enterCommand(username, password, "LG "+ID);
			System.out.println("=getByID=======info=========");
			System.out.println(info);
			if (info.contains("NAME NOT FOUND") || info.contains("NOT AUTHORIZED")) return null;
			String[] infoLines = info.split("\n");
			String nowLine;
			int lineNo = 2;
			
			//set id
			String[] tmp = infoLines[lineNo].split(" ");
			group.setId(tmp[tmp.length-1]);
			lineNo++;
			//set superior group
			int i = 1;
			nowLine = infoLines[lineNo];
			while (nowLine.charAt(i) != '=') i++;
			int j = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			group.setSuperiorGroup(nowLine.substring(i+1, j));
			//set owner
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			j = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			group.setOwner(nowLine.substring(i+1, j));
			//set created
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			j = i+1;
			while (j < nowLine.length() && nowLine.charAt(j) != ' ') j++;
			if (j == nowLine.length())
				group.setCreated(nowLine.substring(i+1));
			else 
				group.setCreated(nowLine.substring(i+1, j));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set installation data
			i = 1;
			while (nowLine.charAt(i) == ' ') i++;
			group.setInstallationData(nowLine.substring(i));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set model data set
			i = 1;
			while (nowLine.charAt(i) == ' ') i++;
			group.setModelDataset(nowLine.substring(i));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set termuacc
			i = 1;	
			while (nowLine.charAt(i) == ' ') i++;
			group.setTermUAcc(nowLine.substring(i));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set subgroups
			List<String> subGroups = new LinkedList<String>();
			if (! nowLine.contains("NO SUBGROUPS")) {
				i = 1;
				while (nowLine.charAt(i) != '=') i++;
				tmp = nowLine.substring(i+2).split(" ");
				for (int k = 0; k < tmp.length; k++)
					if (!tmp[k].equals(""))
						subGroups.add(tmp[k]);
				lineNo++;
				nowLine = infoLines[lineNo];
				while (!nowLine.contains("USER(S)=") && !nowLine.contains("NO USERS")) {
					tmp = nowLine.split(" ");
					for (int k = 0; k < tmp.length; k++)
						if (!tmp[k].equals(""))
							subGroups.add(tmp[k]);
					lineNo++;
					nowLine = infoLines[lineNo];
				}
			}
			else {
				lineNo++;
				nowLine = infoLines[lineNo];
			}
			group.setSubGroups(subGroups);
			
			//set users
			List<ConnectedUser> users = new LinkedList<ConnectedUser>();
			
			if (! nowLine.contains("NO USERS")) {
				lineNo++;
				nowLine = infoLines[lineNo];
				ConnectedUser connectedUser;
				while (!nowLine.contains("READY")) {
					connectedUser = new ConnectedUser();
					tmp = nowLine.substring(1).split(" ");
					i = 0;
					while (tmp[i].equals("")) i++;
					connectedUser.setId(tmp[i]);
					i++;
					while (tmp[i].equals("")) i++;
					connectedUser.setAccess(tmp[i]);
					i++;
					while (tmp[i].equals("")) i++;
					connectedUser.setAccessCount(tmp[i]);
					i++;
					while (tmp[i].equals("")) i++;
					connectedUser.setUniversalAccess(tmp[i]);
					
					lineNo++;
					nowLine = infoLines[lineNo];
					
					i = 1;
					while (nowLine.charAt(i) != '=') i++;
					j = i+1;
					while (nowLine.charAt(j) != ' ') j++;
					connectedUser.setConnectAttributes(nowLine.substring(i+1, j));
					
					lineNo++;
					nowLine = infoLines[lineNo];
					
					i = 1;
					while (nowLine.charAt(i) != '=') i++;
					j = i+1;
					while (nowLine.charAt(j) != ' ') j++;
					connectedUser.setRevokeDate(nowLine.substring(i+1, j));
					i = j;
					while (nowLine.charAt(i) != '=') i++;
					j = i+1;
					while (nowLine.charAt(j) != ' ') j++;
					connectedUser.setResumeDate(nowLine.substring(i+1, j));
					users.add(connectedUser);
					
					lineNo++;
					nowLine = infoLines[lineNo];
				}
			}
			group.setUsers(users);
			
			/*System.out.println(group.getId());
			System.out.println(group.getSuperiorGroup());
			System.out.println(group.getOwner());
			System.out.println(group.getCreated());
			System.out.println(group.getInstallationData());
			System.out.println(group.getModelDataset());
			System.out.println(group.getTermUAcc());
			for (int k = 0; k<subGroups.size(); k++)
					System.out.print(subGroups.get(k) + " ");
			for (int k = 0; k<users.size(); k++)
				System.out.println(users.get(k).getId() + " " + users.get(k).getAccess() + " " + users.get(k).getAccessCount() + " " + users.get(k).getConnectAttributes() + " " + users.get(k).getUniversalAccess() + " " + users.get(k).getRevokeDate() + " " + users.get(k).getResumeDate());*/
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
			Map<Integer,String> groupPath= new HashMap<Integer ,String>();
			String[] infoLines = info.split("\n");
			int position = 5;
			int id;
			String groupID;
			Group group;
			
			while (position < infoLines.length) {
				group = new Group();
				String[] temp= infoLines[position].split(" ");
				if(temp[5].length() != 0 ){
					id=Integer.parseInt((String)temp[5]);
					groupID=(String)temp[id+7];	
					if(id!=1)
						group.setSuperiorGroup(groupPath.get(id-1));
					groupPath.put(id, groupID);
					group.setId(groupID);
					groups.add(group);
				}
				position=position+2;
				if((position-3) % 53 == 0 ) {
					position++;
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
	}
		return groups;
			
		
			/*		for (int i = 2; i < infoLines.length; i++)
				System.out.println("infoline"+i+"---"+infoLines[i]);
			
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
			*/
/*			for (int j = 0; j < groups.size(); j++)
				System.out.println(j+": "+groups.get(j).getId()+"parent: "+groups.get(j).getSuperiorGroup());*/


	}
}
