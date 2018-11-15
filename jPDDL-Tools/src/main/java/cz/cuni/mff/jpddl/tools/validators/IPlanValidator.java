package cz.cuni.mff.jpddl.tools.validators;

import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;

public interface IPlanValidator {
	
	public static class PlanValidatorResult {
		
		/**
		 * Original state that we validated against.
		 */
		public PDDLState state;
		
		/**
		 * Plan we have validated.
		 */
		public PDDLEffector[] plan;
		
		/**
		 * Result of the validator.
		 */
		public boolean valid;				
		
		/**
		 * Last index of the action that is validly executable according to the validator type.
		 */
		public int lastExecutableEffectorIndex;
		
		/**
		 * First safe state after applying actions 0, 1, ..., firstSafeStateIndex-1.
		 */
		public int firstSafeStateIndex = -1;
		
		/**
		 * State was safe after applying actions 0, 1, ..., lastSafeStateIndex-1.
		 */
		public int lastSafeStateIndex = -1;
		
	}
	
	public PlanValidatorResult validate(PDDLGoal goal, PDDLState state, PDDLEffector... plan);
	
	public String getDescription();

}
