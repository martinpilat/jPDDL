package cz.cuni.mff.perestroika.problem1;

import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.problem.E_Location;

public final class E_Locations {
	
	public static final T_Location l11 = new T_Location("l-1-1");
	public static final T_Location l12 = new T_Location("l-1-2");
	public static final T_Location l13 = new T_Location("l-1-3");
	public static final T_Location l14 = new T_Location("l-1-4");
	public static final T_Location l15 = new T_Location("l-1-5");
	public static final T_Location l21 = new T_Location("l-2-1");
	public static final T_Location l22 = new T_Location("l-2-2");
	public static final T_Location l23 = new T_Location("l-2-3");
	public static final T_Location l24 = new T_Location("l-2-4");
	public static final T_Location l25 = new T_Location("l-2-5");
	public static final T_Location l31 = new T_Location("l-3-1");
	public static final T_Location l32 = new T_Location("l-3-2");
	public static final T_Location l33 = new T_Location("l-3-3");
	public static final T_Location l34 = new T_Location("l-3-4");
	public static final T_Location l35 = new T_Location("l-3-5");
	public static final T_Location l41 = new T_Location("l-4-1");
	public static final T_Location l42 = new T_Location("l-4-2");
	public static final T_Location l43 = new T_Location("l-4-3");
	public static final T_Location l44 = new T_Location("l-4-4");
	public static final T_Location l45 = new T_Location("l-4-5");
	public static final T_Location l51 = new T_Location("l-5-1");
	public static final T_Location l52 = new T_Location("l-5-2");
	public static final T_Location l53 = new T_Location("l-5-3");
	public static final T_Location l54 = new T_Location("l-5-4");
	public static final T_Location l55 = new T_Location("l-5-5");
	
	static {
		E_Location.THIS.register(T_Location.getIndex(l11), l11);
		E_Location.THIS.register(T_Location.getIndex(l12), l12);
		E_Location.THIS.register(T_Location.getIndex(l13), l13);
		E_Location.THIS.register(T_Location.getIndex(l14), l14);
		E_Location.THIS.register(T_Location.getIndex(l15), l15);
		E_Location.THIS.register(T_Location.getIndex(l21), l21);
		E_Location.THIS.register(T_Location.getIndex(l22), l22);
		E_Location.THIS.register(T_Location.getIndex(l23), l23);
		E_Location.THIS.register(T_Location.getIndex(l24), l24);
		E_Location.THIS.register(T_Location.getIndex(l25), l25);
		E_Location.THIS.register(T_Location.getIndex(l31), l31);
		E_Location.THIS.register(T_Location.getIndex(l32), l32);
		E_Location.THIS.register(T_Location.getIndex(l33), l33);
		E_Location.THIS.register(T_Location.getIndex(l34), l34);
		E_Location.THIS.register(T_Location.getIndex(l35), l35);
		E_Location.THIS.register(T_Location.getIndex(l41), l41);
		E_Location.THIS.register(T_Location.getIndex(l42), l42);
		E_Location.THIS.register(T_Location.getIndex(l43), l43);
		E_Location.THIS.register(T_Location.getIndex(l44), l44);
		E_Location.THIS.register(T_Location.getIndex(l45), l45);
		E_Location.THIS.register(T_Location.getIndex(l51), l51);
		E_Location.THIS.register(T_Location.getIndex(l52), l52);
		E_Location.THIS.register(T_Location.getIndex(l53), l53);
		E_Location.THIS.register(T_Location.getIndex(l54), l54);
		E_Location.THIS.register(T_Location.getIndex(l55), l55);
	}
		
	protected E_Locations() {
	}

}
