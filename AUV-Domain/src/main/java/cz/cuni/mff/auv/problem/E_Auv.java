package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Auv extends PDDLEnum<T_Auv> {
	
	public static final E_Auv THIS = new E_Auv();
	
	private E_Auv() {		
	}
	
	@Override
	public String getName() {
		return "auv";
	}
	
	@Override
	public boolean isFinalType() {
		return true;
	}
	
	public void register(int index, T_Auv e) {
		super.register(index, e);
	}
	
}
