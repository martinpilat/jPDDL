package cz.cuni.mff.auv.problem5;

import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;

public class Goal extends PDDLGoal {
	
	@Override
	public String toPDDL() {
		return  "(:goal (and (sampled r1)\n"
			  + "           (sampled r2)\n"
			  + "           (sampled r3)\n"
			  + "           (at a l-1-1)\n"
			  + "           (operational a)\n"
			  + "       )\n"
			  + ")";
	}

	
	/**
	 * Whether the goal has been achieved in 'state'.
	 * 
	 * GOAL
	 * 
	 * :goal (and (sampled r1)
     *            (sampled r2)
     *            (sampled r3)
     *            (at a l-1-1)
     *            (operational a)
     *       )
	 * 
	 * @param state
	 * @return
	 */
	public boolean isAchieved(State state) {
//		int achievedCount = 0;
//		
//		if (state.p_Sampled.isSet(E_Resources.r1)) ++achievedCount;
//		if (state.p_Sampled.isSet(E_Resources.r2)) ++achievedCount;
//		if (state.p_Sampled.isSet(E_Resources.r3)) ++achievedCount;
//		if (state.p_At.isSet(E_Auvs.a, E_Locations.l12)) ++achievedCount;
//		if (state.p_Operational.isSet(E_Auvs.a)) ++achievedCount;
//		
//		if (achievedCount == 4) {
//			System.out.println("ALMOST THERE!");
//			state.dump();
//		}
		
		return    state.p_Sampled.isSet(E_Resources.r1)
			   && state.p_Sampled.isSet(E_Resources.r2)
			   && state.p_Sampled.isSet(E_Resources.r3)
			   && state.p_At.isSet(E_Auvs.a, E_Locations.l_1_1)
			   && state.p_Operational.isSet(E_Auvs.a);
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
			if (achieved = state.p_Sampled.isSet(E_Resources.r1)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Sampled.isSet(E_Resources.r2)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Sampled.isSet(E_Resources.r3)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_At.isSet(E_Auvs.a, E_Locations.l_1_1)) break;
		}
		if (!achieved) return false;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Operational.isSet(E_Auvs.a)) break;
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