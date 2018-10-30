package cz.cuni.mff.auv.planners;

import java.io.File;
import java.util.List;

import org.junit.Test;

import cz.cuni.mff.auv.domain.Effector;
import cz.cuni.mff.auv.problem.Problem;
import cz.cuni.mff.jpddl.PDDLStringEffector;
import cz.cuni.mff.jpddl.tools.planners.Lama;

public class Test01_LamaTest {

	@Test
	public void test() {
		Problem problem = new Problem();
		
		File domainFile = problem.getDomain().getDomainFile();
		File problemFile = new File("auv-problem.pddl");
		problem.createProblemFile(problemFile, problem.getState());
		
		Lama lama = new Lama();
		
		List<PDDLStringEffector> lamaPlan = lama.plan(domainFile, problemFile);
		
		if (lamaPlan == null) {
			System.out.println("NO PLAN FOUND!");
		} else {
			System.out.println("PLAN FOUND!");
			Effector[] plan = Effector.toEffectors(lamaPlan.toArray(new PDDLStringEffector[0]));
			boolean first = true;
			for (Effector eff : plan) {
				if (first) first = false;
				else System.out.print(" -> ");
				System.out.print(eff.toEffector());
			}
		}
		
		problemFile.delete();
		
		System.out.println("--// TEST FINISHED //--");
	}
	
	public static void main(String[] args) {
		Test01_LamaTest test = new Test01_LamaTest();
		
		test.test();
	}
	
}
