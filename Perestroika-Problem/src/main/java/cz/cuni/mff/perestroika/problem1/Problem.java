package cz.cuni.mff.perestroika.problem1;

import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.perestroika.domain.Domain;
import cz.cuni.mff.perestroika.domain.State;

public final class Problem extends PDDLProblem {
	
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
		state.p_AtAgent.set(E_Locations.l11);
		state.p_AtRes.set(E_Resources.r1, E_Locations.l15);
		state.p_AtRes.set(E_Resources.r2, E_Locations.l33);
		state.p_AtRes.set(E_Resources.r3, E_Locations.l55);
		state.p_AtRes.set(E_Resources.r4, E_Locations.l51);
		
		state.p_Connected.set(E_Locations.l11, E_Locations.l21);
		state.p_Connected.set(E_Locations.l21, E_Locations.l11);
		state.p_Connected.set(E_Locations.l11, E_Locations.l12);
		state.p_Connected.set(E_Locations.l12, E_Locations.l11);
		state.p_Connected.set(E_Locations.l12, E_Locations.l22);
		state.p_Connected.set(E_Locations.l22, E_Locations.l12);
		state.p_Connected.set(E_Locations.l12, E_Locations.l13);
		state.p_Connected.set(E_Locations.l13, E_Locations.l12);
		state.p_Connected.set(E_Locations.l13, E_Locations.l23);
		state.p_Connected.set(E_Locations.l23, E_Locations.l13);
		state.p_Connected.set(E_Locations.l13, E_Locations.l14);
		state.p_Connected.set(E_Locations.l14, E_Locations.l13);
		state.p_Connected.set(E_Locations.l14, E_Locations.l24);
		state.p_Connected.set(E_Locations.l24, E_Locations.l14);
		state.p_Connected.set(E_Locations.l14, E_Locations.l15);
		state.p_Connected.set(E_Locations.l15, E_Locations.l14);
		state.p_Connected.set(E_Locations.l15, E_Locations.l25);
		state.p_Connected.set(E_Locations.l25, E_Locations.l15);
		state.p_Connected.set(E_Locations.l21, E_Locations.l31);
		state.p_Connected.set(E_Locations.l31, E_Locations.l21);
		state.p_Connected.set(E_Locations.l21, E_Locations.l22);
		state.p_Connected.set(E_Locations.l22, E_Locations.l21);
		state.p_Connected.set(E_Locations.l22, E_Locations.l32);
		state.p_Connected.set(E_Locations.l32, E_Locations.l22);
		state.p_Connected.set(E_Locations.l22, E_Locations.l23);
		state.p_Connected.set(E_Locations.l23, E_Locations.l22);
		state.p_Connected.set(E_Locations.l23, E_Locations.l33);
		state.p_Connected.set(E_Locations.l33, E_Locations.l23);
		state.p_Connected.set(E_Locations.l23, E_Locations.l24);
		state.p_Connected.set(E_Locations.l24, E_Locations.l23);
		state.p_Connected.set(E_Locations.l24, E_Locations.l34);
		state.p_Connected.set(E_Locations.l34, E_Locations.l24);
		state.p_Connected.set(E_Locations.l24, E_Locations.l25);
		state.p_Connected.set(E_Locations.l25, E_Locations.l24);
		state.p_Connected.set(E_Locations.l25, E_Locations.l35);
		state.p_Connected.set(E_Locations.l35, E_Locations.l25);
		state.p_Connected.set(E_Locations.l31, E_Locations.l41);
		state.p_Connected.set(E_Locations.l41, E_Locations.l31);
		state.p_Connected.set(E_Locations.l31, E_Locations.l32);
		state.p_Connected.set(E_Locations.l32, E_Locations.l31);
		state.p_Connected.set(E_Locations.l32, E_Locations.l42);
		state.p_Connected.set(E_Locations.l42, E_Locations.l32);
		state.p_Connected.set(E_Locations.l32, E_Locations.l33);
		state.p_Connected.set(E_Locations.l33, E_Locations.l32);
		state.p_Connected.set(E_Locations.l33, E_Locations.l43);
		state.p_Connected.set(E_Locations.l43, E_Locations.l33);
		state.p_Connected.set(E_Locations.l33, E_Locations.l34);
		state.p_Connected.set(E_Locations.l34, E_Locations.l33);
		state.p_Connected.set(E_Locations.l34, E_Locations.l44);
		state.p_Connected.set(E_Locations.l44, E_Locations.l34);
		state.p_Connected.set(E_Locations.l34, E_Locations.l35);
		state.p_Connected.set(E_Locations.l35, E_Locations.l34);
		state.p_Connected.set(E_Locations.l35, E_Locations.l45);
		state.p_Connected.set(E_Locations.l45, E_Locations.l35);
		state.p_Connected.set(E_Locations.l41, E_Locations.l51);
		state.p_Connected.set(E_Locations.l51, E_Locations.l41);
		state.p_Connected.set(E_Locations.l41, E_Locations.l42);
		state.p_Connected.set(E_Locations.l42, E_Locations.l41);
		state.p_Connected.set(E_Locations.l42, E_Locations.l52);
		state.p_Connected.set(E_Locations.l52, E_Locations.l42);
		state.p_Connected.set(E_Locations.l42, E_Locations.l43);
		state.p_Connected.set(E_Locations.l43, E_Locations.l42);
		state.p_Connected.set(E_Locations.l43, E_Locations.l53);
		state.p_Connected.set(E_Locations.l53, E_Locations.l43);
		state.p_Connected.set(E_Locations.l43, E_Locations.l44);
		state.p_Connected.set(E_Locations.l44, E_Locations.l43);
		state.p_Connected.set(E_Locations.l44, E_Locations.l54);
		state.p_Connected.set(E_Locations.l54, E_Locations.l44);
		state.p_Connected.set(E_Locations.l44, E_Locations.l45);
		state.p_Connected.set(E_Locations.l45, E_Locations.l44);
		state.p_Connected.set(E_Locations.l45, E_Locations.l55);
		state.p_Connected.set(E_Locations.l55, E_Locations.l45);
		state.p_Connected.set(E_Locations.l51, E_Locations.l52);
		state.p_Connected.set(E_Locations.l52, E_Locations.l51);
		state.p_Connected.set(E_Locations.l52, E_Locations.l53);
		state.p_Connected.set(E_Locations.l53, E_Locations.l52);
		state.p_Connected.set(E_Locations.l53, E_Locations.l54);
		state.p_Connected.set(E_Locations.l54, E_Locations.l53);
		state.p_Connected.set(E_Locations.l54, E_Locations.l55);
		state.p_Connected.set(E_Locations.l55, E_Locations.l54);
		
