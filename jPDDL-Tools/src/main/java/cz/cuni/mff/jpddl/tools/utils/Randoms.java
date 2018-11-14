package cz.cuni.mff.jpddl.tools.utils;

import java.util.Random;

public class Randoms {
	
	private static Random random = new Random(1);
	
	public static void setMasterSeed(long masterSeed) {
		random = new Random(masterSeed);
	}
	
	public static Random getNextRandom() {
		return new Random(random.nextLong());
	}
	
	public static long getNextSeed() {
		return random.nextLong();
	}

}
