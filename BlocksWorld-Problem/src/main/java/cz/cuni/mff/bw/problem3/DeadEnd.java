package cz.cuni.mff.bw.problem3;

import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.jpddl.PDDLState;

public class DeadEnd extends PDDLDeadEnd {

	@Override
	public boolean isDeadEnd(PDDLState pddlState) {
		return false;
//		State state = (State)pddlState;
//		return isStick(state, E_Blocks.b1) ||
//			   isStick(state, E_Blocks.b2) ||
//			   isStick(state, E_Blocks.b3) ||
//			   isStick(state, E_Blocks.b4) ||
//			   isStick(state, E_Blocks.b5) ||
//			   isStick(state, E_Blocks.b6) ||
//			   isStick(state, E_Blocks.b8) ||
//			   isStick(state, E_Blocks.b9) ||
//			   !state.p_Movable.isSet(E_Blocks.b7) && !state.p_OnTable.isSet(E_Blocks.b7);
	}
	
	public boolean isStick(State state, T_Block block) {
		return !state.p_Movable.isSet(block) && (state.p_Holding.isSet(E_Slipperys.h1, block) || state.p_Holding.isSet(E_Stickys.h2, block));
	}

}
