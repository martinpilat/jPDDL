package cz.cuni.mff.perestroika.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.store.Single;
import cz.cuni.mff.perestroika.domain.Predicate;

/**
 * PREDICATE
 * (act-turn)
 */
public final class P_ActRound extends Predicate {

	public static final int FLAG_TYPE = 2;
	
	public P_ActRound() {		
	}
	
	@Override
	public P_ActRound create() {
		return new P_ActRound();
	}
	
	public void reset() {		
	}
	
	@Override
	public void assign(String[] args) {
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_ActRound)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "P_ActRound[]";
	}
	
	@Override
	public String getName() {
		return "act-round";
	}
	
	@Override
	public boolean isStatic() {
		return true;
	}
	
	@Override
	public String toPredicate() {
		return "(" + getName() + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt();
	}
	
	public static int toInt() {
		return FLAG_TYPE;
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Storage_P_ActRound extends Single<P_ActRound> {

		@Override
		public Storage_P_ActRound clone() {
			Storage_P_ActRound result = new Storage_P_ActRound();
			result.value = value;
			return result;
		}
		
		@Override
		public String getName() {
			return "act-round";
		}
		
		@Override
		public Class<P_ActRound> getPredicateClass() {
			return P_ActRound.class;
		}
		
		@Override
		public void getAll(Collection<P_ActRound> result) {
			if (isSet()) result.add(get());
		}

		@Override
		public FastIntMap internal() {
			throw new RuntimeException("Not applicable!");
		}
		
		/**
		 * Warning, this does not affect dynamic StateCompact of the parent state!
		 */
		public void clearAll() {
			clear();
		}
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_ActRound> pool = new Pool<P_ActRound>() {

		@Override
		protected P_ActRound create() {
			return new P_ActRound();
		}
		
	};
	
	public static P_ActRound get() {
		P_ActRound result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_ActRound effector) {
		pool.back(effector);
	}

}
