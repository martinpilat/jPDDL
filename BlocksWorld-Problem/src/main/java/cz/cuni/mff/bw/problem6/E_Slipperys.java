package cz.cuni.mff.bw.problem6;

import cz.cuni.mff.bw.domain.types.T_Slippery;
import cz.cuni.mff.bw.problem.E_Slippery;

public final class E_Slipperys {
	
	public static final T_Slippery h1 = new T_Slippery("h1");
	public static final T_Slippery h2 = new T_Slippery("h2");
	
	static {
		E_Slippery.THIS.register(T_Slippery.getIndex(h1), h1);
		E_Slippery.THIS.register(T_Slippery.getIndex(h2), h2);
	}
		
	protected E_Slipperys() {
	}

}
