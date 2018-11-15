package cz.cuni.mff.jpddl.tools.simulations;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.search.bench.Timed;
import cz.cuni.mff.jpddl.tools.utils.CSV;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator.PlanValidatorResult;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.utils.StateCompact;

public class LamaRun {
	
	public static enum LamaRunResult {
		/**
		 * We got to the goal state.
		 */
		GOAL_ACHIEVED,
		
		/**
		 * Maximum number of iterations were reached.
		 */
		MAX_ITERATIONS_REACHED,
		
		/**
		 * We got to the dead end.
		 */
		DEAD_END
		
	}
	
	private List<PDDLEffector> events = new ArrayList<PDDLEffector>();
	
	private int action = 0;
	
	private long planningMillis = 0;
	
	private void simulateEvent(PDDLProblem problem, Random random) {
		// COLLECT APPLICABLE EVENTS
		events .clear();
		events.add(null); // add NO-EVENT
		problem.getApplicables().collectApplicableEvents(problem.getDomain(), problem.getState(), events);
		
		Collections.shuffle(events); // randomize
		
		PDDLEffector event = events.get(0);
		
		System.out.println("    +-- EVENT [" + action + ".]: " + (event == null ? "no-event" : event.toEffector()));
		if (event != null) event.apply(problem.getState());		
	}
	
