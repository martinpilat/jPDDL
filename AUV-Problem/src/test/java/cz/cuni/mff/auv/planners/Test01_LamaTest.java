package cz.cuni.mff.auv.planners;

import java.io.File;
import java.util.List;

import org.junit.Test;

import cz.cuni.mff.auv.domain.Effector;
import cz.cuni.mff.auv.problem.Problem;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker.PlanCheckerResult;
import cz.cuni.mff.jpddl.tools.validators.PlanTester;
import cz.cuni.mff.jpddl.tools.validators.PlanTester.PlanTesterResult;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;

public class Test01_LamaTest {

	@Test
	public void test() {
		Problem problem = new Problem();
		
		File domainFile = problem.getDomain().getDomainPureFile();
		File problemFile = new File("auv-problem.pddl");
		problem.createProblemFile(problemFile, problem.getState());
		
		Lama lama = new Lama();
		
		List<PDDLStringInstance> lamaPlan = lama.plan(domainFile, problemFile);
		
		if (lamaPlan == null) {
			System.out.println("NO PLAN FOUND!");
		} else {
			System.out.println("PLAN FOUND!");
			Effector[] plan = Effector.toEffectors(lamaPlan.toArray(new PDDLStringInstance[0]));
			boolean first = true;
			for (Effector eff : plan) {
				if (first) first = false;
				else System.out.print(" -> ");
				System.out.print(eff.toEffector());
			}
			
			// CHECK THE PLAN
			PlanChecker planChecker = new PlanChecker(problem.getDomain());
			PlanCheckerResult planCheckerResult = planChecker.check(problem.getState(), plan);
			
			if (planCheckerResult.valid) {
				System.out.println("PLAN CHECKER: plan is valid!");
			} else {
				System.out.println("PLAN CHECKER: plan is INvalid! Last executable action index is " + planCheckerResult.lastExecutableEffectorIndex + ".");
			}
			
			// TEST THE PLAN
			PlanTester planTester = new PlanTester(problem.getDomain(), problem.getApplicables());
			SafeStates safeStates = new SafeStates(problem.getDomain(), new File("safe_states"));
			PlanTesterResult planTesterResult = planTester.check(problem.getState(), safeStates, plan);
			if (planTesterResult.valid) {
				System.out.println("PLAN TESTER: plan is valid!");
			} else {
				System.out.println("PLAN TESTER: plan is INvalid! Last executable action index is " + planTesterResult.lastExecutableEffectorIndex + ".");
				System.out.println("PLAN TESTER: Last safe state index is " + planTesterResult.lastSafeStateIndex + ".");
				System.out.println("SAFE STATE");
				for (int i = 0; i < planTesterResult.lastSafeStateIndex; ++i) {
					planTesterResult.plan[i].apply(planTesterResult.state);
					if (planTesterResult.events[i] != null) planTesterResult.events[i].apply(planTesterResult.state);
				}
				planTesterResult.state.dump();
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
