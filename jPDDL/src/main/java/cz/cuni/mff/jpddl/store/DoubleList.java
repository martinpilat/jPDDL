package cz.cuni.mff.jpddl.store;

import java.lang.reflect.Method;

/**
 * Double-linked-list.
 * 
 * ADD - O(1)
 * REMOVE-index - O(1)
 * REMOVE-data - O(size)
 * CONTAINS - O(size)
 * ITERATE via forEach - O(size)
 * 
 * Note that CLONING is **EXTREMELY** SLOW! And should be used for debugging only.
 * 
 * @author Jimmy
 */
public final class DoubleList<DATA> implements Cloneable
{
	public static interface ForEach<DATA> {
		
		/**
		 * @param index list index
		 * @param data data stored in there
		 * @return whether to continue with the for-loop
		 */
		public boolean element(int index, DATA data);
		
	}
	
	private static class Element<DATA> implements Cloneable {
		
		public DATA data;
		public int next;
		public int prev;
		
		@Override
		public Element<DATA> clone() {
			Element<DATA> result = new Element<DATA>();
			result.data = forceClone(data);
			result.next = next;
			result.prev = prev;
			return result;
		}
		
		public DATA forceClone(DATA data) {
			if (data == null) return null;
			if (data instanceof Boolean) return data;
			try {
				Method method = data.getClass().getMethod("clone");
				return (DATA) method.invoke(data);
			} catch (Exception e) {
				throw new RuntimeException("Failed to clone element of class " + data.getClass() + ": " + data);
			}
		}
		
		@Override
		public String toString() {
			return "Element[prev=" + prev + ",next=" + next + ",data=" + data +"]";
		}
		
	}
	
    /**
     * Current capacity of the {@link #list}.
     */
    private int capacity;
    
    /**
     * How many elements we have stored in the list, i.e., the length of 'alive' list.
     */
    private int alives;
    
    /**
     * How many free space we have in the list, i.e., the length of 'dead' list.
     */
    private int deads;
    
    private int firstAlive;
    private int lastAlive;
    private int firstDead;
    private int lastDead;
    
    private Element<DATA>[] list;
    
    protected DoubleList() {    	
    }
    
    public DoubleList(int initialCapacity)
    {
    	capacity = initialCapacity;
    	list = new Element[initialCapacity];
    	for (int i = 0; i < initialCapacity; ++i) {
    		list[i] = new Element<DATA>();
    	}
    	
    	firstAlive = -1;
        lastAlive  = -1;
        initDead(0, capacity - 1);

        alives = 0;
        deads = capacity;
    }
    
    @Override
    public DoubleList<DATA> clone() {
    	DoubleList<DATA> result = new DoubleList<DATA>();
    	result.alives = alives;
    	result.capacity = capacity;
    	result.deads = deads;
    	result.firstAlive = firstAlive;
    	result.firstDead = firstDead;
    	result.lastAlive = lastAlive;
    	result.lastDead = lastDead;
    	result.list = new Element[list.length];
    	for (int i = 0; i < list.length; ++i) {
    		result.list[i] = list[i].clone();
    	}	
    	return result;
    }

    private void initDead(int firstIndex, int lastIndex)
    {
        firstDead = firstIndex;
        lastDead  = lastIndex;

        list[firstIndex].next = firstIndex+1;
        list[firstIndex].prev = -1;
        
        for (int i = firstIndex+1; i < lastIndex; ++i) {
            list[i].prev = i-1;
            list[i].next = i+1;
        }
        
        list[lastIndex].next = -1;
        list[lastIndex].prev = lastIndex-1;
        
        deads = lastIndex - firstIndex + 1;
    }
    
    /**
     * Returns INDEX of added DATA.
     * 
     * INDEXING is not sequential!
     * 
     * O(1) if enough capacity.
     * 
     * @param data
     * @return
     */
    public int add(DATA data) {
    	// DO WE HAVE SPACE?
        if (firstDead == -1) {
        	// NO => add some...
            grow();
        }
        
        // WE'RE ADDING NEW DATA
        --deads;
        ++alives;

        if (firstAlive == -1) {
        	// NO ALIVES YET!
        	
            // => make the first dead as alive
            firstAlive = lastAlive = firstDead;

            // REMOVE THE FIRST DEAD
            removeFirstDead();

            // SET ALIVE AS ALIVE, no prev/next
            list[firstAlive].data = data;
            list[firstAlive].prev = -1;
            list[firstAlive].next = -1;

            return firstAlive;
        }

        // WE HAVE DEADS & ALIVES
        // => extends alives to the first dead
        int newAlive = firstDead;

        // REMOVE THE FIRST DEAD
        removeFirstDead();

        // SET DATA
        list[newAlive].data = data;
        
        // SET ALIVE AS ALIVE with PREV and no NEXT        
        list[newAlive].prev = lastAlive;
        list[newAlive].next = -1;        
        list[lastAlive].next = newAlive;
        
        lastAlive = newAlive;

        return newAlive;
    }
    
    /**
     * Returns DATA associated with 'index'.
     * @param index
     * @return
     */
    public DATA get(int index) {
		return list[index].data;
	}
    
    /**
     * Sets 'data' onto element at 'index' assuming the 'index' is alive (will not break the data structure if it isn't).
     * 
     * @param index
     * @param data
     */
    public void set(int index, DATA data) {
    	list[index].data = data;
    }

    protected void removeFirstDead()
    {
        if (firstDead == lastDead)
        {
            // NO MORE DEADS
            firstDead = lastDead = -1;
        }
        else
        {
            // JUMP ONTO NEXT DEAD
            firstDead = list[firstDead].next;
            list[firstDead].prev = -1;
        }
    }

