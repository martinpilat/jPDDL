package cz.cuni.mff.bw.problem2;

import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.jpddl.PDDLState;

public class DeadEnd extends PDDLDeadEnd {

	@Override
	public boolean isDeadEnd(PDDLState pddlState) {
		return false;
	}

}