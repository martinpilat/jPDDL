package cz.cuni.mff.bw.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.bw.domain.Predicate;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.bw.problem.E_Slippery;
import cz.cuni.mff.bw.problem.E_Block;
import cz.cuni.mff.bw.problem.E_Sticky;
import cz.cuni.mff.bw.problem.E_Hand;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * PREDICATE
 * (at ?v - vehicle ?l - location)
 */
public final class P_Holding extends Predicate {
	
	public static final int FLAG_TYPE = 3;
	
	public T_Hand h;
	public T_Block x;
	
	public P_Holding() {		
	}
	
	public P_Holding(T_Hand h, T_Block x) {
		this.h = h;
		this.x = x;
	}
	
	@Override
	public P_Holding create() {
		return new P_Holding();
	}
	
	public void reset() {
		h = null;
		x = null;
	}
	
	@Override
	public void assign(String[] args) {
		h = E_Hand.THIS.getElement(args[0]);
		if (h == null) h = E_Sticky.THIS.getElement(args[0]);
		if (h == null) h = E_Slippery.THIS.getElement(args[0]);
		
		x = E_Block.THIS.getElement(args[1]);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_Holding)) return false;
		P_Holding other = (P_Holding)obj;
		if (h != other.h) return false;
		if (x != other.x) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_Holding[h - hand = " + h + ",x - block = " + x + "]";
	}
	
	@Override
	public String getName() {
		return "holding";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(holding " + h.name + " " + x.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(h, x);
	}
	
	public static int toInt(T_Hand v, T_Block l) {
		return   (T_Hand.getIndex(v) << (T_Block.bitCount + Predicate.MASK_TYPE_BIT_COUNT))
			   | (T_Block.getIndex(l) << (Predicate.MASK_TYPE_BIT_COUNT))
			   | FLAG_TYPE;
	}
	
	public static T_Hand fromInt_h(int predicate) {
		return E_Hand.THIS.getElement( (predicate >> (T_Block.bitCount + Predicate.MASK_TYPE_BIT_COUNT)) & T_Hand.bitMask );
	}
	
	public static T_Block fromInt_x(int predicate) {
		return E_Block.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Block.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Hand_1 extends FastIntMap<Map_T_Block_2> {

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
		
		public Map_T_Block_2 get(T_Hand obj) {
			return get(T_Hand.getIndex(obj));
		}
		
		public boolean containsKey(T_Hand obj) {
			return containsKey(T_Hand.getIndex(obj));
		}
		
		public void put(T_Hand key, Map_T_Block_2 value) {
			put(T_Hand.getIndex(key), value);
		}
		
		public Map_T_Block_2 remove(T_Hand key) {
			return remove(T_Hand.getIndex(key));
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
	
	public static final class Storage_P_Holding implements IStorage<P_Holding> {
		
		private final Map_T_Hand_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_Holding() {
			storage = new Map_T_Hand_1(T_Hand.getCount());
		}
		
		protected Storage_P_Holding(Map_T_Hand_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_Holding clone() {
			return new Storage_P_Holding(storage.clone());
		}
		
		@Override
		public String getName() {
			return "holding";
		}
		
		@Override
		public Class<P_Holding> getPredicateClass() {
			return P_Holding.class;
		}
		
		public boolean isSet(T_Hand h, T_Block x) {
			Map_T_Block_2 map_t_location_2 = storage.get(h);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.containsKey(x);
		}
		
		/**
		 * Returns TRUE if a NEW predicate was set; false if the predicate was already stored.
		 * @param h
		 * @param x
		 * @return
		 */
		public boolean set(T_Hand h, T_Block x) {
			Map_T_Block_2 map_t_location_2 = storage.get(h);
			if (map_t_location_2 == null) {
				map_t_location_2 = new Map_T_Block_2(T_Block.getCount());
				storage.put(h, map_t_location_2);
			}			
			if (map_t_location_2.put(x, true)) {
				compact.set(P_Holding.toInt(h, x));
				return true;
			}
			return false;
		}
		
		/**
		 * Returns TRUE if a predicate was removed (i.e. was previously set within the storage)
		 * @param h
		 * @param x
		 * @return
		 */
		public boolean clear(T_Hand h, T_Block x) {
			Map_T_Block_2 map_t_location_2 = storage.get(h);
			if (map_t_location_2 == null) return false;
			if (map_t_location_2.remove(x) != null) {
				compact.clear(P_Holding.toInt(h, x));
				return true;
			}
			return false;
		}
	 
		
		@Override
		public boolean isSet(P_Holding p) {
			return isSet(p.h, p.x);
		}

		@Override
		public boolean set(P_Holding p) {
			return set(p.h, p.x);
		}

		@Override
		public boolean clear(P_Holding p) {
			return clear(p.h, p.x);
		}
				
		@Override
		public Map_T_Hand_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_Holding> result) {
			storage.forEachEntry(new ForEachEntry<Map_T_Block_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Block_2 data) {
					final T_Hand v = E_Hand.THIS.getElement(key);
					data.forEachEntry(new ForEachEntry<Boolean>() {
						@Override
						public boolean entry(int key, Boolean data) {
							final T_Block l = E_Block.THIS.getElement(key);
							
							P_Holding p = get();
							p.h = v;
							p.x = l;
							
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
			storage.clear();
		}		
		
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_Holding> pool = new Pool<P_Holding>() {

		@Override
		protected P_Holding create() {
			return new P_Holding();
		}
		
	};
	
	public static P_Holding get() {
		P_Holding result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_Holding predicate) {
		pool.back(predicate);
	}
	
}
