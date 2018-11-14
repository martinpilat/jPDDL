package cz.cuni.mff.jpddl.tools.validators;

import java.util.ArrayList;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;

/**
 * Depth-first search that tests all sequences of events against the plan.
 * 
 * @author Jimmy
 */
public class PlanTesterDFS implements IPlanValidator {
	
	public static class PlanTesterDFSResult extends PlanValidatorResult {
		
		/**
		 * Safe states used.
		 */
		public SafeStates safeStates;
		
		/**
		 * Unfortunate sequence of events in case of failure.
		 */
		public PDDLEffector[] events;

		/**
		 * How many events we have applied during the search.
		 */
		public int appliedEvents = 0;
	}

	private PDDLDomain domain;
	private PDDLApplicables applicables;
	private SafeStates safeStates;
	
	public void config(PDDLDomain domain, PDDLApplicables applicables, SafeStates safeStates) {
		this.domain = domain;
		this.applicables = applicables;
		this.safeStates = safeStates;
	}
	
	@Override
	public PlanTesterDFSResult validate(PDDLGoal goal, PDDLState state, PDDLEffector... plan) {
		PlanTesterDFSResult result = new PlanTesterDFSResult();
		
		result.valid = true;
		result.state = state;
		result.plan = plan;
		result.safeStates = safeStates;
		result.events = new PDDLEffector[plan.length];
		result.lastExecutableEffectorIndex = -1;
		result.lastSafeStateIndex = -1;
		
		if (dfs(state, plan, 0, result)) {
			// CHECK GOAL
			result.valid = goal.isAchieved(state);
		} else {
			result.valid = false;
		}
				
		// RETURN RESULT
		return result;
		
	}
	
	public boolean dfs(PDDLState state, PDDLEffector[] plan, int index, PlanTesterDFSResult result) {
		if (index >= plan.length) return true;
		
		// IS SAFE STATE?
		boolean isSafeState = safeStates.isSafe(state);
		
		// CHECK ACTION APPLICABILITY
		if (!plan[index].isApplicable(state)) {
			// ACTION NOT APPLICABLE!
			result.lastExecutableEffectorIndex = index-1;
			result.valid = false;
			return false;
		}
		
		// APPLY ACTION
		plan[index].apply(state);
		
		// COLLECT APPLICABLE EVENTS		
		List<PDDLEffector> events = new ArrayList<PDDLEffector>();		
		applicables.collectApplicableEvents(domain, state, events);
		
		for (PDDLEffector event : events) {			
			// MARK EVENT
			result.events[index] = event;
			
			// APPLY EVENT
			event.apply(state);
			++result.appliedEvents;
			
			// SEARCH NEXT LEVEL
			if (!dfs(state, plan, index+1, result)) {			
				// WE HAVE FOUND UNFORTUNATE SEQUENCE OF EVENTS
				if (result.lastSafeStateIndex < 0 && isSafeState) {
					result.lastSafeStateIndex = index;					
				}	
				
				// REVERT EVENT
				event.reverse(state);
				// REVERT ACTION
				plan[index].reverse(state);
				
				return false;
			}
			
			// REVERT THE EVENT
			event.reverse(state);
			result.events[index] = null;
			
			// WE WILL NOT BE USING THIS INSTANCE AGAIN
			event.recycle();			
		}	
		
		// NO EVENT HAPPENED 
		// => SEARCH NEXT LEVEL
		if (!dfs(state, plan, index+1, result)) {			
			// WE HAVE FOUND UNFORTUNATE SEQUENCE OF EVENTS
			if (result.lastSafeStateIndex < 0 && isSafeState) {
				result.lastSafeStateIndex = index;					
			}				
			
			// REVERT ACTION
			plan[index].reverse(state);
			
			return false;
		}
		
		// REVERT ACTION
		plan[index].reverse(state);
		
		return true;		
	}
	
	@Override
	public String getDescription() {
		return getClass().getSimpleName();
	}
	
}
