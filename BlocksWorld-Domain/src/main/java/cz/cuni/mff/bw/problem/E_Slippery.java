package cz.cuni.mff.bw.problem;

import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.bw.domain.types.T_Slippery;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Slippery extends PDDLEnum<T_Slippery> {
	
	public static final E_Slippery THIS = new E_Slippery();
	
	private E_Slippery() {		
	}
	
	@Override
	public String getName() {
		return "slippery";
	}
	
	@Override
	public boolean isFinalType() {
		return true;
	}
	
	public void register(int index, T_Slippery e) {
		super.register(index, e);
		E_Hand.THIS.register(T_Hand.getIndex(e), e);
	}
	
}
