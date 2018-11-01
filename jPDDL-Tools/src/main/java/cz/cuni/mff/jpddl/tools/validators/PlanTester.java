package cz.cuni.mff.jpddl.tools.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;

public class PlanTester {
	
	public static class PlanTesterResult {
		
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
	}
	
	private PDDLDomain domain;
	
	private PDDLApplicables applicables;
	
	private Random random;

	public PlanTester(PDDLDomain domain, PDDLApplicables applicables) {
		this(domain, applicables, System.currentTimeMillis());
	}
	
	public PlanTester(PDDLDomain domain, PDDLApplicables applicables, long randomSeed) {
		this.domain = domain;
		this.applicables = applicables;
		this.random = new Random(randomSeed);
	}
	
	public PlanTesterResult check(PDDLState state, SafeStates safeStates, PDDLEffector... plan) {
		PlanTesterResult result = new PlanTesterResult();
		
		result.valid = true;
		result.state = state;
		result.plan = plan;
		result.safeStates = safeStates;
		result.events = new PDDLEffector[plan.length];
		result.lastExecutableEffectorIndex = -1;
		result.lastSafeStateIndex = -1;
		
		for (int i = 0; i < plan.length; ++i) {
			if (safeStates != null && result.lastSafeStateIndex < 0) {
				if (safeStates.isSafe(state)) {
					result.lastSafeStateIndex = i;
				}
			}
			
			if (plan[i].isApplicable(state)) {
				result.lastExecutableEffectorIndex = i;				
				plan[i].apply(state);
				
				// SIMULATE RANDOM EVENT
				List<PDDLEffector> events = new ArrayList<PDDLEffector>();
				applicables.collectApplicableEvents(domain, state, events);				
				int next = random.nextInt(events.size()+1);
				if (next < events.size()) {
					PDDLEffector event = events.get(next);
					result.events[i] = event;
					event.apply(state);
				} else {
					// NO EVENT
				}
			} else {
				result.valid = false;
				break;
			}
		}
		
		if (safeStates != null && result.lastSafeStateIndex < 0) {
			if (safeStates.isSafe(state)) {
				result.lastSafeStateIndex = result.lastExecutableEffectorIndex+1;
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
	
	
}
