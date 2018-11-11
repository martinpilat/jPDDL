package cz.cuni.mff.perestroika.domain.types;

import cz.cuni.mff.jpddl.PDDLType;

public class T_Object extends PDDLType {
	
	public static int count = 0;
	public static int bitCount = 0;
	public static int bitMask = 0;
	
	public static int lastPos = 0;
	public static int lastCount = 0;
	public static long lastFlag = 0;
	
	private int pos;
	private long flag;
	private int index;
	
	public T_Object(String name) {
		super(name);
		
		++count;
		while (count+1 > Math.pow(bitCount, 2)) {
			bitCount += 1;
			bitMask <<= 1;
			bitMask |= 1;
		}
		
		if (lastCount == 0) {
			pos = 0;
			flag = 1;
			
			lastCount = 1;
			lastFlag = 1;
		} else
		if (lastCount == 64) {
			++lastPos;
			
			pos = lastPos;
			flag = 1;
			
			lastCount = 1;
			lastFlag = 1;
		} else {
			++lastCount;
			lastFlag <<= 1;
			
			pos = lastPos;
			flag = lastFlag;
		}
		
		index = lastPos * 64 + lastCount;
		
		inherits = "T_Object[" + index + "]->" + inherits;		
	}
	
	public static int getPos(T_Object instance) {
		return instance.pos;
	}
	
	public static long getFlag(T_Object instance) {
		return instance.flag;
	}
	
	public static int getIndex(T_Object instance) {
		return instance.index;
	}
	
	public static int getCount() {
		return lastPos * 64 + lastCount;
	}

}
