package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.store.Single;

/**
 * PREDICATE
 * (act-turn)
 */
public final class P_ActTurn extends PDDLPredicate {

	public P_ActTurn() {		
	}
	
	public void reset() {		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_ActTurn)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "P_ActTurn[]";
	}
	
	@Override
	public String getName() {
		return "act-turn";
	}
	
	@Override
	public String toPredicate() {
		return "(act-turn)";
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Storage_P_ActTurn extends Single<P_ActTurn> {

		@Override
		public Storage_P_ActTurn clone() {
			Storage_P_ActTurn result = new Storage_P_ActTurn();
			result.value = value;
			return result;
		}
		
		@Override
		public String getName() {
			return "act-turn";
		}
		
		@Override
		public Class<P_ActTurn> getPredicateClass() {
			return P_ActTurn.class;
		}
		
		@Override
		public void getAll(Collection<P_ActTurn> result) {
			if (isSet()) result.add(get());
		}

		@Override
		public FastIntMap internal() {
			throw new RuntimeException("Not applicable!");
		}
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_ActTurn> pool = new Pool<P_ActTurn>() {

		@Override
		protected P_ActTurn create() {
			return new P_ActTurn();
		}
		
	};
	
	public static P_ActTurn get() {
		P_ActTurn result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_ActTurn effector) {
		pool.back(effector);
	}

}
