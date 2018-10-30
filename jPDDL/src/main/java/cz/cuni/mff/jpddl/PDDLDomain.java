package cz.cuni.mff.jpddl;

import java.util.Collection;

public abstract class PDDLDomain {

	public abstract PDDLEffector[] getActions();
	public abstract PDDLEffector[] getEvents();
	public abstract PDDLEffector[] getEffectors();
	
	public <T extends PDDLEffector> void dump(T[] effectors) {
		boolean first = true;
		for (T effector : effectors) {
			if (first) first = false;
			else System.out.print(", ");
			System.out.print(effector.toEffector());
		}
		System.out.println();
	}
	public <T extends PDDLEffector> void dump(Collection<T> effectors) {
		boolean first = true;
		for (T effector : effectors) {
			if (first) first = false;
			else System.out.print(", ");
			System.out.print(effector.toEffector());
		}
		System.out.println();		
	}
	
}
