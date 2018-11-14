package cz.cuni.mff.auv.problem5;

import java.util.Collection;

import cz.cuni.mff.auv.domain.Domain;
import cz.cuni.mff.auv.domain.Effector;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.Pool;

public class Applicables extends PDDLApplicables {

	@Override
	public void collectApplicableActions(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> actions) {
		collectApplicableActions((Domain)domain, (State)state, (Collection)actions);	
	}
	
	public void collectApplicableActions(Domain domain, State state, final Collection<? extends Effector> actions) {
		EffectorsCollector collector = collectorsPool.get();
		collector.effectors = actions;		
		for (Effector action : domain.getActions()) {
			action.unify(state, action, collector);
		}		
		collector.effectors = null;
		collectorsPool.back(collector);		
	}
	
	@Override
	public void collectApplicableEvents(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> events) {
		collectApplicableEvents((Domain)domain, (State)state, (Collection)events);	
	}
	
	public void collectApplicableEvents(Domain domain, State state, final Collection<? extends Effector> events) {
		EffectorsCollector collector = collectorsPool.get();
		collector.effectors = events;		
		for (Effector action : domain.getEvents()) {
			action.unify(state, action, collector);
		}		
		collector.effectors = null;
		collectorsPool.back(collector);		
	}
	
	@Override
	public void collectApplicableEffectors(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> effectors) {
		collectApplicableEffectors((Domain)domain, (State)state, (Collection)effectors);	
	}
	
	public void collectApplicableEffectors(Domain domain, State state, final Collection<? extends Effector> effectors) {
		EffectorsCollector collector = collectorsPool.get();
		collector.effectors = effectors;		
		for (Effector action : domain.getEffectors()) {
			action.unify(state, action, collector);
		}		
		collector.effectors = null;
		collectorsPool.back(collector);		
	}
		
	// ====================
	// COLLECTING EFFECTORS
	// ====================
	
	private static class EffectorsCollector implements IPDDLUnification {

		public Collection effectors;
		
		@Override
		public void start() {
		}

		@Override
		public void unified(PDDLEffector effector) {
			effectors.add(effector.clone());
		}

		@Override
		public void end() {
		}
		
	}
	
	private static Pool<EffectorsCollector> collectorsPool = new Pool<EffectorsCollector>() {

		@Override
		protected EffectorsCollector create() {
			return new EffectorsCollector();
		}
		
	};
	
}