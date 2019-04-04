package cz.cuni.mff.perestroika.problem8;

import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.problem.E_Location;

public final class E_Locations {

	
	public static final T_Location l_1_1 = new T_Location("l-1-1");
	public static final T_Location l_1_2 = new T_Location("l-1-2");
	public static final T_Location l_1_3 = new T_Location("l-1-3");
	public static final T_Location l_2_1 = new T_Location("l-2-1");
	public static final T_Location l_2_2 = new T_Location("l-2-2");
	public static final T_Location l_2_3 = new T_Location("l-2-3");
	public static final T_Location l_3_1 = new T_Location("l-3-1");
	public static final T_Location l_3_2 = new T_Location("l-3-2");
	public static final T_Location l_3_3 = new T_Location("l-3-3");
	
	static {
	    
		E_Location.THIS.register(T_Location.getIndex(l_1_1), l_1_1);
		E_Location.THIS.register(T_Location.getIndex(l_1_2), l_1_2);
		E_Location.THIS.register(T_Location.getIndex(l_1_3), l_1_3);
		E_Location.THIS.register(T_Location.getIndex(l_2_1), l_2_1);
		E_Location.THIS.register(T_Location.getIndex(l_2_2), l_2_2);
		E_Location.THIS.register(T_Location.getIndex(l_2_3), l_2_3);
		E_Location.THIS.register(T_Location.getIndex(l_3_1), l_3_1);
		E_Location.THIS.register(T_Location.getIndex(l_3_2), l_3_2);
		E_Location.THIS.register(T_Location.getIndex(l_3_3), l_3_3);
	}
		
	protected E_Locations() {
	}

}