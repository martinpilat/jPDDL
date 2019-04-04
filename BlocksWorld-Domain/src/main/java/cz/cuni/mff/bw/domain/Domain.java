package cz.cuni.mff.bw.domain;

import java.io.File;

import cz.cuni.mff.bw.problem.E_Block;
import cz.cuni.mff.bw.problem.E_Hand;
import cz.cuni.mff.bw.problem.E_Slippery;
import cz.cuni.mff.bw.problem.E_Sticky;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEnum;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public final class Domain extends PDDLDomain {
	
	public static final PDDLEnum[] ENUMS = new PDDLEnum[] {
		E_Slippery.THIS, E_Block.THIS, E_Sticky.THIS, E_Hand.THIS	
	};
	
	@Override
	public String getName() {
		return "blocksworld";
	}
	
	@Override
	public File getDomainFile() {
		return locateResourceFile("bw/domain.pddl", "resources/bw/domain.pddl", "BlocksWorld-Domain/src/main/resources/bw/domain.pddl", "../BlocksWorld-Domain/src/main/resources/bw/domain.pddl", "../../BlocksWorld-Domain/src/main/resources/bw/domain.pddl" );
	}
	
	@Override
	public File getDomainPureFile() {
		return locateResourceFile("bw/domain-pure.pddl", "resources/bw/domain-pure.pddl", "BlocksWorld-Domain/src/main/resources/bw/domain-pure.pddl", "../BlocksWorld-Domain/src/main/resources/bw/domain-pure.pddl", "../../BlocksWorld-Domain/src/main/resources/bw/domain-pure.pddl" );
	}
	
	@Override
	public File getDomainFlatFile() {
		throw new RuntimeException("FLAT DOMAIN DOES NOT EXIST FOR BLOCKSWORLD (yet?)");
		//return locateResourceFile("bw/domain-flat.pddl", "resources/bw/domain-flat.pddl", "BlocksWorld-Domain/src/main/resources/bw/domain-flat.pddl", "../BlocksWorld-Domain/src/main/resources/bw/domain-flat.pddl", "../../BlocksWorld-Domain/src/main/resources/bw/domain-flat.pddl" );
	}
	
	@Override
	public File getDomainLimitFile() {
		throw new RuntimeException("LIMIT DOMAIN DOES NOT EXIST FOR BLOCKSWORLD (yet?)");
		//return locateResourceFile("bw/domain-flat.pddl", "resources/bw/domain-flat.pddl", "BlocksWorld-Domain/src/main/resources/bw/domain-limit.pddl", "../BlocksWorld-Domain/src/main/resources/bw/domain-limit.pddl", "../../BlocksWorld-Domain/src/main/resources/bw/domain-limit.pddl" );
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
