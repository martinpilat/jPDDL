package cz.cuni.mff.auv.problem3;

import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.auv.problem.E_Vehicle;

public final class E_Vehicles {

	static {
	    
		E_Vehicle.THIS.register(T_Vehicle.getIndex(E_Auvs.a), E_Auvs.a);

		
		E_Vehicle.THIS.register(T_Vehicle.getIndex(E_Ships.s), E_Ships.s);
		E_Vehicle.THIS.register(T_Vehicle.getIndex(E_Ships.s2), E_Ships.s2);
		E_Vehicle.THIS.register(T_Vehicle.getIndex(E_Ships.s3), E_Ships.s3);
	}
		
	protected E_Vehicles() {		
	}
	
}