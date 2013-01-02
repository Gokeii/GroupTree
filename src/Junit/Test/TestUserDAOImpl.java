package Junit.Test;

import java.util.ArrayList;
import java.util.List;

import com.GroupTree.dao.UserDAO;
import com.GroupTree.model.ConnectedGroup;
import com.GroupTree.model.User;

public class TestUserDAOImpl implements UserDAO {

	@Override
	public User getByID(String ID, String username, String password) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setId(ID);
		u.setDefaultGroup("defaultGroup");
		u.setLogonAllowedDays("logonAllowedDays");
		List<ConnectedGroup> groups = new ArrayList<ConnectedGroup>();
		for(int i = 0; i < 5; i++){
			ConnectedGroup cg = new ConnectedGroup();
			cg.setId("connectedGroup"+i);
			cg.setConnectOwner("owner"+i);
			groups.add(cg);
		}
		u.setConnectedGroups(groups);
		return u;
	}

}
