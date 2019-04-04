package cz.cuni.mff.bw.problem1;

import cz.cuni.mff.bw.domain.types.T_Sticky;
import cz.cuni.mff.bw.problem.E_Sticky;

public final class E_Stickys {
	
	public static final T_Sticky h2 = new T_Sticky("h2");
	
	static {
		E_Sticky.THIS.register(T_Sticky.getIndex(h2), h2);
	}
		
	protected E_Stickys() {
	}

}
