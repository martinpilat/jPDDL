package cz.cuni.mff.bw.problem4;

import cz.cuni.mff.bw.domain.types.T_Sticky;
import cz.cuni.mff.bw.problem.E_Sticky;

public final class E_Stickys {
	
	public static final T_Sticky h3 = new T_Sticky("h3");
	
	static {
		E_Sticky.THIS.register(T_Sticky.getIndex(h3), h3);
	}
		
	protected E_Stickys() {
	}

}
