package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.auv.domain.Predicate;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * PREDICATE
 * (free ?l - location)
 */
public class P_Free extends Predicate {

	public T_Location l;
	
	public P_Free() {
	}
	
	public P_Free(T_Location l) {
		this.l = l;
	}
	
	@Override
	public P_Free create() {
		return new P_Free();
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
		if (!(obj instanceof P_Free)) return false;
		P_Free other = (P_Free)obj;
		if (l != other.l) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Free[l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "free";
	}
	
	@Override
	public String toPredicate() {
		return "(free " + l.name + ")";
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
	
	public static final class Storage_P_Free implements IStorage<P_Free> {
		
		private final Map_T_Location_1 storage;
		
		public Storage_P_Free() {
			storage = new Map_T_Location_1(T_Location.getCount());
		}
		
		protected Storage_P_Free(Map_T_Location_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Free clone() {
			return new Storage_P_Free(storage.clone());
		}
		
		@Override
		public String getName() {
			return "free";
		}
		
		@Override
		public Class<P_Free> getPredicateClass() {
			return P_Free.class;
		}
		
		public boolean isSet(T_Location l) {
			return storage.containsKey(l);
		}
		
		public boolean set(T_Location l) {
			return storage.put(l, true);
		}
		
		public boolean clear(T_Location l) {
			return storage.remove(l);
		}
	 
		
		@Override
		public boolean isSet(P_Free p) {
			return isSet(p.l);
		}

		@Override
		public boolean set(P_Free p) {
			return set(p.l);
		}

		@Override
		public boolean clear(P_Free p) {
			return clear(p.l);
		}
		
		@Override
		public Map_T_Location_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Free> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Location l = E_Location.THIS.getElement(key);
							
					P_Free p = get();
					p.l = l;
						
					result.add(p);
							
					return true;
				}						
			});			
		}
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Free> pool = new Pool<P_Free>() {

		@Override
		protected P_Free create() {
			return new P_Free();
		}
		
	};
	
	public static P_Free get() {
		P_Free result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Free predicate) {
		pool.back(predicate);
	}
	
}