		state.p_Accessible.set(E_Locations.l11);
		state.p_Solid.set(E_Locations.l11);
		state.p_Accessible.set(E_Locations.l12);
		state.p_Medium.set(E_Locations.l12);
		state.p_Accessible.set(E_Locations.l13);
		state.p_Solid.set(E_Locations.l13);
		state.p_Accessible.set(E_Locations.l14);
		state.p_Small.set(E_Locations.l14);
		state.p_Accessible.set(E_Locations.l15);
		state.p_Solid.set(E_Locations.l15);
		state.p_Accessible.set(E_Locations.l21);
		state.p_Big.set(E_Locations.l21);
		state.p_Accessible.set(E_Locations.l22);
		state.p_Solid.set(E_Locations.l22);
		state.p_Accessible.set(E_Locations.l23);
		state.p_Small.set(E_Locations.l23);
		state.p_Accessible.set(E_Locations.l24);
		state.p_Solid.set(E_Locations.l24);
		state.p_Accessible.set(E_Locations.l25);
		state.p_Medium.set(E_Locations.l25);
		state.p_Accessible.set(E_Locations.l31);
		state.p_Solid.set(E_Locations.l31);
		state.p_Accessible.set(E_Locations.l32);
		state.p_Small.set(E_Locations.l32);
		state.p_Accessible.set(E_Locations.l33);
		state.p_Solid.set(E_Locations.l33);
		state.p_Accessible.set(E_Locations.l34);
		state.p_Big.set(E_Locations.l34);
		state.p_Accessible.set(E_Locations.l35);
		state.p_Solid.set(E_Locations.l35);
		state.p_Accessible.set(E_Locations.l41);
		state.p_Small.set(E_Locations.l41);
		state.p_Accessible.set(E_Locations.l42);
		state.p_Solid.set(E_Locations.l42);
		state.p_Accessible.set(E_Locations.l43);
		state.p_Big.set(E_Locations.l43);
		state.p_Accessible.set(E_Locations.l44);
		state.p_Solid.set(E_Locations.l44);
		state.p_Accessible.set(E_Locations.l45);
		state.p_Small.set(E_Locations.l45);
		state.p_Accessible.set(E_Locations.l51);
		state.p_Solid.set(E_Locations.l51);
		state.p_Accessible.set(E_Locations.l52);
		state.p_Medium.set(E_Locations.l52);
		state.p_Accessible.set(E_Locations.l53);
		state.p_Solid.set(E_Locations.l53);
		state.p_Accessible.set(E_Locations.l54);
		state.p_Small.set(E_Locations.l54);
		state.p_Accessible.set(E_Locations.l55);
		state.p_Solid.set(E_Locations.l55);			
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
