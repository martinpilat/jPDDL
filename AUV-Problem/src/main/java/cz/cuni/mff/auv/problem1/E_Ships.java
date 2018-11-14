package cz.cuni.mff.auv.problem1;

import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.problem.E_Ship;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Ships {

	public final T_Ship s = new T_Ship("s");
	
	public static E_Ships THIS;
		
	protected E_Ships() {
		THIS = this;
		
		E_Ship.THIS.register(T_Ship.getIndex(s), s);
	}
	
}
