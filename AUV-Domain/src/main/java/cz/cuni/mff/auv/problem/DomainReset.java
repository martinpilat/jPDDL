package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Object;
import cz.cuni.mff.auv.domain.types.T_Resource;
import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.domain.types.T_Vehicle;

public class DomainReset {

	/**
	 * Resets types and enums so another problem may be initialized.
	 */
	public static void reset() {
		E_Auv.THIS.reset();
		E_Location.THIS.reset();
		E_Resource.THIS.reset();
		E_Ship.THIS.reset();
		E_Vehicle.THIS.reset();
		
		T_Object.reset();
		T_Auv.reset();
		T_Location.reset();
		T_Resource.reset();
		T_Ship.reset();
		T_Vehicle.reset();		
	}
	
}
