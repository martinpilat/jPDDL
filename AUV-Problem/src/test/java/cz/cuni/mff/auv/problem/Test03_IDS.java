package cz.cuni.mff.auv.problem;

import java.util.List;

import org.junit.Test;

import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.tools.search.IDS;

public class Test03_IDS {

	@Test
	public void test() {
		int maxDepth = 20;
		System.out.println("Using Iterative Deepening Search to solve the problem, max-limit = " + maxDepth + "...");
		
		Problem problem = new Problem();
		IDS ids = new IDS(problem);
		
		List<PDDLEffector> result = ids.search(maxDepth);
		
		if (result == null) {
			System.out.println("NO plan of up-to " + maxDepth + " actions solving the problem found :-(");
		} else {
			System.out.println("Plan of " + result.size() + " actions found!");
			for (PDDLEffector action : result) {
				System.out.println(action.toEffector());
			}
		}
		
		System.out.println("--// TEST OK //--");
		
	}
	
	public static void main(String[] args) {
		Test03_IDS test = new Test03_IDS();
		test.test();
	}
	
}
