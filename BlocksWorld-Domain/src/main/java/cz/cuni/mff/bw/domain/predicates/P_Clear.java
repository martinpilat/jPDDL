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
 * (clear ?x - block) 
 */
public class P_Clear extends Predicate {

	public static final int FLAG_TYPE = 1;
	
	public T_Block x;
	
	public P_Clear() {
	}
	
	public P_Clear(T_Block x) {
		super();
		this.x = x;
	}
	
	@Override
	public P_Clear create() {
		return new P_Clear();
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
		if (!(obj instanceof P_Clear)) return false;
		P_Clear other = (P_Clear)obj;
		if (x != other.x) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Clear[x - block = " + x + "]";
	}
	
	@Override
	public String getName() {
		return "clear";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(clear " + x.name + ")";
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
	
	public static final class Storage_P_Clear implements IStorage<P_Clear> {
		
		private final Map_T_Block_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_Clear() {
			storage = new Map_T_Block_1(T_Block.getCount());
		}
		
		protected Storage_P_Clear(Map_T_Block_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Clear clone() {
			return new Storage_P_Clear(storage.clone());
		}
		
		@Override
		public String getName() {
			return "clear";
		}
		
		@Override
		public Class<P_Clear> getPredicateClass() {
			return P_Clear.class;
		}
		
		public boolean isSet(T_Block x) {
			return storage.containsKey(x);
		}
		
		public boolean set(T_Block x) {
			if (storage.put(x, true)) {
				compact.set(P_Clear.toInt(x));
				return true;
			}
			return false;
		}
		
		public boolean clear(T_Block x) {
			if (storage.remove(x) != null) {
				compact.clear(P_Clear.toInt(x));
				return true;
			}
			return false;
		}
		
		@Override
		public boolean isSet(P_Clear p) {
			return isSet(p.x);
		}

		@Override
		public boolean set(P_Clear p) {
			return set(p.x);
		}

		@Override
		public boolean clear(P_Clear p) {
			return clear(p.x);
		}
		
		@Override
		public Map_T_Block_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Clear> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Block l = E_Block.THIS.getElement(key);
							
					P_Clear p = get();
					p.x = l;
						
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
	
	private static final Pool<P_Clear> pool = new Pool<P_Clear>() {

		@Override
		protected P_Clear create() {
			return new P_Clear();
		}
		
	};
	
	public static P_Clear get() {
		P_Clear result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Clear predicate) {
		pool.back(predicate);
	}
	
}
