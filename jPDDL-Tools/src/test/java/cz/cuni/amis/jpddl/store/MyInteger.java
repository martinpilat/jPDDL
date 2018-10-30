package cz.cuni.amis.jpddl.store;

import cz.cuni.mff.jpddl.store.ICloneable;

public class MyInteger implements ICloneable {

	public int value;
	
	public MyInteger() {
		
	}
	
	public MyInteger(int value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof MyInteger)) return false;
		return value == ((MyInteger)obj).value;
	}

	public MyInteger clone() {
		MyInteger result = new MyInteger();
		result.value = value;
		return result;
	}	
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
}
