package cz.cuni.mff.jpddl.tools.search;

import java.util.Collections;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;

/**
 * Depth-limited search.
 * @author Jimmy
 *
 */
public class DLS extends SearchBase {

	private List<PDDLEffector> result;
	
	public DLS() {		
	}

	public DLS(PDDLDomain domain, PDDLState state, PDDLGoal goal, PDDLApplicables applicables) {
		super(domain, state, goal, applicables);
	}

	public DLS(PDDLProblem problem) {
		super(problem);		
	}
	
	public DLS(PDDLProblem problem, PDDLState state) {
		super(problem, state);
	}
	
	public List<PDDLEffector> search(int limit) {
		result = pool.get();
		
		if (doSearch(limit)) {
			Collections.reverse(result);
			return result;
		}
		
		return null;
	}
	
	private boolean doSearch(int limit) {		
		if (goal.isAchieved(state)) return true;
		
		if (limit < 1) return false;
		
		List<PDDLEffector> actions = pool.get();
		
		applicables.collectApplicableActions(domain, state, actions);
		
		for (PDDLEffector action : actions) {			
			action.apply(state);
			
			if (doSearch(limit-1)) {
				action.reverse(state);
				result.add(action);
				return true;
			}
			
			action.reverse(state);
			
			// WE WILL NOT BE USING THIS INSTANCE AGAIN
			action.recycle();
		}
		
				
		pool.back(actions);
		
		return false;		
	}
	
}
