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
 * (ontable ?x - block)
 */
public class P_OnTable extends Predicate {

	public static final int FLAG_TYPE = 6;
	
	public T_Block x;
	
	public P_OnTable() {
	}
	
	public P_OnTable(T_Block x) {
		this.x = x;
	}
	
	@Override
	public P_OnTable create() {
		return new P_OnTable();
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
		if (!(obj instanceof P_OnTable)) return false;
		P_OnTable other = (P_OnTable)obj;
		if (x != other.x) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_OnTable[x - block = " + x + "]";
	}
	
	@Override
	public String getName() {
		return "ontable";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(ontable " + x.name + ")";
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
	
	public static final class Storage_P_OnTable implements IStorage<P_OnTable> {
		
		private final Map_T_Block_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_OnTable() {
			storage = new Map_T_Block_1(T_Block.getCount());
		}
		
		protected Storage_P_OnTable(Map_T_Block_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_OnTable clone() {
			return new Storage_P_OnTable(storage.clone());
		}
		
		@Override
		public String getName() {
			return "ontable";
		}
		
		@Override
		public Class<P_OnTable> getPredicateClass() {
			return P_OnTable.class;
		}
		
		public boolean isSet(T_Block x) {
			return storage.containsKey(x);
		}
		
		public boolean set(T_Block x) {
			if (storage.put(x, true)) {
				compact.set(P_OnTable.toInt(x));
				return true;
			}
			return false;
		}
		
		public boolean clear(T_Block x) {
			if (storage.remove(x) != null) {
				compact.clear(P_OnTable.toInt(x));
				return true;
			}
			return false;
		}	 
		
		@Override
		public boolean isSet(P_OnTable p) {
			return isSet(p.x);
		}

		@Override
		public boolean set(P_OnTable p) {
			return set(p.x);
		}

		@Override
		public boolean clear(P_OnTable p) {
			return clear(p.x);
		}
		
		@Override
		public Map_T_Block_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_OnTable> result) {
			storage.forEachEntry(new ForEachEntry<Boolean>() {
				@Override
				public boolean entry(final int key, final Boolean data) {
					final T_Block l = E_Block.THIS.getElement(key);
							
					P_OnTable p = get();
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
	
	private static final Pool<P_OnTable> pool = new Pool<P_OnTable>() {

		@Override
		protected P_OnTable create() {
			return new P_OnTable();
		}
		
	};
	
	public static P_OnTable get() {
		P_OnTable result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_OnTable predicate) {
		pool.back(predicate);
	}
	
}
