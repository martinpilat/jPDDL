package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.auv.domain.Predicate;
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
 * (entry ?s - ship ?l - location)
 */
public final class P_Entry extends Predicate {
	
	public T_Ship s;
	public T_Location l;
	
	public P_Entry() {
	}
	
	public P_Entry(T_Ship s, T_Location l) {
		super();
		this.s = s;
		this.l = l;
	}
	
	@Override
	public P_Entry create() {
		return new P_Entry();
	}
	
	public void reset() {
		s = null;
		l = null;
	}	
	
	@Override
	public void assign(String[] args) {
		s = E_Ship.THIS.getElement(args[0]);
		l = E_Location.THIS.getElement(args[1]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Entry)) return false;
		P_Entry other = (P_Entry)obj;
		if (s != other.s) return false;
		if (l != other.l) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Entry[s - ship = " + s + ",l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "entry";
	}
	
	@Override
	public String toPredicate() {
		return "(entry " + s.name + " " + l.name + ")";
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
	
	public static final class Storage_P_Entry implements IStorage<P_Entry> {
		
		private final Map_T_Ship_1 storage;
		
		public Storage_P_Entry() {
			storage = new Map_T_Ship_1(T_Ship.getCount());
		}
		
		protected Storage_P_Entry(Map_T_Ship_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Entry clone() {
			return new Storage_P_Entry(storage.clone());
		}
		
		@Override
		public String getName() {
			return "entry";
		}
		
		@Override
		public Class<P_Entry> getPredicateClass() {
			return P_Entry.class;
		}
		
		public boolean isSet(T_Ship s, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.containsKey(l);
		}
		
		public boolean set(T_Ship s, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) {
				map_t_location_2 = new Map_T_Location_2(T_Location.getCount());
				storage.put(s, map_t_location_2);
			}			
			return map_t_location_2.put(l, true);
		}
		
		public boolean clear(T_Ship s, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.remove(l) != null;
		}
	 
		
		@Override
		public boolean isSet(P_Entry p) {
			return isSet(p.s, p.l);
		}

		@Override
		public boolean set(P_Entry p) {
			return set(p.s, p.l);
		}

		@Override
		public boolean clear(P_Entry p) {
			return clear(p.s, p.l);
		}
		
		@Override
		public Map_T_Ship_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Entry> result) {
			storage.forEachEntry(new ForEachEntry<Map_T_Location_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Location_2 data) {
					final T_Ship s = E_Ship.THIS.getElement(key);
					data.forEachEntry(new ForEachEntry<Boolean>() {
						@Override
						public boolean entry(int key, Boolean data) {
							final T_Location l = E_Location.THIS.getElement(key);
							
							P_Entry p = get();
							p.s = s;
							p.l = l;
							
							result.add(p);
							
							return true;
						}						
					});
					return true;
				}
				
			});			
		}
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Entry> pool = new Pool<P_Entry>() {

		@Override
		protected P_Entry create() {
			return new P_Entry();
		}
		
	};
	
	public static P_Entry get() {
		P_Entry result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Entry predicate) {
		pool.back(predicate);
	}
	
}
