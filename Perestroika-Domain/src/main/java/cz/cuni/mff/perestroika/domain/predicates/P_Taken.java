package cz.cuni.mff.perestroika.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Predicate;
import cz.cuni.mff.perestroika.domain.types.T_Resource;
import cz.cuni.mff.perestroika.problem.E_Resource;

/**
 * PREDICATE
 * (taken ?r - resource)
 */
public class P_Taken extends Predicate {

	public static final int FLAG_TYPE = 14;
	
	public T_Resource r;
	
	public P_Taken() {
	}
	
	public P_Taken(T_Resource r) {
		super();
		this.r = r;
	}
	
	@Override
	public P_Taken create() {
		return new P_Taken();
	}
	
	public void reset() {
		r = null;
	}
	
	@Override
	public void assign(String[] args) {
		r = E_Resource.THIS.getElement(args[0]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Taken)) return false;
		P_Taken other = (P_Taken)obj;
		if (r != other.r) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Taken[s - ship = " + r + "]";
	}
	
	@Override
	public String getName() {
		return "taken";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(taken " + r.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(r);
	}
	
	public static int toInt(T_Resource r) {
		return    (T_Resource.getIndex(r) << (Predicate.MASK_TYPE_BIT_COUNT))
			    | FLAG_TYPE;
	}
	
	public static T_Resource fromInt_r(int predicate) {
		return E_Resource.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Resource.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Resource_1 extends FastIntMap<Boolean> {

		protected Map_T_Resource_1() {			
		}
		
		public Map_T_Resource_1(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Resource_1 clone() {
			Map_T_Resource_1 result = new Map_T_Resource_1();
			rewriteClone(result);
			return result;
		}
		
		public Boolean get(T_Resource obj) {
			return get(T_Resource.getIndex(obj));
		}
		
		public boolean containsKey(T_Resource obj) {
			return containsKey(T_Resource.getIndex(obj));
		}
		
		public boolean put(T_Resource key, Boolean value) {
			return put(T_Resource.getIndex(key), value);
		}
		
		public Boolean remove(T_Resource key) {
			return remove(T_Resource.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_Taken implements IStorage<P_Taken> {
		
		private final Map_T_Resource_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_Taken() {
			storage = new Map_T_Resource_1(T_Resource.getCount());
		}
		
		protected Storage_P_Taken(Map_T_Resource_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Taken clone() {
			return new Storage_P_Taken(storage.clone());
		}	
		
		
		@Override
		public String getName() {
			return "taken";
		}
		
		@Override
		public Class<P_Taken> getPredicateClass() {
			return P_Taken.class;
		}
		
		public boolean isSet(T_Resource r) {
			return storage.containsKey(r);
		}
		
		public boolean set(T_Resource r) {
			if (storage.put(r, true)) {
				compact.set(P_Taken.toInt(r));
				return true;
			}
			return false;
		}
		
		public boolean clear(T_Resource r) {
			if (storage.remove(r) != null) {
				compact.clear(P_Taken.toInt(r));
				return true;
			}
			return false;
		}
		
		@Override
		public boolean isSet(P_Taken p) {
			return isSet(p.r);
		}

		@Override
		public boolean set(P_Taken p) {
			return set(p.r);
		}

		@Override
		public boolean clear(P_Taken p) {
			return clear(p.r);
		}
		
		@Override
		public Map_T_Resource_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Taken> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Resource r = E_Resource.THIS.getElement(key);
							
					P_Taken p = get();
					p.r = r;
						
					result.add(p);
							
					return true;
				}						
			});			
		}
		
		/**
		 * Warning, this does not affect dynamic StateCompact of the state!
		 */
		public void clearAll() {
			storage.clear();
		}	
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Taken> pool = new Pool<P_Taken>() {

		@Override
		protected P_Taken create() {
			return new P_Taken();
		}
		
	};
	
	public static P_Taken get() {
		P_Taken result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Taken predicate) {
		pool.back(predicate);
	}
	
}
