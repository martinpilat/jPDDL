package cz.cuni.mff.perestroika.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.store.Single;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Predicate;

/**
 * PREDICATE
 * (dead)
 */
public final class P_Dead extends Predicate {

	public static final int FLAG_TYPE = 8;
	
	public P_Dead() {		
	}
	
	@Override
	public P_Dead create() {
		return new P_Dead();
	}
	
	public void reset() {		
	}
	
	@Override
	public void assign(String[] args) {
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Dead)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "P_Dead[]";
	}
	
	@Override
	public String getName() {
		return "dead";
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
	
	public static final class Storage_P_Dead extends Single<P_Dead> {

		public StateCompact compact;
		
		@Override
		public Storage_P_Dead clone() {
			Storage_P_Dead result = new Storage_P_Dead();
			result.value = value;
			return result;
		}
		
		@Override
		public String getName() {
			return "dead";
		}
		
		@Override
		public Class<P_Dead> getPredicateClass() {
			return P_Dead.class;
		}
		
		@Override
		public void getAll(Collection<P_Dead> result) {
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
		
		@Override
		public boolean set() {
			if (super.set()) {
				compact.set(P_Dead.toInt());
				return true;
			}
			return false;
		}
		
		@Override
		public boolean clear() {
			if (super.clear()) {
				compact.clear(P_Dead.toInt());
				return true;
			}
			return false;
		}
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Dead> pool = new Pool<P_Dead>() {

		@Override
		protected P_Dead create() {
			return new P_Dead();
		}
		
	};
	
	public static P_Dead get() {
		P_Dead result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Dead effector) {
		pool.back(effector);
	}

}
