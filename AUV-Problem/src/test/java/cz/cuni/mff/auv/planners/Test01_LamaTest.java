package cz.cuni.mff.auv.planners;

import java.io.File;
import java.util.List;

import org.junit.Test;

import cz.cuni.mff.auv.domain.Effector;
import cz.cuni.mff.auv.problem.Problem;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.search.bench.Timed;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker.PlanCheckerResult;
import cz.cuni.mff.jpddl.tools.validators.PlanTester;
import cz.cuni.mff.jpddl.tools.validators.PlanTester.PlanTesterResult;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterDFS;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterDFS.PlanTesterDFSResult;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;

public class Test01_LamaTest {

	//@Test
	public void test() {
		Timed time = new Timed();
		time.start();
		
		Problem problem = new Problem();
		
		time.end();
		time.reportInline("Domain/Problem init");
		System.out.println();
		
		time.start();
		
		File domainFile = problem.getDomain().getDomainPureFile();
		File problemFile = new File("auv-problem.pddl");
		problem.createProblemFile(problemFile, problem.getState());
		
		Lama lama = new Lama();
		
		time.end();
		time.reportInline("Lama and inputs init");
		System.out.println();
		
		time.start();
		
		List<PDDLStringInstance> lamaPlan = lama.plan(domainFile, problemFile);
		
		time.end();
		time.reportInline("Lama planning");
		System.out.println();
		
		if (lamaPlan == null) {
			System.out.println("NO PLAN FOUND!");
		} else {
			System.out.println("PLAN FOUND!");
			Effector[] plan = Effector.toEffectors(lamaPlan.toArray(new PDDLStringInstance[0]));
			System.out.println("  +-- #steps = " + plan.length);					
			boolean first = true;
			for (Effector eff : plan) {
				if (first) first = false;
				else System.out.print(" -> ");
				System.out.print(eff.toEffector());
			}
			System.out.println();
			
			// CHECK THE PLAN
			
			time.start();
			
			PlanChecker planChecker = new PlanChecker(problem.getDomain());
			PlanCheckerResult planCheckerResult = planChecker.check(problem.getGoal(), problem.getState(), plan);
			
			time.end();
			System.out.println();
			time.reportInline("Plan checking");			
			
			if (planCheckerResult.valid) {
				System.out.println("PLAN CHECKER: plan is valid!");
			} else {
				System.out.println("PLAN CHECKER: plan is INvalid! Last executable action index is " + planCheckerResult.lastExecutableEffectorIndex + ".");
			}
			
			// TEST THE PLAN - OPPORTUNISTIC
			time.start();
			PlanTester planTester = new PlanTester(problem.getDomain(), problem.getApplicables());
			SafeStates safeStates = new SafeStates(problem.getDomain(), new File("safe_states"));
			PlanTesterResult planTesterResult = planTester.check(problem.getGoal(), problem.getState(), safeStates, plan);
			time.end();
			System.out.println();
			time.reportInline("Plan testing");
			
			if (planTesterResult.valid) {
				System.out.println("PLAN TESTER: plan is valid!");
			} else {
				System.out.println("PLAN TESTER: plan is INvalid! Last executable action index is " + planTesterResult.lastExecutableEffectorIndex + ".");
				
				System.out.print("  +-- Events: ");
				for (int i = 0; i < planTesterResult.events.length; ++i) {
					if (i != 0) System.out.print(" -> ");
					System.out.print(planTesterResult.events[i] == null ? "null" : planTesterResult.events[i].toEffector());
				}
				System.out.println();				
				System.out.println("  +-- Last safe state index is " + planTesterResult.lastSafeStateIndex + ".");
				System.out.println("SAFE STATE");
				for (int i = 0; i < planTesterResult.lastSafeStateIndex; ++i) {
					planTesterResult.plan[i].apply(planTesterResult.state);
					if (planTesterResult.events[i] != null) planTesterResult.events[i].apply(planTesterResult.state);
				}
				planTesterResult.state.dump();
			}
			
			// TEST THE PLAN - EXHAUSTIVE
			if (true || planTesterResult.valid) {
				time.start();
				PlanTesterDFS planTesterDFS = new PlanTesterDFS(problem.getDomain(), problem.getApplicables());
				PlanTesterDFSResult planTesterDFSResult = planTesterDFS.check(problem.getGoal(), problem.getState(), safeStates, plan);
				time.end();
				System.out.println();
				time.reportInline("Plan testign DFS");				
				if (planTesterResult.valid) {
					System.out.println("PLAN TESTER DFS: plan is valid!");
					System.out.println("  +-- applied events " + planTesterDFSResult.appliedEvents);
				} else {
					System.out.println("PLAN TESTER DFS: plan is INvalid! Last executable action index is " + planTesterResult.lastExecutableEffectorIndex + ".");
					System.out.println("  +-- applied events " + planTesterDFSResult.appliedEvents);
					System.out.print("  +-- Events: ");
					for (int i = 0; i < planTesterResult.events.length; ++i) {
						if (i != 0) System.out.print(" -> ");
						System.out.print(planTesterResult.events[i] == null ? "null" : planTesterResult.events[i].toEffector());
					}
					System.out.println();	
					System.out.println("  +-- Last safe state index is " + planTesterDFSResult.lastSafeStateIndex + ".");
					System.out.println("SAFE STATE");
					for (int i = 0; i < planTesterResult.lastSafeStateIndex; ++i) {
						planTesterResult.plan[i].apply(planTesterResult.state);
						if (planTesterResult.events[i] != null) planTesterResult.events[i].apply(planTesterResult.state);
					}
					planTesterResult.state.dump();
				}
			}
		}
		
		problemFile.delete();
		
		System.out.println("--// TEST FINISHED //--");
	}
	
	public static void main(String[] args) {
		Timed t = new Timed();
		t.start();
		
		Test01_LamaTest test = new Test01_LamaTest();
		
		test.test();
		
		t.end();
		t.reportInline("TEST TIME");
	}
	
}
