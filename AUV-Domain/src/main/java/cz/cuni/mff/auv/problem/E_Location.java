package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.jpddl.PDDLEnum;

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
