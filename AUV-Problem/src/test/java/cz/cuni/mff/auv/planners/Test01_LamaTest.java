package cz.cuni.mff.auv.planners;

import java.io.File;
import java.util.List;

import cz.cuni.mff.auv.domain.Effector;
import cz.cuni.mff.auv.problem3.Problem;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.search.bench.Timed;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker.PlanCheckerResult;
import cz.cuni.mff.jpddl.tools.validators.PlanTester;
import cz.cuni.mff.jpddl.tools.validators.PlanTester.PlanTesterResult;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterBFS;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterBFS.PlanTesterBFSResult;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterDFS;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterDFS.PlanTesterDFSResult;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterFlat;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterFlat.PlanTesterFlatResult;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;
import cz.cuni.mff.jpddl.utils.StateCompact;

public class Test01_LamaTest {

	//@Test
	public void test() {
		Timed time = new Timed();
		time.start();
		
		Problem problem = new Problem();
		
		StateCompact originalState = problem.state.getDynamic().clone();
		
		time.end();
		time.reportInline("Domain/Problem init");
		System.out.println();
		
		time.start();
		
		File domainFile = problem.getDomain().getDomainPureFile();
		File problemFile = new File("auv-problem.pddl");
		problem.createProblemFile(problemFile, problem.getState());
		
		SafeStates safeStates = new SafeStates(problem.getDomain(), new File("../Domains/AUV/safe_states-3"));
		
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
			{
				System.out.println();
				System.out.println("PLAN CHECKING");			
				
				time.start();
				PlanChecker planChecker = new PlanChecker();
				planChecker.config(problem.getDomain(), safeStates);
				PlanCheckerResult planCheckerResult = planChecker.validate(problem.getGoal(), problem.getState(), plan);
				time.end();
				time.reportInline("  +-- TIME");
				
				if (planCheckerResult.valid) {
					System.out.println("  +-- Plan is valid!");
				} else {
					System.out.println("  +-- Plan is INvalid! Last executable action index is " + planCheckerResult.lastExecutableEffectorIndex + ".");
				}
				
				/*
				System.out.println("CHECKING THE STATE!");
				if (!originalState.equals(problem.getState().getDynamic())) {
					System.out.println("  +-- State differs from the original state!");
					return;
				} else {
					System.out.println("  +-- State is still the same.");
				}
				*/
			}
			
			// TEST THE PLAN - OPPORTUNISTIC
			
			{
				System.out.println();
				System.out.println("PLAN TESTING");				
				
				time.start();
				PlanTester planTester = new PlanTester();
				planTester.config(problem.getDomain(), problem.getApplicables(), 1, safeStates);				
				PlanTesterResult planTesterResult = planTester.validate(problem.getGoal(), problem.getState(), plan);
				time.end();
				time.reportInline("  +-- TIME");
				
				if (planTesterResult.valid) {
					System.out.println("  +-- Plan is valid!");
				} else {
					System.out.println("  +-- Plan is INvalid! Last executable action index is " + planTesterResult.lastExecutableEffectorIndex + ".");
					
					System.out.print("  +-- Action/Events: ");
					for (int i = 0; i < planTesterResult.events.length; ++i) {
						if (i != 0) System.out.print(" -> ");
						if (i == planTesterResult.lastExecutableEffectorIndex+1) {
							System.out.print("!!! ");
						}
						System.out.print(planTesterResult.plan[i] == null ? "null" : planTesterResult.plan[i].toEffector());
						if (i == planTesterResult.lastExecutableEffectorIndex+1) {
							System.out.print(" !!!");
						}
						System.out.print("/");
						System.out.print(planTesterResult.events[i] == null ? "null" : planTesterResult.events[i].toEffector());
					}					
					System.out.println();
				}								
				System.out.println("  +-- Last safe state index is " + planTesterResult.lastSafeStateIndex + ".");
				/*
				System.out.println("SAFE STATE");
				for (int i = 0; i < planTesterResult.lastSafeStateIndex; ++i) {
					planTesterResult.plan[i].apply(planTesterResult.state);
					if (planTesterResult.events[i] != null) planTesterResult.events[i].apply(planTesterResult.state);
				}
				planTesterResult.state.dump();
				*/
				
				/*
				System.out.println("CHECKING THE STATE!");
				if (!originalState.equals(problem.getState().getDynamic())) {
					System.out.println("  +-- State differs from the original state!");
					problem.getState().setDynamic(originalState);
				} else {
					System.out.println("  +-- State is still the same.");
				}
				*/
			}
			
			// TEST THE PLAN - DFS WAY
			{
				System.out.println();
				System.out.println("PLAN TESTER DFS");				
				
				time.start();
				PlanTesterDFS planTesterDFS = new PlanTesterDFS();
				planTesterDFS.config(problem.getDomain(), problem.getApplicables(), safeStates);
				PlanTesterDFSResult planTesterDFSResult = planTesterDFS.validate(problem.getGoal(), problem.getState(), plan);
				time.end();
				time.reportInline("  +-- TIME");
				
				if (planTesterDFSResult.valid) {
					System.out.println("  +-- Plan is valid!");
					System.out.println("  +-- Applied events " + planTesterDFSResult.appliedEvents);
				} else {
					System.out.println("  +-- Plan is INvalid! Last executable action index is " + planTesterDFSResult.lastExecutableEffectorIndex + ".");
					System.out.println("  +-- applied events " + planTesterDFSResult.appliedEvents);
					System.out.print("  +-- Action/Events: ");
					for (int i = 0; i < planTesterDFSResult.events.length; ++i) {
						if (i != 0) System.out.print(" -> ");
						if (i == planTesterDFSResult.lastExecutableEffectorIndex+1) {
							System.out.print("!!! ");
						}
						System.out.print(planTesterDFSResult.plan[i] == null ? "null" : planTesterDFSResult.plan[i].toEffector());
						if (i == planTesterDFSResult.lastExecutableEffectorIndex+1) {
							System.out.print(" !!!");
						}
						System.out.print("/");
						System.out.print(planTesterDFSResult.events[i] == null ? "null" : planTesterDFSResult.events[i].toEffector());
					}
					System.out.println();						
				}
				System.out.println("  +-- Last safe state index is " + planTesterDFSResult.lastSafeStateIndex + ".");
				/*
				System.out.println("SAFE STATE");
				for (int i = 0; i < planTesterDFSResult.lastSafeStateIndex; ++i) {
					planTesterDFSResult.plan[i].apply(planTesterDFSResult.state);
					if (planTesterDFSResult.events[i] != null) planTesterDFSResult.events[i].apply(planTesterDFSResult.state);
				}
				planTesterDFSResult.state.dump();
				*/
			
				/*
				System.out.println("CHECKING THE STATE!");
				if (!originalState.equals(problem.getState().getDynamic())) {
					System.out.println("  +-- State differs from the original state!");
					problem.getState().setDynamic(originalState);
				} else {
					System.out.println("  +-- State is still the same.");
				}
				*/
			}
				
			// TEST THE PLAN - BFS WAY
			{
				System.out.println();
				System.out.println("PLAN TESTING BFS");	
				
				time.start();
				PlanTesterBFS planTesterBFS = new PlanTesterBFS();
				planTesterBFS.config(problem.getDomain(), problem.getApplicables(), safeStates, 10);
				PlanTesterBFSResult planTesterBFSResult = planTesterBFS.validate(problem.getGoal(), problem.getState(), plan);
				time.end();
				time.reportInline("  +-- TIME");					
				
				System.out.println("  +-- Events-lookahead " + (planTesterBFSResult.bfsLimit));
				if (planTesterBFSResult.valid) {
					System.out.println("  +-- Plan is valid!");
					System.out.println("  +-- Applied events " + planTesterBFSResult.statesProbed);
				} else {
					System.out.println("  +-- Plan is INvalid! Last executable action index is " + planTesterBFSResult.lastExecutableEffectorIndex + ".");
					System.out.println("  +-- Applied events " + planTesterBFSResult.statesProbed);
					System.out.print("  +-- Action/Events: ");
					for (int i = 0; i < planTesterBFSResult.events.length; ++i) {
						if (i != 0) System.out.print(" -> ");
						if (i == planTesterBFSResult.lastExecutableEffectorIndex+1) {
							System.out.print("!!! ");
						}
						System.out.print(planTesterBFSResult.plan[i] == null ? "null" : planTesterBFSResult.plan[i].toEffector());
						if (i == planTesterBFSResult.lastExecutableEffectorIndex+1) {
							System.out.print(" !!! ");
						}
						System.out.print("/");
						System.out.print(planTesterBFSResult.events[i] == null ? "null" : planTesterBFSResult.events[i].toEffector());
					}
					System.out.println();						
				}
				
				System.out.println("  +-- Last safe state index is " + planTesterBFSResult.lastSafeStateIndex + ".");
				/*
				System.out.println("SAFE STATE");
				for (int i = 0; i < planTesterBFSResult.lastSafeStateIndex; ++i) {
					planTesterBFSResult.plan[i].apply(planTesterBFSResult.state);
					if (planTesterBFSResult.events[i] != null) planTesterBFSResult.events[i].apply(planTesterBFSResult.state);
				}
				planTesterBFSResult.state.dump();
				*/
				
				/*
				System.out.println("CHECKING THE STATE!");
				if (!originalState.equals(problem.getState().getDynamic())) {
					System.out.println("  +-- State differs from the original state!");
					problem.getState().setDynamic(originalState);
				} else {
					System.out.println("  +-- State is still the same.");
				}
				*/
			}
			
			// TEST THE PLAN - FLAT WAY
			{
				System.out.println();
				System.out.println("PLAN TESTING FLAT");
				//problem.getState().dump(true);
				
				time.start();
				PlanTesterFlat planTesterFlat = new PlanTesterFlat();
				planTesterFlat.config(problem.getDomain(), problem.getApplicables(), safeStates);
				PlanTesterFlatResult planTesterFlatResult = planTesterFlat.validate(problem.getGoal(), problem.getState(), plan);
				time.end();
				time.reportInline("  +-- TIME");
				
				if (planTesterFlatResult.valid) {
					System.out.println("  +-- Plan is valid!");
				} else {
					System.out.println("  +-- Plan is INvalid! Last executable action index is " + planTesterFlatResult.lastExecutableEffectorIndex + ".");
					System.out.print("  +-- Actions: ");
					for (int i = 0; i < planTesterFlatResult.plan.length; ++i) {
						if (i != 0) System.out.print(" -> ");
						if (i == planTesterFlatResult.lastExecutableEffectorIndex+1) {
							System.out.print("!!! ");
						}
						System.out.print(planTesterFlatResult.plan[i] == null ? "null" : planTesterFlatResult.plan[i].toEffector());
						if (i == planTesterFlatResult.lastExecutableEffectorIndex+1) {
							System.out.print(" !!! ");
						}
					}
					System.out.println();						
				}
				
				System.out.println("  +-- Last safe state index is " + planTesterFlatResult.lastSafeStateIndex + ".");
				/*
				System.out.println("SAFE STATE");
				for (int i = 0; i < planTesterFlatResult.lastSafeStateIndex; ++i) {
					planTesterFlatResult.plan[i].apply(planTesterFlatResult.state);
					if (planTesterFlatResult.events[i] != null) planTesterFlatResult.events[i].apply(planTesterFlatResult.state);
				}
				planTesterFlatResult.state.dump();
				*/
				
				/*
				System.out.println("CHECKING THE STATE!");
				if (!originalState.equals(problem.getState().getDynamic())) {
					System.out.println("  +-- State differs from the original state!");
					problem.getState().setDynamic(originalState);
				} else {
					System.out.println("  +-- State is still the same.");
				}
				*/
			}
			
		}
		
		problemFile.delete();
		
		System.out.println();
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
