package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.auv.domain.predicates.P_Operational.Map_T_AUV_1;
import cz.cuni.mff.auv.domain.predicates.P_Operational.Storage_P_Operational;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.problem.E_Ship;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * PREDICATE
 * (operational ?s - ship)
 */
public class P_Outside extends PDDLPredicate {

	public T_Ship s;
	
	public P_Outside() {
	}
	
	public P_Outside(T_Ship s) {
		super();
		this.s = s;
	}
	
	public void reset() {
		s = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Outside)) return false;
		P_Outside other = (P_Outside)obj;
		if (s != other.s) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Outside[s - ship = " + s + "]";
	}
	
	@Override
	public String getName() {
		return "outside";
	}
	
	@Override
	public String toPredicate() {
		return "(outside " + s.name + ")";
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Ship_1 extends FastIntMap<Boolean> {

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
		
		public Boolean get(T_Ship obj) {
			return get(T_Ship.getIndex(obj));
		}
		
		public boolean containsKey(T_Ship obj) {
			return containsKey(T_Ship.getIndex(obj));
		}
		
		public void put(T_Ship key, Boolean value) {
			put(T_Ship.getIndex(key), value);
		}
		
		public Boolean remove(T_Ship key) {
			return remove(T_Ship.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_Outside implements IStorage<P_Outside> {
		
		private final Map_T_Ship_1 storage;
		
		public Storage_P_Outside() {
			storage = new Map_T_Ship_1(T_Ship.getCount());
		}
		
		protected Storage_P_Outside(Map_T_Ship_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Outside clone() {
			return new Storage_P_Outside(storage.clone());
		}		
		
		@Override
		public String getName() {
			return "outside";
		}
		
		@Override
		public Class<P_Outside> getPredicateClass() {
			return P_Outside.class;
		}
		
		public boolean isSet(T_Ship s) {
			return storage.containsKey(s);
		}
		
		public void set(T_Ship s) {
			storage.put(s, true);
		}
		
		public void clear(T_Ship s) {
			storage.remove(s);
		}
	 
		
		@Override
		public boolean isSet(P_Outside p) {
			return isSet(p.s);
		}

		@Override
		public void set(P_Outside p) {
			set(p.s);
		}

		@Override
		public void clear(P_Outside p) {
			clear(p.s);
		}
		
		@Override
		public Map_T_Ship_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Outside> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Ship s = E_Ship.THIS.getElement(key);
							
					P_Outside p = get();
					p.s = s;
						
					result.add(p);
							
					return true;
				}						
			});			
		}
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Outside> pool = new Pool<P_Outside>() {

		@Override
		protected P_Outside create() {
			return new P_Outside();
		}
		
	};
	
	public static P_Outside get() {
		P_Outside result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Outside predicate) {
		pool.back(predicate);
	}
	
}
