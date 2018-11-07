package cz.cuni.mff.auv.planners;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.cuni.mff.auv.domain.Effector;
import cz.cuni.mff.auv.problem.Problem;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.search.bench.Timed;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterBFS;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterBFS.PlanTesterBFSResult;

public class Test02_LamaSimulation {
	
	private List<PDDLEffector> events = new ArrayList<PDDLEffector>();
	
	private int action = 0;

	public void simulateEvent(Problem problem) {
		// COLLECT APPLICABLE EVENTS
		events .clear();
		events.add(null); // add NO-EVENT
		problem.applicables.collectApplicableEvents(problem.domain, problem.state, events);
		Collections.shuffle(events); // randomize
		
		PDDLEffector event = events.get(0);
		
		System.out.println("  +-- EVENT [" + action + ".]: " + (event == null ? "no-event" : event.toEffector()));
		if (event != null) event.apply(problem.state);		
	}
	
	//@Test
	public boolean test() {
		int LOOKAHEAD = 15;
		
		Problem problem = new Problem();		
		SafeStates safeStates = new SafeStates(problem.getDomain(), new File("safe_states"));
		PlanTesterBFS planTesterBFS = new PlanTesterBFS(problem.getDomain(), problem.getApplicables());
		
		File domainFile = problem.getDomain().getDomainPureFile();
		
		Lama lama = new Lama();
		
		int iteration = 0;
		action = 0;
		
		// ALGORITHM 5
		while (!problem.getGoal().isAchieved(problem.state)) {
			
			iteration++;
			System.out.println("ITERATION " + iteration);
			
			// PLAN
			System.out.println("  +-- Planning...");
			File problemFile = new File("auv-problem.pddl");
			problem.createProblemFile(problemFile, problem.getState());
			List<PDDLStringInstance> lamaPlan = lama.plan(domainFile, problemFile);
			problemFile.delete();
			if (lamaPlan == null) {
				System.out.println("  +-- LAMA FAILED TO FIND THE PLAN!");
				return false;
			}
			
			// TRANSLATE PLAN
			Effector[] plan = Effector.toEffectors(lamaPlan.toArray(new PDDLStringInstance[0]));			
			System.out.println("  +-- Plan has " + plan.length + " steps");
			
			System.out.println("  +-- Checking with lookahead " + LOOKAHEAD + "...");
					
			int toExecuteActions = 0;
			
			PlanTesterBFSResult planTesterBFSResult = planTesterBFS.check(problem.getGoal(), problem.getState(), safeStates, LOOKAHEAD, plan);
			if (planTesterBFSResult.valid) {
				if (planTesterBFSResult.bfsLimit > plan.length) {
					System.out.println("  +-- Plan cannot be interrupted, simulating it all...");
					toExecuteActions = plan.length;
				} else {
					System.out.println("  +-- Plan cannot be interrupted in " + LOOKAHEAD + " steps");
					toExecuteActions = planTesterBFSResult.lastSafeStateIndex;
					System.out.println("  +-- The furthest safe state is in " + toExecuteActions + " actions");
				}
			} else {
				System.out.println("  +-- Plan can be interrupted by events");
				toExecuteActions = planTesterBFSResult.lastSafeStateIndex;
				System.out.println("  +-- The furthest safe state is in " + toExecuteActions + " actions");
			}
			
			if (toExecuteActions > 0) {
				for (int i = 0; i < toExecuteActions; ++i) {
					if (!plan[i].isApplicable(problem.state)) {
						System.out.println("  +-- Action[" + i  + "/" + (++action) + "]: " + plan[i].toEffector() + " is not applicable, FATAL ERROR!");
						return false;
					}
					System.out.println("  +-- ACTION[" + (++action) + ".]: " + plan[i].toEffector());
					plan[i].apply(problem.state);
					if (problem.goal.isAchieved(problem.state)) {
						// GOAL ACHIEVED!
						break;
					}
					simulateEvent(problem);
				}
			} else {
				System.out.println("  +-- ACTION[" + (++action) + ".]: no-op");
				simulateEvent(problem);
			}			
		}
		
		System.out.println("GOAL ACHIEVED");
		System.out.println("  +-- iterations " + iteration);
		System.out.println("  +-- total actions " + action);
		
		return true;
		
	}
	
	public void testIterated(int iterations) {
		Timed time = new Timed();
		time.start();
		for (int i = 0; i < iterations; ++i) {
			System.out.println();
			System.out.println("===== TEST " + (i+1) + " / " + iterations + " =====");
			System.out.println();
			if (!test()) {
				System.out.println("!!! FAILURE !!!");
				return;
			}
		}
		time.end();
		System.out.println();
		System.out.println("Average time: " + ((double)time.durationMillis / (double)iterations) + "ms");
	}
	
	public static void main(String[] args) {
		Test02_LamaSimulation test = new Test02_LamaSimulation();
		Timed time = new Timed();
		time.start();		
		test.testIterated(20);
		time.end();
		System.out.println();
		time.report("TOTAL TIME (planning+checking+simulation+test overhead)");
	}

}
