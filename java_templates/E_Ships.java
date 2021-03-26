package cz.cuni.mff.auv.problem1;

import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.problem.E_Ship;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Ships {

	public static final T_Ship s = new T_Ship("s");
	
	static {
		E_Ship.THIS.register(T_Ship.getIndex(s), s);
	}
		
	protected E_Ships() {
	}
	
}
