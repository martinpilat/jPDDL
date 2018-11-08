package cz.cuni.mff.perestroika.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.store.Single;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Predicate;

/**
 * PREDICATE
 * (alive)
 */
public final class P_Alive extends Predicate {

	public static final int FLAG_TYPE = 3;
	
	public P_Alive() {		
	}
	
	@Override
	public P_Alive create() {
		return new P_Alive();
	}
	
	public void reset() {		
	}
	
	@Override
	public void assign(String[] args) {
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Alive)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "P_Alive[]";
	}
	
	@Override
	public String getName() {
		return "alive";
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
	
	public static final class Storage_P_Alive extends Single<P_Alive> {

		public StateCompact compact;
		
		@Override
		public Storage_P_Alive clone() {
			Storage_P_Alive result = new Storage_P_Alive();
			result.value = value;
			return result;
		}
		
		@Override
		public String getName() {
			return "alive";
		}
		
		@Override
		public Class<P_Alive> getPredicateClass() {
			return P_Alive.class;
		}
		
		@Override
		public void getAll(Collection<P_Alive> result) {
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
				compact.set(P_Alive.toInt());
				return true;
			}
			return false;
		}
		
		@Override
		public boolean clear() {
			if (super.clear()) {
				compact.clear(P_Alive.toInt());
				return true;
			}
			return false;
		}
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Alive> pool = new Pool<P_Alive>() {

		@Override
		protected P_Alive create() {
			return new P_Alive();
		}
		
	};
	
	public static P_Alive get() {
		P_Alive result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Alive effector) {
		pool.back(effector);
	}

}