	public void run(String id, PDDLProblem problem, PlanChecker planChecker, IPlanValidator validator, int maxIterations, long randomSeed, File csvOutputFile) {
		StateCompact initialState = problem.getState().getDynamic().clone();
		
		Random random = new Random(randomSeed);
		
		Timed allTime = new Timed();
		Timed planningTime = new Timed();
		Timed validationTime = new Timed();
				
		planningMillis = 0;
		long validatingMillis = 0;
		
		allTime.start();
		
		System.out.println();
		System.out.println("---------");
		System.out.println("LAMA RUN: " + id);
		System.out.println("---------");
		System.out.println("  +-- Validator: " + validator.getDescription());
		
		// RESET
		events.clear();
		action = 0;
		
		// INIT LAMA
		File domainFile = problem.getDomain().getDomainPureFile();		
		File flatDomainFile = problem.getDomain().getDomainFlatFile();		
		Lama lama = new Lama();
		
		// INTERNAL VARS
		int iteration = 0;
		
		// RESULT
		LamaRunResult result = LamaRunResult.GOAL_ACHIEVED;
		
		// ALGORITHM 3
		while (true) {
			
			if (problem.getGoal().isAchieved(problem.getState())) {
				System.out.println("  +-- GOAL ACHIEVED!");
				result = LamaRunResult.GOAL_ACHIEVED;
				break;
			}
			
			if (iteration >= maxIterations) {
				result = LamaRunResult.MAX_ITERATIONS_REACHED;
				break;
			}
			
			iteration++;
			System.out.println("ITERATION " + iteration);
			
			if (problem.getDeadEnd().isDeadEnd(problem.getState())) {
				System.out.println("  +-- GOT INTO THE DEAD END STATE!");
				result = LamaRunResult.DEAD_END;
				break;
			} else {
				System.out.println("  +-- Not in dead-end state.");								
			}
			
			// PLAN
			System.out.println("  +-- Planning...");
			File problemFile = new File("problem.pddl");
			problem.createProblemFile(problemFile, problem.getState());
			planningTime.start();
			List<PDDLStringInstance> lamaPlan = lama.plan(domainFile, problemFile);
			planningTime.end();
			planningMillis += planningTime.durationMillis;
			problemFile.delete();
			if (lamaPlan == null) {
				System.out.println("  +-- LAMA FAILED TO FIND THE PLAN!");
			} else {
				// TRANSLATE PLAN
				PDDLEffector[] plan = problem.getDomain().toEffectors(lamaPlan.toArray(new PDDLStringInstance[0]));			
				System.out.println("  +-- Plan has " + plan.length + " steps");
					
				int toExecuteActions = 0;
				
				System.out.println("  +-- Validating the plan...");
				validationTime.start();
				PlanValidatorResult validatorResult = validator.validate(problem.getGoal(), problem.getState(), plan);
				validationTime.end();
				validatingMillis += validationTime.durationMillis;
				
				if (validatorResult.valid) {
					System.out.println("    +-- Plan reported as valid.");
					if (validatorResult.lastExecutableEffectorIndex+1 < plan.length) {
						System.out.println("    +-- Plan was not simulated fully, only up to action index " + validatorResult.lastExecutableEffectorIndex);
						if (validatorResult.lastSafeStateIndex > 0) {
							System.out.println("    +-- But safe state can be reached after simulating the plan up to action index " + validatorResult.lastExecutableEffectorIndex);
							toExecuteActions = validatorResult.lastSafeStateIndex;
						} else {
							System.out.println("    +-- And no safe state reported to be found.");
						}
					} else {
						System.out.println("    +-- Plan reported to be uninterruptible, simulating it all...");
						toExecuteActions = plan.length;
					}					
				} else {
					System.out.println("    +-- Plan can be interrupted by events");
					if (validatorResult.lastSafeStateIndex > 0) {
						System.out.println("    +-- But safe state can be reached after simulating the plan up to action index " + validatorResult.lastExecutableEffectorIndex);
						toExecuteActions = validatorResult.lastSafeStateIndex;
					} else {
						System.out.println("    +-- And no safe state reported to be found.");
					}
				}
				
				if (toExecuteActions == 0 && plan.length > 0) {
					System.out.println("  +-- No actions to execute, trying to find a safe state via plan-checker...");
					validationTime.start();
					PlanValidatorResult planCheckerResult = planChecker.validate(problem.getGoal(), problem.getState(), plan);
					validationTime.end();
					validatingMillis += validationTime.durationMillis;
					if (planCheckerResult.firstSafeStateIndex > 0) {
						System.out.println("    +-- First safe state in " + planCheckerResult.firstSafeStateIndex + " actions, going to improve the plan.");
						PDDLEffector[] improvedPlan = improvePlan(problem, lama, flatDomainFile, plan, planCheckerResult.firstSafeStateIndex, planningTime);
						if (improvedPlan != null) {
							System.out.println("      +-- Improved plan found, it has " + improvedPlan.length + " steps");
							System.out.println("      +-- Checking the improved plan...");
							validationTime.start();
							PlanValidatorResult planCheckerImprovedResult = validator.validate(problem.getGoal(), problem.getState(), improvedPlan);
							validationTime.end();
							validatingMillis += validationTime.durationMillis;
							if (planCheckerImprovedResult.firstSafeStateIndex > 0) {
								System.out.println("      +-- Safe state found in " + planCheckerImprovedResult.firstSafeStateIndex + " steps, simulating plan!");
								plan = improvedPlan;
								toExecuteActions = planCheckerImprovedResult.firstSafeStateIndex;
							} else {
								System.out.println("      +-- NO SAFE STATE CAN BE REACHED WITH THE IMPROVED PLAN!");
							}
						}
					} else {
						System.out.println("    +-- No safe state found along the plan.");
					}
				}
				
				if (toExecuteActions > 0) {
					System.out.println("  +-- Executing plan, actions[0-" + (toExecuteActions-1) + "]");
					for (int i = 0; i < toExecuteActions; ++i) {
						if (!plan[i].isApplicable(problem.getState())) {
							System.out.println("    +-- Action[" + i  + "/" + (++action) + "]: " + plan[i].toEffector() + " is NOT APPLICABLE, terminating the plan execution!");
							break;
						}
						System.out.println("    +-- ACTION[" + (++action) + ".]: " + plan[i].toEffector());
						plan[i].apply(problem.getState());
						if (problem.getGoal().isAchieved(problem.getState())) {
							System.out.println("    +-- GOAL ACHIEVED!");
							result = LamaRunResult.GOAL_ACHIEVED;
							break;
						}
						simulateEvent(problem, random);
					}
				} else {
					System.out.println("  +-- ACTION[" + (++action) + ".]: no-op");
					simulateEvent(problem, random);
				}		
			}
		}
		
		System.out.println("LamaRun Finished");
		System.out.println("  +-- result " + result);
		System.out.println("  +-- iterations " + iteration);
		System.out.println("  +-- total actions " + action);
		allTime.end();
		
		long simulationMillis = allTime.durationMillis - planningMillis - validatingMillis;
		
		System.out.println("  +-- total time   " + Timed.getTimeString(allTime.durationMillis));
		System.out.println("    +-- planning   " + Timed.getTimeString(planningMillis));
		System.out.println("    +-- validation " + Timed.getTimeString(validatingMillis));
		System.out.println("    +-- simulation " + Timed.getTimeString(simulationMillis));
		
		outputToCSV(csvOutputFile, id, problem, validator, result, iteration, allTime.durationMillis, planningMillis, validatingMillis, simulationMillis, randomSeed, maxIterations);
		
		problem.getState().setDynamic(initialState);
	}

