package cz.cuni.mff.auv.domain;

import java.io.File;
import java.net.URI;
import java.net.URL;

import cz.cuni.mff.auv.problem.E_Auv;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.auv.problem.E_Resource;
import cz.cuni.mff.auv.problem.E_Ship;
import cz.cuni.mff.auv.problem.E_Vehicle;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEnum;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public final class Domain extends PDDLDomain {
	
	public static final PDDLEnum[] ENUMS = new PDDLEnum[] {
		E_Auv.THIS, E_Location.THIS, E_Resource.THIS, E_Ship.THIS, E_Vehicle.THIS	
	};
	
	@Override
	public String getName() {
		return "AUV";
	}
	
	@Override
	public File getDomainFile() {
		return locateResourceFile("auv/domain.pddl", "resources/auv/domain.pddl", "AUV-Domain/src/main/resources/auv/domain.pddl", "../AUV-Domain/src/main/resources/auv/domain.pddl", "../../AUV-Domain/src/main/resources/auv/domain.pddl" );
	}
	
	@Override
	public File getDomainPureFile() {
		return locateResourceFile("auv/domain-pure.pddl", "resources/auv/domain-pure.pddl", "AUV-Domain/src/main/resources/auv/domain-pure.pddl", "../AUV-Domain/src/main/resources/auv/domain-pure.pddl", "../../AUV-Domain/src/main/resources/auv/domain-pure.pddl" );
	}
	
	@Override
	public File getDomainFlatFile() {
		return locateResourceFile("auv/domain-flat.pddl", "resources/auv/domain-flat.pddl", "AUV-Domain/src/main/resources/auv/domain-flat.pddl", "../AUV-Domain/src/main/resources/auv/domain-flat.pddl", "../../AUV-Domain/src/main/resources/auv/domain-flat.pddl" );
	}

	public File getDomainLimitFile() {
		return locateResourceFile("auv/domain-limit.pddl", "resources/auv/domain-limit.pddl", "AUV-Domain/src/main/resources/auv/domain-limit.pddl", "../AUV-Domain/src/main/resources/auv/domain-limit.pddl", "../../AUV-Domain/src/main/resources/auv/domain-limit.pddl" );
	}

	@Override
	public File getDomainCostFile() {
		throw  new UnsupportedOperationException();
	}

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
	
	@Override
	public PDDLPredicate[] getPredicates() {
		return Predicate.ALL;
	}
	
	@Override
	public PDDLEnum[] getEnums() {
		return ENUMS; 
	}
	
	// CONVERTORS
	
	@Override
	public Effector toEffector(PDDLStringInstance se) {
		return Effector.toEffector(se);
	}
	
	@Override
	public Effector[] toEffectors(PDDLStringInstance[] ses) {
		return Effector.toEffectors(ses);
	}
	
	@Override
	public Action toAction(PDDLStringInstance se) {
		return Action.toAction(se);
	}
	
	@Override
	public Action[] toActions(PDDLStringInstance[] ses) {
		return Action.toActions(ses);
	}
	
	@Override
	public Event toEvent(PDDLStringInstance se) {
		return Event.toEvent(se);
	}
	
	@Override
	public Event[] toEvents(PDDLStringInstance[] ses) {
		return Event.toEvents(ses);
	}
	
	@Override
	public Predicate toPredicate(PDDLStringInstance se) {
		return Predicate.toPredicate(se);
	}
	
	@Override
	public Predicate[] toPredicates(PDDLStringInstance[] ses) {
		return Predicate.toPredicates(ses);
	}

}
