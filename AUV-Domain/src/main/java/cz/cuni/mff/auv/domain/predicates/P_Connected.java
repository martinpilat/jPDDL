package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.auv.domain.Predicate;
import cz.cuni.mff.auv.domain.predicates.P_AtRes.Map_T_Location_2;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Resource;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.auv.problem.E_Resource;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * PREDICATE
 * (connected ?l1 ?l2 - location)
 */
public class P_Connected extends Predicate {

	public static final int FLAG_TYPE = 4;
	
	public T_Location l1;
	public T_Location l2;
	
	public P_Connected() {
	}
	
	public P_Connected(T_Location l1, T_Location l2) {
		this.l1 = l1;
		this.l2 = l2;
	}
	
	@Override
	public P_Connected create() {
		return new P_Connected();
	}
	
	public void reset() {
		l1 = null;
		l2 = null;
	}
	
	@Override
	public void assign(String[] args) {
		l1 = E_Location.THIS.getElement(args[0]);
		l2 = E_Location.THIS.getElement(args[1]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Connected)) return false;
		P_Connected other = (P_Connected)obj;
		if (l1 != other.l1) return false;
		if (l2 != other.l2) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Connected[l1 - location = " + l1 + ", l2 - location = " + l2 + "]";
	}
	
	@Override
	public String getName() {
		return "connected";
	}
	
	@Override
	public boolean isStatic() {
		return true;
	}
	
	@Override
	public String toPredicate() {
		return "(connected " + l1.name + " " + l2.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(l1, l2);
	}
	
	public static int toInt(T_Location l1, T_Location l2) {
		return   (T_Location.getIndex(l1) << (T_Location.bitCount + Predicate.MASK_TYPE_BIT_COUNT))
			   | (T_Location.getIndex(l2) << (Predicate.MASK_TYPE_BIT_COUNT))
			   | FLAG_TYPE;
	}
	
	public static T_Location fromInt_l1(int predicate) {
		return E_Location.THIS.getElement( (predicate >> (T_Location.bitCount + Predicate.MASK_TYPE_BIT_COUNT)) & T_Location.bitMask );
	}
	
	public static T_Location fromInt_l2(int predicate) {
		return E_Location.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Location.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Location_1 extends FastIntMap<Map_T_Location_2> {

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
		
		public Map_T_Location_2 get(T_Location obj) {
			return get(T_Location.getIndex(obj));
		}
		
		public boolean containsKey(T_Location obj) {
			return containsKey(T_Location.getIndex(obj));
		}
		
		public void put(T_Location key, Map_T_Location_2 value) {
			put(T_Location.getIndex(key), value);
		}
		
		public Map_T_Location_2 remove(T_Location key) {
			return remove(T_Location.getIndex(key));
		}	
		
	}
	
	public static final class Map_T_Location_2 extends FastIntMap<Boolean> {

		protected Map_T_Location_2() {			
		}
		
		public Map_T_Location_2(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Location_2 clone() {
			Map_T_Location_2 result = new Map_T_Location_2();
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
	
	public static final class Storage_P_Connected implements IStorage<P_Connected> {
		
		private final Map_T_Location_1 storage;
		
		public Storage_P_Connected() {
			storage = new Map_T_Location_1(T_Location.getCount());
		}
		
		protected Storage_P_Connected(Map_T_Location_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Connected clone() {
			return new Storage_P_Connected(storage.clone());
		}		
		
		@Override
		public String getName() {
			return "connected";
		}
		
		@Override
		public Class<P_Connected> getPredicateClass() {
			return P_Connected.class;
		}
		
		public boolean isSet(T_Location l1, T_Location l2) {
			Map_T_Location_2 map_t_location_2 = storage.get(l1);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.containsKey(l2);
		}
		
		public boolean set(T_Location l1, T_Location l2) {
			Map_T_Location_2 map_t_location_2 = storage.get(l1);
			if (map_t_location_2 == null) {
				map_t_location_2 = new Map_T_Location_2(T_Location.getCount());
				storage.put(l1, map_t_location_2);
			}			
			return map_t_location_2.put(l2, true);
		}
		
		public boolean clear(T_Location l1, T_Location l2) {
			Map_T_Location_2 map_t_location_2 = storage.get(l1);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.remove(l2) != null;
		}
	 
		
		@Override
		public boolean isSet(P_Connected p) {
			return isSet(p.l1, p.l2);
		}

		@Override
		public boolean set(P_Connected p) {
			return set(p.l1, p.l2);
		}

		@Override
		public boolean clear(P_Connected p) {
			return clear(p.l1, p.l2);
		}
		
		@Override
		public Map_T_Location_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Connected> result) {
			storage.forEachEntry(new ForEachEntry<Map_T_Location_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Location_2 data) {
					final T_Location l1 = E_Location.THIS.getElement(key);
					data.forEachEntry(new ForEachEntry<Boolean>() {
						@Override
						public boolean entry(int key, Boolean data) {
							final T_Location l2 = E_Location.THIS.getElement(key);
							
							P_Connected p = get();
							p.l1 = l1;
							p.l2 = l2;
							
							result.add(p);
							
							return true;
						}						
					});
					return true;
				}
				
			});			
		}
		
		/**
		 * Warning, this does not affect dynamic StateCompact of the state!
		 */
		public void clearAll() {
			storage.forEachEntry(new ForEachEntry<Map_T_Location_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Location_2 data) {
					data.clear();
					return true;
				}
				
			});	
		}	
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Connected> pool = new Pool<P_Connected>() {

		@Override
		protected P_Connected create() {
			return new P_Connected();
		}
		
	};
	
	public static P_Connected get() {
		P_Connected result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Connected predicate) {
		pool.back(predicate);
	}
	
}