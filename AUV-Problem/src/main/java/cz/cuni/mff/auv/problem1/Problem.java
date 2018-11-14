package cz.cuni.mff.auv.problem1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import cz.cuni.mff.auv.domain.Domain;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLEnum;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.PDDLType;

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
	
	public Applicables applicables;
	
	public Problem() {
		domain = new Domain();
		state = new State();
		goal = new Goal();
		applicables = new Applicables();
		
		state.p_ActTurn.set();
		state.p_Operational.set(E_Auvs.a);
		state.p_Outside.set(E_Ships.s);
		state.p_At.set(E_Auvs.a, E_Locations.l11);
		state.p_AtRes.set(E_Resources.r1, E_Locations.l27);
		state.p_AtRes.set(E_Resources.r2, E_Locations.l76);
		state.p_AtRes.set(E_Resources.r3, E_Locations.l82);
		
//		state.p_AtRes.set(E_Resources.r1, E_Locations.l12);
//		state.p_AtRes.set(E_Resources.r2, E_Locations.l21);
//		state.p_AtRes.set(E_Resources.r3, E_Locations.l22);
		
		state.p_Free.set(E_Locations.l12);
		state.p_Free.set(E_Locations.l13);
		state.p_Free.set(E_Locations.l14);
		state.p_Free.set(E_Locations.l15);
		state.p_Free.set(E_Locations.l16);
		state.p_Free.set(E_Locations.l17);
		state.p_Free.set(E_Locations.l18);
		state.p_Free.set(E_Locations.l21);
		state.p_Free.set(E_Locations.l22);
		state.p_Free.set(E_Locations.l23);
		state.p_Free.set(E_Locations.l24);
		state.p_Free.set(E_Locations.l25);
		state.p_Free.set(E_Locations.l26);
		state.p_Free.set(E_Locations.l27);
		state.p_Free.set(E_Locations.l28);
		state.p_Free.set(E_Locations.l31);
		state.p_Free.set(E_Locations.l32);
		state.p_Free.set(E_Locations.l33);
		state.p_Free.set(E_Locations.l34);
		state.p_Free.set(E_Locations.l35);
		state.p_Free.set(E_Locations.l36);
		state.p_Free.set(E_Locations.l37);
		state.p_Free.set(E_Locations.l38);
		state.p_Free.set(E_Locations.l41);
		state.p_Free.set(E_Locations.l42);
		state.p_Free.set(E_Locations.l43);
		state.p_Free.set(E_Locations.l44);
		state.p_Free.set(E_Locations.l45);
		state.p_Free.set(E_Locations.l46);
		state.p_Free.set(E_Locations.l47);
		state.p_Free.set(E_Locations.l48);
		state.p_Free.set(E_Locations.l51);
		state.p_Free.set(E_Locations.l52);
		state.p_Free.set(E_Locations.l53);
		state.p_Free.set(E_Locations.l54);
		state.p_Free.set(E_Locations.l55);
		state.p_Free.set(E_Locations.l56);
		state.p_Free.set(E_Locations.l57);
		state.p_Free.set(E_Locations.l58);
		state.p_Free.set(E_Locations.l61);
		state.p_Free.set(E_Locations.l62);
		state.p_Free.set(E_Locations.l63);
		state.p_Free.set(E_Locations.l64);
		state.p_Free.set(E_Locations.l65);
		state.p_Free.set(E_Locations.l66);
		state.p_Free.set(E_Locations.l67);
		state.p_Free.set(E_Locations.l68);
		state.p_Free.set(E_Locations.l71);
		state.p_Free.set(E_Locations.l72);
		state.p_Free.set(E_Locations.l73);
		state.p_Free.set(E_Locations.l74);
		state.p_Free.set(E_Locations.l75);
		state.p_Free.set(E_Locations.l76);
		state.p_Free.set(E_Locations.l77);
		state.p_Free.set(E_Locations.l78);
		state.p_Free.set(E_Locations.l81);
		state.p_Free.set(E_Locations.l82);
		state.p_Free.set(E_Locations.l83);
		state.p_Free.set(E_Locations.l84);
		state.p_Free.set(E_Locations.l85);
		state.p_Free.set(E_Locations.l86);
		state.p_Free.set(E_Locations.l87);
		state.p_Free.set(E_Locations.l88);
		
		state.p_DupFree.set(E_Locations.l12);
		state.p_DupFree.set(E_Locations.l13);
		state.p_DupFree.set(E_Locations.l14);
		state.p_DupFree.set(E_Locations.l15);
		state.p_DupFree.set(E_Locations.l16);
		state.p_DupFree.set(E_Locations.l17);
		state.p_DupFree.set(E_Locations.l18);
		state.p_DupFree.set(E_Locations.l21);
		state.p_DupFree.set(E_Locations.l22);
		state.p_DupFree.set(E_Locations.l23);
		state.p_DupFree.set(E_Locations.l24);
		state.p_DupFree.set(E_Locations.l25);
		state.p_DupFree.set(E_Locations.l26);
		state.p_DupFree.set(E_Locations.l27);
		state.p_DupFree.set(E_Locations.l28);
		state.p_DupFree.set(E_Locations.l31);
		state.p_DupFree.set(E_Locations.l32);
		state.p_DupFree.set(E_Locations.l33);
		state.p_DupFree.set(E_Locations.l34);
		state.p_DupFree.set(E_Locations.l35);
		state.p_DupFree.set(E_Locations.l36);
		state.p_DupFree.set(E_Locations.l37);
		state.p_DupFree.set(E_Locations.l38);
		state.p_DupFree.set(E_Locations.l41);
		state.p_DupFree.set(E_Locations.l42);
		state.p_DupFree.set(E_Locations.l43);
		state.p_DupFree.set(E_Locations.l44);
		state.p_DupFree.set(E_Locations.l45);
		state.p_DupFree.set(E_Locations.l46);
		state.p_DupFree.set(E_Locations.l47);
		state.p_DupFree.set(E_Locations.l48);
		state.p_DupFree.set(E_Locations.l51);
		state.p_DupFree.set(E_Locations.l52);
		state.p_DupFree.set(E_Locations.l53);
		state.p_DupFree.set(E_Locations.l54);
		state.p_DupFree.set(E_Locations.l55);
		state.p_DupFree.set(E_Locations.l56);
		state.p_DupFree.set(E_Locations.l57);
		state.p_DupFree.set(E_Locations.l58);
		state.p_DupFree.set(E_Locations.l61);
		state.p_DupFree.set(E_Locations.l62);
		state.p_DupFree.set(E_Locations.l63);
		state.p_DupFree.set(E_Locations.l64);
		state.p_DupFree.set(E_Locations.l65);
		state.p_DupFree.set(E_Locations.l66);
		state.p_DupFree.set(E_Locations.l67);
		state.p_DupFree.set(E_Locations.l68);
		state.p_DupFree.set(E_Locations.l71);
		state.p_DupFree.set(E_Locations.l72);
		state.p_DupFree.set(E_Locations.l73);
		state.p_DupFree.set(E_Locations.l74);
		state.p_DupFree.set(E_Locations.l75);
		state.p_DupFree.set(E_Locations.l76);
		state.p_DupFree.set(E_Locations.l77);
		state.p_DupFree.set(E_Locations.l78);
		state.p_DupFree.set(E_Locations.l81);
		state.p_DupFree.set(E_Locations.l82);
		state.p_DupFree.set(E_Locations.l83);
		state.p_DupFree.set(E_Locations.l84);
		state.p_DupFree.set(E_Locations.l85);
		state.p_DupFree.set(E_Locations.l86);
		state.p_DupFree.set(E_Locations.l87);
		state.p_DupFree.set(E_Locations.l88);
		
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
		state.p_Connected.set(E_Locations.l15, E_Locations.l16);
		state.p_Connected.set(E_Locations.l16, E_Locations.l15);
		state.p_Connected.set(E_Locations.l16, E_Locations.l26);
		state.p_Connected.set(E_Locations.l26, E_Locations.l16);
		state.p_Connected.set(E_Locations.l16, E_Locations.l17);
		state.p_Connected.set(E_Locations.l17, E_Locations.l16);
		state.p_Connected.set(E_Locations.l17, E_Locations.l27);
		state.p_Connected.set(E_Locations.l27, E_Locations.l17);
		state.p_Connected.set(E_Locations.l17, E_Locations.l18);
		state.p_Connected.set(E_Locations.l18, E_Locations.l17);
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
		state.p_Connected.set(E_Locations.l25, E_Locations.l26);
		state.p_Connected.set(E_Locations.l26, E_Locations.l25);
		state.p_Connected.set(E_Locations.l26, E_Locations.l36);
		state.p_Connected.set(E_Locations.l36, E_Locations.l26);
		state.p_Connected.set(E_Locations.l26, E_Locations.l27);
		state.p_Connected.set(E_Locations.l27, E_Locations.l26);
		state.p_Connected.set(E_Locations.l27, E_Locations.l37);
		state.p_Connected.set(E_Locations.l37, E_Locations.l27);
		state.p_Connected.set(E_Locations.l27, E_Locations.l28);
		state.p_Connected.set(E_Locations.l28, E_Locations.l27);
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
		state.p_Connected.set(E_Locations.l35, E_Locations.l36);
		state.p_Connected.set(E_Locations.l36, E_Locations.l35);
		state.p_Connected.set(E_Locations.l36, E_Locations.l46);
		state.p_Connected.set(E_Locations.l46, E_Locations.l36);
		state.p_Connected.set(E_Locations.l36, E_Locations.l37);
		state.p_Connected.set(E_Locations.l37, E_Locations.l36);
		state.p_Connected.set(E_Locations.l37, E_Locations.l47);
		state.p_Connected.set(E_Locations.l47, E_Locations.l37);
		state.p_Connected.set(E_Locations.l37, E_Locations.l38);
		state.p_Connected.set(E_Locations.l38, E_Locations.l37);
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
		state.p_Connected.set(E_Locations.l45, E_Locations.l46);
		state.p_Connected.set(E_Locations.l46, E_Locations.l45);
		state.p_Connected.set(E_Locations.l46, E_Locations.l56);
		state.p_Connected.set(E_Locations.l56, E_Locations.l46);
		state.p_Connected.set(E_Locations.l46, E_Locations.l47);
		state.p_Connected.set(E_Locations.l47, E_Locations.l46);
		state.p_Connected.set(E_Locations.l47, E_Locations.l57);
		state.p_Connected.set(E_Locations.l57, E_Locations.l47);
		state.p_Connected.set(E_Locations.l47, E_Locations.l48);
		state.p_Connected.set(E_Locations.l48, E_Locations.l47);
		state.p_Connected.set(E_Locations.l51, E_Locations.l61);
		state.p_Connected.set(E_Locations.l61, E_Locations.l51);
		state.p_Connected.set(E_Locations.l51, E_Locations.l52);
		state.p_Connected.set(E_Locations.l52, E_Locations.l51);
		state.p_Connected.set(E_Locations.l52, E_Locations.l62);
		state.p_Connected.set(E_Locations.l62, E_Locations.l52);
		state.p_Connected.set(E_Locations.l52, E_Locations.l53);
		state.p_Connected.set(E_Locations.l53, E_Locations.l52);
		state.p_Connected.set(E_Locations.l53, E_Locations.l63);
		state.p_Connected.set(E_Locations.l63, E_Locations.l53);
		state.p_Connected.set(E_Locations.l53, E_Locations.l54);
		state.p_Connected.set(E_Locations.l54, E_Locations.l53);
		state.p_Connected.set(E_Locations.l54, E_Locations.l64);
		state.p_Connected.set(E_Locations.l64, E_Locations.l54);
		state.p_Connected.set(E_Locations.l54, E_Locations.l55);
		state.p_Connected.set(E_Locations.l55, E_Locations.l54);
		state.p_Connected.set(E_Locations.l55, E_Locations.l65);
		state.p_Connected.set(E_Locations.l65, E_Locations.l55);
		state.p_Connected.set(E_Locations.l55, E_Locations.l56);
		state.p_Connected.set(E_Locations.l56, E_Locations.l55);
		state.p_Connected.set(E_Locations.l56, E_Locations.l66);
		state.p_Connected.set(E_Locations.l66, E_Locations.l56);
		state.p_Connected.set(E_Locations.l56, E_Locations.l57);
		state.p_Connected.set(E_Locations.l57, E_Locations.l56);
		state.p_Connected.set(E_Locations.l57, E_Locations.l67);
		state.p_Connected.set(E_Locations.l67, E_Locations.l57);
		state.p_Connected.set(E_Locations.l57, E_Locations.l58);
		state.p_Connected.set(E_Locations.l58, E_Locations.l57);
		state.p_Connected.set(E_Locations.l61, E_Locations.l71);
		state.p_Connected.set(E_Locations.l71, E_Locations.l61);
		state.p_Connected.set(E_Locations.l61, E_Locations.l62);
		state.p_Connected.set(E_Locations.l62, E_Locations.l61);
		state.p_Connected.set(E_Locations.l62, E_Locations.l72);
		state.p_Connected.set(E_Locations.l72, E_Locations.l62);
		state.p_Connected.set(E_Locations.l62, E_Locations.l63);
		state.p_Connected.set(E_Locations.l63, E_Locations.l62);
		state.p_Connected.set(E_Locations.l63, E_Locations.l73);
		state.p_Connected.set(E_Locations.l73, E_Locations.l63);
		state.p_Connected.set(E_Locations.l63, E_Locations.l64);
		state.p_Connected.set(E_Locations.l64, E_Locations.l63);
		state.p_Connected.set(E_Locations.l64, E_Locations.l74);
		state.p_Connected.set(E_Locations.l74, E_Locations.l64);
		state.p_Connected.set(E_Locations.l64, E_Locations.l65);
		state.p_Connected.set(E_Locations.l65, E_Locations.l64);
		state.p_Connected.set(E_Locations.l65, E_Locations.l75);
		state.p_Connected.set(E_Locations.l75, E_Locations.l65);
		state.p_Connected.set(E_Locations.l65, E_Locations.l66);
		state.p_Connected.set(E_Locations.l66, E_Locations.l65);
		state.p_Connected.set(E_Locations.l66, E_Locations.l76);
		state.p_Connected.set(E_Locations.l76, E_Locations.l66);
		state.p_Connected.set(E_Locations.l66, E_Locations.l67);
		state.p_Connected.set(E_Locations.l67, E_Locations.l66);
		state.p_Connected.set(E_Locations.l67, E_Locations.l77);
		state.p_Connected.set(E_Locations.l77, E_Locations.l67);
		state.p_Connected.set(E_Locations.l67, E_Locations.l68);
		state.p_Connected.set(E_Locations.l68, E_Locations.l67);
		state.p_Connected.set(E_Locations.l71, E_Locations.l81);
		state.p_Connected.set(E_Locations.l81, E_Locations.l71);
		state.p_Connected.set(E_Locations.l71, E_Locations.l72);
		state.p_Connected.set(E_Locations.l72, E_Locations.l71);
		state.p_Connected.set(E_Locations.l72, E_Locations.l82);
		state.p_Connected.set(E_Locations.l82, E_Locations.l72);
		state.p_Connected.set(E_Locations.l72, E_Locations.l73);
		state.p_Connected.set(E_Locations.l73, E_Locations.l72);
		state.p_Connected.set(E_Locations.l73, E_Locations.l83);
		state.p_Connected.set(E_Locations.l83, E_Locations.l73);
		state.p_Connected.set(E_Locations.l73, E_Locations.l74);
		state.p_Connected.set(E_Locations.l74, E_Locations.l73);
		state.p_Connected.set(E_Locations.l74, E_Locations.l84);
		state.p_Connected.set(E_Locations.l84, E_Locations.l74);
		state.p_Connected.set(E_Locations.l74, E_Locations.l75);
		state.p_Connected.set(E_Locations.l75, E_Locations.l74);
		state.p_Connected.set(E_Locations.l75, E_Locations.l85);
		state.p_Connected.set(E_Locations.l85, E_Locations.l75);
		state.p_Connected.set(E_Locations.l75, E_Locations.l76);
		state.p_Connected.set(E_Locations.l76, E_Locations.l75);
		state.p_Connected.set(E_Locations.l76, E_Locations.l86);
		state.p_Connected.set(E_Locations.l86, E_Locations.l76);
		state.p_Connected.set(E_Locations.l76, E_Locations.l77);
		state.p_Connected.set(E_Locations.l77, E_Locations.l76);
		state.p_Connected.set(E_Locations.l77, E_Locations.l87);
		state.p_Connected.set(E_Locations.l87, E_Locations.l77);
		state.p_Connected.set(E_Locations.l77, E_Locations.l78);
		state.p_Connected.set(E_Locations.l78, E_Locations.l77);
		state.p_Connected.set(E_Locations.l87, E_Locations.l88);
		state.p_Connected.set(E_Locations.l88, E_Locations.l87);
		state.p_Connected.set(E_Locations.l78, E_Locations.l88);
		state.p_Connected.set(E_Locations.l88, E_Locations.l78);
		
		state.p_Entry.set(E_Ships.s, E_Locations.l58);
		state.p_Exit.set(E_Ships.s, E_Locations.l51);
		state.p_Exit.set(E_Ships.s, E_Locations.l85);
		
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l58, E_Locations.l57);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l57, E_Locations.l56);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l56, E_Locations.l54);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l55, E_Locations.l54);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l54, E_Locations.l53);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l53, E_Locations.l52);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l52, E_Locations.l51);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l55, E_Locations.l65);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l65, E_Locations.l75);
		state.p_ConnectedShip.set(E_Ships.s, E_Locations.l75, E_Locations.l85);		
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
	public Applicables getApplicables() {
		return applicables;
	}
	
}
