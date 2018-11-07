package cz.cuni.mff.jpddl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cz.cuni.mff.jpddl.store.ICloneable;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * State of the domain; to be subclassed and filled by respective domains.
 * 
 * @author Jimmy
 */
public abstract class PDDLState implements ICloneable {

	@Override
	public abstract PDDLState clone();	
	
	public void dump() {
		dump(false);
	}
	
	public void dump(boolean includeStatic) {
		dump(includeStatic, false, 80);
	}
	
	public abstract boolean isSet(PDDLPredicate predicate);
	
	public abstract void setDynamic(StateCompact dynamicPartOfTheState);
	
	/**
	 * NOT A CLONE, if you want to persist the state, you have to  {@link StateCompact#clone()} it!
	 * @return
	 */
	public abstract StateCompact getDynamic();
	
	public abstract void dump(boolean includeStatic, boolean includeEmpty, int maxLineLength);
	
	public static <T extends PDDLPredicate> void dumpStorage(IStorage<T> storage, boolean includeEmpty, int maxLineLength) {
		List<T> predicates = new ArrayList<T>();
		storage.getAll(predicates);
		if (!includeEmpty && predicates.size() == 0) return;
		System.out.println(storage.getName() + ":");	
		predicates.sort(PDDLPredicate.COMPARATOR);
		StringBuffer predicatesDump = new StringBuffer();
		PDDLPredicate.dumpPredicates(predicatesDump, predicates, maxLineLength, "  ");
		System.out.println(predicatesDump.toString());
	}	
	
	public static <T extends PDDLPredicate> void diff(IStorage<T> source, IStorage<T> diffFrom, Collection<T> resultAdded, Collection<T> resultRemoved) {
		List<T> predicates1 = new ArrayList<T>();
		List<T> predicates2 = new ArrayList<T>();
		source.getAll(predicates1);
		diffFrom.getAll(predicates2);
		
		List<T> added = new ArrayList<T>(predicates1);
		added.removeAll(predicates2);
		
		List<T> removed = new ArrayList<T>(predicates2);
		removed.removeAll(predicates1);
		
		resultAdded.addAll(added);
		resultRemoved.addAll(removed);
	}
	
	public static void dumpDiff(IStorage[] sources, IStorage[] diffFroms, int maxLineLength) {
		List added = new ArrayList();
		List removed = new ArrayList();
		
		for (int i = 0; i < sources.length; ++i) {
			if (i < diffFroms.length) {
				diff(sources[i], diffFroms[i], added, removed);
			}
		}
		
		if (added.size() > 0) {
			System.out.println("+++ ADDED:");
			added.sort(PDDLPredicate.COMPARATOR);
			StringBuffer result = new StringBuffer();
			PDDLPredicate.dumpPredicates(result, added, maxLineLength, "  ");
			System.out.println(result.toString());
		}
		if (removed.size() > 0) {
			System.out.println("--- REMOVED:");
			removed.sort(PDDLPredicate.COMPARATOR);
			StringBuffer result = new StringBuffer();
			PDDLPredicate.dumpPredicates(result, removed, maxLineLength, "  ");
			System.out.println(result.toString());
		}		
	}
	
}
