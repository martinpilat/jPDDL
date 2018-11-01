package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.auv.domain.Predicate;
import cz.cuni.mff.auv.domain.types.T_Resource;
import cz.cuni.mff.auv.problem.E_Resource;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * PREDICATE
 * (sampled ?r - resource)
 */
public class P_Sampled extends Predicate {

	public T_Resource r;
	
	public P_Sampled() {
	}
	
	public P_Sampled(T_Resource r) {
		super();
		this.r = r;
	}
	
	@Override
	public P_Sampled create() {
		return new P_Sampled();
	}
	
	public void reset() {
		r = null;
	}
	
	@Override
	public void assign(String[] args) {
		r = E_Resource.THIS.getElement(args[0]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Sampled)) return false;
		P_Sampled other = (P_Sampled)obj;
		if (r != other.r) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Sampled[s - ship = " + r + "]";
	}
	
	@Override
	public String getName() {
		return "sampled";
	}
	
	@Override
	public String toPredicate() {
		return "(sampled " + r.name + ")";
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Resource_1 extends FastIntMap<Boolean> {

		protected Map_T_Resource_1() {			
		}
		
		public Map_T_Resource_1(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Resource_1 clone() {
			Map_T_Resource_1 result = new Map_T_Resource_1();
			rewriteClone(result);
			return result;
		}
		
		public Boolean get(T_Resource obj) {
			return get(T_Resource.getIndex(obj));
		}
		
		public boolean containsKey(T_Resource obj) {
			return containsKey(T_Resource.getIndex(obj));
		}
		
		public void put(T_Resource key, Boolean value) {
			put(T_Resource.getIndex(key), value);
		}
		
		public Boolean remove(T_Resource key) {
			return remove(T_Resource.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_Sampled implements IStorage<P_Sampled> {
		
		private final Map_T_Resource_1 storage;
		
		public Storage_P_Sampled() {
			storage = new Map_T_Resource_1(T_Resource.getCount());
		}
		
		protected Storage_P_Sampled(Map_T_Resource_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Sampled clone() {
			return new Storage_P_Sampled(storage.clone());
		}	
		
		
		@Override
		public String getName() {
			return "sampled";
		}
		
		@Override
		public Class<P_Sampled> getPredicateClass() {
			return P_Sampled.class;
		}
		
		public boolean isSet(T_Resource r) {
			return storage.containsKey(r);
		}
		
		public void set(T_Resource r) {
			storage.put(r, true);
		}
		
		public void clear(T_Resource r) {
			storage.remove(r);
		}
		
		@Override
		public boolean isSet(P_Sampled p) {
			return isSet(p.r);
		}

		@Override
		public void set(P_Sampled p) {
			set(p.r);
		}

		@Override
		public void clear(P_Sampled p) {
			clear(p.r);
		}
		
		@Override
		public Map_T_Resource_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Sampled> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Resource r = E_Resource.THIS.getElement(key);
							
					P_Sampled p = get();
					p.r = r;
						
					result.add(p);
							
					return true;
				}						
			});			
		}
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Sampled> pool = new Pool<P_Sampled>() {

		@Override
		protected P_Sampled create() {
			return new P_Sampled();
		}
		
	};
	
	public static P_Sampled get() {
		P_Sampled result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Sampled predicate) {
		pool.back(predicate);
	}
	
}
