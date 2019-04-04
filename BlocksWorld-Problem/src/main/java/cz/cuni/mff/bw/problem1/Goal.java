package cz.cuni.mff.bw.problem1;

import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;

public class Goal extends PDDLGoal {
	
	@Override
	public String toPDDL() {
		return  "(:goal (and\n"
			  + "            (on b1 b4)\n"
			  + "            (on b2 b5)\n"
			  + "            (on b3 b1)\n"
			  + "            (on b5 b3)\n"
			  + "       )\n"
			  + ")";
	}

	
	/**
	 * Whether the goal has been achieved in 'state'.
	 * 
	 * GOAL
	 * 
	 * :goal (and (on b1 b4)
     *            (on b2 b5)
     *            (on b3 b1)
     *            (on b5 b3))
	 * 
	 * @param state
	 * @return
	 */
	public boolean isAchieved(State state) {
		return    state.p_On.isSet(E_Blocks.b1, E_Blocks.b4)
			   && state.p_On.isSet(E_Blocks.b2, E_Blocks.b5)
			   && state.p_On.isSet(E_Blocks.b3, E_Blocks.b1)
			   && state.p_On.isSet(E_Blocks.b5, E_Blocks.b3);
	}
	
	public boolean isAchievedAll(State... states) {
		for (State state : states) {
			if (!isAchieved(state)) return false;
		}
		return true;
	}
	
	public boolean isAchievedUnion(State... states) {
		boolean achieved;
				
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b1, E_Blocks.b4)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b2, E_Blocks.b5)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b3, E_Blocks.b1)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b5, E_Blocks.b3)) break;
		}
		if (!achieved) return false;		
		
		return true;
	}
	
	public boolean isAchievedAny(State... states) {
		for (State state : states) {
			if (isAchieved(state)) return true;
		}
		return false;
	}

	// ========================
	// PDDLGoal Generic Methods
	// ========================
	
	@Override
	public boolean isAchieved(PDDLState state) {
		return isAchieved((State)state);
	}

	@Override
	public boolean isAchievedAll(PDDLState... states) {
		return isAchievedAll((State[])states);
	}

	@Override
	public boolean isAchievedUnion(PDDLState... states) {
		return isAchievedUnion((State[])states);
	}

	@Override
	public boolean isAchievedAny(PDDLState... states) {
		return isAchievedAny((State[])states);
	}

}
