package cz.cuni.mff.bw.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.bw.domain.Predicate;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.problem.E_Block;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * PREDICATE
 * (on ?x - block ?y - block)
 */
public class P_On extends Predicate {

	public static final int FLAG_TYPE = 5;
	
	public T_Block x;
	public T_Block y;
	
	public P_On() {
	}
	
	public P_On(T_Block x, T_Block y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public P_On create() {
		return new P_On();
	}
	
	public void reset() {
		x = null;
		y = null;
	}
	
	@Override
	public void assign(String[] args) {
		x = E_Block.THIS.getElement(args[0]);
		y = E_Block.THIS.getElement(args[1]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_On)) return false;
		P_On other = (P_On)obj;
		if (x != other.x) return false;
		if (y != other.y) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_On[x - block = " + x + ", y - block = " + y + "]";
	}
	
	@Override
	public String getName() {
		return "on";
	}
	
	@Override
	public boolean isStatic() {
		return true;
	}
	
	@Override
	public String toPredicate() {
		return "(on " + x.name + " " + y.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(x, y);
	}
	
	public static int toInt(T_Block x, T_Block y) {
		return   (T_Block.getIndex(x) << (T_Block.bitCount + Predicate.MASK_TYPE_BIT_COUNT))
			   | (T_Block.getIndex(y) << (Predicate.MASK_TYPE_BIT_COUNT))
			   | FLAG_TYPE;
	}
	
	public static T_Block fromInt_x(int predicate) {
		return E_Block.THIS.getElement( (predicate >> (T_Block.bitCount + Predicate.MASK_TYPE_BIT_COUNT)) & T_Block.bitMask );
	}
	
	public static T_Block fromInt_y(int predicate) {
		return E_Block.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Block.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Block_1 extends FastIntMap<Map_T_Block_2> {

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
		
		public Map_T_Block_2 get(T_Block obj) {
			return get(T_Block.getIndex(obj));
		}
		
		public boolean containsKey(T_Block obj) {
			return containsKey(T_Block.getIndex(obj));
		}
		
		public void put(T_Block key, Map_T_Block_2 value) {
			put(T_Block.getIndex(key), value);
		}
		
		public Map_T_Block_2 remove(T_Block key) {
			return remove(T_Block.getIndex(key));
		}	
		
	}
	
	public static final class Map_T_Block_2 extends FastIntMap<Boolean> {

		protected Map_T_Block_2() {			
		}
		
		public Map_T_Block_2(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Block_2 clone() {
			Map_T_Block_2 result = new Map_T_Block_2();
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
	
	public static final class Storage_P_On implements IStorage<P_On> {
		
		private final Map_T_Block_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_On() {
			storage = new Map_T_Block_1(T_Block.getCount());
		}
		
		protected Storage_P_On(Map_T_Block_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_On clone() {
			return new Storage_P_On(storage.clone());
		}		
		
		@Override
		public String getName() {
			return "on";
		}
		
		@Override
		public Class<P_On> getPredicateClass() {
			return P_On.class;
		}
		
		public boolean isSet(T_Block x, T_Block y) {
			Map_T_Block_2 map_t_location_2 = storage.get(x);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.containsKey(y);
		}
		
		public boolean set(T_Block x, T_Block y) {
			Map_T_Block_2 map_t_location_2 = storage.get(x);
			if (map_t_location_2 == null) {
				map_t_location_2 = new Map_T_Block_2(T_Block.getCount());
				storage.put(x, map_t_location_2);
				compact.set(P_On.toInt(x, y));
			}			
			return map_t_location_2.put(y, true);
		}
		
		public boolean clear(T_Block x, T_Block y) {
			Map_T_Block_2 map_t_location_2 = storage.get(x);
			if (map_t_location_2 == null) return false;
			compact.clear(P_On.toInt(x, y));
			return map_t_location_2.remove(y) != null;
		}
	 
		
		@Override
		public boolean isSet(P_On p) {
			return isSet(p.x, p.y);
		}

		@Override
		public boolean set(P_On p) {
			return set(p.x, p.y);
		}

		@Override
		public boolean clear(P_On p) {
			return clear(p.x, p.y);
		}
		
		@Override
		public Map_T_Block_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_On> result) {
			storage.forEachEntry(new ForEachEntry<Map_T_Block_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Block_2 data) {
					final T_Block l1 = E_Block.THIS.getElement(key);
					data.forEachEntry(new ForEachEntry<Boolean>() {
						@Override
						public boolean entry(int key, Boolean data) {
							final T_Block l2 = E_Block.THIS.getElement(key);
							
							P_On p = get();
							p.x = l1;
							p.y = l2;
							
							result.add(p);
							
							return true;
						}						
					});
					return true;
				}
				
			});			
		}
		
		/**
		 * Warning, this does not affect dynamic StateCompact of the state!
		 */
		public void clearAll() {
			storage.forEachEntry(new ForEachEntry<Map_T_Block_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Block_2 data) {
					data.clear();
					return true;
				}
				
			});	
		}	
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_On> pool = new Pool<P_On>() {

		@Override
		protected P_On create() {
			return new P_On();
		}
		
	};
	
	public static P_On get() {
		P_On result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_On predicate) {
		pool.back(predicate);
	}
	
}