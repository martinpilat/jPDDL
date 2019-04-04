package cz.cuni.mff.bw.problem;

import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.jpddl.PDDLEnum;

public final class E_Block extends PDDLEnum<T_Block> {
	
	public static final E_Block THIS = new E_Block();
	
	private E_Block() {	
	}
	
	@Override
	public String getName() {
		return "block";
	}
	
	@Override
	public boolean isFinalType() {
		return true;
	}

	public void register(int index, T_Block e) {
		super.register(index, e);
	}
	
}
