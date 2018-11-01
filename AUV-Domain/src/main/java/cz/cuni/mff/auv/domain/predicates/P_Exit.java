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
 * (exit ?s - ship ?l - location)
 */
public final class P_Exit extends Predicate {
	
	public T_Ship s;
	public T_Location l;
	
	public P_Exit() {
	}
	
	public P_Exit(T_Ship s, T_Location l) {
		super();
		this.s = s;
		this.l = l;
	}
	
	@Override
	public P_Exit create() {
		return new P_Exit();
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
		if (!(obj instanceof P_Exit)) return false;
		P_Exit other = (P_Exit)obj;
		if (s != other.s) return false;
		if (l != other.l) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Exit[s - ship = " + s + ",l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "exit";
	}
	
	@Override
	public String toPredicate() {
		return "(exit " + s.name + " " + l.name + ")";
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
		
		public void put(T_Location key, Boolean value) {
			put(T_Location.getIndex(key), value);
		}
		
		public Boolean remove(T_Location key) {
			return remove(T_Location.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_Exit implements IStorage<P_Exit> {
		
		private final Map_T_Ship_1 storage;
		
		public Storage_P_Exit() {
			storage = new Map_T_Ship_1(T_Ship.getCount());
		}
		
		protected Storage_P_Exit(Map_T_Ship_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Exit clone() {
			return new Storage_P_Exit(storage.clone());
		}		
		
		@Override
		public String getName() {
			return "exit";
		}
		
		@Override
		public Class<P_Exit> getPredicateClass() {
			return P_Exit.class;
		}
		
		public boolean isSet(T_Ship s, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.containsKey(l);
		}
		
		public void set(T_Ship s, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) {
				map_t_location_2 = new Map_T_Location_2(T_Location.getCount());
				storage.put(s, map_t_location_2);
			}			
			map_t_location_2.put(l, true);
		}
		
		public void clear(T_Ship s, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(s);
			if (map_t_location_2 == null) return;
			map_t_location_2.remove(l);
		}
	 
		
		@Override
		public boolean isSet(P_Exit p) {
			return isSet(p.s, p.l);
		}

		@Override
		public void set(P_Exit p) {
			set(p.s, p.l);
		}

		@Override
		public void clear(P_Exit p) {
			clear(p.s, p.l);
		}
		
		@Override
		public Map_T_Ship_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Exit> result) {
			storage.forEachEntry(new ForEachEntry<Map_T_Location_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Location_2 data) {
					final T_Ship s = E_Ship.THIS.getElement(key);
					data.forEachEntry(new ForEachEntry<Boolean>() {
						@Override
						public boolean entry(int key, Boolean data) {
							final T_Location l = E_Location.THIS.getElement(key);
							
							P_Exit p = get();
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
	
	private static final Pool<P_Exit> pool = new Pool<P_Exit>() {

		@Override
		protected P_Exit create() {
			return new P_Exit();
		}
		
	};
	
	public static P_Exit get() {
		P_Exit result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Exit predicate) {
		pool.back(predicate);
	}
	
}
