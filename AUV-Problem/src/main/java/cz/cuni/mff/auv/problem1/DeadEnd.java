package cz.cuni.mff.auv.problem1;

import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.problem.E_Auv;
import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.jpddl.PDDLState;

public class DeadEnd extends PDDLDeadEnd {

	private T_Auv a = E_Auv.THIS.getElement("a"); 
	
	@Override
	public boolean isDeadEnd(PDDLState pddlState) {
		State state = (State)pddlState;
		if (!state.p_Operational.isSet(a)) return true;
		return false;
	}

}
