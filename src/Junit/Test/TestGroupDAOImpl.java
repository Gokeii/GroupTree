package Junit.Test;

import java.util.ArrayList;
import java.util.List;

import com.GroupTree.dao.GroupDAO;
import com.GroupTree.model.ConnectedUser;
import com.GroupTree.model.Group;

public class TestGroupDAOImpl implements GroupDAO {

	@Override
	public Group getByID(String ID, String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("username:"+username);
		Group group = new Group();
		group.setCreated("created");
		group.setId(ID);
		group.setInstallationData("installationData");
		group.setModelDataset("fdsafda");
		group.setOwner("owner");
		group.setSuperiorGroup("aaaa");
		group.setTermUAcc("fdsfa");
		List<ConnectedUser> users = new ArrayList<ConnectedUser>();
		for(int i = 0; i<5; i++){
			ConnectedUser u = new ConnectedUser();
			u.setId("id"+i);
			u.setAccess("access" + i);
			u.setAccessCount("accessCount"+ i );
			users.add(u);
		}	
		group.setUsers(users);
		return group;
	}

	@Override
	public List<Group> getAllGroups(String username, String password) {
		// TODO Auto-generated method stub
		List<Group> list = new ArrayList<Group>();
		
		Group g1 = new Group();
		g1.setId("1");
		list.add(g1);
		
		Group g2 = new Group();
		g2.setId("2");
		g2.setSuperiorGroup("1");
		list.add(g2);
		
		Group g3 = new Group();
		g3.setId("3");
		g3.setSuperiorGroup("1");
		list.add(g3);
		
		Group g4 = new Group();
		g4.setId("4");
		g4.setSuperiorGroup("1");
		list.add(g4);
		
		Group g5 = new Group();
		g5.setId("5");
		g5.setSuperiorGroup("2");
		list.add(g5);
		
		Group g6 = new Group();
		g6.setId("6");
		g6.setSuperiorGroup("2");
		list.add(g6);
		
		return list;
	}

}
