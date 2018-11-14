package cz.cuni.mff.perestroika.problem1;

import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.perestroika.domain.State;

public class DeadEnd extends PDDLDeadEnd {

	@Override
	public boolean isDeadEnd(PDDLState pddlState) {
		State state = (State)pddlState;
		if (!state.p_Alive.isSet()) return true;
		return false;
	}

}
