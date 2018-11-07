package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.auv.domain.Predicate;
import cz.cuni.mff.auv.domain.predicates.P_Connected.Map_T_Location_2;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.auv.problem.E_Ship;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * PREDICATE
 * (connected-ship ?s - ship ?l1 ?l2 - location)
 */
public class P_ConnectedShip extends Predicate {

	public static final int FLAG_TYPE = 5;
	
	public T_Ship s;
	public T_Location l1;
	public T_Location l2;
	
	public P_ConnectedShip() {
	}
	
	public P_ConnectedShip(T_Ship s, T_Location l1, T_Location l2) {
		this.s = s;
		this.l1 = l1;
		this.l2 = l2;
	}
	
	@Override
	public P_ConnectedShip create() {
		return new P_ConnectedShip();
	}
	
	public void reset() {
		s = null;
		l1 = null;
		l2 = null;
	}
	
	@Override
	public void assign(String[] args) {
		s = E_Ship.THIS.getElement(args[0]);
		l1 = E_Location.THIS.getElement(args[1]);
		l2 = E_Location.THIS.getElement(args[2]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_ConnectedShip)) return false;
		P_ConnectedShip other = (P_ConnectedShip)obj;
		if (s != other.s) return false;
		if (l1 != other.l1) return false;
		if (l2 != other.l2) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_ConnectedShip[s - ship = " + s + ", l1 - location = " + l1 + ", l2 - location = " + l2 + "]";
	}
	
	@Override
	public String getName() {
		return "connected-ship";
	}
	
	@Override
	public boolean isStatic() {
		return true;
	}
	
	@Override
	public String toPredicate() {
		return "(connected-ship " + s.name + " " + l1.name + " " + l2.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(s, l1, l2);
	}
	
	public static int toInt(T_Ship s, T_Location l1, T_Location l2) {
		return    (T_Ship.getIndex(s) << (T_Location.bitCount + T_Location.bitCount + Predicate.MASK_TYPE_BIT_COUNT))
			    | (T_Location.getIndex(l1) << (T_Location.bitCount + Predicate.MASK_TYPE_BIT_COUNT))
			    | (T_Location.getIndex(l2) << (Predicate.MASK_TYPE_BIT_COUNT))
			    | FLAG_TYPE;
	}
	
	public static T_Ship fromInt_s(int predicate) {
		return E_Ship.THIS.getElement( (predicate >> (T_Location.bitCount + T_Location.bitCount + Predicate.MASK_TYPE_BIT_COUNT)) & T_Ship.bitMask );
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
	
	public static final class Map_T_Ship_1 extends FastIntMap<Map_T_Location_2> {

		protected Map_T_Ship_1() {			
		}
		
		public Map_T_Ship_1(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Ship_1 clone() {
			Map_T_Ship_1 result = new Map_T_Ship_1();
			rewriteClone(result);
			return result;
		}
		
		public Map_T_Location_2 get(T_Ship obj) {
			return get(T_Ship.getIndex(obj));
		}
		
		public boolean containsKey(T_Ship obj) {
			return containsKey(T_Ship.getIndex(obj));
		}
		
		public void put(T_Ship key, Map_T_Location_2 value) {
			put(T_Ship.getIndex(key), value);
		}
		
		public Map_T_Location_2 remove(T_Ship key) {
			return remove(T_Ship.getIndex(key));
		}	
		
	}
	
	public static final class Map_T_Location_2 extends FastIntMap<Map_T_Location_3> {

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
		
		public Map_T_Location_3 get(T_Location obj) {
			return get(T_Location.getIndex(obj));
		}
		
		public boolean containsKey(T_Location obj) {
			return containsKey(T_Location.getIndex(obj));
		}
		
		public void put(T_Location key, Map_T_Location_3 value) {
			put(T_Location.getIndex(key), value);
		}
		
		public Map_T_Location_3 remove(T_Location key) {
			return remove(T_Location.getIndex(key));
		}	
		
	}
	
	public static final class Map_T_Location_3 extends FastIntMap<Boolean> {

		protected Map_T_Location_3() {			
		}
		
		public Map_T_Location_3(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Location_3 clone() {
			Map_T_Location_3 result = new Map_T_Location_3();
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
	
	public static final class Storage_P_ConnectedShip implements IStorage<P_ConnectedShip> {
		
		private final Map_T_Ship_1 storage;
		
		public Storage_P_ConnectedShip() {
			storage = new Map_T_Ship_1(T_Ship.getCount());
		}
		
		protected Storage_P_ConnectedShip(Map_T_Ship_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_ConnectedShip clone() {
			return new Storage_P_ConnectedShip(storage.clone());
		}
		
		@Override
		public String getName() {
			return "connected-ship";
		}
		
		@Override
		public Class<P_ConnectedShip> getPredicateClass() {
			return P_ConnectedShip.class;
		}
		
		public boolean isSet(T_Ship s, T_Location l1, T_Location l2) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) return false;			
			Map_T_Location_3 map_t_location_3 = map_t_location_2.get(l1);
			if (map_t_location_3 == null) return false;
			return map_t_location_3.containsKey(l2);
		}
		
		public boolean set(T_Ship s, T_Location l1, T_Location l2) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) {
				map_t_location_2 = new Map_T_Location_2(T_Location.getCount());
				storage.put(s, map_t_location_2);
			}			
			Map_T_Location_3 map_t_location_3 = map_t_location_2.get(l1);
			if (map_t_location_3 == null) {
				map_t_location_3 = new Map_T_Location_3(T_Location.getCount());
				map_t_location_2.put(l1, map_t_location_3);
			}			
			return map_t_location_3.put(l2, true);
		}
		
		public boolean clear(T_Ship s, T_Location l1, T_Location l2) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) return false;			
			Map_T_Location_3 map_t_location_3 = map_t_location_2.get(l1);
			if (map_t_location_3 == null) return false;
			return map_t_location_3.remove(l2) != null;
		}
	 
		
		@Override
		public boolean isSet(P_ConnectedShip p) {
			return isSet(p.s, p.l1, p.l2);
		}

		@Override
		public boolean set(P_ConnectedShip p) {
			return set(p.s, p.l1, p.l2);
		}

		@Override
		public boolean clear(P_ConnectedShip p) {
			return clear(p.s, p.l1, p.l2);
		}
		
		public Map_T_Ship_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_ConnectedShip> result) {
			storage.forEachEntry(
				new ForEachEntry<Map_T_Location_2>() {
					@Override
					public boolean entry(int key, Map_T_Location_2 data) {
						final T_Ship s = E_Ship.THIS.getElement(key);
						data.forEachEntry(
							new ForEachEntry<Map_T_Location_3>() {
								@Override
								public boolean entry(final int key, final Map_T_Location_3 data) {
									final T_Location l1 = E_Location.THIS.getElement(key);
									data.forEachEntry(new ForEachEntry<Boolean>() {
										@Override
										public boolean entry(int key, Boolean data) {
											final T_Location l2 = E_Location.THIS.getElement(key);
											
											P_ConnectedShip p = get();
											p.s = s;
											p.l1 = l1;
											p.l2 = l2;
											
											result.add(p);
											
											return true;
										}						
									});
									return true;
								}									
							}
						);
						return true;
					}
					
				}
			);			
		}
		
		/**
		 * Warning, this does not affect dynamic StateCompact of the state!
		 */
		public void clearAll() {
			storage.forEachEntry(
					new ForEachEntry<Map_T_Location_2>() {
						@Override
						public boolean entry(int key, Map_T_Location_2 data) {
							final T_Ship s = E_Ship.THIS.getElement(key);
							data.forEachEntry(
								new ForEachEntry<Map_T_Location_3>() {
									@Override
									public boolean entry(final int key, final Map_T_Location_3 data) {
										data.clear();
										return true;
									}									
								}
							);
							return true;
						}
						
					}
				);		
		}	
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_ConnectedShip> pool = new Pool<P_ConnectedShip>() {

		@Override
		protected P_ConnectedShip create() {
			return new P_ConnectedShip();
		}
		
	};
	
	public static P_ConnectedShip get() {
		P_ConnectedShip result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_ConnectedShip predicate) {
		pool.back(predicate);
	}
	
}