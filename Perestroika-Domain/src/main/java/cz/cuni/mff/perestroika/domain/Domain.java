package cz.cuni.mff.perestroika.domain;

import java.io.File;
import java.net.URI;
import java.net.URL;

import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEnum;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.perestroika.problem.E_Location;
import cz.cuni.mff.perestroika.problem.E_Resource;

public final class Domain extends PDDLDomain {
	
	public static final PDDLEnum[] ENUMS = new PDDLEnum[] {
		E_Location.THIS, E_Resource.THIS	
	};
	
	@Override
	public String getName() {
		return "Perestroika";
	}
	
	@Override
	public File getDomainFile() {
		return locateResourceFile("perestroika/domain.pddl", "resources/perestroika/domain.pddl", "Perestroika-Domain/src/main/resources/perestroika/domain.pddl", "../Perestroika-Domain/src/main/resources/perestroika/domain.pddl", "../../Perestroika-Domain/src/main/resources/perestroika/domain.pddl" );
	}

	@Override
	public File getDomainCostFile() {
		return locateResourceFile("perestroika/domain-cost.pddl", "resources/perestroika/domain-cost.pddl", "Perestroika-Domain/src/main/resources/perestroika/domain-cost.pddl", "../Perestroika-Domain/src/main/resources/perestroika/domain-cost.pddl", "../../Perestroika-Domain/src/main/resources/perestroika/domain-cost.pddl" );
	}

	@Override
	public File getDomainPureFile() {
		return locateResourceFile("perestroika/domain-pure.pddl", "resources/perestroika/domain-pure.pddl", "Perestroika-Domain/src/main/resources/perestroika/domain-pure.pddl", "../Perestroika-Domain/src/main/resources/perestroika/domain-pure.pddl", "../../Perestroika-Domain/src/main/resources/perestroika/domain-pure.pddl" );
	}
	
	@Override
	public File getDomainFlatFile() {
		return locateResourceFile("perestroika/domain-flat.pddl", "resources/perestroika/domain-flat.pddl", "Perestroika-Domain/src/main/resources/perestroika/domain-flat.pddl", "../Perestroika-Domain/src/main/resources/perestroika/domain-flat.pddl", "../../Perestroika-Domain/src/main/resources/perestroika/domain-flat.pddl" );
	}

	public File getDomainLimitFile() {
		return locateResourceFile("perestroika/domain-limit.pddl", "resources/perestroika/domain-limit.pddl", "Perestroika-Domain/src/main/resources/perestroika/domain-limit.pddl", "../Perestroika-Domain/src/main/resources/perestroika/domain-limit.pddl", "../../Perestroika-Domain/src/main/resources/perestroika/domain-limit.pddl" );
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
