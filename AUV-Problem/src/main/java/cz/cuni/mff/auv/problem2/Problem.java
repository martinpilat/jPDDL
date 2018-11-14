package cz.cuni.mff.auv.problem2;

import cz.cuni.mff.auv.domain.Domain;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.problem1.DeadEnd;
import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.jpddl.PDDLProblem;

public final class Problem extends PDDLProblem {
	
	static {
		// ENSURE STATIC INITIALIZATION OF THE CLASSES
		new E_Auvs();
		new E_Locations();
		new E_Resources();
		new E_Ships();
		new E_Vehicles();
	}
	
	public Domain domain;
	
	public State state;
	
	public Goal goal;
	
	public DeadEnd deadEnd;
	
	public Problem() {
		domain = new Domain();
		state = new State();
		goal = new Goal();
		deadEnd = new DeadEnd();
		
		state.p_ActTurn.set();

		
		state.p_Operational.set(E_Auvs.a);

		
		state.p_Outside.set(E_Ships.s);
		state.p_Outside.set(E_Ships.s2);

		
		state.p_At.set(E_Auvs.a, E_Locations.l_1_1);

		
		state.p_AtRes.set(E_Resources.r1, E_Locations.l_2_7);
		state.p_AtRes.set(E_Resources.r2, E_Locations.l_7_6);
		state.p_AtRes.set(E_Resources.r3, E_Locations.l_8_2);

		
		state.p_Free.set(E_Locations.l_1_6);
		state.p_Free.set(E_Locations.l_7_4);
		state.p_Free.set(E_Locations.l_3_1);
		state.p_Free.set(E_Locations.l_8_8);
		state.p_Free.set(E_Locations.l_7_2);
		state.p_Free.set(E_Locations.l_8_5);
		state.p_Free.set(E_Locations.l_5_2);
		state.p_Free.set(E_Locations.l_6_5);
		state.p_Free.set(E_Locations.l_6_4);
		state.p_Free.set(E_Locations.l_6_1);
		state.p_Free.set(E_Locations.l_1_3);
		state.p_Free.set(E_Locations.l_7_1);
		state.p_Free.set(E_Locations.l_3_5);
		state.p_Free.set(E_Locations.l_8_3);
		state.p_Free.set(E_Locations.l_4_6);
		state.p_Free.set(E_Locations.l_5_3);
		state.p_Free.set(E_Locations.l_4_3);
		state.p_Free.set(E_Locations.l_2_6);
		state.p_Free.set(E_Locations.l_1_5);
		state.p_Free.set(E_Locations.l_1_4);
		state.p_Free.set(E_Locations.l_5_4);
		state.p_Free.set(E_Locations.l_2_4);
		state.p_Free.set(E_Locations.l_5_1);
		state.p_Free.set(E_Locations.l_2_8);
		state.p_Free.set(E_Locations.l_8_6);
		state.p_Free.set(E_Locations.l_6_3);
		state.p_Free.set(E_Locations.l_3_6);
		state.p_Free.set(E_Locations.l_3_7);
		state.p_Free.set(E_Locations.l_1_8);
		state.p_Free.set(E_Locations.l_2_2);
		state.p_Free.set(E_Locations.l_3_2);
		state.p_Free.set(E_Locations.l_6_6);
		state.p_Free.set(E_Locations.l_4_4);
		state.p_Free.set(E_Locations.l_7_7);
		state.p_Free.set(E_Locations.l_7_6);
		state.p_Free.set(E_Locations.l_1_2);
		state.p_Free.set(E_Locations.l_6_2);
		state.p_Free.set(E_Locations.l_7_5);
		state.p_Free.set(E_Locations.l_3_8);
		state.p_Free.set(E_Locations.l_5_8);
		state.p_Free.set(E_Locations.l_2_5);
		state.p_Free.set(E_Locations.l_6_8);
		state.p_Free.set(E_Locations.l_5_5);
		state.p_Free.set(E_Locations.l_5_7);
		state.p_Free.set(E_Locations.l_8_1);
		state.p_Free.set(E_Locations.l_2_1);
		state.p_Free.set(E_Locations.l_3_4);
		state.p_Free.set(E_Locations.l_2_7);
		state.p_Free.set(E_Locations.l_4_2);
		state.p_Free.set(E_Locations.l_6_7);
		state.p_Free.set(E_Locations.l_7_8);
		state.p_Free.set(E_Locations.l_4_7);
		state.p_Free.set(E_Locations.l_8_2);
		state.p_Free.set(E_Locations.l_5_6);
		state.p_Free.set(E_Locations.l_4_8);
		state.p_Free.set(E_Locations.l_8_4);
		state.p_Free.set(E_Locations.l_2_3);
		state.p_Free.set(E_Locations.l_4_5);
		state.p_Free.set(E_Locations.l_4_1);
		state.p_Free.set(E_Locations.l_7_3);
		state.p_Free.set(E_Locations.l_1_7);
		state.p_Free.set(E_Locations.l_3_3);
		state.p_Free.set(E_Locations.l_8_7);

		
		state.p_DupFree.set(E_Locations.l_5_7);
		state.p_DupFree.set(E_Locations.l_5_8);
		state.p_DupFree.set(E_Locations.l_2_5);
		state.p_DupFree.set(E_Locations.l_2_4);
		state.p_DupFree.set(E_Locations.l_4_6);
		state.p_DupFree.set(E_Locations.l_6_8);
		state.p_DupFree.set(E_Locations.l_5_4);
		state.p_DupFree.set(E_Locations.l_7_5);
		state.p_DupFree.set(E_Locations.l_4_3);
		state.p_DupFree.set(E_Locations.l_8_1);
		state.p_DupFree.set(E_Locations.l_4_4);
		state.p_DupFree.set(E_Locations.l_8_5);
		state.p_DupFree.set(E_Locations.l_7_4);
		state.p_DupFree.set(E_Locations.l_1_3);
		state.p_DupFree.set(E_Locations.l_7_7);
		state.p_DupFree.set(E_Locations.l_6_1);
		state.p_DupFree.set(E_Locations.l_2_8);
		state.p_DupFree.set(E_Locations.l_4_5);
		state.p_DupFree.set(E_Locations.l_3_1);
		state.p_DupFree.set(E_Locations.l_1_8);
		state.p_DupFree.set(E_Locations.l_1_7);
		state.p_DupFree.set(E_Locations.l_8_7);
		state.p_DupFree.set(E_Locations.l_4_7);
		state.p_DupFree.set(E_Locations.l_1_4);
		state.p_DupFree.set(E_Locations.l_6_7);
		state.p_DupFree.set(E_Locations.l_4_8);
		state.p_DupFree.set(E_Locations.l_8_4);
		state.p_DupFree.set(E_Locations.l_4_2);
		state.p_DupFree.set(E_Locations.l_3_4);
		state.p_DupFree.set(E_Locations.l_7_1);
		state.p_DupFree.set(E_Locations.l_5_5);
		state.p_DupFree.set(E_Locations.l_2_7);
		state.p_DupFree.set(E_Locations.l_8_3);
		state.p_DupFree.set(E_Locations.l_5_3);
		state.p_DupFree.set(E_Locations.l_1_2);
		state.p_DupFree.set(E_Locations.l_2_1);
		state.p_DupFree.set(E_Locations.l_1_5);
		state.p_DupFree.set(E_Locations.l_7_8);
		state.p_DupFree.set(E_Locations.l_6_4);
		state.p_DupFree.set(E_Locations.l_6_5);
		state.p_DupFree.set(E_Locations.l_6_2);
		state.p_DupFree.set(E_Locations.l_3_5);
		state.p_DupFree.set(E_Locations.l_8_8);
		state.p_DupFree.set(E_Locations.l_5_2);
		state.p_DupFree.set(E_Locations.l_3_6);
		state.p_DupFree.set(E_Locations.l_3_7);
		state.p_DupFree.set(E_Locations.l_3_8);
		state.p_DupFree.set(E_Locations.l_7_2);
		state.p_DupFree.set(E_Locations.l_1_6);
		state.p_DupFree.set(E_Locations.l_4_1);
		state.p_DupFree.set(E_Locations.l_8_6);
		state.p_DupFree.set(E_Locations.l_7_6);
		state.p_DupFree.set(E_Locations.l_3_3);
		state.p_DupFree.set(E_Locations.l_6_3);
		state.p_DupFree.set(E_Locations.l_2_3);
		state.p_DupFree.set(E_Locations.l_5_6);
		state.p_DupFree.set(E_Locations.l_2_2);
		state.p_DupFree.set(E_Locations.l_5_1);
		state.p_DupFree.set(E_Locations.l_6_6);
		state.p_DupFree.set(E_Locations.l_3_2);
		state.p_DupFree.set(E_Locations.l_8_2);
		state.p_DupFree.set(E_Locations.l_7_3);
		state.p_DupFree.set(E_Locations.l_2_6);

		
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_4_7, E_Locations.l_4_8);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_7_1, E_Locations.l_6_1);
		state.p_Connected.set(E_Locations.l_1_7, E_Locations.l_2_7);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_1_5, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_7_8, E_Locations.l_8_8);
		state.p_Connected.set(E_Locations.l_5_6, E_Locations.l_6_6);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_1_5, E_Locations.l_1_6);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_5_7, E_Locations.l_6_7);
		state.p_Connected.set(E_Locations.l_5_7, E_Locations.l_5_8);
		state.p_Connected.set(E_Locations.l_7_4, E_Locations.l_8_4);
		state.p_Connected.set(E_Locations.l_5_1, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_3_7, E_Locations.l_2_7);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_4_8, E_Locations.l_4_7);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_6_3, E_Locations.l_7_3);
		state.p_Connected.set(E_Locations.l_7_6, E_Locations.l_7_5);
		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_2_6, E_Locations.l_2_7);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_8_4, E_Locations.l_7_4);
		state.p_Connected.set(E_Locations.l_8_6, E_Locations.l_7_6);
		state.p_Connected.set(E_Locations.l_6_6, E_Locations.l_7_6);
		state.p_Connected.set(E_Locations.l_4_6, E_Locations.l_4_7);
		state.p_Connected.set(E_Locations.l_2_8, E_Locations.l_2_7);
		state.p_Connected.set(E_Locations.l_8_3, E_Locations.l_7_3);
		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_4_6, E_Locations.l_3_6);
		state.p_Connected.set(E_Locations.l_5_6, E_Locations.l_5_7);
		state.p_Connected.set(E_Locations.l_6_7, E_Locations.l_6_6);
		state.p_Connected.set(E_Locations.l_7_7, E_Locations.l_8_7);
		state.p_Connected.set(E_Locations.l_7_4, E_Locations.l_6_4);
		state.p_Connected.set(E_Locations.l_8_8, E_Locations.l_8_7);
		state.p_Connected.set(E_Locations.l_5_6, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_6_1, E_Locations.l_5_1);
		state.p_Connected.set(E_Locations.l_6_5, E_Locations.l_7_5);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_6_5);
		state.p_Connected.set(E_Locations.l_7_7, E_Locations.l_7_6);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_7_1, E_Locations.l_8_1);
		state.p_Connected.set(E_Locations.l_6_3, E_Locations.l_6_2);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_5_1);
		state.p_Connected.set(E_Locations.l_6_2, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_1_5, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_4_6, E_Locations.l_5_6);
		state.p_Connected.set(E_Locations.l_8_5, E_Locations.l_7_5);
		state.p_Connected.set(E_Locations.l_6_2, E_Locations.l_6_1);
		state.p_Connected.set(E_Locations.l_6_2, E_Locations.l_7_2);
		state.p_Connected.set(E_Locations.l_7_6, E_Locations.l_6_6);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_8_2, E_Locations.l_7_2);
		state.p_Connected.set(E_Locations.l_6_4, E_Locations.l_7_4);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_8_1, E_Locations.l_7_1);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_7_3, E_Locations.l_7_2);
		state.p_Connected.set(E_Locations.l_7_3, E_Locations.l_7_4);
		state.p_Connected.set(E_Locations.l_2_6, E_Locations.l_1_6);
		state.p_Connected.set(E_Locations.l_6_4, E_Locations.l_6_5);
		state.p_Connected.set(E_Locations.l_6_5, E_Locations.l_6_4);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_2_6, E_Locations.l_3_6);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_1_1);
		state.p_Connected.set(E_Locations.l_3_6, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_6_7, E_Locations.l_5_7);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_4_6, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_7_7, E_Locations.l_6_7);
		state.p_Connected.set(E_Locations.l_7_6, E_Locations.l_7_7);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_6_4, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_5_7, E_Locations.l_5_6);
		state.p_Connected.set(E_Locations.l_7_5, E_Locations.l_8_5);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_6_3);
		state.p_Connected.set(E_Locations.l_7_5, E_Locations.l_6_5);
		state.p_Connected.set(E_Locations.l_6_1, E_Locations.l_7_1);
		state.p_Connected.set(E_Locations.l_8_7, E_Locations.l_8_8);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_7_2, E_Locations.l_8_2);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_7_3, E_Locations.l_6_3);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_6_7, E_Locations.l_6_8);
		state.p_Connected.set(E_Locations.l_2_6, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_7_3, E_Locations.l_8_3);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_1_6, E_Locations.l_2_6);
		state.p_Connected.set(E_Locations.l_8_7, E_Locations.l_7_7);
		state.p_Connected.set(E_Locations.l_4_7, E_Locations.l_5_7);
		state.p_Connected.set(E_Locations.l_4_7, E_Locations.l_3_7);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_3_7, E_Locations.l_3_6);
		state.p_Connected.set(E_Locations.l_5_8, E_Locations.l_5_7);
		state.p_Connected.set(E_Locations.l_1_6, E_Locations.l_1_7);
		state.p_Connected.set(E_Locations.l_7_2, E_Locations.l_7_3);
		state.p_Connected.set(E_Locations.l_1_6, E_Locations.l_1_5);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_2_7, E_Locations.l_1_7);
		state.p_Connected.set(E_Locations.l_7_4, E_Locations.l_7_3);
		state.p_Connected.set(E_Locations.l_3_8, E_Locations.l_3_7);
		state.p_Connected.set(E_Locations.l_7_4, E_Locations.l_7_5);
		state.p_Connected.set(E_Locations.l_1_7, E_Locations.l_1_8);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_1_1);
		state.p_Connected.set(E_Locations.l_6_7, E_Locations.l_7_7);
		state.p_Connected.set(E_Locations.l_6_6, E_Locations.l_6_7);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_3_7, E_Locations.l_4_7);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_7_1, E_Locations.l_7_2);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_7_8, E_Locations.l_7_7);
		state.p_Connected.set(E_Locations.l_6_2, E_Locations.l_6_3);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_6_8, E_Locations.l_6_7);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_2_6);
		state.p_Connected.set(E_Locations.l_2_7, E_Locations.l_3_7);
		state.p_Connected.set(E_Locations.l_6_5, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_6_4, E_Locations.l_6_3);
		state.p_Connected.set(E_Locations.l_5_7, E_Locations.l_4_7);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_7_2, E_Locations.l_7_1);
		state.p_Connected.set(E_Locations.l_7_6, E_Locations.l_8_6);
		state.p_Connected.set(E_Locations.l_3_6, E_Locations.l_2_6);
		state.p_Connected.set(E_Locations.l_8_8, E_Locations.l_7_8);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_3_7, E_Locations.l_3_8);
		state.p_Connected.set(E_Locations.l_7_7, E_Locations.l_7_8);
		state.p_Connected.set(E_Locations.l_6_5, E_Locations.l_6_6);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_1_1, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_7_5, E_Locations.l_7_4);
		state.p_Connected.set(E_Locations.l_7_5, E_Locations.l_7_6);
		state.p_Connected.set(E_Locations.l_2_7, E_Locations.l_2_8);
		state.p_Connected.set(E_Locations.l_3_6, E_Locations.l_3_7);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_6_3, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_7_2, E_Locations.l_6_2);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_5_6);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_1_5);
		state.p_Connected.set(E_Locations.l_6_6, E_Locations.l_6_5);
		state.p_Connected.set(E_Locations.l_5_1, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_5_1, E_Locations.l_6_1);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_4_6);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_6_4);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_1_5);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_6_1, E_Locations.l_6_2);
		state.p_Connected.set(E_Locations.l_6_6, E_Locations.l_5_6);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_3_6);
		state.p_Connected.set(E_Locations.l_1_8, E_Locations.l_1_7);
		state.p_Connected.set(E_Locations.l_1_1, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_6_2);
		state.p_Connected.set(E_Locations.l_2_7, E_Locations.l_2_6);
		state.p_Connected.set(E_Locations.l_6_3, E_Locations.l_6_4);
		state.p_Connected.set(E_Locations.l_1_7, E_Locations.l_1_6);
		state.p_Connected.set(E_Locations.l_3_6, E_Locations.l_4_6);
		state.p_Connected.set(E_Locations.l_4_7, E_Locations.l_4_6);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_5_6, E_Locations.l_4_6);
		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_5_1);

		
		state.p_Entry.set(E_Ships.s, E_Locations.l_5_8);
		state.p_Entry.set(E_Ships.s2, E_Locations.l_1_5);

		
		state.p_Exit.set(E_Ships.s2, E_Locations.l_8_5);
		state.p_Exit.set(E_Ships.s, E_Locations.l_5_1);

		
		state.p_ConnectedShip.set(E_Ships.s2, E_Locations.l_2_5, E_Locations.l_3_5);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l_5_5, E_Locations.l_5_4);
		state.p_ConnectedShip.set(E_Ships.s2, E_Locations.l_1_5, E_Locations.l_2_5);
		state.p_ConnectedShip.set(E_Ships.s2, E_Locations.l_4_5, E_Locations.l_5_5);
		state.p_ConnectedShip.set(E_Ships.s2, E_Locations.l_6_5, E_Locations.l_7_5);
		state.p_ConnectedShip.set(E_Ships.s2, E_Locations.l_7_5, E_Locations.l_8_5);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l_5_2, E_Locations.l_5_1);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l_5_3, E_Locations.l_5_2);
		state.p_ConnectedShip.set(E_Ships.s2, E_Locations.l_3_5, E_Locations.l_4_5);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l_5_8, E_Locations.l_5_7);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l_5_6, E_Locations.l_5_5);
		state.p_ConnectedShip.set(E_Ships.s2, E_Locations.l_5_5, E_Locations.l_6_5);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l_5_4, E_Locations.l_5_3);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l_5_7, E_Locations.l_5_6);
	}
	
	@Override
	public String getName() {
		return "AUV-Problem";
	}
	
	@Override
	public Domain getDomain() {
		return domain;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public Goal getGoal() {
		return goal;
	}	
	
	@Override
	public PDDLDeadEnd getDeadEnd() {
		return deadEnd;
	}
	
}