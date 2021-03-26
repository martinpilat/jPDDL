package cz.cuni.mff.perestroika.perestroika_problem_5_3_0_1_0_4_0;

import cz.cuni.mff.perestroika.domain.types.T_Resource;
import cz.cuni.mff.perestroika.problem.E_Resource;

public final class E_Resources {

	public static final T_Resource r1 = new T_Resource("r1");
	

	static {

		E_Resource.THIS.register(T_Resource.getIndex(r1), r1);
		
	}
		
	protected E_Resources() {
	}

}