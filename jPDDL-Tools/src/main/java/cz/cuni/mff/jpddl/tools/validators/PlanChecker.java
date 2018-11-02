package cz.cuni.mff.jpddl.tools.validators;

import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;

public class PlanChecker {

	public static class PlanCheckerResult {
		
		public PDDLState state;
		
		public PDDLEffector[] plan;
		
		public boolean valid;				
		
		public int lastExecutableEffectorIndex;		
	}
	
	private PDDLDomain domain;

	public PlanChecker(PDDLDomain domain) {
		this.domain = domain;
	}
	
	public PlanCheckerResult check(PDDLState state, PDDLEffector... plan) {
		PlanCheckerResult result = new PlanCheckerResult();
		
		result.valid = true;
		result.state = state;
		result.plan = plan;
		result.lastExecutableEffectorIndex = -1;
		
		for (int i = 0; i < plan.length; ++i) {
			if (plan[i].isApplicable(state)) {
				result.lastExecutableEffectorIndex = i;
				plan[i].apply(state);
			} else {
				result.valid = false;
				break;
			}
		}
		
		// ROLLBACK STATE
		for (int j = result.lastExecutableEffectorIndex; j >= 0; --j) {
			plan[j].reverse(state);
		}

		// RETURN RESULT
		return result;
		
	}
	
}
