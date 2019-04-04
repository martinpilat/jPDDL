package cz.cuni.mff.bw.problem;

import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Hand extends PDDLEnum<T_Hand> {

	public static final E_Hand THIS = new E_Hand();
	
	private E_Hand() {
	}
	
	@Override
	public String getName() {
		return "hand";
	}
	
	@Override
	public boolean isFinalType() {
		return false;
	}
	
	public void register(int index, T_Hand e) {
		super.register(index, e);
	}
	
}
