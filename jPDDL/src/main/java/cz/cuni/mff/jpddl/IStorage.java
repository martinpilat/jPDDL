package cz.cuni.mff.jpddl;

import java.util.Collection;

import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.ICloneable;

public interface IStorage<P extends PDDLPredicate> extends ICloneable {

	/**
	 * Returns PDDL name of the predicate this is storing.
	 * @return
	 */
	String getName();
	
	/**
	 * Returns class of the predicate we store in here. 
	 * @return
	 */
	Class<P> getPredicateClass();
	
	/**
	 * Does predicate 'p' holds?
	 * @param p
	 * @return
	 */
	boolean isSet(P p);
	
	/**
	 * Sets predicate 'p' to hold.
	 * @param p
	 */
	void set(P p);
	
	/**
	 * Ceases 'p' to hold, invalidates it.
	 * @param p
	 */
	void clear(P p);
	
	/**
	 * Populates 'predicates' with all predicates that hold; non-trivial operation should be used for dumping/displaying the domain only.
	 * All predicates should be backed to their pool when before their pointers are discarded.
	 * 
	 * @param predicates
	 */
	void getAll(Collection<P> predicates);
	
	/**
	 * Returns underlaying map holding the predicate tree.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	FastIntMap internal();
	
}
