package cz.cuni.mff.bw.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.bw.domain.Predicate;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.problem.E_Block;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * PREDICATE
 * (movable ?x - block)
 */
public class P_Movable extends Predicate {

	public static final int FLAG_TYPE = 4;
	
	public T_Block x;
	
	public P_Movable() {
	}
	
	public P_Movable(T_Block x) {
		super();
		this.x = x;
	}
	
	@Override
	public P_Movable create() {
		return new P_Movable();
	}
	
	public void reset() {
		x = null;
	}
	
	@Override
	public void assign(String[] args) {
		x = E_Block.THIS.getElement(args[0]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Movable)) return false;
		P_Movable other = (P_Movable)obj;
		if (x != other.x) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Movable[x - block = " + x + "]";
	}
	
	@Override
	public String getName() {
		return "movable";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(movable " + x.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(x);
	}
	
	public static int toInt(T_Block x) {
		return    (T_Block.getIndex(x) << (Predicate.MASK_TYPE_BIT_COUNT))
			    | FLAG_TYPE;
	}
	
	public static T_Block fromInt_x(int predicate) {
		return E_Block.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Block.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Block_1 extends FastIntMap<Boolean> {

		protected Map_T_Block_1() {			
		}
		
		public Map_T_Block_1(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Block_1 clone() {
			Map_T_Block_1 result = new Map_T_Block_1();
			rewriteClone(result);
			return result;
		}
		
		public Boolean get(T_Block obj) {
			return get(T_Block.getIndex(obj));
		}
		
		public boolean containsKey(T_Block obj) {
			return containsKey(T_Block.getIndex(obj));
		}
		
		public boolean put(T_Block key, Boolean value) {
			return put(T_Block.getIndex(key), value);
		}
		
		public Boolean remove(T_Block key) {
			return remove(T_Block.getIndex(key));
		}
		
	}
	
	public static final class Storage_P_Movable implements IStorage<P_Movable> {
		
		private final Map_T_Block_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_Movable() {
			storage = new Map_T_Block_1(T_Block.getCount());
		}
		
		protected Storage_P_Movable(Map_T_Block_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Movable clone() {
			return new Storage_P_Movable(storage.clone());
		}	
		
		
		@Override
		public String getName() {
			return "movable";
		}
		
		@Override
		public Class<P_Movable> getPredicateClass() {
			return P_Movable.class;
		}
		
		public boolean isSet(T_Block x) {
			return storage.containsKey(x);
		}
		
		public boolean set(T_Block x) {
			if (storage.put(x, true)) {
				compact.set(P_Movable.toInt(x));
				return true;
			}
			return false;
		}
		
		public boolean clear(T_Block x) {
			if (storage.remove(x) != null) {
				compact.clear(P_Movable.toInt(x));
				return true;
			}
			return false;
		}
		
		@Override
		public boolean isSet(P_Movable p) {
			return isSet(p.x);
		}

		@Override
		public boolean set(P_Movable p) {
			return set(p.x);
		}

		@Override
		public boolean clear(P_Movable p) {
			return clear(p.x);
		}
		
		@Override
		public Map_T_Block_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Movable> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Block r = E_Block.THIS.getElement(key);
							
					P_Movable p = get();
					p.x = r;
						
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
	
	private static final Pool<P_Movable> pool = new Pool<P_Movable>() {

		@Override
		protected P_Movable create() {
			return new P_Movable();
		}
		
	};
	
	public static P_Movable get() {
		P_Movable result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Movable predicate) {
		pool.back(predicate);
	}
	
}
