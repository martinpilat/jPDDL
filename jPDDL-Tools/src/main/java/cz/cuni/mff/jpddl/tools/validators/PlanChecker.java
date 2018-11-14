package cz.cuni.mff.jpddl.tools.validators;

import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator.PlanValidatorResult;

public class PlanChecker implements IPlanValidator {

	public static class PlanCheckerResult extends PlanValidatorResult {
	}
	
	private PDDLDomain domain;

	public PlanChecker() {
	}
	
	public void config(PDDLDomain domain) {
		this.domain = domain;
	}
	
	@Override
	public PlanCheckerResult validate(PDDLGoal goal, PDDLState state, PDDLEffector... plan) {
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
		
		// CHECK GOAL
		if (!goal.isAchieved(state)) {
			result.valid = false;
		}
 		
		// ROLLBACK STATE
		for (int j = result.lastExecutableEffectorIndex; j >= 0; --j) {
			plan[j].reverse(state);
		}

		// RETURN RESULT
		return result;
		
	}
	
	@Override
	public String getDescription() {
		return getClass().getSimpleName();
	}
	
}
