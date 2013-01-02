package Junit.Test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;

import com.GroupTree.action.HomeAction;
import com.GroupTree.data.GroupTreeNode;
import com.GroupTree.model.Group;

public class TestForGenerateTree {
	static AbstractApplicationContext ctx;
	static HomeAction generateGroupTree;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		generateGroupTree = (GenerateGroupTree) ctx.getBean("generateGroupTree");
	}

	@Test
	public void test() {
		//generateGroupTree.generate();
//		GroupNode gn = generateGroupTree.getRoot().get(0);
//		System.out.println("root id:" + gn.getId());
//		for(GroupNode g : gn.getSubGroups())
//			System.out.print(g.getId()+" ");
//		System.out.println();
//		for(GroupNode g : gn.getSubGroups().get(0).getSubGroups())
//			System.out.print(g.getId()+" ");
	}
	@Test public void test2(){
		Group group = new Group();
		group.setCreated("created");
		group.setId("123");
		group.setInstallationData("installationData");
		group.setModelDataset("fdsafda");
		group.setOwner("owner");
		group.setSuperiorGroup("aaaa");
		group.setTermUAcc("fdsfa");
		JSONObject json=JSONObject.fromObject(group);
		System.out.println(json.toString());
	}
	@Test public void test3(){
		List<GroupTreeNode> gl = new ArrayList<GroupTreeNode>();
		for(int i = 0; i<5; i++){
			GroupTreeNode gtn = new GroupTreeNode();
			gtn.setId(i+"");
			gl.add(gtn);
		}
		JSONArray json=JSONArray.fromObject(gl);
		System.out.println(json.toString());
	}

}
