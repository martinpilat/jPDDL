package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.auv.problem.E_Vehicle;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * PREDICATE
 * (at ?v - vehicle ?l - location)
 */
public final class P_At extends PDDLPredicate {
	
	public T_Vehicle v;
	public T_Location l;
	
	public P_At() {		
	}
	
	public P_At(T_Vehicle v, T_Location l) {
		this.v = v;
		this.l = l;
	}
	
	public void reset() {
		v = null;
		l = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_At)) return false;
		P_At other = (P_At)obj;
		if (v != other.v) return false;
		if (l != other.l) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_At[v - vehicle = " + v + ",l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "at";
	}
	
	@Override
	public String toPredicate() {
		return "(at " + v.name + " " + l.name + ")";
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Vehicle_1 extends FastIntMap<Map_T_Location_2> {

		protected Map_T_Vehicle_1() {			
		}
		
		public Map_T_Vehicle_1(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Vehicle_1 clone() {
			Map_T_Vehicle_1 result = new Map_T_Vehicle_1();
			rewriteClone(result);
			return result;
		}
		
		public Map_T_Location_2 get(T_Vehicle obj) {
			return get(T_Vehicle.getIndex(obj));
		}
		
		public boolean containsKey(T_Vehicle obj) {
			return containsKey(T_Vehicle.getIndex(obj));
		}
		
		public void put(T_Vehicle key, Map_T_Location_2 value) {
			put(T_Vehicle.getIndex(key), value);
		}
		
		public Map_T_Location_2 remove(T_Vehicle key) {
			return remove(T_Vehicle.getIndex(key));
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
	
	public static final class Storage_P_At implements IStorage<P_At> {
		
		private final Map_T_Vehicle_1 storage;
		
		public Storage_P_At() {
			storage = new Map_T_Vehicle_1(T_Vehicle.getCount());
		}
		
		protected Storage_P_At(Map_T_Vehicle_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_At clone() {
			return new Storage_P_At(storage.clone());
		}
		
		@Override
		public String getName() {
			return "at";
		}
		
		@Override
		public Class<P_At> getPredicateClass() {
			return P_At.class;
		}
		
		public boolean isSet(T_Vehicle v, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(v);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.containsKey(l);
		}
		
		public void set(T_Vehicle v, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(v);
			if (map_t_location_2 == null) {
				map_t_location_2 = new Map_T_Location_2(T_Location.getCount());
				storage.put(v, map_t_location_2);
			}			
			map_t_location_2.put(l, true);
		}
		
		public void clear(T_Vehicle v, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(v);
			if (map_t_location_2 == null) return;
			map_t_location_2.remove(l);
		}
	 
		
		@Override
		public boolean isSet(P_At p) {
			return isSet(p.v, p.l);
		}

		@Override
		public void set(P_At p) {
			set(p.v, p.l);
		}

		@Override
		public void clear(P_At p) {
			clear(p.v, p.l);
		}
				
		@Override
		public Map_T_Vehicle_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_At> result) {
			storage.forEachEntry(new ForEachEntry<Map_T_Location_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Location_2 data) {
					final T_Vehicle v = E_Vehicle.THIS.getElement(key);
					data.forEachEntry(new ForEachEntry<Boolean>() {
						@Override
						public boolean entry(int key, Boolean data) {
							final T_Location l = E_Location.THIS.getElement(key);
							
							P_At p = get();
							p.v = v;
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
	
	private static final Pool<P_At> pool = new Pool<P_At>() {

		@Override
		protected P_At create() {
			return new P_At();
		}
		
	};
	
	public static P_At get() {
		P_At result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_At predicate) {
		pool.back(predicate);
	}
	
}
