package cz.cuni.mff.jpddl;

import java.io.File;
import java.util.Collection;

public abstract class PDDLDomain {

	public abstract String getName();
	
	/**
	 * Returns file with PDDL domain including events.
	 * @return
	 */
	public abstract File getDomainFile();
	
	/**
	 * Returns file with PDDL domain without events.
	 * @return
	 */
	public abstract File getDomainPureFile();
	
	public abstract PDDLEffector[] getActions();
	public abstract PDDLEffector[] getEvents();
	public abstract PDDLEffector[] getEffectors();
	public abstract PDDLEnum[] getEnums();
	
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
