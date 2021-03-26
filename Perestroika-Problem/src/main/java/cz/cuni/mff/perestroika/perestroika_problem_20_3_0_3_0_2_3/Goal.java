package cz.cuni.mff.perestroika.perestroika_problem_20_3_0_3_0_2_3;

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
			  + "            (taken r45)\n"			
			  + "            (taken r46)\n"			
			  + "            (taken r47)\n"			
			  + "            (taken r48)\n"			
			  + "            (taken r49)\n"			
			  + "            (taken r50)\n"			
			  + "            (taken r51)\n"			
			  + "            (taken r52)\n"			
			  + "            (taken r53)\n"			
			  + "            (taken r54)\n"			
			  + "            (taken r55)\n"			
			  + "            (taken r56)\n"			
			  + "            (taken r57)\n"			
			  + "            (taken r58)\n"			
			  + "            (taken r59)\n"			
			  + "            (taken r60)\n"			
			  + "            (taken r61)\n"			
			  + "            (taken r62)\n"			
			  + "            (taken r63)\n"			
			  + "            (taken r64)\n"			
			  + "            (taken r65)\n"			
			  + "            (taken r66)\n"			
			  + "            (taken r67)\n"			
			  + "            (taken r68)\n"			
			  + "            (taken r69)\n"			
			  + "            (taken r70)\n"			
			  + "            (taken r71)\n"			
			  + "            (taken r72)\n"			
			  + "            (taken r73)\n"			
			  + "            (taken r74)\n"			
			  + "            (taken r75)\n"			
			  + "            (taken r76)\n"			
			  + "            (taken r77)\n"			
			  + "            (taken r78)\n"			
			  + "            (taken r79)\n"			
			  + "            (taken r80)\n"			
			  + "            (taken r81)\n"			
			  + "            (taken r82)\n"			
			  + "            (taken r83)\n"			
			  + "            (taken r84)\n"			
			  + "            (taken r85)\n"			
			  + "            (taken r86)\n"			
			  + "            (taken r87)\n"			
			  + "            (taken r88)\n"			
			  + "            (taken r89)\n"			
			  + "            (taken r90)\n"			
			  + "            (taken r91)\n"			
			  + "            (taken r92)\n"			
			  + "            (taken r93)\n"			
			  + "            (taken r94)\n"			
			  + "            (taken r95)\n"			
			  + "            (taken r96)\n"			
			  + "            (taken r97)\n"			
			  + "            (taken r98)\n"			
			  + "            (taken r99)\n"			
			  + "            (taken r100)\n"			
			  + "            (taken r101)\n"			
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
			   && state.p_Taken.isSet(E_Resources.r44)
			   && state.p_Taken.isSet(E_Resources.r45)
			   && state.p_Taken.isSet(E_Resources.r46)
			   && state.p_Taken.isSet(E_Resources.r47)
			   && state.p_Taken.isSet(E_Resources.r48)
			   && state.p_Taken.isSet(E_Resources.r49)
			   && state.p_Taken.isSet(E_Resources.r50)
			   && state.p_Taken.isSet(E_Resources.r51)
			   && state.p_Taken.isSet(E_Resources.r52)
			   && state.p_Taken.isSet(E_Resources.r53)
			   && state.p_Taken.isSet(E_Resources.r54)
			   && state.p_Taken.isSet(E_Resources.r55)
			   && state.p_Taken.isSet(E_Resources.r56)
			   && state.p_Taken.isSet(E_Resources.r57)
			   && state.p_Taken.isSet(E_Resources.r58)
			   && state.p_Taken.isSet(E_Resources.r59)
			   && state.p_Taken.isSet(E_Resources.r60)
			   && state.p_Taken.isSet(E_Resources.r61)
			   && state.p_Taken.isSet(E_Resources.r62)
			   && state.p_Taken.isSet(E_Resources.r63)
			   && state.p_Taken.isSet(E_Resources.r64)
			   && state.p_Taken.isSet(E_Resources.r65)
			   && state.p_Taken.isSet(E_Resources.r66)
			   && state.p_Taken.isSet(E_Resources.r67)
			   && state.p_Taken.isSet(E_Resources.r68)
			   && state.p_Taken.isSet(E_Resources.r69)
			   && state.p_Taken.isSet(E_Resources.r70)
			   && state.p_Taken.isSet(E_Resources.r71)
			   && state.p_Taken.isSet(E_Resources.r72)
			   && state.p_Taken.isSet(E_Resources.r73)
			   && state.p_Taken.isSet(E_Resources.r74)
			   && state.p_Taken.isSet(E_Resources.r75)
			   && state.p_Taken.isSet(E_Resources.r76)
			   && state.p_Taken.isSet(E_Resources.r77)
			   && state.p_Taken.isSet(E_Resources.r78)
			   && state.p_Taken.isSet(E_Resources.r79)
			   && state.p_Taken.isSet(E_Resources.r80)
			   && state.p_Taken.isSet(E_Resources.r81)
			   && state.p_Taken.isSet(E_Resources.r82)
			   && state.p_Taken.isSet(E_Resources.r83)
			   && state.p_Taken.isSet(E_Resources.r84)
			   && state.p_Taken.isSet(E_Resources.r85)
			   && state.p_Taken.isSet(E_Resources.r86)
			   && state.p_Taken.isSet(E_Resources.r87)
			   && state.p_Taken.isSet(E_Resources.r88)
			   && state.p_Taken.isSet(E_Resources.r89)
			   && state.p_Taken.isSet(E_Resources.r90)
			   && state.p_Taken.isSet(E_Resources.r91)
			   && state.p_Taken.isSet(E_Resources.r92)
			   && state.p_Taken.isSet(E_Resources.r93)
			   && state.p_Taken.isSet(E_Resources.r94)
			   && state.p_Taken.isSet(E_Resources.r95)
			   && state.p_Taken.isSet(E_Resources.r96)
			   && state.p_Taken.isSet(E_Resources.r97)
			   && state.p_Taken.isSet(E_Resources.r98)
			   && state.p_Taken.isSet(E_Resources.r99)
			   && state.p_Taken.isSet(E_Resources.r100)
			   && state.p_Taken.isSet(E_Resources.r101);
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

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r45)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r46)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r47)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r48)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r49)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r50)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r51)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r52)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r53)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r54)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r55)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r56)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r57)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r58)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r59)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r60)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r61)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r62)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r63)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r64)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r65)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r66)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r67)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r68)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r69)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r70)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r71)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r72)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r73)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r74)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r75)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r76)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r77)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r78)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r79)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r80)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r81)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r82)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r83)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r84)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r85)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r86)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r87)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r88)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r89)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r90)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r91)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r92)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r93)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r94)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r95)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r96)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r97)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r98)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r99)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r100)) break;
		}
		if (!achieved) return false;

		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.r101)) break;
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