package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Vehicle extends PDDLEnum<T_Vehicle> {

	public static final E_Vehicle THIS = new E_Vehicle();
	
	private E_Vehicle() {
	}
	
	protected void register(int index, T_Vehicle e) {
		super.register(index, e);
	}
	
}
