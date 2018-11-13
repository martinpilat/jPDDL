package cz.cuni.mff.auv.problem;

import cz.cuni.mff.auv.problem1.Problem;
import org.junit.Test;

public class Test01_Instantiation {

	@Test
	public void test() {
		Problem problem = new Problem();
		problem.getState().dump();
	}
	
	public static void main(String[] args) {
		Test01_Instantiation test = new Test01_Instantiation();
		
		test.test();
	}
	
}
