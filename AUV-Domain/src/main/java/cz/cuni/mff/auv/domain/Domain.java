package cz.cuni.mff.auv.domain;

import cz.cuni.mff.jpddl.PDDLDomain;

public class Domain extends PDDLDomain {

	@Override
	public Action[] getActions() {
		return Action.ALL;
	}

	@Override
	public Event[] getEvents() {
		return Event.ALL;
	}
	
	@Override
	public Effector[] getEffectors() {
		return Effector.ALL;
	}

}
