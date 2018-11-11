package cz.cuni.mff.jpddl.store;

import java.util.Arrays;

/**
 * Map (int => DATA) that has pre-allocated fixed capacity and:
 * 
 * ADD(int, data) - O(1)
 * REMOVE(int) - O(1)
 * GET(int) - O(1)
 * CONTAINS(int) - O(1)
 * 
 * ITERATE over ENTRIES - O(size) ... use {@link #forEachEntry(ForEachEntry)}.
 * 
 * Requires keys to always be within the range of [0;capacity)
 * 
 * The cost is that we need three arrays of the 'capacity' size.
 * 
 * GC-free implementation!
 * 
 * @author Jimmy
 *
 * @param <KEY>
 * @param <DATA>
 */
public class FastIntMap<DATA> implements Cloneable {
	
	public static abstract class ForEachEntry<DATA> implements DoubleList.ForEach<DATA> {

		protected FastIntMap<DATA> owner;
		
		@Override
		public final boolean element(int index, DATA data) {
			return entry(owner.keys[index], data);
		}
		
		public abstract boolean entry(int key, DATA data);
		
	}

	private DoubleList<DATA> list;
	
	/**
	 * [key] => index within {@link #list}
	 */
	private int[] indices;
	
	/**
	 * [index within {@link #list}] => key
	 */
	private int[] keys;
	
	protected FastIntMap() {		
	}
	
	public FastIntMap(int capacity) {
		list = new DoubleList<DATA>(capacity);
		indices = new int[capacity];
		Arrays.fill(indices, -1);
		keys = new int[capacity];
		Arrays.fill(keys, -1);
	}
	
	@Override
	public FastIntMap<DATA> clone() {
		FastIntMap<DATA> result = new FastIntMap<DATA>();
		rewriteClone(result);
		return result;
	}
	
	protected void rewriteClone(FastIntMap<DATA> result) {
		result.list = list.clone();
		result.indices = Arrays.copyOf(indices, indices.length);
		result.keys = Arrays.copyOf(keys, keys.length);
	}
	
	/**
	 * @param key
	 * @param data
	 * @return whether the container has changed, i.e., whether we have inserted the 'data' under the new key, i.e., no 'data' has been previously associated with 'key'
	 */
	public boolean put(int key, DATA data) {
		if (indices[key] >= 0) {
			// we already stores data under this key
			list.set(indices[key], data);
			return false;
		}
		
		indices[key] = list.add(data);
		keys[indices[key]] = key;
		
		return true;
	}
	
	public DATA get(int key) {
		if (indices[key] < 0) return null;
		
		return list.get(indices[key]);
	}
	
	/**
	 * @param key
	 * @return the data that was previously associated with the 'key'
	 */
	public DATA remove(int key) {
		if (indices[key] < 0) return null;
		
		DATA removed = list.remove(indices[key]);
		keys[indices[key]] = -1;
		indices[key] = -1;
		
		return removed;
	}
	
	public void clear() {
		Arrays.fill(indices, -1);
		Arrays.fill(keys, -1);
		list.clear();
	}
	
	public boolean containsKey(int key) {
		return indices[key] >= 0;
	}
	
	public void forEachEntry(ForEachEntry<DATA> proc) {
		proc.owner = this;
		list.forEach(proc);
	}
	
	public int size() {
		return list.size();
	}
	
	public int capacity() {
		return indices.length;
	}
		
}
