package cz.cuni.mff.jpddl.store;

import java.util.ArrayList;
import java.util.List;

public abstract class Pool<T> {

	private List<T> pool;
	
	public Pool() {
		this(0);
	}
	
	public Pool(int initialCapacity) {
		this(initialCapacity, initialCapacity + 10);
	}
	
	public Pool(int initialCapacity, int poolSize) {
		pool = new ArrayList<T>(poolSize);
		for (int i = 0; i < initialCapacity; ++i) {
			pool.add(create());
		}
	}
	
	protected abstract T create();
	
	public T getSafe() {
		synchronized (pool) {
			return get();
		}
	}

	public T get() {
		if (pool.size() == 0) return create();
		return pool.remove(pool.size()-1);
	}
	
	public void backSafe(T obj) {
		synchronized(pool) {
			back(obj);
		}
	}
	
	public void back(T obj) {
		pool.add(obj);
	}
	
}
