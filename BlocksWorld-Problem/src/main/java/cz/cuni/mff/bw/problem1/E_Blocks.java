package cz.cuni.mff.bw.problem1;

import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.problem.E_Block;

public final class E_Blocks {
	
	public static final T_Block b1 = new T_Block("b1");
	public static final T_Block b2 = new T_Block("b2");
	public static final T_Block b3 = new T_Block("b3");
	public static final T_Block b4 = new T_Block("b4");
	public static final T_Block b5 = new T_Block("b5");
	
	static {
		E_Block.THIS.register(T_Block.getIndex(b1), b1);
		E_Block.THIS.register(T_Block.getIndex(b2), b2);
		E_Block.THIS.register(T_Block.getIndex(b3), b3);
		E_Block.THIS.register(T_Block.getIndex(b4), b4);
		E_Block.THIS.register(T_Block.getIndex(b5), b5);
	}
		
	protected E_Blocks() {
	}

}
