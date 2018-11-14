package cz.cuni.mff.jpddl;

import java.util.Collection;

import cz.cuni.mff.jpddl.store.Pool;

public class PDDLApplicables {

	public void collectApplicableActions(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> actions) {
		PDDLEffectorsCollector collector = collectorsPool.get();
		collector.effectors = actions;		
		for (PDDLEffector action : domain.getActions()) {
			action.unify(state, action, collector);
		}		
		collector.effectors = null;
		collectorsPool.back(collector);		
	}
	
	public void collectApplicableEvents(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> events) {
		PDDLEffectorsCollector collector = collectorsPool.get();
		collector.effectors = events;		
		for (PDDLEffector action : domain.getEvents()) {
			action.unify(state, action, collector);
		}		
		collector.effectors = null;
		collectorsPool.back(collector);		
	}
	
	public void collectApplicableEffectors(PDDLDomain domain, PDDLState state, final Collection<? extends PDDLEffector> effectors) {
		PDDLEffectorsCollector collector = collectorsPool.get();
		collector.effectors = effectors;		
		for (PDDLEffector action : domain.getEffectors()) {
			action.unify(state, action, collector);
		}		
		collector.effectors = null;
		collectorsPool.back(collector);		
	}
		
	// ====================
	// COLLECTING EFFECTORS
	// ====================
	
	private static class PDDLEffectorsCollector implements IPDDLUnification {

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
	
	private static Pool<PDDLEffectorsCollector> collectorsPool = new Pool<PDDLEffectorsCollector>() {

		@Override
		protected PDDLEffectorsCollector create() {
			return new PDDLEffectorsCollector();
		}
		
	};
	
}
