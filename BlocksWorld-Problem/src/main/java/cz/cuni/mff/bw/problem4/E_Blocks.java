package cz.cuni.mff.bw.problem4;

import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.problem.E_Block;

public final class E_Blocks {
	
	public static final T_Block b1 = new T_Block("b1");
	public static final T_Block b2 = new T_Block("b2");
	public static final T_Block b3 = new T_Block("b3");
	public static final T_Block b4 = new T_Block("b4");
	public static final T_Block b5 = new T_Block("b5");
	public static final T_Block b6 = new T_Block("b6");
	public static final T_Block b7 = new T_Block("b7");
	public static final T_Block b8 = new T_Block("b8");
	public static final T_Block b9 = new T_Block("b9");
	public static final T_Block b10 = new T_Block("b10");
	
	static {
		E_Block.THIS.register(T_Block.getIndex(b1), b1);
		E_Block.THIS.register(T_Block.getIndex(b2), b2);
		E_Block.THIS.register(T_Block.getIndex(b3), b3);
		E_Block.THIS.register(T_Block.getIndex(b4), b4);
		E_Block.THIS.register(T_Block.getIndex(b5), b5);
		E_Block.THIS.register(T_Block.getIndex(b6), b6);
		E_Block.THIS.register(T_Block.getIndex(b7), b7);
		E_Block.THIS.register(T_Block.getIndex(b8), b8);
		E_Block.THIS.register(T_Block.getIndex(b9), b9);
		E_Block.THIS.register(T_Block.getIndex(b10), b10);
	}
		
	protected E_Blocks() {
	}

}
