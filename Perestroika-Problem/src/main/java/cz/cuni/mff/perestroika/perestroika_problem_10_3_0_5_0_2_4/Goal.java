package cz.cuni.mff.perestroika.perestroika_problem_10_3_0_5_0_2_4;

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
			  + "            (taken r6)\n"			
			  + "            (taken r7)\n"			
			  + "            (taken r8)\n"			
			  + "            (taken r9)\n"			
			  + "            (taken r10)\n"			
			  + "            (taken r11)\n"			
			  + "            (taken r12)\n"			
			  + "            (taken r13)\n"			
			  + "            (taken r14)\n"			
			  + "            (taken r15)\n"			
			  + "            (taken r16)\n"			
			  + "            (taken r17)\n"			
			  + "            (taken r18)\n"			
			  + "            (taken r19)\n"			
			  + "            (taken r20)\n"			
			  + "            (taken r21)\n"			
			  + "            (taken r22)\n"			
			  + "            (taken r23)\n"			
			  + "            (taken r24)\n"			
			  + "            (taken r25)\n"			
			  + "            (taken r26)\n"			
			  + "            (taken r27)\n"			
			  + "            (taken r28)\n"			
			  + "            (taken r29)\n"			
			  + "            (taken r30)\n"			
			  + "            (taken r31)\n"			
			  + "            (taken r32)\n"			
			  + "            (taken r33)\n"			
			  + "            (taken r34)\n"			
			  + "            (taken r35)\n"			
			  + "            (taken r36)\n"			
			  + "            (taken r37)\n"			
			  + "            (taken r38)\n"			
			  + "            (taken r39)\n"			
			  + "            (taken r40)\n"			
			  + "            (taken r41)\n"			
			  + "            (taken r42)\n"			
			  + "            (taken r43)\n"			
			  + "            (taken r44)\n"			
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
			   && state.p_Taken.isSet(E_Resources.r5)
			   && state.p_Taken.isSet(E_Resources.r6)
			   && state.p_Taken.isSet(E_Resources.r7)
			   && state.p_Taken.isSet(E_Resources.r8)
			   && state.p_Taken.isSet(E_Resources.r9)
			   && state.p_Taken.isSet(E_Resources.r10)
			   && state.p_Taken.isSet(E_Resources.r11)
			   && state.p_Taken.isSet(E_Resources.r12)
			   && state.p_Taken.isSet(E_Resources.r13)
			   && state.p_Taken.isSet(E_Resources.r14)
			   && state.p_Taken.isSet(E_Resources.r15)
			   && state.p_Taken.isSet(E_Resources.r16)
			   && state.p_Taken.isSet(E_Resources.r17)
			   && state.p_Taken.isSet(E_Resources.r18)
			   && state.p_Taken.isSet(E_Resources.r19)
			   && state.p_Taken.isSet(E_Resources.r20)
			   && state.p_Taken.isSet(E_Resources.r21)
			   && state.p_Taken.isSet(E_Resources.r22)
			   && state.p_Taken.isSet(E_Resources.r23)
			   && state.p_Taken.isSet(E_Resources.r24)
			   && state.p_Taken.isSet(E_Resources.r25)
			   && state.p_Taken.isSet(E_Resources.r26)
			   && state.p_Taken.isSet(E_Resources.r27)
			   && state.p_Taken.isSet(E_Resources.r28)
			   && state.p_Taken.isSet(E_Resources.r29)
			   && state.p_Taken.isSet(E_Resources.r30)
			   && state.p_Taken.isSet(E_Resources.r31)
			   && state.p_Taken.isSet(E_Resources.r32)
			   && state.p_Taken.isSet(E_Resources.r33)
			   && state.p_Taken.isSet(E_Resources.r34)
			   && state.p_Taken.isSet(E_Resources.r35)
			   && state.p_Taken.isSet(E_Resources.r36)
			   && state.p_Taken.isSet(E_Resources.r37)
			   && state.p_Taken.isSet(E_Resources.r38)
			   && state.p_Taken.isSet(E_Resources.r39)
			   && state.p_Taken.isSet(E_Resources.r40)
			   && state.p_Taken.isSet(E_Resources.r41)
			   && state.p_Taken.isSet(E_Resources.r42)
			   && state.p_Taken.isSet(E_Resources.r43)
			   && state.p_Taken.isSet(E_Resources.r44);
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

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r6)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r7)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r8)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r9)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r10)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r11)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r12)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r13)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r14)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r15)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r16)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r17)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r18)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r19)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r20)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r21)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r22)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r23)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r24)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r25)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r26)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r27)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r28)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r29)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r30)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r31)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r32)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r33)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r34)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r35)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r36)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r37)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r38)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r39)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r40)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r41)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r42)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r43)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r44)) break;
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