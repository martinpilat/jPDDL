package cz.cuni.mff.jpddl.tools.search.bench;

import java.util.Collections;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.tools.search.SearchBase;

/**
 * Depth-limited search.
 * @author Jimmy
 *
 */
public class DLSBench extends SearchBase {

	public Bench bench = new Bench();
	
	private List<PDDLEffector> result;
	
	public DLSBench() {
	}
	

	public DLSBench(PDDLDomain domain, PDDLState state, PDDLGoal goal, PDDLApplicables applicables) {
		super(domain, state, goal, applicables);
	}

	public DLSBench(PDDLProblem problem) {
		super(problem);		
	}
	
	public DLSBench(PDDLProblem problem, PDDLState state) {
		super(problem, state);
	}
	
	public List<PDDLEffector> search(int limit) {
		bench.start();
		
		result = pool.get();
		
		if (doSearch(limit)) {
			Collections.reverse(result);
			bench.end();
			return result;
		}
		
		bench.end();
		
		return null;
	}
	
	private boolean doSearch(int limit) {		
		if (goal.isAchieved(state)) {
			return true;
		}
		
		if (limit < 1) {
			return false;
		}
		
		bench.nodesVisited += 1;
		
		List<PDDLEffector> actions = pool.get();
		
//		System.out.println("---=== STATE ===---");
//		state.dump();
		
		applicables.collectApplicableActions(domain, state, actions);
		
//		System.out.println("APPLICABLE ACTIONS: ");
//		System.out.print("  ");
	//	domain.dump(actions);
		
		for (PDDLEffector action : actions) {
//			System.out.println("APPLYING: " + action.toEffector());		
			action.apply(state);
			
//			result.add(action);			
//			System.out.print("APPLIED:  ");
//			dumpPlan(result);
			
			if (doSearch(limit-1)) {
				action.reverse(state);
				result.add(action);
				return true;
			}
			
//			System.out.println("REVERTING: " + action.toEffector());
			action.reverse(state);
			
//			result.remove(result.size()-1);		
//			System.out.print("REVERTED: ");
//			dumpPlan(result);
			
//			System.out.println("---=== STATE AFTER REVERT ===---");
//			state.dump();			
		}
		
		return false;		
	}
	
	private void dumpPlan(List<PDDLEffector> actions) {
		boolean first = true;
		for (PDDLEffector effector : actions) {
			if (first) first = false;
			else System.out.print("->");
			System.out.print(effector.toEffector());
		}
		System.out.println();
	}
	
}