    protected void grow()
    {
    	int newCapacity = Math.min(list.length * 2, 100);
    	
    	Element<DATA>[] newList = new Element[newCapacity];
    	
    	for (int i = 0; i < list.length; ++i) {
    		newList[i] = list[i];
    	}
    	for (int i = list.length; i < newList.length; ++i) {
    		newList[i] = new Element<DATA>();
    	}
    	
    	list = newList;
    	
    	initDead(capacity, newCapacity-1);
    	
    	capacity = newCapacity;
    }

    /**
     * Removes data at INDEX.
     * 
     * NO SAFETY - if you try to remove index, which is DEAD, you will break-up the indexing and the data structure.
     * 
     * O(1)
     * 
     * @param index
     * @return
     */
    public DATA remove(int index)
    {
        ++deads;
        --alives;
        
        // DISCONNECT index FROM ALIVES
        if (firstAlive == lastAlive)
        {
            // last alive to be back-ed
            firstAlive = lastAlive = -1;
        } else
        {
            int prevIndex = list[index].prev;
            int nextIndex = list[index].next;

            if (prevIndex >= 0) {
            	list[prevIndex].next = nextIndex;
            } else
            {
                firstAlive = nextIndex;
            }
            if (nextIndex >= 0)
            {
            	list[nextIndex].prev = prevIndex;
            } else
            {
                lastAlive = prevIndex;
            }
        }

        // ADD index INTO THE DEAD
        if (firstDead < 0) // NO DEADS WHAT SO EVER
        {
            firstDead = lastDead = index;
            list[firstDead].prev = -1;
            list[firstDead].next = -1;
        } else
        { // WE HAVE SOME DEADS, add as the last one
        	list[lastDead].next = index;
        	list[index].prev = lastDead;
        	list[index].next = -1;
        	lastDead = index;
        }
        
        DATA removed = list[index].data;
        list[index].data = null;
        
        return removed;
    }
    
    /**
     * Whether 'data' are stored within alive chain (using operator ==).
     * 
     * O(size)
     * 
     * @param data
     * @return
     */
    public boolean contains(DATA data) {
    	if (firstAlive < 0) return false;
    	int index = firstAlive;
    	while (index >= 0) {
    		if (list[index].data == data) return true;
    		index = list[index].next;
    	}
    	return false;
    }
    
    /**
     * Whether 'data' are stored within alive chain (using .equals(), null-safe).
     * 
     * O(size)
     * 
     * @param data
     * @return
     */
    public boolean containsEqual(DATA data) {
    	if (firstAlive < 0) return false;
    	int index = firstAlive;
    	while (index >= 0) {
    		if (data == null) {
    			if (list[index].data == null) return true;
    		} else {
    			if (list[index].data != null && data.equals(list[index].data)) return true;
    		}
    		index = list[index].next;
    	}
    	return false;
    }
    
    /**
     * Returns index of 'data' or -1 if not within the list (using operator ==).
     * 
     * O(size)
     * 
     * @param data
     * @return
     */
    public int find(DATA data) {
    	if (firstAlive < 0) return -1;
    	int index = firstAlive;
    	while (index >= 0) {
    		if (list[index].data == data) return index;
    		index = list[index].next;
    	}
    	return -1;
    }
    
    /**
     * Returns index of 'data' or -1 if not within the list (using .equals(), null-safe).
     * 
     * O(size)
     * 
     * @param data
     * @return
     */
    public int findEqual(DATA data) {
    	if (firstAlive < 0) return -1;
    	int index = firstAlive;
    	while (index >= 0) {
    		if (data == null) {
    			if (list[index].data == null) return index;
    		} else {
    			if (list[index].data != null && data.equals(list[index].data)) return index;
    		}
    		index = list[index].next;
    	}
    	return -1;
    }
    
    /**
     * Removes data from the list (using operator ==).
     * 
     * O(size)
     * 
     * @param data
     */
    public void remove(DATA data) {
    	int index = find(data);
    	if (index < 0) return;
    	remove(index);
    }
    
    /**
     * Removes data from the list (using .equals(), null-safe).
     * 
     * O(size)
     * 
     * @param data
     */
    public void removeEqual(DATA data) {
    	int index = findEqual(data);
    	if (index < 0) return;
    	remove(index);
    }
    
    /**
     * For-loop over stored elements.
     * 
     * O(size)
     * 
     * @param proc
     */
    public void forEach(ForEach<DATA> proc) {
    	int index = firstAlive;
    	while (index >= 0) {
    		if (proc.element(index, list[index].data)) {
    			index = list[index].next;
    		} else {
    			break;
    		}
    	}
    }
    
    /**
     * How many elements we are storing in here.
     * @return
     */
    public int size() {
    	return alives;
    }
    
    /**
     * Maximum number of elements we can store without {@link #grow()}ing.
     * @return
     */
    public int capacity() {
    	return capacity;
    }
    
    /**
     * How many more elements we can add before we will have to {@link #grow()}.
     * @return
     */
    public int capacityLeft() {
    	return deads;
    }

    // =====
    // DEBUG
    // =====

    public String getAliveChain()
    {
        return getChainFrom(firstAlive);
    }

    public String getDeadChain()
    {
        return getChainFrom(firstDead);
    }

    public String getChainFrom(int index)
    {
        if (index < 0) return "NONE";
        StringBuffer result = new StringBuffer();

        while (index >= 0)
        {
            if (result.length() != 0) result.append("->");
            result.append(index);
            index = list[index].next;
        }

        return result.toString();
    }
	
}