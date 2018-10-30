package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Vehicles {

	static {
		E_Vehicle.THIS.register(T_Vehicle.getIndex(E_Auvs.a), E_Auvs.a);
		E_Vehicle.THIS.register(T_Vehicle.getIndex(E_Ships.s), E_Ships.s);
	}
		
	protected E_Vehicles() {		
	}
	
}
