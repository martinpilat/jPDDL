package cz.cuni.mff.bw.problem5;

import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;

public class Goal extends PDDLGoal {
	
	@Override
	public String toPDDL() {
		return  "(:goal (and\n"
			  + "            (on b1 b15)\n"
			  + "            (on b3 b12)\n"
			  + "            (on b4 b2)\n"
			  + "            (on b6 b13)\n"
			  + "            (on b7 b8)\n"
			  + "            (on b8 b14)\n"
			  + "            (on b9 b6)\n"
			  + "            (on b10 b11)\n"
			  + "            (on b11 b4)\n"
			  + "            (on b12 b1)\n"
			  + "            (on b13 b7)\n"
			  + "            (on b14 b5)\n"
			  + "            (on b15 b9)\n"
			  + "       )\n"
			  + ")";
	}

	
	/**
	 * Whether the goal has been achieved in 'state'.
	 * 
	 * GOAL
	 * 
	 * :goal (and (on b1 b15)
     *            (on b3 b12)
     *            (on b4 b2)
     *            (on b6 b13)
     *            (on b7 b8)
     *            (on b8 b14)
     *            (on b9 b6)
     *            (on b10 b11)
     *            (on b11 b4)
     *            (on b12 b1)
     *            (on b13 b7)
     *            (on b14 b5)
     *            (on b15 b9))
	 * 
	 * @param state
	 * @return
	 */
	public boolean isAchieved(State state) {
		return    state.p_On.isSet(E_Blocks.b1, E_Blocks.b15)
			   && state.p_On.isSet(E_Blocks.b3, E_Blocks.b12)
			   && state.p_On.isSet(E_Blocks.b4, E_Blocks.b2)
			   && state.p_On.isSet(E_Blocks.b6, E_Blocks.b13)
			   && state.p_On.isSet(E_Blocks.b7, E_Blocks.b8)
			   && state.p_On.isSet(E_Blocks.b8, E_Blocks.b14)
			   && state.p_On.isSet(E_Blocks.b9, E_Blocks.b6)
			   && state.p_On.isSet(E_Blocks.b10, E_Blocks.b11)
			   && state.p_On.isSet(E_Blocks.b11, E_Blocks.b4)
			   && state.p_On.isSet(E_Blocks.b12, E_Blocks.b1)
			   && state.p_On.isSet(E_Blocks.b13, E_Blocks.b7)
			   && state.p_On.isSet(E_Blocks.b14, E_Blocks.b5)
			   && state.p_On.isSet(E_Blocks.b15, E_Blocks.b9);
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
			if (achieved = state.p_On.isSet(E_Blocks.b1, E_Blocks.b15)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b3, E_Blocks.b12)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b4, E_Blocks.b2)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b6, E_Blocks.b13)) break;
		}
		if (!achieved) return false;	
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b7, E_Blocks.b8)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b8, E_Blocks.b14)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b9, E_Blocks.b6)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b10, E_Blocks.b11)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b11, E_Blocks.b4)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b12, E_Blocks.b1)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b13, E_Blocks.b7)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b14, E_Blocks.b15)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_On.isSet(E_Blocks.b15, E_Blocks.b9)) break;
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
