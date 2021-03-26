package cz.cuni.mff.perestroika.perestroika_problem_10_3_0_2_0_3_9;

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
	public static final T_Resource r8 = new T_Resource("r8");
	public static final T_Resource r9 = new T_Resource("r9");
	public static final T_Resource r10 = new T_Resource("r10");
	public static final T_Resource r11 = new T_Resource("r11");
	public static final T_Resource r12 = new T_Resource("r12");
	public static final T_Resource r13 = new T_Resource("r13");
	public static final T_Resource r14 = new T_Resource("r14");
	

	static {

		E_Resource.THIS.register(T_Resource.getIndex(r1), r1);
		E_Resource.THIS.register(T_Resource.getIndex(r2), r2);
		E_Resource.THIS.register(T_Resource.getIndex(r3), r3);
		E_Resource.THIS.register(T_Resource.getIndex(r4), r4);
		E_Resource.THIS.register(T_Resource.getIndex(r5), r5);
		E_Resource.THIS.register(T_Resource.getIndex(r6), r6);
		E_Resource.THIS.register(T_Resource.getIndex(r7), r7);
		E_Resource.THIS.register(T_Resource.getIndex(r8), r8);
		E_Resource.THIS.register(T_Resource.getIndex(r9), r9);
		E_Resource.THIS.register(T_Resource.getIndex(r10), r10);
		E_Resource.THIS.register(T_Resource.getIndex(r11), r11);
		E_Resource.THIS.register(T_Resource.getIndex(r12), r12);
		E_Resource.THIS.register(T_Resource.getIndex(r13), r13);
		E_Resource.THIS.register(T_Resource.getIndex(r14), r14);
		
	}
		
	protected E_Resources() {
	}

}