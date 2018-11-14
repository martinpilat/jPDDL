package cz.cuni.mff.auv.problem2;

import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.problem.E_Ship;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Ships {

	
	public static final T_Ship s = new T_Ship("s");
	public static final T_Ship s2 = new T_Ship("s2");

	static {
	    
		E_Ship.THIS.register(T_Ship.getIndex(s), s);
		E_Ship.THIS.register(T_Ship.getIndex(s2), s2);
	}
		
	protected E_Ships() {
	}
	
}