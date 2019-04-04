package cz.cuni.mff.bw.problem6;

import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.jpddl.PDDLState;

public class DeadEnd extends PDDLDeadEnd {

	@Override
	public boolean isDeadEnd(PDDLState pddlState) {
		return false;
	}

}
