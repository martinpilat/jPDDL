package cz.cuni.mff.perestroika.perestroika_problem_10_3_0_1_0_5_1;

import cz.cuni.mff.perestroika.domain.types.T_Resource;
import cz.cuni.mff.perestroika.problem.E_Resource;

public final class E_Resources {

	public static final T_Resource r1 = new T_Resource("r1");
	public static final T_Resource r2 = new T_Resource("r2");
	public static final T_Resource r3 = new T_Resource("r3");
	public static final T_Resource r4 = new T_Resource("r4");
	

	static {

		E_Resource.THIS.register(T_Resource.getIndex(r1), r1);
		E_Resource.THIS.register(T_Resource.getIndex(r2), r2);
		E_Resource.THIS.register(T_Resource.getIndex(r3), r3);
		E_Resource.THIS.register(T_Resource.getIndex(r4), r4);
		
	}
		
	protected E_Resources() {
	}

}