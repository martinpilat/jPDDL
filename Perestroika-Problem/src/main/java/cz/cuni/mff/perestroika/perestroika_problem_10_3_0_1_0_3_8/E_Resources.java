package cz.cuni.mff.perestroika.perestroika_problem_10_3_0_1_0_3_8;

import cz.cuni.mff.perestroika.domain.types.T_Resource;
import cz.cuni.mff.perestroika.problem.E_Resource;

public final class E_Resources {

	public static final T_Resource r1 = new T_Resource("r1");
	public static final T_Resource r2 = new T_Resource("r2");
	public static final T_Resource r3 = new T_Resource("r3");
	public static final T_Resource r4 = new T_Resource("r4");
	public static final T_Resource r5 = new T_Resource("r5");
	public static final T_Resource r6 = new T_Resource("r6");
	public static final T_Resource r7 = new T_Resource("r7");
	

	static {

		E_Resource.THIS.register(T_Resource.getIndex(r1), r1);
		E_Resource.THIS.register(T_Resource.getIndex(r2), r2);
		E_Resource.THIS.register(T_Resource.getIndex(r3), r3);
		E_Resource.THIS.register(T_Resource.getIndex(r4), r4);
		E_Resource.THIS.register(T_Resource.getIndex(r5), r5);
		E_Resource.THIS.register(T_Resource.getIndex(r6), r6);
		E_Resource.THIS.register(T_Resource.getIndex(r7), r7);
		
	}
		
	protected E_Resources() {
	}

}