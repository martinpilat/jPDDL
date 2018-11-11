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
 * (medium ?l - location)
 */
public final class P_Medium extends Predicate {
	
	public static final int FLAG_TYPE = 10;
	
	public T_Location l;
	
	public P_Medium() {		
	}
	
	public P_Medium(T_Location l) {
		this.l = l;
	}
	
	@Override
	public P_Medium create() {
		return new P_Medium();
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
		if (!(obj instanceof P_Medium)) return false;
		P_Medium other = (P_Medium)obj;
		if (l != other.l) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Medium[l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "medium";
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
	
	public static final class Storage_P_Medium implements IStorage<P_Medium> {
		
		private final Map_T_Location_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_Medium() {
			storage = new Map_T_Location_1(T_Location.getCount());
		}
		
		protected Storage_P_Medium(Map_T_Location_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Medium clone() {
			return new Storage_P_Medium(storage.clone());
		}
		
		@Override
		public String getName() {
			return "medium";
		}
		
		@Override
		public Class<P_Medium> getPredicateClass() {
			return P_Medium.class;
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
				compact.set(P_Medium.toInt(l));
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
				compact.clear(P_Medium.toInt(l));
				return true;
			}
			return false;
		}
	 
		
		@Override
		public boolean isSet(P_Medium p) {
			return isSet(p.l);
		}

		@Override
		public boolean set(P_Medium p) {
			return set(p.l);
		}

		@Override
		public boolean clear(P_Medium p) {
			return clear(p.l);
		}
				
		@Override
		public Map_T_Location_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Medium> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Location l = E_Location.THIS.getElement(key);
					
					P_Medium p = get();
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
	
	private static final Pool<P_Medium> pool = new Pool<P_Medium>() {

		@Override
		protected P_Medium create() {
			return new P_Medium();
		}
		
	};
	
	public static P_Medium get() {
		P_Medium result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Medium predicate) {
		pool.back(predicate);
	}
	
}
