package cz.cuni.mff.perestroika.perestroika_problem_5_3_0_2_0_3_8;

import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.perestroika.domain.State;

public class Goal extends PDDLGoal {
	
	@Override
	public String toPDDL() {
		return  "(:goal (and (alive)\n"
			  + "            (taken r1)\n"			
			  + "            (taken r2)\n"			
			  + "            (taken r3)\n"			
			  + "            (taken r4)\n"			
			  + "            (taken r5)\n"			
			  + "       )\n"
			  + ")";
	}

	
	/**
	 * Whether the goal has been achieved in 'state'.
	 * 
	 * GOAL
	 * 
	 * :goal (and (alive r1)
     *            (taken r1)
     *            (taken r2)
     *            (taken r3)
     *            (taken r4)
     *       )
	 * 
	 * @param state
	 * @return
	 */
	public boolean isAchieved(State state) {
		return    state.p_Alive.isSet()
			   && state.p_Taken.isSet(E_Resources.r1)
			   && state.p_Taken.isSet(E_Resources.r2)
			   && state.p_Taken.isSet(E_Resources.r3)
			   && state.p_Taken.isSet(E_Resources.r4)
			   && state.p_Taken.isSet(E_Resources.r5);
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
			if (achieved = state.p_Alive.isSet()) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r1)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r2)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r3)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r4)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r5)) break;
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