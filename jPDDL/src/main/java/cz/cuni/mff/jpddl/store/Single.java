package cz.cuni.mff.jpddl.store;

import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLPredicate;

public abstract class Single<P extends PDDLPredicate> implements IStorage<P> {

	protected boolean value = false;
		
	@Override
	public abstract Single<P> clone();
	
	public boolean isSet() {
		return value;
	}
	
	public void set() {
		value = true;
	}
	
	public void clear() {
		value = false;
	}
	
	public boolean isSet(final P p) {
		return value;
	}
	
	public void set(final P p) {
		value = true;
	}

	public void clear(final P p) {
		value = false;
	}	
	
}
