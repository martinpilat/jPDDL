package cz.cuni.mff.jpddl.tools.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;

public class PlanTester implements IPlanValidator {
	
	public static class PlanTesterResult extends PlanValidatorResult {
		
		/**
		 * Safe states used.
		 */
		public SafeStates safeStates;
		
		/**
		 * Sequence of unfortunate events in case of failure.
		 */
		public PDDLEffector[] events;
		
	}
	
	private PDDLDomain domain;
	
	private PDDLApplicables applicables;

	private long randomSeed;
	
	private Random random;
	
	private SafeStates safeStates;

	public void config(PDDLDomain domain, PDDLApplicables applicables, long randomSeed, SafeStates safeStates) {
		this.domain = domain;
		this.applicables = applicables;
		this.randomSeed = randomSeed;
		this.random = new Random(randomSeed);
		this.safeStates = safeStates;
	}
	
	@Override
	public PlanTesterResult validate(PDDLGoal goal, PDDLState state, PDDLEffector... plan) {
		PlanTesterResult result = new PlanTesterResult();
		
		result.valid = true;
		result.state = state;
		result.plan = plan;
		result.safeStates = safeStates;
		result.events = new PDDLEffector[plan.length];
		result.lastExecutableEffectorIndex = -1;
		result.lastSafeStateIndex = -1;
		
		for (int i = 0; i < plan.length; ++i) {
			if (safeStates != null && safeStates.isSafe(state)) {
				result.lastSafeStateIndex = i;
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
		
		// CHECK GOAL
		if (!goal.isAchieved(state)) {
			result.valid = false;
		}
		
		// ROLLBACK STATE
		for (int j = result.lastExecutableEffectorIndex; j >= 0; --j) {			
			if (result.events[j] != null) result.events[j].reverse(state);
			plan[j].reverse(state);			
		}		

		// RETURN RESULT
		return result;
		
	}
	
	@Override
	public String getDescription() {
		return getClass().getSimpleName() + "[seed=" + randomSeed + "]";
	}	
	
}
