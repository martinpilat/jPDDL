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
	
	public boolean set() {
		if (value) return false;
		return value = true;
	}
	
	public boolean clear() {
		if (!value) return false;
		value = false;
		return true;
	}
	
	@Override
	public boolean isSet(final P p) {
		return value;
	}
	
	@Override
	public boolean set(final P p) {
		return set();
	}

	@Override
	public boolean clear(final P p) {
		return clear();
	}	
	
}
