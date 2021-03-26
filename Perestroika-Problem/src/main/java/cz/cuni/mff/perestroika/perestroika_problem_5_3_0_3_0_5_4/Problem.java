package cz.cuni.mff.perestroika.perestroika_problem_5_3_0_3_0_5_4;

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

		state.p_AtRes.set(E_Resources.r1, E_Locations.l_1_5);
		state.p_AtRes.set(E_Resources.r3, E_Locations.l_4_3);
		state.p_AtRes.set(E_Resources.r2, E_Locations.l_2_5);
		state.p_AtRes.set(E_Resources.r4, E_Locations.l_5_1);
		

		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_1_5, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_1_1);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_1_1, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_1_5);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_1_5);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_1_1);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_1_4);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_1_3, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_2_5, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_1_2);
		state.p_Connected.set(E_Locations.l_3_1, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_3_5);
		state.p_Connected.set(E_Locations.l_5_3, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_1_1, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_4_2, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_1_5, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_4_1, E_Locations.l_5_1);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_3_2);
		state.p_Connected.set(E_Locations.l_5_1, E_Locations.l_4_1);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_5_5);
		state.p_Connected.set(E_Locations.l_5_4, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_1_2, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_5_1, E_Locations.l_5_2);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_5_1);
		state.p_Connected.set(E_Locations.l_3_3, E_Locations.l_2_3);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_4_3);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_5_4);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_4_2);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_2_2);
		state.p_Connected.set(E_Locations.l_4_3, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_3_2, E_Locations.l_3_3);
		state.p_Connected.set(E_Locations.l_4_5, E_Locations.l_4_4);
		state.p_Connected.set(E_Locations.l_2_2, E_Locations.l_2_1);
		state.p_Connected.set(E_Locations.l_2_4, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_4_4, E_Locations.l_3_4);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_2_4);
		state.p_Connected.set(E_Locations.l_1_4, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_5_5, E_Locations.l_4_5);
		state.p_Connected.set(E_Locations.l_5_2, E_Locations.l_5_3);
		state.p_Connected.set(E_Locations.l_2_3, E_Locations.l_1_3);
		state.p_Connected.set(E_Locations.l_3_5, E_Locations.l_2_5);
		state.p_Connected.set(E_Locations.l_2_1, E_Locations.l_3_1);
		state.p_Connected.set(E_Locations.l_3_4, E_Locations.l_3_5);
		

		state.p_Accessible.set(E_Locations.l_4_3);
		state.p_Accessible.set(E_Locations.l_2_4);
		state.p_Accessible.set(E_Locations.l_3_1);
		state.p_Accessible.set(E_Locations.l_1_2);
		state.p_Accessible.set(E_Locations.l_4_5);
		state.p_Accessible.set(E_Locations.l_3_5);
		state.p_Accessible.set(E_Locations.l_5_3);
		state.p_Accessible.set(E_Locations.l_1_1);
		state.p_Accessible.set(E_Locations.l_3_4);
		state.p_Accessible.set(E_Locations.l_5_1);
		state.p_Accessible.set(E_Locations.l_4_2);
		state.p_Accessible.set(E_Locations.l_4_1);
		state.p_Accessible.set(E_Locations.l_2_3);
		state.p_Accessible.set(E_Locations.l_5_4);
		state.p_Accessible.set(E_Locations.l_1_3);
		state.p_Accessible.set(E_Locations.l_2_2);
		state.p_Accessible.set(E_Locations.l_1_4);
		state.p_Accessible.set(E_Locations.l_5_2);
		state.p_Accessible.set(E_Locations.l_3_3);
		state.p_Accessible.set(E_Locations.l_2_1);
		state.p_Accessible.set(E_Locations.l_1_5);
		state.p_Accessible.set(E_Locations.l_5_5);
		state.p_Accessible.set(E_Locations.l_3_2);
		state.p_Accessible.set(E_Locations.l_2_5);
		state.p_Accessible.set(E_Locations.l_4_4);
		

		state.p_Solid.set(E_Locations.l_1_5);
		state.p_Solid.set(E_Locations.l_3_3);
		state.p_Solid.set(E_Locations.l_5_5);
		state.p_Solid.set(E_Locations.l_2_5);
		state.p_Solid.set(E_Locations.l_4_3);
		state.p_Solid.set(E_Locations.l_3_1);
		state.p_Solid.set(E_Locations.l_4_5);
		state.p_Solid.set(E_Locations.l_1_1);
		state.p_Solid.set(E_Locations.l_5_1);
		

		state.p_Medium.set(E_Locations.l_3_2);
		state.p_Medium.set(E_Locations.l_2_1);
		state.p_Medium.set(E_Locations.l_5_3);
		state.p_Medium.set(E_Locations.l_2_3);
		state.p_Medium.set(E_Locations.l_1_3);
		state.p_Medium.set(E_Locations.l_1_2);
		

		state.p_Small.set(E_Locations.l_3_4);
		state.p_Small.set(E_Locations.l_1_4);
		state.p_Small.set(E_Locations.l_4_2);
		state.p_Small.set(E_Locations.l_5_4);
		state.p_Small.set(E_Locations.l_5_2);
		

		state.p_Big.set(E_Locations.l_3_5);
		state.p_Big.set(E_Locations.l_2_4);
		state.p_Big.set(E_Locations.l_4_1);
		state.p_Big.set(E_Locations.l_2_2);
		state.p_Big.set(E_Locations.l_4_4);
		

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