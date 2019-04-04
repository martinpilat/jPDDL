package cz.cuni.mff.bw.problem5;

import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.jpddl.PDDLState;

public class DeadEnd extends PDDLDeadEnd {

	@Override
	public boolean isDeadEnd(PDDLState pddlState) {
		State state = (State)pddlState;
		return isStick(state, E_Blocks.b1) ||
			   isStick(state, E_Blocks.b3) ||
			   isStick(state, E_Blocks.b4) ||
			   isStick(state, E_Blocks.b6) ||
			   isStick(state, E_Blocks.b7) ||
			   isStick(state, E_Blocks.b8) ||
			   isStick(state, E_Blocks.b9) ||
			   isStick(state, E_Blocks.b10) ||
			   isStick(state, E_Blocks.b11) ||
			   isStick(state, E_Blocks.b12) ||
			   isStick(state, E_Blocks.b13) ||
			   isStick(state, E_Blocks.b14) ||
			   isStick(state, E_Blocks.b15) ||
			   !state.p_Movable.isSet(E_Blocks.b2) && !state.p_OnTable.isSet(E_Blocks.b2) ||
			   !state.p_Movable.isSet(E_Blocks.b5) && !state.p_OnTable.isSet(E_Blocks.b5);
	}
	
	public boolean isStick(State state, T_Block block) {
		return !state.p_Movable.isSet(block) && (state.p_Holding.isSet(E_Slipperys.h1, block) || state.p_Holding.isSet(E_Stickys.h2, block));
	}

}
