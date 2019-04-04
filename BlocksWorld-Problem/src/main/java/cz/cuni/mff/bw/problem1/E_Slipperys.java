package cz.cuni.mff.bw.problem1;

import cz.cuni.mff.bw.domain.types.T_Slippery;
import cz.cuni.mff.bw.problem.E_Slippery;

public final class E_Slipperys {
	
	public static final T_Slippery h1 = new T_Slippery("h1");
	
	static {
		E_Slippery.THIS.register(T_Slippery.getIndex(h1), h1);
	}
		
	protected E_Slipperys() {
	}

}
