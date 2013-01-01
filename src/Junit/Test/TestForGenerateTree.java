package Junit.Test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.GroupTree.action.GenerateGroupTree;
import com.GroupTree.data.GroupNode;

public class TestForGenerateTree {
	static AbstractApplicationContext ctx;
	static GenerateGroupTree generateGroupTree;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		generateGroupTree = (GenerateGroupTree) ctx.getBean("generateGroupTree");
	}

	@Test
	public void test() {
		generateGroupTree.generate();
		GroupNode gn = generateGroupTree.getRoot().get(0);
		System.out.println("root id:" + gn.getId());
		for(GroupNode g : gn.getSubGroups())
			System.out.print(g.getId()+" ");
		System.out.println();
		for(GroupNode g : gn.getSubGroups().get(0).getSubGroups())
			System.out.print(g.getId()+" ");
	}

}
