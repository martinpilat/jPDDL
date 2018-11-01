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
 * (dup-free ?l - location)
 */
public class P_DupFree extends Predicate {

	public T_Location l;
	
	public P_DupFree() {
	}
	
	public P_DupFree(T_Location l) {
		super();
		this.l = l;
	}
	
	@Override
	public P_DupFree create() {
		return new P_DupFree();
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
		if (!(obj instanceof P_DupFree)) return false;
		P_DupFree other = (P_DupFree)obj;
		if (l != other.l) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_DupFree[l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "dup-free";
	}
	
	@Override
	public String toPredicate() {
		return "(dup-free " + l.name + ")";
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
		
		public void put(T_Location key, Boolean value) {
			put(T_Location.getIndex(key), value);
		}
		
		public Boolean remove(T_Location key) {
			return remove(T_Location.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_DupFree implements IStorage<P_DupFree> {
		
		private final Map_T_Location_1 storage;
		
		public Storage_P_DupFree() {
			storage = new Map_T_Location_1(T_Location.getCount());
		}
		
		protected Storage_P_DupFree(Map_T_Location_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_DupFree clone() {
			return new Storage_P_DupFree(storage.clone());
		}
		
		@Override
		public String getName() {
			return "dup-free";
		}
		
		@Override
		public Class<P_DupFree> getPredicateClass() {
			return P_DupFree.class;
		}
		
		public boolean isSet(T_Location l) {
			return storage.containsKey(l);
		}
		
		public void set(T_Location l) {
			storage.put(l, true);
		}
		
		public void clear(T_Location l) {
			storage.remove(l);
		}
		
		@Override
		public boolean isSet(P_DupFree p) {
			return isSet(p.l);
		}

		@Override
		public void set(P_DupFree p) {
			set(p.l);
		}

		@Override
		public void clear(P_DupFree p) {
			clear(p.l);
		}
		
		@Override
		public Map_T_Location_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_DupFree> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Location l = E_Location.THIS.getElement(key);
							
					P_DupFree p = get();
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
	
	private static final Pool<P_DupFree> pool = new Pool<P_DupFree>() {

		@Override
		protected P_DupFree create() {
			return new P_DupFree();
		}
		
	};
	
	public static P_DupFree get() {
		P_DupFree result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_DupFree predicate) {
		pool.back(predicate);
	}
	
}
