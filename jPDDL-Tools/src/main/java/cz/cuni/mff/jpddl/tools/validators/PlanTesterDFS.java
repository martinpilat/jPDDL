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
public class PlanTesterDFS {
	
	public static class PlanTesterDFSResult {
		
		public PDDLState state;
		
		public SafeStates safeStates;
		
		public PDDLEffector[] plan;
		
		public PDDLEffector[] events;
		
		public boolean valid;				
		
		public int lastExecutableEffectorIndex;
		
		/**
		 * State was safe after actions and events [0;lastSafeStateIndex) were applied.
		 * I.e., if you take state and applies plan[0];events[0];plane[1];events[1];...;plan[lastSafeStateIndex-1];events[lastSafeStateIndex-1]
		 * you are in the safe state.
		 */
		public int lastSafeStateIndex;
		
		public int appliedEvents = 0;
	}

	private PDDLDomain domain;
	private PDDLApplicables applicables;
	
	public PlanTesterDFS() {		
	}

	public PlanTesterDFS(PDDLDomain domain, PDDLApplicables applicables) {
		this.domain = domain;
		this.applicables = applicables;
	}
	
	public PlanTesterDFSResult check(PDDLGoal goal, PDDLState state, SafeStates safeStates, PDDLEffector... plan) {
		PlanTesterDFSResult result = new PlanTesterDFSResult();
		
		result.valid = true;
		result.state = state;
		result.plan = plan;
		result.safeStates = safeStates;
		result.events = new PDDLEffector[plan.length];
		result.lastExecutableEffectorIndex = -1;
		result.lastSafeStateIndex = -1;
		
		if (dfs(state, safeStates, plan, 0, result)) {
			// CHECK GOAL
			if (!goal.isAchieved(state)) {
				result.valid = false;
			}
		}
		
		// ROLLBACK STATE
		for (int j = result.lastExecutableEffectorIndex; j >= 0; --j) {			
			if (result.events[j] != null) result.events[j].reverse(state);
			plan[j].reverse(state);			
		}		

		// RETURN RESULT
		return result;
		
	}
	
	public boolean dfs(PDDLState state, SafeStates safeStates, PDDLEffector[] plan, int index, PlanTesterDFSResult result) {
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
			if (!dfs(state, safeStates, plan, index+1, result)) {			
				// WE HAVE FOUND UNFORTUNATE SEQUENCE OF EVENTS
				if (result.lastSafeStateIndex < 0 && isSafeState) {
					result.lastSafeStateIndex = index;					
				}					
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
		if (!dfs(state, safeStates, plan, index+1, result)) {			
			// WE HAVE FOUND UNFORTUNATE SEQUENCE OF EVENTS
			if (result.lastSafeStateIndex < 0 && isSafeState) {
				result.lastSafeStateIndex = index;					
			}				
			return false;
		}
		
		return true;		
	}
	
}
