package cz.cuni.mff.auv.problem1;

import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.auv.problem.E_Vehicle;

public final class E_Vehicles {

	public E_Vehicles THIS = this;
	
	protected E_Vehicles() {
		THIS = this;
		
		E_Vehicle.THIS.register(T_Vehicle.getIndex(E_Auvs.THIS.a), E_Auvs.THIS.a);
		E_Vehicle.THIS.register(T_Vehicle.getIndex(E_Ships.THIS.s), E_Ships.THIS.s);

	}
	
}
