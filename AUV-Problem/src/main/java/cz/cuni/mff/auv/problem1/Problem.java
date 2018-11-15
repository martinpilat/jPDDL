package cz.cuni.mff.auv.problem1;

import cz.cuni.mff.auv.domain.Domain;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.problem.AuvProblem;
import cz.cuni.mff.jpddl.PDDLDeadEnd;

public final class Problem extends AuvProblem {
	
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
		state.p_Operational.set(E_Auvs.THIS.a);
		state.p_Outside.set(E_Ships.THIS.s);
		state.p_At.set(E_Auvs.THIS.a, E_Locations.THIS.l11);
		state.p_AtRes.set(E_Resources.THIS.r1, E_Locations.THIS.l27);
		state.p_AtRes.set(E_Resources.THIS.r2, E_Locations.THIS.l76);
		state.p_AtRes.set(E_Resources.THIS.r3, E_Locations.THIS.l82);
		
//		state.p_AtRes.set(E_Resources.THIS.r1, E_Locations.THIS.l12);
//		state.p_AtRes.set(E_Resources.THIS.r2, E_Locations.THIS.l21);
//		state.p_AtRes.set(E_Resources.THIS.r3, E_Locations.THIS.l22);
		
		state.p_Free.set(E_Locations.THIS.l12);
		state.p_Free.set(E_Locations.THIS.l13);
		state.p_Free.set(E_Locations.THIS.l14);
		state.p_Free.set(E_Locations.THIS.l15);
		state.p_Free.set(E_Locations.THIS.l16);
		state.p_Free.set(E_Locations.THIS.l17);
		state.p_Free.set(E_Locations.THIS.l18);
		state.p_Free.set(E_Locations.THIS.l21);
		state.p_Free.set(E_Locations.THIS.l22);
		state.p_Free.set(E_Locations.THIS.l23);
		state.p_Free.set(E_Locations.THIS.l24);
		state.p_Free.set(E_Locations.THIS.l25);
		state.p_Free.set(E_Locations.THIS.l26);
		state.p_Free.set(E_Locations.THIS.l27);
		state.p_Free.set(E_Locations.THIS.l28);
		state.p_Free.set(E_Locations.THIS.l31);
		state.p_Free.set(E_Locations.THIS.l32);
		state.p_Free.set(E_Locations.THIS.l33);
		state.p_Free.set(E_Locations.THIS.l34);
		state.p_Free.set(E_Locations.THIS.l35);
		state.p_Free.set(E_Locations.THIS.l36);
		state.p_Free.set(E_Locations.THIS.l37);
		state.p_Free.set(E_Locations.THIS.l38);
		state.p_Free.set(E_Locations.THIS.l41);
		state.p_Free.set(E_Locations.THIS.l42);
		state.p_Free.set(E_Locations.THIS.l43);
		state.p_Free.set(E_Locations.THIS.l44);
		state.p_Free.set(E_Locations.THIS.l45);
		state.p_Free.set(E_Locations.THIS.l46);
		state.p_Free.set(E_Locations.THIS.l47);
		state.p_Free.set(E_Locations.THIS.l48);
		state.p_Free.set(E_Locations.THIS.l51);
		state.p_Free.set(E_Locations.THIS.l52);
		state.p_Free.set(E_Locations.THIS.l53);
		state.p_Free.set(E_Locations.THIS.l54);
		state.p_Free.set(E_Locations.THIS.l55);
		state.p_Free.set(E_Locations.THIS.l56);
		state.p_Free.set(E_Locations.THIS.l57);
		state.p_Free.set(E_Locations.THIS.l58);
		state.p_Free.set(E_Locations.THIS.l61);
		state.p_Free.set(E_Locations.THIS.l62);
		state.p_Free.set(E_Locations.THIS.l63);
		state.p_Free.set(E_Locations.THIS.l64);
		state.p_Free.set(E_Locations.THIS.l65);
		state.p_Free.set(E_Locations.THIS.l66);
		state.p_Free.set(E_Locations.THIS.l67);
		state.p_Free.set(E_Locations.THIS.l68);
		state.p_Free.set(E_Locations.THIS.l71);
		state.p_Free.set(E_Locations.THIS.l72);
		state.p_Free.set(E_Locations.THIS.l73);
		state.p_Free.set(E_Locations.THIS.l74);
		state.p_Free.set(E_Locations.THIS.l75);
		state.p_Free.set(E_Locations.THIS.l76);
		state.p_Free.set(E_Locations.THIS.l77);
		state.p_Free.set(E_Locations.THIS.l78);
		state.p_Free.set(E_Locations.THIS.l81);
		state.p_Free.set(E_Locations.THIS.l82);
		state.p_Free.set(E_Locations.THIS.l83);
		state.p_Free.set(E_Locations.THIS.l84);
		state.p_Free.set(E_Locations.THIS.l85);
		state.p_Free.set(E_Locations.THIS.l86);
		state.p_Free.set(E_Locations.THIS.l87);
		state.p_Free.set(E_Locations.THIS.l88);
		
