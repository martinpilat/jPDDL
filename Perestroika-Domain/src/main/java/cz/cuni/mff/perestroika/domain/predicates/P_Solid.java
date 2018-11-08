package cz.cuni.mff.perestroika.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Predicate;
import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.problem.E_Location;

/**
 * PREDICATE
 * (solid ?l - location)
 */
public final class P_Solid extends Predicate {
	
	public static final int FLAG_TYPE = 13;
	
	public T_Location l;
	
	public P_Solid() {		
	}
	
	public P_Solid(T_Location l) {
		this.l = l;
	}
	
	@Override
	public P_Solid create() {
		return new P_Solid();
	}
	
	public void reset() {
		l = null;
	}
	
	@Override
	public void assign(String[] args) {
		l = E_Location.THIS.getElement(args[0]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Solid)) return false;
		P_Solid other = (P_Solid)obj;
		if (l != other.l) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Solid[l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "solid";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(" + getName() + " " + l.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(l);
	}
	
	public static int toInt(T_Location l) {
		return   (T_Location.getIndex(l) << (Predicate.MASK_TYPE_BIT_COUNT))
			   | FLAG_TYPE;
	}
	
	public static T_Location fromInt_l(int predicate) {
		return E_Location.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Location.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Location_1 extends FastIntMap<Boolean> {

		protected Map_T_Location_1() {			
		}
		
		public Map_T_Location_1(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Location_1 clone() {
			Map_T_Location_1 result = new Map_T_Location_1();
			rewriteClone(result);
			return result;
		}
		
		public Boolean get(T_Location obj) {
			return get(T_Location.getIndex(obj));
		}
		
		public boolean containsKey(T_Location obj) {
			return containsKey(T_Location.getIndex(obj));
		}
		
		public boolean put(T_Location key, Boolean value) {
			return put(T_Location.getIndex(key), value);
		}
		
		public Boolean remove(T_Location key) {
			return remove(T_Location.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_Solid implements IStorage<P_Solid> {
		
		private final Map_T_Location_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_Solid() {
			storage = new Map_T_Location_1(T_Location.getCount());
		}
		
		protected Storage_P_Solid(Map_T_Location_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Solid clone() {
			return new Storage_P_Solid(storage.clone());
		}
		
		@Override
		public String getName() {
			return "solid";
		}
		
		@Override
		public Class<P_Solid> getPredicateClass() {
			return P_Solid.class;
		}
		
		public boolean isSet(T_Location l) {
			return storage.containsKey(l);
		}
		
		/**
		 * Returns TRUE if a NEW predicate was set; false if the predicate was already stored.
		 * @param v
		 * @param l
		 * @return
		 */
		public boolean set(T_Location l) {
			if (storage.put(l, true)) {
				compact.set(P_Solid.toInt(l));
				return true;
			}
			return false;
		}
		
		/**
		 * Returns TRUE if a predicate was removed (i.e. was previously set within the storage)
		 * @param v
		 * @param l
		 * @return
		 */
		public boolean clear(T_Location l) {
			if (storage.remove(l) != null) {
				compact.clear(P_Solid.toInt(l));
				return true;
			}
			return false;
		}
	 
		
		@Override
		public boolean isSet(P_Solid p) {
			return isSet(p.l);
		}

		@Override
		public boolean set(P_Solid p) {
			return set(p.l);
		}

		@Override
		public boolean clear(P_Solid p) {
			return clear(p.l);
		}
				
		@Override
		public Map_T_Location_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Solid> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Location l = E_Location.THIS.getElement(key);
					
					P_Solid p = get();
					p.l = l;
					
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
	
	private static final Pool<P_Solid> pool = new Pool<P_Solid>() {

		@Override
		protected P_Solid create() {
			return new P_Solid();
		}
		
	};
	
	public static P_Solid get() {
		P_Solid result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Solid predicate) {
		pool.back(predicate);
	}
	
}
