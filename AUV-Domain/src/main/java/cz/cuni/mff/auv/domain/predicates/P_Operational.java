package cz.cuni.mff.auv.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.auv.domain.Predicate;
import cz.cuni.mff.auv.domain.predicates.P_Entry.Map_T_Location_2;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.problem.E_Auv;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * PREDICATE
 * (operational ?a - auv)
 */
public class P_Operational extends Predicate {
	
	public static final int FLAG_TYPE = 10;
	
	public T_Auv a;
	
	public P_Operational() {
	}
	
	public P_Operational(T_Auv a) {
		this.a = a;
	}
	
	@Override
	public P_Operational create() {
		return new P_Operational();
	}
	
	public void reset() {
		a = null;
	}
	
	@Override
	public void assign(String[] args) {
		a = E_Auv.THIS.getElement(args[0]);
	}
	
	@Override
	public int compareTo(PDDLPredicate o) {
		if (o == null) return 1;
		return toPredicate().compareTo(o.toPredicate());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Operational)) return false;
		P_Operational other = (P_Operational)obj;
		if (a != other.a) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Operational[a - auv = " + a + "]";
	}
	
	@Override
	public String getName() {
		return "operational";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(operational " + a.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(a);
	}
	
	public static int toInt(T_Auv a) {
		return    (T_Auv.getIndex(a) << (Predicate.MASK_TYPE_BIT_COUNT))
			    | FLAG_TYPE;
	}
	
	public static T_Auv fromInt_a(int predicate) {
		return E_Auv.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Auv.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_AUV_1 extends FastIntMap<Boolean> {

		protected Map_T_AUV_1() {			
		}
		
		public Map_T_AUV_1(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_AUV_1 clone() {
			Map_T_AUV_1 result = new Map_T_AUV_1();
			rewriteClone(result);
			return result;
		}	
		
		public Boolean get(T_Auv obj) {
			return get(T_Auv.getIndex(obj));
		}
		
		public boolean containsKey(T_Auv obj) {
			return containsKey(T_Auv.getIndex(obj));
		}
		
		public boolean put(T_Auv key, Boolean value) {
			return put(T_Auv.getIndex(key), value);
		}
		
		public Boolean remove(T_Auv key) {
			return remove(T_Auv.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_Operational implements IStorage<P_Operational> {
		
		private final Map_T_AUV_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_Operational() {
			storage = new Map_T_AUV_1(T_Auv.getCount());
		}
		
		protected Storage_P_Operational(Map_T_AUV_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Operational clone() {
			return new Storage_P_Operational(storage.clone());
		}
				
		@Override
		public String getName() {
			return "operational";
		}
		
		@Override
		public Class<P_Operational> getPredicateClass() {
			return P_Operational.class;
		}
		
		public boolean isSet(T_Auv a) {
			return storage.containsKey(a);
		}
		
		public boolean set(T_Auv a) {
			if (storage.put(a, true)) {
				compact.set(P_Operational.toInt(a));
				return true;
			}
			return false;
		}
		
		public boolean clear(T_Auv a) {
			if (storage.remove(a) != null) {
				compact.clear(P_Operational.toInt(a));
				return true;
			}
			return false;
		}
	 
		
		@Override
		public boolean isSet(P_Operational p) {
			return isSet(p.a);
		}

		@Override
		public boolean set(P_Operational p) {
			return set(p.a);
		}

		@Override
		public boolean clear(P_Operational p) {
			return clear(p.a);
		}
		
		@Override
		public Map_T_AUV_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Operational> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Auv a = E_Auv.THIS.getElement(key);
							
					P_Operational p = get();
					p.a = a;
						
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
	
	private static final Pool<P_Operational> pool = new Pool<P_Operational>() {

		@Override
		protected P_Operational create() {
			return new P_Operational();
		}
		
	};
	
	public static P_Operational get() {
		P_Operational result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Operational predicate) {
		pool.back(predicate);
	}
	
}
