package com.GroupTree.dao.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.GroupTree.dao.UserDAO;
import com.GroupTree.model.ConnectedGroup;
import com.GroupTree.model.User;
import com.GroupTree.utils.MainframeCommandEntry;

public class UserDAOImpl implements UserDAO{

	public User getByID(String ID, String username, String password) {
		User user = new User();
		try {
			String info = MainframeCommandEntry.enterCommand(username, password, "LU "+ID);
			/*System.out.println(info);*/
			if (info.contains("UNABLE TO LOCATE")) return null;
			String[] infoLines = info.split("\n");
			int lineNo = 2;
			String nowLine = infoLines[lineNo];
			
			//set id
			int i  = 1;
			while (nowLine.charAt(i) != '=') i++;
			int j  = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setId(nowLine.substring(i+1, j));
			//set name
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			j = nowLine.indexOf("OWNER")-1;
			while (nowLine.charAt(j) == ' ') j--;
			user.setName(nowLine.substring(i+1, j+1));
			//set owner
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			j = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setOwner(nowLine.substring(i+1, j));
			//set created
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			j = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setCreated(nowLine.substring(i+1, j));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set default-group
			i = 1;
			while (nowLine.charAt(i) != '=') i++;
			j  = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setDefaultGroup(nowLine.substring(i+1, j));
			//set passdate
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			j = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setPassDate(nowLine.substring(i+1, j));
			//set pass-interval
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			j = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setPassInterval(nowLine.substring(i+1, j));
			//set phrasedate
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			j = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setPhraseDate(nowLine.substring(i+1, j));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set attributes
			i = 1;
			while (nowLine.charAt(i) != '=') i++;
			j  = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setAttributes(nowLine.substring(i+1, j));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set revoke date
			i = 1;
			while (nowLine.charAt(i) != '=') i++;
			j  = nowLine.indexOf("RESUME")-1;
			while (nowLine.charAt(j) == ' ') j--;
			user.setRevokeDate(nowLine.substring(i+1, j+1));
			//set resume date
			i = j;
			while (nowLine.charAt(i) != '=') i++;
			user.setResumeDate(nowLine.substring(i+1));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set last-access
			i = 1;
			while (nowLine.charAt(i) != '=') i++;
			user.setLastAccess(nowLine.substring(i+1));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set class authorizations
			i = 1;
			while (nowLine.charAt(i) != '=') i++;
			user.setClassAuthorization(nowLine.substring(i+1));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set installation data
			i = 1;
			while (nowLine.charAt(i) == ' ') i++;
			user.setInstallationData(nowLine.substring(i));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set model name
			i = 1;
			while (nowLine.charAt(i) == ' ') i++;
			user.setModelName(nowLine.substring(i));
			
			lineNo += 3;
			nowLine = infoLines[lineNo];
			//set logon allowed days
			i = 1;
			while (nowLine.charAt(i) == ' ') i++;
			j = i+1;
			while (nowLine.charAt(j) != ' ') j++;
			user.setLogonAllowedDays(nowLine.substring(i, j));
			//set logon allowed time
			i = j;
			while (nowLine.charAt(i) == ' ') i++;
			user.setLogonAllowedTime(nowLine.substring(i));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set connected group
			List<ConnectedGroup> connectedGroups = new LinkedList<ConnectedGroup>();
			ConnectedGroup connectedGroup;
			while (!nowLine.contains("SECURITY-LEVEL")) {
				connectedGroup = new ConnectedGroup();
				//set id
				i = 1;
				while (nowLine.charAt(i) != '=') i++;
				j = i+1;
				while (nowLine.charAt(j) != ' ') j++;
				connectedGroup.setId(nowLine.substring(i+1, j));
				//set auth
				i = j;
				while (nowLine.charAt(i) != '=') i++;
				j = i+1;
				while (nowLine.charAt(j) != ' ') j++;
				connectedGroup.setAuth(nowLine.substring(i+1, j));
				//set connect-owner
				i = j;
				while (nowLine.charAt(i) != '=') i++;
				j = i+1;
				while (nowLine.charAt(j) != ' ') j++;
				connectedGroup.setConnectOwner(nowLine.substring(i+1, j));
				//set connect-date
				i = j;
				while (nowLine.charAt(i) != '=') i++;
				connectedGroup.setConnectDate(nowLine.substring(i+1));
				
				lineNo++;
				nowLine = infoLines[lineNo];
				//set connects
				i = 1;
				while (nowLine.charAt(i) != '=') i++;
				i++;
				while (nowLine.charAt(i) == ' ') i++;
				j = i+1;
				while (nowLine.charAt(j) != ' ') j++;
				connectedGroup.setConnects(nowLine.substring(i, j));
				//set uacc
				i = j;
				while (nowLine.charAt(i) != '=') i++;
				j = i+1;
				while (nowLine.charAt(j) != ' ') j++;
				connectedGroup.setuAcc(nowLine.substring(i+1, j));
				//set last-connect
				i = j;
				while (nowLine.charAt(i) != '=') i++;
				connectedGroup.setLastConnect(nowLine.substring(i+1));
				
				lineNo++;
				nowLine = infoLines[lineNo];
				//set connect attributes
				i = 1;
				while (nowLine.charAt(i) != '=') i++;
				j = i+1;
				while (nowLine.charAt(j) != ' ') j++;
				connectedGroup.setConnectAttributes(nowLine.substring(i+1, j));
				
				lineNo++;
				nowLine = infoLines[lineNo];
				//set revoke date
				i = 1;
				while (nowLine.charAt(i) != '=') i++;
				j = i+1;
				while (nowLine.charAt(j) != ' ') j++;
				connectedGroup.setRevokeDate(nowLine.substring(i+1, j));
				//set resume date
				i = j;
				while (nowLine.charAt(i) != '=') i++;
				j = i+1;
				while (nowLine.charAt(j) != ' ') j++;
				connectedGroup.setResumeDate(nowLine.substring(i+1, j));
				
				connectedGroups.add(connectedGroup);
				
				lineNo++;
				nowLine = infoLines[lineNo];
			}
			user.setConnectedGroups(connectedGroups);
			
			//set security-level
			i = 1;
			while (nowLine.charAt(i) != '=') i++;
			user.setSecurityLevel(nowLine.substring(i+1));
			
			lineNo += 2;
			nowLine = infoLines[lineNo];
			//set category-authorization
			i = 1;
			while (nowLine.charAt(i) == ' ') i++;
			user.setCategoryAuthorization(nowLine.substring(i));
			
			lineNo++;
			nowLine = infoLines[lineNo];
			//set security-level
			i = 1;
			while (nowLine.charAt(i) != '=') i++;
			user.setSecurityLabel(nowLine.substring(i+1));
			
			/*System.out.println();
			System.out.println(user.getId());
			System.out.println(user.getName());
			System.out.println(user.getOwner());
			System.out.println(user.getCreated());
			System.out.println(user.getDefaultGroup());
			System.out.println(user.getPassDate());
			System.out.println(user.getPassInterval());
			System.out.println(user.getPhraseDate());
			System.out.println(user.getAttributes());
			System.out.println(user.getRevokeDate());
			System.out.println(user.getResumeDate());
			System.out.println(user.getLastAccess());
			System.out.println(user.getClassAuthorization());
			System.out.println(user.getInstallationData());
			System.out.println(user.getModelName());
			System.out.println(user.getLogonAllowedDays());
			System.out.println(user.getLogonAllowedTime());
			System.out.println(user.getSecurityLevel());
			System.out.println(user.getCategoryAuthorization());
			System.out.println(user.getSecurityLabel());
			for (int k = 0; k<connectedGroups.size(); k++)
				System.out.println(connectedGroups.get(k).getId() + " "+connectedGroups.get(k).getAuth()+" "+connectedGroups.get(k).getConnectOwner()+" "+connectedGroups.get(k).getConnectDate()+" "+connectedGroups.get(k).getConnects()+" "+connectedGroups.get(k).getuAcc()+" "+connectedGroups.get(k).getLastConnect()+" "+connectedGroups.get(k).getConnectAttributes()+" "+connectedGroups.get(k).getRevokeDate()+ " "+connectedGroups.get(k).getResumeDate());*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
