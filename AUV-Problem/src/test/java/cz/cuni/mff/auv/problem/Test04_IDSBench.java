package cz.cuni.mff.auv.problem;

import java.util.List;

import org.junit.Test;

import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.tools.search.bench.IDSBench;

public class Test04_IDSBench {

	@Test
	public void test() {
		int minDepth = 1;
		int maxDepth = 10;
		System.out.println("Using Iterative Deepening Search to solve the problem, max-limit = " + maxDepth + "...");
		
		Problem problem = new Problem();
		IDSBench ids = new IDSBench(problem);
		
		List<PDDLEffector> result = ids.search(minDepth, maxDepth);
		
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
		Test04_IDSBench test = new Test04_IDSBench();
		test.test();
	}
	
}
