package cz.cuni.mff.auv.problem7;

import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Locations {

	
	public static final T_Location l_1_1 = new T_Location("l-1-1");
	public static final T_Location l_1_2 = new T_Location("l-1-2");
	public static final T_Location l_1_3 = new T_Location("l-1-3");
	public static final T_Location l_1_4 = new T_Location("l-1-4");
	public static final T_Location l_2_1 = new T_Location("l-2-1");
	public static final T_Location l_2_2 = new T_Location("l-2-2");
	public static final T_Location l_2_3 = new T_Location("l-2-3");
	public static final T_Location l_2_4 = new T_Location("l-2-4");
	public static final T_Location l_3_1 = new T_Location("l-3-1");
	public static final T_Location l_3_2 = new T_Location("l-3-2");
	public static final T_Location l_3_3 = new T_Location("l-3-3");
	public static final T_Location l_3_4 = new T_Location("l-3-4");
	public static final T_Location l_4_1 = new T_Location("l-4-1");
	public static final T_Location l_4_2 = new T_Location("l-4-2");
	public static final T_Location l_4_3 = new T_Location("l-4-3");
	public static final T_Location l_4_4 = new T_Location("l-4-4");

	static {

		
		E_Location.THIS.register(T_Location.getIndex(l_1_1), l_1_1);
		E_Location.THIS.register(T_Location.getIndex(l_1_2), l_1_2);
		E_Location.THIS.register(T_Location.getIndex(l_1_3), l_1_3);
		E_Location.THIS.register(T_Location.getIndex(l_1_4), l_1_4);

		E_Location.THIS.register(T_Location.getIndex(l_2_1), l_2_1);
		E_Location.THIS.register(T_Location.getIndex(l_2_2), l_2_2);
		E_Location.THIS.register(T_Location.getIndex(l_2_3), l_2_3);
		E_Location.THIS.register(T_Location.getIndex(l_2_4), l_2_4);

		E_Location.THIS.register(T_Location.getIndex(l_3_1), l_3_1);
		E_Location.THIS.register(T_Location.getIndex(l_3_2), l_3_2);
		E_Location.THIS.register(T_Location.getIndex(l_3_3), l_3_3);
		E_Location.THIS.register(T_Location.getIndex(l_3_4), l_3_4);

		E_Location.THIS.register(T_Location.getIndex(l_4_1), l_4_1);
		E_Location.THIS.register(T_Location.getIndex(l_4_2), l_4_2);
		E_Location.THIS.register(T_Location.getIndex(l_4_3), l_4_3);
		E_Location.THIS.register(T_Location.getIndex(l_4_4), l_4_4);
	}
		
	protected E_Locations() {
	}

}