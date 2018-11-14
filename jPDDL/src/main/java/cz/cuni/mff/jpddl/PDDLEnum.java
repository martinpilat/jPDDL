package cz.cuni.mff.jpddl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gnu.trove.map.hash.THashMap;

public abstract class PDDLEnum<T extends PDDLType> {

	/**
	 * The first element is ALWAYS null!
	 */
	private final List<T> elements = new ArrayList<T>();
	
	private T[] elementsArray;

	private Map<String, T> elementsMap = new THashMap<String, T>();
	
	public abstract String getName();
	
	public abstract boolean isFinalType();
	
	private int bitCount = 0;
	
	public void register(int index, T e) {
		while (elements.size() <= index) elements.add(null);
		elements.set(index, e);
		elementsMap.put(e.name, e);
		while (elements.size() > Math.pow(bitCount, 2)) ++bitCount;
	}
	
	public int getBitCount() {
		return bitCount;
	}

	public T[] elements() {
		if (elementsArray == null) {
			elementsArray = (T[]) new PDDLType[elements.size()];
			for (int i = 0; i < elements.size(); ++i) {
				elementsArray[i] = elements.get(i);
			}
		}
		return elementsArray;
	}
	
	public T getElement(int index) {
		return elements()[index];
	}
	
	public T getElement(String name) {
		return elementsMap.get(name);
	}
	
	public int getSize() {
		return elements.size();
	}
	
	@Override
	public String toString() {
		return (getClass() == null ? "PDDLEnum[" : getClass().getSimpleName() + "[") + "size=" + getSize() + "]";
	}
	
}