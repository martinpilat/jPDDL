package cz.cuni.mff.perestroika.problem;

import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.domain.types.T_Object;
import cz.cuni.mff.perestroika.domain.types.T_Resource;

public class DomainReset {

	/**
	 * Resets types and enums so another problem may be initialized.
	 */
	public static void reset() {
		E_Location.THIS.reset();
		E_Resource.THIS.reset();
		
		T_Object.reset();
		T_Location.reset();
		T_Resource.reset();
		
	}
	
}
