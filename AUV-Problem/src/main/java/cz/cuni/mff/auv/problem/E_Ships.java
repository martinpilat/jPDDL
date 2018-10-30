package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Ships extends PDDLEnum<T_Ship> {

	public static final T_Ship s = new T_Ship("s");
	
	static {
		E_Ship.THIS.register(T_Ship.getIndex(s), s);
	}
		
	protected E_Ships() {
	}
	
}
