package cz.cuni.mff.perestroika.problem;

import org.junit.Test;

import cz.cuni.mff.perestroika.problem.Problem;

public class Test01_Instantiation {

	@Test
	public void test() {
		Problem problem = new Problem();		
		problem.getState().dump(true);
	}
	
	public static void main(String[] args) {
		Test01_Instantiation test = new Test01_Instantiation();
		
		test.test();
	}
	
}