		state.p_DupFree.set(E_Locations.THIS.l12);
		state.p_DupFree.set(E_Locations.THIS.l13);
		state.p_DupFree.set(E_Locations.THIS.l14);
		state.p_DupFree.set(E_Locations.THIS.l15);
		state.p_DupFree.set(E_Locations.THIS.l16);
		state.p_DupFree.set(E_Locations.THIS.l17);
		state.p_DupFree.set(E_Locations.THIS.l18);
		state.p_DupFree.set(E_Locations.THIS.l21);
		state.p_DupFree.set(E_Locations.THIS.l22);
		state.p_DupFree.set(E_Locations.THIS.l23);
		state.p_DupFree.set(E_Locations.THIS.l24);
		state.p_DupFree.set(E_Locations.THIS.l25);
		state.p_DupFree.set(E_Locations.THIS.l26);
		state.p_DupFree.set(E_Locations.THIS.l27);
		state.p_DupFree.set(E_Locations.THIS.l28);
		state.p_DupFree.set(E_Locations.THIS.l31);
		state.p_DupFree.set(E_Locations.THIS.l32);
		state.p_DupFree.set(E_Locations.THIS.l33);
		state.p_DupFree.set(E_Locations.THIS.l34);
		state.p_DupFree.set(E_Locations.THIS.l35);
		state.p_DupFree.set(E_Locations.THIS.l36);
		state.p_DupFree.set(E_Locations.THIS.l37);
		state.p_DupFree.set(E_Locations.THIS.l38);
		state.p_DupFree.set(E_Locations.THIS.l41);
		state.p_DupFree.set(E_Locations.THIS.l42);
		state.p_DupFree.set(E_Locations.THIS.l43);
		state.p_DupFree.set(E_Locations.THIS.l44);
		state.p_DupFree.set(E_Locations.THIS.l45);
		state.p_DupFree.set(E_Locations.THIS.l46);
		state.p_DupFree.set(E_Locations.THIS.l47);
		state.p_DupFree.set(E_Locations.THIS.l48);
		state.p_DupFree.set(E_Locations.THIS.l51);
		state.p_DupFree.set(E_Locations.THIS.l52);
		state.p_DupFree.set(E_Locations.THIS.l53);
		state.p_DupFree.set(E_Locations.THIS.l54);
		state.p_DupFree.set(E_Locations.THIS.l55);
		state.p_DupFree.set(E_Locations.THIS.l56);
		state.p_DupFree.set(E_Locations.THIS.l57);
		state.p_DupFree.set(E_Locations.THIS.l58);
		state.p_DupFree.set(E_Locations.THIS.l61);
		state.p_DupFree.set(E_Locations.THIS.l62);
		state.p_DupFree.set(E_Locations.THIS.l63);
		state.p_DupFree.set(E_Locations.THIS.l64);
		state.p_DupFree.set(E_Locations.THIS.l65);
		state.p_DupFree.set(E_Locations.THIS.l66);
		state.p_DupFree.set(E_Locations.THIS.l67);
		state.p_DupFree.set(E_Locations.THIS.l68);
		state.p_DupFree.set(E_Locations.THIS.l71);
		state.p_DupFree.set(E_Locations.THIS.l72);
		state.p_DupFree.set(E_Locations.THIS.l73);
		state.p_DupFree.set(E_Locations.THIS.l74);
		state.p_DupFree.set(E_Locations.THIS.l75);
		state.p_DupFree.set(E_Locations.THIS.l76);
		state.p_DupFree.set(E_Locations.THIS.l77);
		state.p_DupFree.set(E_Locations.THIS.l78);
		state.p_DupFree.set(E_Locations.THIS.l81);
		state.p_DupFree.set(E_Locations.THIS.l82);
		state.p_DupFree.set(E_Locations.THIS.l83);
		state.p_DupFree.set(E_Locations.THIS.l84);
		state.p_DupFree.set(E_Locations.THIS.l85);
		state.p_DupFree.set(E_Locations.THIS.l86);
		state.p_DupFree.set(E_Locations.THIS.l87);
		state.p_DupFree.set(E_Locations.THIS.l88);
		
