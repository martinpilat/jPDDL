package cz.cuni.mff.auv.problem1;

import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.problem.E_Auv;

public final class E_Auvs {

	public final T_Auv a = new T_Auv("a");
	
	public static E_Auvs THIS;
	
	protected E_Auvs() {
		THIS = this;
		
		E_Auv.THIS.register(T_Auv.getIndex(a), a);
	}	
	
}
