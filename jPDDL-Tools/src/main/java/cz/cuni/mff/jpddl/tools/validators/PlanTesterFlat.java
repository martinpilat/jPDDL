package cz.cuni.mff.jpddl.tools.validators;

import java.util.ArrayList;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * Testing the plan using "Algorithm 2", i.e., accumulating positive and negative effects of all events that can
 * happen along the plan and validating the actions against all possibilities.
 * 
 * Not as precise as PlanTesterBFS but much faster.
 * 
 * @author Jimmy
 */
public class PlanTesterFlat implements IPlanValidator {
	
	public static class PlanTesterFlatResult extends PlanValidatorResult {
	
		/**
		 * Safe states used.
		 */
		public SafeStates safeStates;

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
	public PlanTesterFlatResult validate(PDDLGoal goal, PDDLState state, PDDLEffector... plan) {
		PlanTesterFlatResult result = new PlanTesterFlatResult();
		
		result.valid = true;
		result.state = state;
		result.plan = plan;
		result.safeStates = safeStates;
		result.lastExecutableEffectorIndex = -1;
		result.lastSafeStateIndex = -1;
		
		StateCompact original = state.getDynamic().clone();
		
		// WITH ACCORDANCE OF THE PAPER
		StateCompact s = state.getDynamic().clone();
		StateCompact pPlus = new StateCompact();
		StateCompact pMinus = new StateCompact();		
		
		StateCompact stateWithoutPMinus = new StateCompact();
		StateCompact stateWithPPlus = new StateCompact();		
		
		int i;
		for (i = 0; i < plan.length; ++i) {
			// SET STATE WITHOUT pMinus
			stateWithoutPMinus.reset();
			stateWithoutPMinus.add(s);
			stateWithoutPMinus.remove(pMinus);
			state.setDynamic(stateWithoutPMinus);	
			
			if (safeStates != null && safeStates.isSafe(state)) {
				if (result.firstSafeStateIndex < 0 && i > 0) {
					result.firstSafeStateIndex = i;
				}
				result.lastSafeStateIndex = i;
			}			
			
			if (plan[i].isApplicable(state)) {
				result.lastExecutableEffectorIndex = i;		
				
				// OBTAIN STATE S
				state.setDynamic(s);
				
				// APPLY ACTION
				plan[i].apply(state);
				s.reset();
				s.add(state.getDynamic());
				
				// ALTER Ps
				plan[i].removeAdds(pMinus);
				plan[i].removeDeletes(pPlus);
				
				// GET STATE S + PPlus
				stateWithPPlus.reset();
				stateWithPPlus.add(s);
				stateWithPPlus.add(pPlus);				
				state.setDynamic(stateWithPPlus);
				
				// FIND ALL APPLICABLE EVENTS
				List<PDDLEffector> events = new ArrayList<PDDLEffector>();
				applicables.collectApplicableEvents(domain, state, events);
				
				// PROCESS ALL EVENTS
				for (PDDLEffector event : events) {
					event.addDeletes(pMinus);
					event.addAdds(pPlus);
				}				
			} else {
				//state.dump(true);
				result.valid = false;
				break;
			}
		}
		
		// CHECK GOAL
		stateWithoutPMinus.reset();
		stateWithoutPMinus.add(s);
		stateWithoutPMinus.remove(pMinus);
		state.setDynamic(stateWithoutPMinus);		
		if (!goal.isAchieved(state)) {
			result.valid = false;
		} else {
			result.lastSafeStateIndex = i;
		}
		
		// ROLLBACK STATE
		state.setDynamic(original);
		
		// RETURN RESULT
		return result;
		
	}
	
	@Override
	public String getDescription() {
		return getClass().getSimpleName();
	}
	
	
}
