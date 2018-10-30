package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Auv;

public final class E_Auvs {

	public static final T_Auv a = new T_Auv("a");
	
	static {
		E_Auv.THIS.register(T_Auv.getIndex(a), a);	
	}
	
	protected E_Auvs() {		
	}	
	
}
