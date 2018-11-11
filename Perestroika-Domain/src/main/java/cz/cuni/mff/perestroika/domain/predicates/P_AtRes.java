package cz.cuni.mff.perestroika.domain.predicates;

import java.util.Collection;

import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Predicate;
import cz.cuni.mff.perestroika.domain.predicates.P_AtAgent.Map_T_Location_1;
import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.domain.types.T_Resource;
import cz.cuni.mff.perestroika.problem.E_Location;
import cz.cuni.mff.perestroika.problem.E_Resource;

/**
 * PREDICATE
 * (at-res ?r - resource ?l - location)
 */
public final class P_AtRes extends Predicate {
	
	public static final int FLAG_TYPE = 5;
	
	public T_Resource r;
	public T_Location l;
	
	public P_AtRes() {
	}
	
	public P_AtRes(T_Resource r, T_Location l) {
		this.r = r;
		this.l = l;
	}
	
	@Override
	public P_AtRes create() {
		return new P_AtRes();
	}
	
	public void reset() {
		r = null;
		l = null;
	}
	
	@Override
	public void assign(String[] args) {
		r = E_Resource.THIS.getElement(args[0]);
		if (r == null) r = E_Resource.THIS.getElement(args[0]);
		
		l = E_Location.THIS.getElement(args[1]);
	}
		
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof P_AtRes)) return false;
		P_AtRes other = (P_AtRes)obj;
		if (r != other.r) return false;
		if (l != other.l) return false;
		return true;
	}

	@Override
	public String toString() {
		return "P_AtRes[r - resource = " + r + ",l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "at-res";
	}
	
	@Override
	public boolean isStatic() {
		return false;
	}
	
	@Override
	public String toPredicate() {
		return "(at-res " + r.name + " " + l.name + ")";
	}
	
	@Override
	public int toInteger() {
		return toInt(r, l);
	}
	
	public static int toInt(T_Resource r, T_Location l) {
		return   (T_Resource.getIndex(r) << (T_Location.bitCount + Predicate.MASK_TYPE_BIT_COUNT))
			   | (T_Location.getIndex(l) << (Predicate.MASK_TYPE_BIT_COUNT))
			   | FLAG_TYPE;
	}
	
	public static T_Resource fromInt_r(int predicate) {
		return E_Resource.THIS.getElement( (predicate >> (T_Location.bitCount + Predicate.MASK_TYPE_BIT_COUNT)) & T_Resource.bitMask );
	}
	
	public static T_Location fromInt_l(int predicate) {
		return E_Location.THIS.getElement( (predicate >> (Predicate.MASK_TYPE_BIT_COUNT)) & T_Location.bitMask );
	}
	
	// =======
	// STORAGE
	// =======
	
	public static final class Map_T_Resource_1 extends FastIntMap<Map_T_Location_2> {

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
				
		public Map_T_Location_2 get(T_Resource obj) {
			return get(T_Resource.getIndex(obj));
		}
		
		public boolean containsKey(T_Resource obj) {
			return containsKey(T_Resource.getIndex(obj));
		}
		
		public void put(T_Resource key, Map_T_Location_2 value) {
			put(T_Resource.getIndex(key), value);
		}
		
		public Map_T_Location_2 remove(T_Resource key) {
			return remove(T_Resource.getIndex(key));
		}		
		
	}
	
	public static final class Map_T_Location_2 extends FastIntMap<Boolean> {

		protected Map_T_Location_2() {			
		}
		
		public Map_T_Location_2(int maxElements) {
			super(maxElements+1);
		}
		
		@Override
		public Map_T_Location_2 clone() {
			Map_T_Location_2 result = new Map_T_Location_2();
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
	
	public static final class Storage_P_AtRes implements IStorage<P_AtRes> {
		
		private final Map_T_Resource_1 storage;
		
		public StateCompact compact;
		
		public Storage_P_AtRes() {
			storage = new Map_T_Resource_1(T_Resource.getCount());
		}
		
		protected Storage_P_AtRes(Map_T_Resource_1 storage) {
			this.storage = storage;
		}
		
		@Override
		public Storage_P_AtRes clone() {
			return new Storage_P_AtRes(storage.clone());
		}		
		
		@Override
		public String getName() {
			return "at-res";
		}
		
		@Override
		public Class<P_AtRes> getPredicateClass() {
			return P_AtRes.class;
		}
		
		public boolean isSet(T_Resource r, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(r);
			if (map_t_location_2 == null) return false;
			return map_t_location_2.containsKey(l);
		}
		
		public boolean set(T_Resource r, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(r);
			if (map_t_location_2 == null) {
				map_t_location_2 = new Map_T_Location_2(T_Location.getCount());
				storage.put(r, map_t_location_2);
			}			
			if (map_t_location_2.put(l, true)) {
				compact.set(P_AtRes.toInt(r, l));
				return true;
			}
			return false;
		}
		
		public boolean clear(T_Resource r, T_Location l) {
			Map_T_Location_2 map_t_location_2 = storage.get(r);
			if (map_t_location_2 == null) return false;
			if (map_t_location_2.remove(l) != null) {
				compact.clear(P_AtRes.toInt(r, l));
				return true;
			}
			return false;
		}
	 
		@Override
		public boolean isSet(P_AtRes p) {
			return isSet(p.r, p.l);
		}

		@Override
		public boolean set(P_AtRes p) {
			return set(p.r, p.l);
		}

		@Override
		public boolean clear(P_AtRes p) {
			return clear(p.r, p.l);
		}
		
		@Override
		public Map_T_Resource_1 internal() {
			return storage;
		}
		
		@Override
		public void getAll(final Collection<P_AtRes> result) {
			storage.forEachEntry(new ForEachEntry<Map_T_Location_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Location_2 data) {
					final T_Resource r = E_Resource.THIS.getElement(key);
					data.forEachEntry(new ForEachEntry<Boolean>() {
						@Override
						public boolean entry(int key, Boolean data) {
							final T_Location l = E_Location.THIS.getElement(key);
							
							P_AtRes p = get();
							p.r = r;
							p.l = l;
							
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
			storage.forEachEntry(new ForEachEntry<Map_T_Location_2>() {
				@Override
				public boolean entry(final int key, final Map_T_Location_2 data) {
					data.clear();
					return true;
				}
				
			});	
		}	
				
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<P_AtRes> pool = new Pool<P_AtRes>() {

		@Override
		protected P_AtRes create() {
			return new P_AtRes();
		}
		
	};
	
	public static P_AtRes get() {
		P_AtRes result = pool.get();
		result.reset();
		return result;
	}
	
	public static void back(P_AtRes predicate) {
		pool.back(predicate);
	}
	
}
