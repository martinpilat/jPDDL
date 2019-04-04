package cz.cuni.mff.bw.problem;

import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.bw.domain.types.T_Object;
import cz.cuni.mff.bw.domain.types.T_Slippery;
import cz.cuni.mff.bw.domain.types.T_Sticky;

public class DomainReset {

	/**
	 * Resets types and enums so another problem may be initialized.
	 */
	public static void reset() {
		E_Slippery.THIS.reset();
		E_Block.THIS.reset();
		E_Sticky.THIS.reset();
		E_Hand.THIS.reset();
		
		T_Object.reset();
		T_Slippery.reset();
		T_Block.reset();
		T_Sticky.reset();
		T_Hand.reset();		
	}
	
}
