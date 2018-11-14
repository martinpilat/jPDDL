package cz.cuni.mff.auv.problem1;

import cz.cuni.mff.auv.domain.types.T_Resource;
import cz.cuni.mff.auv.problem.E_Resource;

public final class E_Resources {
	
	public final T_Resource r1 = new T_Resource("r1");
	public final T_Resource r2 = new T_Resource("r2");
	public final T_Resource r3 = new T_Resource("r3");
	
	public static E_Resources THIS;
	
	protected E_Resources() {
		THIS = this;
		
		E_Resource.THIS.register(T_Resource.getIndex(r1), r1);
		E_Resource.THIS.register(T_Resource.getIndex(r2), r2);
		E_Resource.THIS.register(T_Resource.getIndex(r3), r3);
	}
	
}
