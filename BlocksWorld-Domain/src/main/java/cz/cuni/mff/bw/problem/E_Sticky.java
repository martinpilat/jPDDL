package cz.cuni.mff.bw.problem;

import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.bw.domain.types.T_Sticky;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Sticky extends PDDLEnum<T_Sticky> {

	public static final E_Sticky THIS = new E_Sticky();
	
	private E_Sticky() {
	}
	
	@Override
	public String getName() {
		return "sticky";
	}
	
	@Override
	public boolean isFinalType() {
		return true;
	}
	
	public void register(int index, T_Sticky e) {
		super.register(index, e);
		E_Hand.THIS.register(T_Hand.getIndex(e), e);
	}
	
}
