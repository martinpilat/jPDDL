package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Resource;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Resource extends PDDLEnum<T_Resource> {
	
	public static final E_Resource THIS = new E_Resource();
	
	private E_Resource() {
	}
	
	@Override
	public String getName() {
		return "resource";
	}
	
	@Override
	public boolean isFinalType() {
		return true;
	}
	
	protected void register(int index, T_Resource e) {
		super.register(index, e);
	}
	
}
