package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Ship extends PDDLEnum<T_Ship> {

	public static final E_Ship THIS = new E_Ship();
	
	private E_Ship() {
	}
	
	@Override
	public String getName() {
		return "ship";
	}
	
	@Override
	public boolean isFinalType() {
		return true;
	}
	
	protected void register(int index, T_Ship e) {
		super.register(index, e);
	}
	
}
