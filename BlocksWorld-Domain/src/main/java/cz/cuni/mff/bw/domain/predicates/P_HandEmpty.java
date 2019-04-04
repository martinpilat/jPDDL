package cz.cuni.mff.bw.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.bw.domain.Predicate;
import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.bw.problem.E_Hand;
import cz.cuni.mff.bw.problem.E_Slippery;
import cz.cuni.mff.bw.problem.E_Sticky;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * PREDICATE
 * (handempty ?h - hand)
 */
public class P_HandEmpty extends Predicate {
	
	public static final int FLAG_TYPE = 2;
	
	public T_Hand h;
	
	public P_HandEmpty() {
	}
	
	public P_HandEmpty(T_Hand h) {
		this.h = h;
	}
	
	@Override
	public P_HandEmpty create() {
		return new P_HandEmpty();
	}
	
	public void reset() {
		h = null;
	}
	
	@Override
	public void assign(String[] args) {
		h = E_Hand.THIS.getElement(args[0]);
		if (h == null) h = E_Sticky.THIS.getElement(args[0]);
		if (h == null) h = E_Slippery.THIS.getElement(args[0]);
	}
	
	@Override
	public int compareTo(PDDLPredicate o) {
		if (o == null) return 1;
		return toPredicate().compareTo(o.toPredicate());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_HandEmpty)) return false;
		P_HandEmpty other = (P_HandEmpty)obj;
		if (h != other.h) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_HandEmpty[h - hand = " + h + "]";
	}
	
	@Override
	public String getName() {
		return "handempty";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(handempty " + h.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(h);
	}
	
	public static int toInt(T_Hand h) {
		return    (T_Hand.getIndex(h) << (Predicate.MASK_TYPE_BIT_COUNT))
			    | FLAG_TYPE;
	}
	
	public static T_Hand fromInt_h(int predicate) {
		return E_Hand.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Hand.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Hand_1 extends FastIntMap<Boolean> {

		protected Map_T_Hand_1() {			
		}
		
		public Map_T_Hand_1(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Hand_1 clone() {
			Map_T_Hand_1 result = new Map_T_Hand_1();
			rewriteClone(result);
			return result;
		}	
		
		public Boolean get(T_Hand obj) {
			return get(T_Hand.getIndex(obj));
		}
		
		public boolean containsKey(T_Hand obj) {
			return containsKey(T_Hand.getIndex(obj));
		}
		
		public boolean put(T_Hand key, Boolean value) {
			return put(T_Hand.getIndex(key), value);
		}
		
		public Boolean remove(T_Hand key) {
			return remove(T_Hand.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_HandEmpty implements IStorage<P_HandEmpty> {
		
		private final Map_T_Hand_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_HandEmpty() {
			storage = new Map_T_Hand_1(T_Hand.getCount());
		}
		
		protected Storage_P_HandEmpty(Map_T_Hand_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_HandEmpty clone() {
			return new Storage_P_HandEmpty(storage.clone());
		}
				
		@Override
		public String getName() {
			return "handempty";
		}
		
		@Override
		public Class<P_HandEmpty> getPredicateClass() {
			return P_HandEmpty.class;
		}
		
		public boolean isSet(T_Hand h) {
			return storage.containsKey(h);
		}
		
		public boolean set(T_Hand h) {
			if (storage.put(h, true)) {
				compact.set(P_HandEmpty.toInt(h));
				return true;
			}
			return false;
		}
		
		public boolean clear(T_Hand h) {
			if (storage.remove(h) != null) {
				compact.clear(P_HandEmpty.toInt(h));
				return true;
			}
			return false;
		}
	 
		
		@Override
		public boolean isSet(P_HandEmpty p) {
			return isSet(p.h);
		}

		@Override
		public boolean set(P_HandEmpty p) {
			return set(p.h);
		}

		@Override
		public boolean clear(P_HandEmpty p) {
			return clear(p.h);
		}
		
		@Override
		public Map_T_Hand_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_HandEmpty> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Hand a = E_Hand.THIS.getElement(key);
							
					P_HandEmpty p = get();
					p.h = a;
						
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
	
	private static final Pool<P_HandEmpty> pool = new Pool<P_HandEmpty>() {

		@Override
		protected P_HandEmpty create() {
			return new P_HandEmpty();
		}
		
	};
	
	public static P_HandEmpty get() {
		P_HandEmpty result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_HandEmpty predicate) {
		pool.back(predicate);
	}
	
}
