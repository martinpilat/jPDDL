package cz.cuni.mff.perestroika.problem;

import cz.cuni.mff.jpddl.PDDLEnum;
import cz.cuni.mff.perestroika.domain.types.T_Location;

public final class E_Location extends PDDLEnum<T_Location> {
	
	public static final E_Location THIS = new E_Location();
	
	private E_Location() {	
	}
	
	@Override
	public String getName() {
		return "location";
	}
	
	@Override
	public boolean isFinalType() {
		return true;
	}

	public void register(int index, T_Location e) {
		super.register(index, e);
	}
	
}
