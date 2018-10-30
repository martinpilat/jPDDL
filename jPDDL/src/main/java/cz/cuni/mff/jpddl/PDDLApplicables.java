package cz.cuni.mff.jpddl;

import java.util.Collection;

public abstract class PDDLApplicables {

	public abstract void collectApplicableActions(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> actions);
	public abstract void collectApplicableEvents(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> events);
	public abstract void collectApplicableEffectors(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> effectors);
	
}