		state.p_Connected.set(E_Locations.THIS.l11, E_Locations.THIS.l21);
		state.p_Connected.set(E_Locations.THIS.l21, E_Locations.THIS.l11);
		state.p_Connected.set(E_Locations.THIS.l11, E_Locations.THIS.l12);
		state.p_Connected.set(E_Locations.THIS.l12, E_Locations.THIS.l11);
		state.p_Connected.set(E_Locations.THIS.l12, E_Locations.THIS.l22);
		state.p_Connected.set(E_Locations.THIS.l22, E_Locations.THIS.l12);
		state.p_Connected.set(E_Locations.THIS.l12, E_Locations.THIS.l13);
		state.p_Connected.set(E_Locations.THIS.l13, E_Locations.THIS.l12);
		state.p_Connected.set(E_Locations.THIS.l13, E_Locations.THIS.l23);
		state.p_Connected.set(E_Locations.THIS.l23, E_Locations.THIS.l13);
		state.p_Connected.set(E_Locations.THIS.l13, E_Locations.THIS.l14);
		state.p_Connected.set(E_Locations.THIS.l14, E_Locations.THIS.l13);
		state.p_Connected.set(E_Locations.THIS.l14, E_Locations.THIS.l24);
		state.p_Connected.set(E_Locations.THIS.l24, E_Locations.THIS.l14);
		state.p_Connected.set(E_Locations.THIS.l14, E_Locations.THIS.l15);
		state.p_Connected.set(E_Locations.THIS.l15, E_Locations.THIS.l14);
		state.p_Connected.set(E_Locations.THIS.l15, E_Locations.THIS.l25);
		state.p_Connected.set(E_Locations.THIS.l25, E_Locations.THIS.l15);
		state.p_Connected.set(E_Locations.THIS.l15, E_Locations.THIS.l16);
		state.p_Connected.set(E_Locations.THIS.l16, E_Locations.THIS.l15);
		state.p_Connected.set(E_Locations.THIS.l16, E_Locations.THIS.l26);
		state.p_Connected.set(E_Locations.THIS.l26, E_Locations.THIS.l16);
		state.p_Connected.set(E_Locations.THIS.l16, E_Locations.THIS.l17);
		state.p_Connected.set(E_Locations.THIS.l17, E_Locations.THIS.l16);
		state.p_Connected.set(E_Locations.THIS.l17, E_Locations.THIS.l27);
		state.p_Connected.set(E_Locations.THIS.l27, E_Locations.THIS.l17);
		state.p_Connected.set(E_Locations.THIS.l17, E_Locations.THIS.l18);
		state.p_Connected.set(E_Locations.THIS.l18, E_Locations.THIS.l17);
		state.p_Connected.set(E_Locations.THIS.l21, E_Locations.THIS.l31);
		state.p_Connected.set(E_Locations.THIS.l31, E_Locations.THIS.l21);
		state.p_Connected.set(E_Locations.THIS.l21, E_Locations.THIS.l22);
		state.p_Connected.set(E_Locations.THIS.l22, E_Locations.THIS.l21);
		state.p_Connected.set(E_Locations.THIS.l22, E_Locations.THIS.l32);
		state.p_Connected.set(E_Locations.THIS.l32, E_Locations.THIS.l22);
		state.p_Connected.set(E_Locations.THIS.l22, E_Locations.THIS.l23);
		state.p_Connected.set(E_Locations.THIS.l23, E_Locations.THIS.l22);
		state.p_Connected.set(E_Locations.THIS.l23, E_Locations.THIS.l33);
		state.p_Connected.set(E_Locations.THIS.l33, E_Locations.THIS.l23);
		state.p_Connected.set(E_Locations.THIS.l23, E_Locations.THIS.l24);
		state.p_Connected.set(E_Locations.THIS.l24, E_Locations.THIS.l23);
		state.p_Connected.set(E_Locations.THIS.l24, E_Locations.THIS.l34);
		state.p_Connected.set(E_Locations.THIS.l34, E_Locations.THIS.l24);
		state.p_Connected.set(E_Locations.THIS.l24, E_Locations.THIS.l25);
		state.p_Connected.set(E_Locations.THIS.l25, E_Locations.THIS.l24);
		state.p_Connected.set(E_Locations.THIS.l25, E_Locations.THIS.l35);
		state.p_Connected.set(E_Locations.THIS.l35, E_Locations.THIS.l25);
		state.p_Connected.set(E_Locations.THIS.l25, E_Locations.THIS.l26);
		state.p_Connected.set(E_Locations.THIS.l26, E_Locations.THIS.l25);
		state.p_Connected.set(E_Locations.THIS.l26, E_Locations.THIS.l36);
		state.p_Connected.set(E_Locations.THIS.l36, E_Locations.THIS.l26);
		state.p_Connected.set(E_Locations.THIS.l26, E_Locations.THIS.l27);
		state.p_Connected.set(E_Locations.THIS.l27, E_Locations.THIS.l26);
		state.p_Connected.set(E_Locations.THIS.l27, E_Locations.THIS.l37);
		state.p_Connected.set(E_Locations.THIS.l37, E_Locations.THIS.l27);
		state.p_Connected.set(E_Locations.THIS.l27, E_Locations.THIS.l28);
		state.p_Connected.set(E_Locations.THIS.l28, E_Locations.THIS.l27);
		state.p_Connected.set(E_Locations.THIS.l31, E_Locations.THIS.l41);
		state.p_Connected.set(E_Locations.THIS.l41, E_Locations.THIS.l31);
		state.p_Connected.set(E_Locations.THIS.l31, E_Locations.THIS.l32);
		state.p_Connected.set(E_Locations.THIS.l32, E_Locations.THIS.l31);
		state.p_Connected.set(E_Locations.THIS.l32, E_Locations.THIS.l42);
		state.p_Connected.set(E_Locations.THIS.l42, E_Locations.THIS.l32);
		state.p_Connected.set(E_Locations.THIS.l32, E_Locations.THIS.l33);
		state.p_Connected.set(E_Locations.THIS.l33, E_Locations.THIS.l32);
		state.p_Connected.set(E_Locations.THIS.l33, E_Locations.THIS.l43);
		state.p_Connected.set(E_Locations.THIS.l43, E_Locations.THIS.l33);
		state.p_Connected.set(E_Locations.THIS.l33, E_Locations.THIS.l34);
		state.p_Connected.set(E_Locations.THIS.l34, E_Locations.THIS.l33);
		state.p_Connected.set(E_Locations.THIS.l34, E_Locations.THIS.l44);
		state.p_Connected.set(E_Locations.THIS.l44, E_Locations.THIS.l34);
		state.p_Connected.set(E_Locations.THIS.l34, E_Locations.THIS.l35);
		state.p_Connected.set(E_Locations.THIS.l35, E_Locations.THIS.l34);
		state.p_Connected.set(E_Locations.THIS.l35, E_Locations.THIS.l45);
		state.p_Connected.set(E_Locations.THIS.l45, E_Locations.THIS.l35);
		state.p_Connected.set(E_Locations.THIS.l35, E_Locations.THIS.l36);
		state.p_Connected.set(E_Locations.THIS.l36, E_Locations.THIS.l35);
		state.p_Connected.set(E_Locations.THIS.l36, E_Locations.THIS.l46);
		state.p_Connected.set(E_Locations.THIS.l46, E_Locations.THIS.l36);
		state.p_Connected.set(E_Locations.THIS.l36, E_Locations.THIS.l37);
		state.p_Connected.set(E_Locations.THIS.l37, E_Locations.THIS.l36);
		state.p_Connected.set(E_Locations.THIS.l37, E_Locations.THIS.l47);
		state.p_Connected.set(E_Locations.THIS.l47, E_Locations.THIS.l37);
		state.p_Connected.set(E_Locations.THIS.l37, E_Locations.THIS.l38);
		state.p_Connected.set(E_Locations.THIS.l38, E_Locations.THIS.l37);
		state.p_Connected.set(E_Locations.THIS.l41, E_Locations.THIS.l51);
		state.p_Connected.set(E_Locations.THIS.l51, E_Locations.THIS.l41);
		state.p_Connected.set(E_Locations.THIS.l41, E_Locations.THIS.l42);
		state.p_Connected.set(E_Locations.THIS.l42, E_Locations.THIS.l41);
		state.p_Connected.set(E_Locations.THIS.l42, E_Locations.THIS.l52);
		state.p_Connected.set(E_Locations.THIS.l52, E_Locations.THIS.l42);
		state.p_Connected.set(E_Locations.THIS.l42, E_Locations.THIS.l43);
		state.p_Connected.set(E_Locations.THIS.l43, E_Locations.THIS.l42);
		state.p_Connected.set(E_Locations.THIS.l43, E_Locations.THIS.l53);
		state.p_Connected.set(E_Locations.THIS.l53, E_Locations.THIS.l43);
		state.p_Connected.set(E_Locations.THIS.l43, E_Locations.THIS.l44);
		state.p_Connected.set(E_Locations.THIS.l44, E_Locations.THIS.l43);
		state.p_Connected.set(E_Locations.THIS.l44, E_Locations.THIS.l54);
		state.p_Connected.set(E_Locations.THIS.l54, E_Locations.THIS.l44);
		state.p_Connected.set(E_Locations.THIS.l44, E_Locations.THIS.l45);
		state.p_Connected.set(E_Locations.THIS.l45, E_Locations.THIS.l44);
		state.p_Connected.set(E_Locations.THIS.l45, E_Locations.THIS.l55);
		state.p_Connected.set(E_Locations.THIS.l55, E_Locations.THIS.l45);
		state.p_Connected.set(E_Locations.THIS.l45, E_Locations.THIS.l46);
		state.p_Connected.set(E_Locations.THIS.l46, E_Locations.THIS.l45);
		state.p_Connected.set(E_Locations.THIS.l46, E_Locations.THIS.l56);
		state.p_Connected.set(E_Locations.THIS.l56, E_Locations.THIS.l46);
		state.p_Connected.set(E_Locations.THIS.l46, E_Locations.THIS.l47);
		state.p_Connected.set(E_Locations.THIS.l47, E_Locations.THIS.l46);
		state.p_Connected.set(E_Locations.THIS.l47, E_Locations.THIS.l57);
		state.p_Connected.set(E_Locations.THIS.l57, E_Locations.THIS.l47);
		state.p_Connected.set(E_Locations.THIS.l47, E_Locations.THIS.l48);
		state.p_Connected.set(E_Locations.THIS.l48, E_Locations.THIS.l47);
		state.p_Connected.set(E_Locations.THIS.l51, E_Locations.THIS.l61);
		state.p_Connected.set(E_Locations.THIS.l61, E_Locations.THIS.l51);
		state.p_Connected.set(E_Locations.THIS.l51, E_Locations.THIS.l52);
		state.p_Connected.set(E_Locations.THIS.l52, E_Locations.THIS.l51);
		state.p_Connected.set(E_Locations.THIS.l52, E_Locations.THIS.l62);
		state.p_Connected.set(E_Locations.THIS.l62, E_Locations.THIS.l52);
		state.p_Connected.set(E_Locations.THIS.l52, E_Locations.THIS.l53);
		state.p_Connected.set(E_Locations.THIS.l53, E_Locations.THIS.l52);
		state.p_Connected.set(E_Locations.THIS.l53, E_Locations.THIS.l63);
		state.p_Connected.set(E_Locations.THIS.l63, E_Locations.THIS.l53);
		state.p_Connected.set(E_Locations.THIS.l53, E_Locations.THIS.l54);
		state.p_Connected.set(E_Locations.THIS.l54, E_Locations.THIS.l53);
		state.p_Connected.set(E_Locations.THIS.l54, E_Locations.THIS.l64);
		state.p_Connected.set(E_Locations.THIS.l64, E_Locations.THIS.l54);
		state.p_Connected.set(E_Locations.THIS.l54, E_Locations.THIS.l55);
		state.p_Connected.set(E_Locations.THIS.l55, E_Locations.THIS.l54);
		state.p_Connected.set(E_Locations.THIS.l55, E_Locations.THIS.l65);
		state.p_Connected.set(E_Locations.THIS.l65, E_Locations.THIS.l55);
		state.p_Connected.set(E_Locations.THIS.l55, E_Locations.THIS.l56);
		state.p_Connected.set(E_Locations.THIS.l56, E_Locations.THIS.l55);
		state.p_Connected.set(E_Locations.THIS.l56, E_Locations.THIS.l66);
		state.p_Connected.set(E_Locations.THIS.l66, E_Locations.THIS.l56);
		state.p_Connected.set(E_Locations.THIS.l56, E_Locations.THIS.l57);
		state.p_Connected.set(E_Locations.THIS.l57, E_Locations.THIS.l56);
		state.p_Connected.set(E_Locations.THIS.l57, E_Locations.THIS.l67);
		state.p_Connected.set(E_Locations.THIS.l67, E_Locations.THIS.l57);
		state.p_Connected.set(E_Locations.THIS.l57, E_Locations.THIS.l58);
		state.p_Connected.set(E_Locations.THIS.l58, E_Locations.THIS.l57);
		state.p_Connected.set(E_Locations.THIS.l61, E_Locations.THIS.l71);
		state.p_Connected.set(E_Locations.THIS.l71, E_Locations.THIS.l61);
		state.p_Connected.set(E_Locations.THIS.l61, E_Locations.THIS.l62);
		state.p_Connected.set(E_Locations.THIS.l62, E_Locations.THIS.l61);
		state.p_Connected.set(E_Locations.THIS.l62, E_Locations.THIS.l72);
		state.p_Connected.set(E_Locations.THIS.l72, E_Locations.THIS.l62);
		state.p_Connected.set(E_Locations.THIS.l62, E_Locations.THIS.l63);
		state.p_Connected.set(E_Locations.THIS.l63, E_Locations.THIS.l62);
		state.p_Connected.set(E_Locations.THIS.l63, E_Locations.THIS.l73);
		state.p_Connected.set(E_Locations.THIS.l73, E_Locations.THIS.l63);
		state.p_Connected.set(E_Locations.THIS.l63, E_Locations.THIS.l64);
		state.p_Connected.set(E_Locations.THIS.l64, E_Locations.THIS.l63);
		state.p_Connected.set(E_Locations.THIS.l64, E_Locations.THIS.l74);
		state.p_Connected.set(E_Locations.THIS.l74, E_Locations.THIS.l64);
		state.p_Connected.set(E_Locations.THIS.l64, E_Locations.THIS.l65);
		state.p_Connected.set(E_Locations.THIS.l65, E_Locations.THIS.l64);
		state.p_Connected.set(E_Locations.THIS.l65, E_Locations.THIS.l75);
		state.p_Connected.set(E_Locations.THIS.l75, E_Locations.THIS.l65);
		state.p_Connected.set(E_Locations.THIS.l65, E_Locations.THIS.l66);
		state.p_Connected.set(E_Locations.THIS.l66, E_Locations.THIS.l65);
		state.p_Connected.set(E_Locations.THIS.l66, E_Locations.THIS.l76);
		state.p_Connected.set(E_Locations.THIS.l76, E_Locations.THIS.l66);
		state.p_Connected.set(E_Locations.THIS.l66, E_Locations.THIS.l67);
		state.p_Connected.set(E_Locations.THIS.l67, E_Locations.THIS.l66);
		state.p_Connected.set(E_Locations.THIS.l67, E_Locations.THIS.l77);
		state.p_Connected.set(E_Locations.THIS.l77, E_Locations.THIS.l67);
		state.p_Connected.set(E_Locations.THIS.l67, E_Locations.THIS.l68);
		state.p_Connected.set(E_Locations.THIS.l68, E_Locations.THIS.l67);
		state.p_Connected.set(E_Locations.THIS.l71, E_Locations.THIS.l81);
		state.p_Connected.set(E_Locations.THIS.l81, E_Locations.THIS.l71);
		state.p_Connected.set(E_Locations.THIS.l71, E_Locations.THIS.l72);
		state.p_Connected.set(E_Locations.THIS.l72, E_Locations.THIS.l71);
		state.p_Connected.set(E_Locations.THIS.l72, E_Locations.THIS.l82);
		state.p_Connected.set(E_Locations.THIS.l82, E_Locations.THIS.l72);
		state.p_Connected.set(E_Locations.THIS.l72, E_Locations.THIS.l73);
		state.p_Connected.set(E_Locations.THIS.l73, E_Locations.THIS.l72);
		state.p_Connected.set(E_Locations.THIS.l73, E_Locations.THIS.l83);
		state.p_Connected.set(E_Locations.THIS.l83, E_Locations.THIS.l73);
		state.p_Connected.set(E_Locations.THIS.l73, E_Locations.THIS.l74);
		state.p_Connected.set(E_Locations.THIS.l74, E_Locations.THIS.l73);
		state.p_Connected.set(E_Locations.THIS.l74, E_Locations.THIS.l84);
		state.p_Connected.set(E_Locations.THIS.l84, E_Locations.THIS.l74);
		state.p_Connected.set(E_Locations.THIS.l74, E_Locations.THIS.l75);
		state.p_Connected.set(E_Locations.THIS.l75, E_Locations.THIS.l74);
		state.p_Connected.set(E_Locations.THIS.l75, E_Locations.THIS.l85);
		state.p_Connected.set(E_Locations.THIS.l85, E_Locations.THIS.l75);
		state.p_Connected.set(E_Locations.THIS.l75, E_Locations.THIS.l76);
		state.p_Connected.set(E_Locations.THIS.l76, E_Locations.THIS.l75);
		state.p_Connected.set(E_Locations.THIS.l76, E_Locations.THIS.l86);
		state.p_Connected.set(E_Locations.THIS.l86, E_Locations.THIS.l76);
		state.p_Connected.set(E_Locations.THIS.l76, E_Locations.THIS.l77);
		state.p_Connected.set(E_Locations.THIS.l77, E_Locations.THIS.l76);
		state.p_Connected.set(E_Locations.THIS.l77, E_Locations.THIS.l87);
		state.p_Connected.set(E_Locations.THIS.l87, E_Locations.THIS.l77);
		state.p_Connected.set(E_Locations.THIS.l77, E_Locations.THIS.l78);
		state.p_Connected.set(E_Locations.THIS.l78, E_Locations.THIS.l77);
		state.p_Connected.set(E_Locations.THIS.l87, E_Locations.THIS.l88);
		state.p_Connected.set(E_Locations.THIS.l88, E_Locations.THIS.l87);
		state.p_Connected.set(E_Locations.THIS.l78, E_Locations.THIS.l88);
		state.p_Connected.set(E_Locations.THIS.l88, E_Locations.THIS.l78);
		
		state.p_Entry.set(E_Ships.THIS.s, E_Locations.THIS.l58);
		state.p_Exit.set(E_Ships.THIS.s, E_Locations.THIS.l51);
		state.p_Exit.set(E_Ships.THIS.s, E_Locations.THIS.l85);
		
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l58, E_Locations.THIS.l57);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l57, E_Locations.THIS.l56);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l56, E_Locations.THIS.l54);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l55, E_Locations.THIS.l54);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l54, E_Locations.THIS.l53);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l53, E_Locations.THIS.l52);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l52, E_Locations.THIS.l51);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l55, E_Locations.THIS.l65);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l65, E_Locations.THIS.l75);
		state.p_ConnectedShip.set(E_Ships.THIS.s, E_Locations.THIS.l75, E_Locations.THIS.l85);		
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
