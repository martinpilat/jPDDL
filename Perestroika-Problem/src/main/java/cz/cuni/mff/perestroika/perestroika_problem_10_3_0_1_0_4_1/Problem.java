package cz.cuni.mff.perestroika.perestroika_problem_10_3_0_1_0_4_1;

import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.perestroika.domain.Domain;
import cz.cuni.mff.perestroika.domain.State;
import cz.cuni.mff.perestroika.problem.PerestroikaProblem;
import cz.cuni.mff.perestroika.problem1.DeadEnd;

public final class Problem extends PerestroikaProblem {
	
	static {
		// ENSURE STATIC INITIALIZATION OF THE CLASSES
		new E_Locations();
		new E_Resources();
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
		
		state.p_ActRound.set();
		state.p_Alive.set();

		
		state.p_AtAgent.set(E_Locations.l_1_1);

		state.p_AtRes.set(E_Resources.r3, E_Locations.l_3_8);
		state.p_AtRes.set(E_Resources.r2, E_Locations.l_3_7);
		state.p_AtRes.set(E_Resources.r1, E_Locations.l_3_2);
		state.p_AtRes.set(E_Resources.r6, E_Locations.l_9_4);
		state.p_AtRes.set(E_Resources.r5, E_Locations.l_7_2);
		state.p_AtRes.set(E_Resources.r4, E_Locations.l_5_2);
		

		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_7_4, E_Locations.l_7_3);
		state.p_Connected.set(E_Locations.l_7_9, E_Locations.l_8_9);
		state.p_Connected.set(E_Locations.l_8_2, E_Locations.l_9_2);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_5_9, E_Locations.l_4_9);
		state.p_Connected.set(E_Locations.l_2_10, E_Locations.l_2_9);
		state.p_Connected.set(E_Locations.l_7_7, E_Locations.l_6_7);
		state.p_Connected.set(E_Locations.l_3_8, E_Locations.l_4_8);
		state.p_Connected.set(E_Locations.l_8_4, E_Locations.l_8_3);
		state.p_Connected.set(E_Locations.l_10_6, E_Locations.l_10_7);
		state.p_Connected.set(E_Locations.l_6_7, E_Locations.l_6_6);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_9_7, E_Locations.l_8_7);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_10_4, E_Locations.l_10_3);
		state.p_Connected.set(E_Locations.l_4_8, E_Locations.l_4_7);
		state.p_Connected.set(E_Locations.l_5_7, E_Locations.l_5_6);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_5_7, E_Locations.l_5_8);
		state.p_Connected.set(E_Locations.l_1_5, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_10_5, E_Locations.l_10_6);
		state.p_Connected.set(E_Locations.l_3_9, E_Locations.l_3_8);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_6_4);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_6_6, E_Locations.l_7_6);
		state.p_Connected.set(E_Locations.l_10_6, E_Locations.l_9_6);
		state.p_Connected.set(E_Locations.l_4_10, E_Locations.l_5_10);
		state.p_Connected.set(E_Locations.l_3_9, E_Locations.l_2_9);
		state.p_Connected.set(E_Locations.l_8_7, E_Locations.l_8_6);
		state.p_Connected.set(E_Locations.l_7_2, E_Locations.l_7_1);
		state.p_Connected.set(E_Locations.l_6_5, E_Locations.l_6_4);
		state.p_Connected.set(E_Locations.l_10_8, E_Locations.l_10_7);
		state.p_Connected.set(E_Locations.l_9_9, E_Locations.l_9_10);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_10_2, E_Locations.l_9_2);
		state.p_Connected.set(E_Locations.l_8_10, E_Locations.l_9_10);
		state.p_Connected.set(E_Locations.l_7_9, E_Locations.l_7_10);
		state.p_Connected.set(E_Locations.l_2_6, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_10_9, E_Locations.l_10_8);
		state.p_Connected.set(E_Locations.l_6_4, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_7_8, E_Locations.l_7_9);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_8_9, E_Locations.l_8_8);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_10_7, E_Locations.l_9_7);
		state.p_Connected.set(E_Locations.l_2_8, E_Locations.l_2_7);
		state.p_Connected.set(E_Locations.l_3_6, E_Locations.l_2_6);
		state.p_Connected.set(E_Locations.l_7_4, E_Locations.l_8_4);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_6_5);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_7_1, E_Locations.l_7_2);
		state.p_Connected.set(E_Locations.l_9_7, E_Locations.l_9_8);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_1_8, E_Locations.l_1_9);
		state.p_Connected.set(E_Locations.l_2_9, E_Locations.l_3_9);
		state.p_Connected.set(E_Locations.l_9_9, E_Locations.l_10_9);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_5_1);
		state.p_Connected.set(E_Locations.l_9_7, E_Locations.l_10_7);
		state.p_Connected.set(E_Locations.l_6_3, E_Locations.l_6_4);
		state.p_Connected.set(E_Locations.l_6_9, E_Locations.l_6_8);
		state.p_Connected.set(E_Locations.l_8_6, E_Locations.l_8_5);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_8_4, E_Locations.l_9_4);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_3_6, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_5_8, E_Locations.l_6_8);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_5_9, E_Locations.l_6_9);
		state.p_Connected.set(E_Locations.l_5_6, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_8_5, E_Locations.l_9_5);
		state.p_Connected.set(E_Locations.l_10_7, E_Locations.l_10_8);
		state.p_Connected.set(E_Locations.l_8_5, E_Locations.l_8_6);
		state.p_Connected.set(E_Locations.l_8_2, E_Locations.l_7_2);
		state.p_Connected.set(E_Locations.l_1_9, E_Locations.l_2_9);
		state.p_Connected.set(E_Locations.l_7_2, E_Locations.l_7_3);
		state.p_Connected.set(E_Locations.l_4_10, E_Locations.l_4_9);
		state.p_Connected.set(E_Locations.l_8_3, E_Locations.l_8_2);
		state.p_Connected.set(E_Locations.l_4_8, E_Locations.l_5_8);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_8_7, E_Locations.l_9_7);
		state.p_Connected.set(E_Locations.l_6_3, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_2_9, E_Locations.l_2_8);
		state.p_Connected.set(E_Locations.l_10_6, E_Locations.l_10_5);
		state.p_Connected.set(E_Locations.l_3_8, E_Locations.l_3_9);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_2_10, E_Locations.l_3_10);
		state.p_Connected.set(E_Locations.l_8_9, E_Locations.l_9_9);
		state.p_Connected.set(E_Locations.l_8_10, E_Locations.l_8_9);
		state.p_Connected.set(E_Locations.l_2_6, E_Locations.l_2_7);
		state.p_Connected.set(E_Locations.l_10_4, E_Locations.l_10_5);
		state.p_Connected.set(E_Locations.l_3_8, E_Locations.l_3_7);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_4_7, E_Locations.l_3_7);
		state.p_Connected.set(E_Locations.l_9_8, E_Locations.l_10_8);
		state.p_Connected.set(E_Locations.l_10_1, E_Locations.l_10_2);
		state.p_Connected.set(E_Locations.l_5_1, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_7_5, E_Locations.l_6_5);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_6_3);
		state.p_Connected.set(E_Locations.l_8_3, E_Locations.l_7_3);
		state.p_Connected.set(E_Locations.l_10_7, E_Locations.l_10_6);
		state.p_Connected.set(E_Locations.l_6_10, E_Locations.l_7_10);
		state.p_Connected.set(E_Locations.l_8_9, E_Locations.l_7_9);
		state.p_Connected.set(E_Locations.l_6_4, E_Locations.l_7_4);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_4_9, E_Locations.l_3_9);
		state.p_Connected.set(E_Locations.l_8_3, E_Locations.l_9_3);
		state.p_Connected.set(E_Locations.l_6_7, E_Locations.l_6_8);
		state.p_Connected.set(E_Locations.l_2_9, E_Locations.l_1_9);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_9_8, E_Locations.l_9_9);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_2_9, E_Locations.l_2_10);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_7_3, E_Locations.l_7_2);
		state.p_Connected.set(E_Locations.l_9_5, E_Locations.l_9_6);
		state.p_Connected.set(E_Locations.l_10_1, E_Locations.l_9_1);
		state.p_Connected.set(E_Locations.l_7_10, E_Locations.l_8_10);
		state.p_Connected.set(E_Locations.l_4_7, E_Locations.l_5_7);
		state.p_Connected.set(E_Locations.l_5_7, E_Locations.l_6_7);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_2_6);
		state.p_Connected.set(E_Locations.l_5_6, E_Locations.l_6_6);
		state.p_Connected.set(E_Locations.l_2_7, E_Locations.l_2_6);
		state.p_Connected.set(E_Locations.l_7_3, E_Locations.l_6_3);
		state.p_Connected.set(E_Locations.l_6_8, E_Locations.l_7_8);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_1_8, E_Locations.l_1_7);
		state.p_Connected.set(E_Locations.l_5_8, E_Locations.l_5_9);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_3_6);
		state.p_Connected.set(E_Locations.l_6_2, E_Locations.l_6_3);
		state.p_Connected.set(E_Locations.l_6_8, E_Locations.l_5_8);
		state.p_Connected.set(E_Locations.l_8_8, E_Locations.l_9_8);
		state.p_Connected.set(E_Locations.l_6_3, E_Locations.l_7_3);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_8_6, E_Locations.l_7_6);
		state.p_Connected.set(E_Locations.l_2_8, E_Locations.l_2_9);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_6_2, E_Locations.l_7_2);
		state.p_Connected.set(E_Locations.l_1_10, E_Locations.l_2_10);
		state.p_Connected.set(E_Locations.l_7_7, E_Locations.l_8_7);
		state.p_Connected.set(E_Locations.l_1_7, E_Locations.l_1_8);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_5_6);
		state.p_Connected.set(E_Locations.l_10_3, E_Locations.l_10_4);
		state.p_Connected.set(E_Locations.l_3_6, E_Locations.l_3_7);
		state.p_Connected.set(E_Locations.l_10_5, E_Locations.l_9_5);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_4_6);
		state.p_Connected.set(E_Locations.l_10_2, E_Locations.l_10_1);
		state.p_Connected.set(E_Locations.l_9_4, E_Locations.l_8_4);
		state.p_Connected.set(E_Locations.l_3_9, E_Locations.l_3_10);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_1_6, E_Locations.l_1_7);
		state.p_Connected.set(E_Locations.l_5_9, E_Locations.l_5_10);
		state.p_Connected.set(E_Locations.l_2_6, E_Locations.l_3_6);
		state.p_Connected.set(E_Locations.l_2_6, E_Locations.l_1_6);
		state.p_Connected.set(E_Locations.l_6_1, E_Locations.l_5_1);
		state.p_Connected.set(E_Locations.l_10_9, E_Locations.l_10_10);
		state.p_Connected.set(E_Locations.l_7_8, E_Locations.l_8_8);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_6_7, E_Locations.l_5_7);
		state.p_Connected.set(E_Locations.l_1_9, E_Locations.l_1_8);
		state.p_Connected.set(E_Locations.l_4_9, E_Locations.l_4_8);
		state.p_Connected.set(E_Locations.l_1_7, E_Locations.l_2_7);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_10_4, E_Locations.l_9_4);
		state.p_Connected.set(E_Locations.l_10_9, E_Locations.l_9_9);
		state.p_Connected.set(E_Locations.l_8_1, E_Locations.l_7_1);
		state.p_Connected.set(E_Locations.l_4_8, E_Locations.l_4_9);
		state.p_Connected.set(E_Locations.l_9_8, E_Locations.l_8_8);
		state.p_Connected.set(E_Locations.l_7_5, E_Locations.l_7_6);
		state.p_Connected.set(E_Locations.l_2_7, E_Locations.l_3_7);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_9_2, E_Locations.l_9_3);
		state.p_Connected.set(E_Locations.l_6_8, E_Locations.l_6_7);
		state.p_Connected.set(E_Locations.l_4_7, E_Locations.l_4_6);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_3_10, E_Locations.l_4_10);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_1_5, E_Locations.l_1_6);
		state.p_Connected.set(E_Locations.l_5_7, E_Locations.l_4_7);
		state.p_Connected.set(E_Locations.l_9_10, E_Locations.l_10_10);
		state.p_Connected.set(E_Locations.l_8_2, E_Locations.l_8_3);
		state.p_Connected.set(E_Locations.l_6_6, E_Locations.l_6_5);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_9_9, E_Locations.l_9_8);
		state.p_Connected.set(E_Locations.l_10_8, E_Locations.l_9_8);
		state.p_Connected.set(E_Locations.l_7_10, E_Locations.l_7_9);
		state.p_Connected.set(E_Locations.l_8_1, E_Locations.l_8_2);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_1_1);
		state.p_Connected.set(E_Locations.l_2_7, E_Locations.l_1_7);
		state.p_Connected.set(E_Locations.l_4_6, E_Locations.l_3_6);
		state.p_Connected.set(E_Locations.l_6_1, E_Locations.l_6_2);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_1_5);
		state.p_Connected.set(E_Locations.l_10_8, E_Locations.l_10_9);
		state.p_Connected.set(E_Locations.l_8_4, E_Locations.l_7_4);
		state.p_Connected.set(E_Locations.l_8_6, E_Locations.l_9_6);
		state.p_Connected.set(E_Locations.l_5_8, E_Locations.l_5_7);
		state.p_Connected.set(E_Locations.l_9_1, E_Locations.l_10_1);
		state.p_Connected.set(E_Locations.l_9_2, E_Locations.l_9_1);
		state.p_Connected.set(E_Locations.l_9_8, E_Locations.l_9_7);
		state.p_Connected.set(E_Locations.l_3_8, E_Locations.l_2_8);
		state.p_Connected.set(E_Locations.l_5_8, E_Locations.l_4_8);
		state.p_Connected.set(E_Locations.l_9_6, E_Locations.l_8_6);
		state.p_Connected.set(E_Locations.l_9_2, E_Locations.l_8_2);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_8_8, E_Locations.l_8_7);
		state.p_Connected.set(E_Locations.l_4_6, E_Locations.l_5_6);
		state.p_Connected.set(E_Locations.l_10_3, E_Locations.l_10_2);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_6_7, E_Locations.l_7_7);
		state.p_Connected.set(E_Locations.l_9_5, E_Locations.l_8_5);
		state.p_Connected.set(E_Locations.l_7_1, E_Locations.l_8_1);
		state.p_Connected.set(E_Locations.l_5_10, E_Locations.l_5_9);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_8_8, E_Locations.l_7_8);
		state.p_Connected.set(E_Locations.l_8_10, E_Locations.l_7_10);
		state.p_Connected.set(E_Locations.l_6_9, E_Locations.l_7_9);
		state.p_Connected.set(E_Locations.l_9_5, E_Locations.l_10_5);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_6_2, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_7_2, E_Locations.l_8_2);
		state.p_Connected.set(E_Locations.l_7_2, E_Locations.l_6_2);
		state.p_Connected.set(E_Locations.l_1_7, E_Locations.l_1_6);
		state.p_Connected.set(E_Locations.l_2_8, E_Locations.l_3_8);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_6_3, E_Locations.l_6_2);
		state.p_Connected.set(E_Locations.l_6_4, E_Locations.l_6_3);
		state.p_Connected.set(E_Locations.l_6_4, E_Locations.l_6_5);
		state.p_Connected.set(E_Locations.l_8_7, E_Locations.l_7_7);
		state.p_Connected.set(E_Locations.l_5_1, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_1_6, E_Locations.l_2_6);
		state.p_Connected.set(E_Locations.l_3_7, E_Locations.l_3_8);
		state.p_Connected.set(E_Locations.l_1_5, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_7_3, E_Locations.l_8_3);
		state.p_Connected.set(E_Locations.l_1_8, E_Locations.l_2_8);
		state.p_Connected.set(E_Locations.l_6_5, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_9_4, E_Locations.l_9_3);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_1_1, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_9_4, E_Locations.l_10_4);
		state.p_Connected.set(E_Locations.l_9_2, E_Locations.l_10_2);
		state.p_Connected.set(E_Locations.l_10_2, E_Locations.l_10_3);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_2_7, E_Locations.l_2_8);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_7_9, E_Locations.l_6_9);
		state.p_Connected.set(E_Locations.l_5_6, E_Locations.l_4_6);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_8_5, E_Locations.l_8_4);
		state.p_Connected.set(E_Locations.l_5_1, E_Locations.l_6_1);
		state.p_Connected.set(E_Locations.l_7_4, E_Locations.l_6_4);
		state.p_Connected.set(E_Locations.l_8_5, E_Locations.l_7_5);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_7_8, E_Locations.l_6_8);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_1_5);
		state.p_Connected.set(E_Locations.l_6_8, E_Locations.l_6_9);
		state.p_Connected.set(E_Locations.l_9_9, E_Locations.l_8_9);
		state.p_Connected.set(E_Locations.l_4_9, E_Locations.l_5_9);
		state.p_Connected.set(E_Locations.l_4_8, E_Locations.l_3_8);
		state.p_Connected.set(E_Locations.l_3_7, E_Locations.l_2_7);
		state.p_Connected.set(E_Locations.l_4_6, E_Locations.l_4_7);
		state.p_Connected.set(E_Locations.l_6_5, E_Locations.l_6_6);
		state.p_Connected.set(E_Locations.l_1_6, E_Locations.l_1_5);
		state.p_Connected.set(E_Locations.l_3_10, E_Locations.l_2_10);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_4_7, E_Locations.l_4_8);
		state.p_Connected.set(E_Locations.l_7_4, E_Locations.l_7_5);
		state.p_Connected.set(E_Locations.l_4_6, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_6_2);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_9_1, E_Locations.l_8_1);
		state.p_Connected.set(E_Locations.l_6_10, E_Locations.l_5_10);
		state.p_Connected.set(E_Locations.l_9_6, E_Locations.l_9_7);
		state.p_Connected.set(E_Locations.l_2_10, E_Locations.l_1_10);
		state.p_Connected.set(E_Locations.l_7_6, E_Locations.l_6_6);
		state.p_Connected.set(E_Locations.l_10_3, E_Locations.l_9_3);
		state.p_Connected.set(E_Locations.l_5_10, E_Locations.l_4_10);
		state.p_Connected.set(E_Locations.l_9_4, E_Locations.l_9_5);
		state.p_Connected.set(E_Locations.l_9_6, E_Locations.l_10_6);
		state.p_Connected.set(E_Locations.l_5_9, E_Locations.l_5_8);
		state.p_Connected.set(E_Locations.l_3_7, E_Locations.l_3_6);
		state.p_Connected.set(E_Locations.l_10_5, E_Locations.l_10_4);
		state.p_Connected.set(E_Locations.l_8_9, E_Locations.l_8_10);
		state.p_Connected.set(E_Locations.l_2_8, E_Locations.l_1_8);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_10_10, E_Locations.l_10_9);
		state.p_Connected.set(E_Locations.l_9_3, E_Locations.l_9_4);
		state.p_Connected.set(E_Locations.l_1_10, E_Locations.l_1_9);
		state.p_Connected.set(E_Locations.l_6_2, E_Locations.l_6_1);
		state.p_Connected.set(E_Locations.l_8_6, E_Locations.l_8_7);
		state.p_Connected.set(E_Locations.l_8_2, E_Locations.l_8_1);
		state.p_Connected.set(E_Locations.l_9_10, E_Locations.l_9_9);
		state.p_Connected.set(E_Locations.l_7_7, E_Locations.l_7_6);
		state.p_Connected.set(E_Locations.l_6_6, E_Locations.l_5_6);
		state.p_Connected.set(E_Locations.l_5_6, E_Locations.l_5_7);
		state.p_Connected.set(E_Locations.l_3_7, E_Locations.l_4_7);
		state.p_Connected.set(E_Locations.l_9_3, E_Locations.l_10_3);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_9_7, E_Locations.l_9_6);
		state.p_Connected.set(E_Locations.l_7_10, E_Locations.l_6_10);
		state.p_Connected.set(E_Locations.l_9_10, E_Locations.l_8_10);
		state.p_Connected.set(E_Locations.l_4_10, E_Locations.l_3_10);
		state.p_Connected.set(E_Locations.l_5_10, E_Locations.l_6_10);
		state.p_Connected.set(E_Locations.l_7_7, E_Locations.l_7_8);
		state.p_Connected.set(E_Locations.l_7_8, E_Locations.l_7_7);
		state.p_Connected.set(E_Locations.l_8_8, E_Locations.l_8_9);
		state.p_Connected.set(E_Locations.l_6_10, E_Locations.l_6_9);
		state.p_Connected.set(E_Locations.l_10_10, E_Locations.l_9_10);
		state.p_Connected.set(E_Locations.l_7_6, E_Locations.l_7_7);
		state.p_Connected.set(E_Locations.l_7_5, E_Locations.l_8_5);
		state.p_Connected.set(E_Locations.l_9_3, E_Locations.l_9_2);
		state.p_Connected.set(E_Locations.l_7_6, E_Locations.l_7_5);
		state.p_Connected.set(E_Locations.l_8_7, E_Locations.l_8_8);
		state.p_Connected.set(E_Locations.l_9_5, E_Locations.l_9_4);
		state.p_Connected.set(E_Locations.l_7_9, E_Locations.l_7_8);
		state.p_Connected.set(E_Locations.l_9_6, E_Locations.l_9_5);
		state.p_Connected.set(E_Locations.l_7_5, E_Locations.l_7_4);
		state.p_Connected.set(E_Locations.l_3_10, E_Locations.l_3_9);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_3_6, E_Locations.l_4_6);
		state.p_Connected.set(E_Locations.l_1_1, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_7_1, E_Locations.l_6_1);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_8_3, E_Locations.l_8_4);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_8_4, E_Locations.l_8_5);
		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_5_1);
		state.p_Connected.set(E_Locations.l_6_6, E_Locations.l_6_7);
		state.p_Connected.set(E_Locations.l_6_9, E_Locations.l_5_9);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_1_1);
		state.p_Connected.set(E_Locations.l_8_1, E_Locations.l_9_1);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_6_9, E_Locations.l_6_10);
		state.p_Connected.set(E_Locations.l_6_5, E_Locations.l_7_5);
		state.p_Connected.set(E_Locations.l_1_9, E_Locations.l_1_10);
		state.p_Connected.set(E_Locations.l_4_9, E_Locations.l_4_10);
		state.p_Connected.set(E_Locations.l_3_9, E_Locations.l_4_9);
		state.p_Connected.set(E_Locations.l_6_1, E_Locations.l_7_1);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_9_1, E_Locations.l_9_2);
		state.p_Connected.set(E_Locations.l_9_3, E_Locations.l_8_3);
		state.p_Connected.set(E_Locations.l_7_6, E_Locations.l_8_6);
		state.p_Connected.set(E_Locations.l_7_3, E_Locations.l_7_4);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_3_3);
		

		state.p_Accessible.set(E_Locations.l_9_9);
		state.p_Accessible.set(E_Locations.l_4_9);
		state.p_Accessible.set(E_Locations.l_6_5);
		state.p_Accessible.set(E_Locations.l_3_8);
		state.p_Accessible.set(E_Locations.l_6_1);
		state.p_Accessible.set(E_Locations.l_3_6);
		state.p_Accessible.set(E_Locations.l_1_2);
		state.p_Accessible.set(E_Locations.l_8_9);
		state.p_Accessible.set(E_Locations.l_10_1);
		state.p_Accessible.set(E_Locations.l_2_5);
		state.p_Accessible.set(E_Locations.l_2_7);
		state.p_Accessible.set(E_Locations.l_4_1);
		state.p_Accessible.set(E_Locations.l_6_8);
		state.p_Accessible.set(E_Locations.l_2_4);
		state.p_Accessible.set(E_Locations.l_9_2);
		state.p_Accessible.set(E_Locations.l_8_8);
		state.p_Accessible.set(E_Locations.l_9_10);
		state.p_Accessible.set(E_Locations.l_5_1);
		state.p_Accessible.set(E_Locations.l_7_7);
		state.p_Accessible.set(E_Locations.l_2_9);
		state.p_Accessible.set(E_Locations.l_6_10);
		state.p_Accessible.set(E_Locations.l_5_6);
		state.p_Accessible.set(E_Locations.l_1_1);
		state.p_Accessible.set(E_Locations.l_8_5);
		state.p_Accessible.set(E_Locations.l_4_7);
		state.p_Accessible.set(E_Locations.l_9_1);
		state.p_Accessible.set(E_Locations.l_8_7);
		state.p_Accessible.set(E_Locations.l_1_8);
		state.p_Accessible.set(E_Locations.l_5_9);
		state.p_Accessible.set(E_Locations.l_7_4);
		state.p_Accessible.set(E_Locations.l_6_6);
		state.p_Accessible.set(E_Locations.l_10_10);
		state.p_Accessible.set(E_Locations.l_10_5);
		state.p_Accessible.set(E_Locations.l_5_7);
		state.p_Accessible.set(E_Locations.l_4_5);
		state.p_Accessible.set(E_Locations.l_10_2);
		state.p_Accessible.set(E_Locations.l_1_5);
		state.p_Accessible.set(E_Locations.l_1_4);
		state.p_Accessible.set(E_Locations.l_6_3);
		state.p_Accessible.set(E_Locations.l_4_6);
		state.p_Accessible.set(E_Locations.l_5_10);
		state.p_Accessible.set(E_Locations.l_9_7);
		state.p_Accessible.set(E_Locations.l_1_6);
		state.p_Accessible.set(E_Locations.l_3_10);
		state.p_Accessible.set(E_Locations.l_10_3);
		state.p_Accessible.set(E_Locations.l_8_6);
		state.p_Accessible.set(E_Locations.l_5_4);
		state.p_Accessible.set(E_Locations.l_7_5);
		state.p_Accessible.set(E_Locations.l_8_4);
		state.p_Accessible.set(E_Locations.l_8_3);
		state.p_Accessible.set(E_Locations.l_3_4);
		state.p_Accessible.set(E_Locations.l_3_3);
		state.p_Accessible.set(E_Locations.l_7_3);
		state.p_Accessible.set(E_Locations.l_5_8);
		state.p_Accessible.set(E_Locations.l_1_3);
		state.p_Accessible.set(E_Locations.l_2_1);
		state.p_Accessible.set(E_Locations.l_2_3);
		state.p_Accessible.set(E_Locations.l_1_9);
		state.p_Accessible.set(E_Locations.l_1_10);
		state.p_Accessible.set(E_Locations.l_6_4);
		state.p_Accessible.set(E_Locations.l_3_9);
		state.p_Accessible.set(E_Locations.l_8_10);
		state.p_Accessible.set(E_Locations.l_5_3);
		state.p_Accessible.set(E_Locations.l_7_6);
		state.p_Accessible.set(E_Locations.l_7_9);
		state.p_Accessible.set(E_Locations.l_10_6);
		state.p_Accessible.set(E_Locations.l_7_2);
		state.p_Accessible.set(E_Locations.l_10_7);
		state.p_Accessible.set(E_Locations.l_9_5);
		state.p_Accessible.set(E_Locations.l_2_8);
		state.p_Accessible.set(E_Locations.l_10_4);
		state.p_Accessible.set(E_Locations.l_3_7);
		state.p_Accessible.set(E_Locations.l_10_8);
		state.p_Accessible.set(E_Locations.l_2_2);
		state.p_Accessible.set(E_Locations.l_4_2);
		state.p_Accessible.set(E_Locations.l_7_8);
		state.p_Accessible.set(E_Locations.l_9_4);
		state.p_Accessible.set(E_Locations.l_6_2);
		state.p_Accessible.set(E_Locations.l_3_2);
		state.p_Accessible.set(E_Locations.l_7_1);
		state.p_Accessible.set(E_Locations.l_2_6);
		state.p_Accessible.set(E_Locations.l_6_9);
		state.p_Accessible.set(E_Locations.l_2_10);
		state.p_Accessible.set(E_Locations.l_4_8);
		state.p_Accessible.set(E_Locations.l_4_10);
		state.p_Accessible.set(E_Locations.l_8_2);
		state.p_Accessible.set(E_Locations.l_3_5);
		state.p_Accessible.set(E_Locations.l_4_4);
		state.p_Accessible.set(E_Locations.l_1_7);
		state.p_Accessible.set(E_Locations.l_9_3);
		state.p_Accessible.set(E_Locations.l_4_3);
		state.p_Accessible.set(E_Locations.l_7_10);
		state.p_Accessible.set(E_Locations.l_9_6);
		state.p_Accessible.set(E_Locations.l_5_2);
		state.p_Accessible.set(E_Locations.l_9_8);
		state.p_Accessible.set(E_Locations.l_3_1);
		state.p_Accessible.set(E_Locations.l_10_9);
		state.p_Accessible.set(E_Locations.l_5_5);
		state.p_Accessible.set(E_Locations.l_8_1);
		state.p_Accessible.set(E_Locations.l_6_7);
		

		state.p_Solid.set(E_Locations.l_7_10);
		state.p_Solid.set(E_Locations.l_1_7);
		state.p_Solid.set(E_Locations.l_10_5);
		state.p_Solid.set(E_Locations.l_5_2);
		state.p_Solid.set(E_Locations.l_4_7);
		state.p_Solid.set(E_Locations.l_5_6);
		state.p_Solid.set(E_Locations.l_4_2);
		state.p_Solid.set(E_Locations.l_9_4);
		state.p_Solid.set(E_Locations.l_3_7);
		state.p_Solid.set(E_Locations.l_8_5);
		state.p_Solid.set(E_Locations.l_5_1);
		state.p_Solid.set(E_Locations.l_10_6);
		state.p_Solid.set(E_Locations.l_8_10);
		state.p_Solid.set(E_Locations.l_9_10);
		state.p_Solid.set(E_Locations.l_7_7);
		state.p_Solid.set(E_Locations.l_2_7);
		state.p_Solid.set(E_Locations.l_3_10);
		state.p_Solid.set(E_Locations.l_9_7);
		state.p_Solid.set(E_Locations.l_7_2);
		state.p_Solid.set(E_Locations.l_2_2);
		state.p_Solid.set(E_Locations.l_6_10);
		state.p_Solid.set(E_Locations.l_9_9);
		state.p_Solid.set(E_Locations.l_1_6);
		state.p_Solid.set(E_Locations.l_5_5);
		state.p_Solid.set(E_Locations.l_1_2);
		state.p_Solid.set(E_Locations.l_7_3);
		state.p_Solid.set(E_Locations.l_3_2);
		state.p_Solid.set(E_Locations.l_8_9);
		state.p_Solid.set(E_Locations.l_3_4);
		state.p_Solid.set(E_Locations.l_1_5);
		state.p_Solid.set(E_Locations.l_6_9);
		state.p_Solid.set(E_Locations.l_3_5);
		state.p_Solid.set(E_Locations.l_1_4);
		state.p_Solid.set(E_Locations.l_4_8);
		state.p_Solid.set(E_Locations.l_9_6);
		state.p_Solid.set(E_Locations.l_6_3);
		state.p_Solid.set(E_Locations.l_5_4);
		state.p_Solid.set(E_Locations.l_1_1);
		state.p_Solid.set(E_Locations.l_3_1);
		state.p_Solid.set(E_Locations.l_8_7);
		state.p_Solid.set(E_Locations.l_9_1);
		state.p_Solid.set(E_Locations.l_7_8);
		state.p_Solid.set(E_Locations.l_5_9);
		state.p_Solid.set(E_Locations.l_5_3);
		state.p_Solid.set(E_Locations.l_1_9);
		state.p_Solid.set(E_Locations.l_7_6);
		state.p_Solid.set(E_Locations.l_2_3);
		state.p_Solid.set(E_Locations.l_8_8);
		state.p_Solid.set(E_Locations.l_9_8);
		state.p_Solid.set(E_Locations.l_6_4);
		state.p_Solid.set(E_Locations.l_7_5);
		state.p_Solid.set(E_Locations.l_3_6);
		state.p_Solid.set(E_Locations.l_5_10);
		state.p_Solid.set(E_Locations.l_3_3);
		state.p_Solid.set(E_Locations.l_4_9);
		state.p_Solid.set(E_Locations.l_8_3);
		state.p_Solid.set(E_Locations.l_10_1);
		state.p_Solid.set(E_Locations.l_8_1);
		state.p_Solid.set(E_Locations.l_3_8);
		state.p_Solid.set(E_Locations.l_10_2);
		state.p_Solid.set(E_Locations.l_1_8);
		state.p_Solid.set(E_Locations.l_2_1);
		state.p_Solid.set(E_Locations.l_2_10);
		

		state.p_Medium.set(E_Locations.l_2_4);
		state.p_Medium.set(E_Locations.l_6_5);
		state.p_Medium.set(E_Locations.l_6_6);
		state.p_Medium.set(E_Locations.l_10_3);
		state.p_Medium.set(E_Locations.l_3_9);
		state.p_Medium.set(E_Locations.l_10_7);
		state.p_Medium.set(E_Locations.l_9_5);
		state.p_Medium.set(E_Locations.l_10_8);
		state.p_Medium.set(E_Locations.l_1_3);
		state.p_Medium.set(E_Locations.l_7_1);
		state.p_Medium.set(E_Locations.l_8_2);
		state.p_Medium.set(E_Locations.l_6_2);
		state.p_Medium.set(E_Locations.l_9_3);
		

		state.p_Small.set(E_Locations.l_2_5);
		state.p_Small.set(E_Locations.l_2_8);
		state.p_Small.set(E_Locations.l_7_4);
		state.p_Small.set(E_Locations.l_10_10);
		state.p_Small.set(E_Locations.l_10_9);
		state.p_Small.set(E_Locations.l_10_4);
		state.p_Small.set(E_Locations.l_6_8);
		state.p_Small.set(E_Locations.l_9_2);
		state.p_Small.set(E_Locations.l_4_4);
		

		state.p_Big.set(E_Locations.l_4_6);
		state.p_Big.set(E_Locations.l_4_1);
		state.p_Big.set(E_Locations.l_6_7);
		state.p_Big.set(E_Locations.l_5_7);
		state.p_Big.set(E_Locations.l_6_1);
		state.p_Big.set(E_Locations.l_4_3);
		state.p_Big.set(E_Locations.l_2_9);
		state.p_Big.set(E_Locations.l_2_6);
		state.p_Big.set(E_Locations.l_1_10);
		state.p_Big.set(E_Locations.l_8_4);
		state.p_Big.set(E_Locations.l_4_10);
		state.p_Big.set(E_Locations.l_5_8);
		state.p_Big.set(E_Locations.l_8_6);
		state.p_Big.set(E_Locations.l_7_9);
		state.p_Big.set(E_Locations.l_4_5);
		

	}
	
	@Override
	public String getName() {
		return "Perestroika-Problem";
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