	private PDDLEffector[] improvePlan(PDDLProblem problem, Lama lama, File flatDomainFile, PDDLEffector[] plan, int firstSafeStateIndex, Timed planningTime) {
		// BACKUP STATE
		StateCompact currentState = problem.getState().getDynamic().clone();
		
		// GET TO SAFE STATE
		for (int i = 0; i < firstSafeStateIndex; ++i) {
			plan[i].apply(problem.getState());
		}
		
		// SAVE THE SAFE STATE
		StateCompact targetState = problem.getState().getDynamic().clone();
		
		// RESTORE THE CURRENT STATE
		problem.getState().setDynamic(currentState);
		
		// GENERATE PROBLEM FILE
		File problemFile = new File("problem-flat.pddl");
		problem.createProblemFile(problemFile, problem.getState(), targetState);
		
		// PLAN!
		planningTime.start();
		List<PDDLStringInstance> lamaPlan = lama.plan(flatDomainFile, problemFile);
		planningTime.end();
		planningMillis += planningTime.durationMillis;
		problemFile.delete();
		
		if (lamaPlan == null) {
			System.out.println("    +-- LAMA FAILED TO FIND IMPROVEMENT!");
			return null;
		}
		
		// TRANSLATE PLAN
		PDDLEffector[] improvement = problem.getDomain().toEffectors(lamaPlan.toArray(new PDDLStringInstance[0]));			
		System.out.println("    +-- Improvement has " + plan.length + " steps, merging with original plan");
		
		PDDLEffector[] result = new PDDLEffector[improvement.length + plan.length - firstSafeStateIndex];
		for (int i = 0; i < improvement.length; ++i) result[i] = improvement[i];
		for (int i = firstSafeStateIndex; i < plan.length; ++i) result[improvement.length + i - firstSafeStateIndex] = plan[firstSafeStateIndex];
		
		return result;
	}

	private void outputToCSV(File csvOutputFile, String id, PDDLProblem problem, IPlanValidator validator, LamaRunResult result, int iterations,
			long durationMillis, long planningMillis, long validatingMillis, long simulationMillis, long randomSeed,
			int maxIterations) {
		
		System.out.println("  +-- appending result into " + csvOutputFile.getAbsolutePath());
		
		Date now = Calendar.getInstance().getTime();
		
		CSV.appendCSVRow(csvOutputFile, 
			new String[] {"date", "id", "problem",            "validator",                 "result", "iterations", "durationMillis", "planningMillis", "validatingMillis", "simulationMillis", "randomSeed", "maxIterations"},
			               now,    id,   problem.getClass(),   validator.getDescription(),  result,   iterations,   durationMillis,   planningMillis,   validatingMillis,   simulationMillis,   randomSeed,   maxIterations			               
		);
		
	}
	
}
