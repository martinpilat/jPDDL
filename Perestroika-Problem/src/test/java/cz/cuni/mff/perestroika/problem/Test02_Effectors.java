package cz.cuni.mff.perestroika.problem;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.perestroika.domain.Effector;
import cz.cuni.mff.perestroika.domain.State;
import cz.cuni.mff.perestroika.problem1.Problem;

public class Test02_Effectors {

	@Test
	public void test01() {
		Problem problem = new Problem();
		
		State state = problem.state;
		
		for (Effector action : problem.domain.getEffectors()) {
			final List<Effector> applicables = new ArrayList<Effector>();
			
			IPDDLUnification<Effector> callback = new IPDDLUnification<Effector>() {

				@Override
				public void start() {
				}

				@Override
				public void unified(Effector effector) {
					applicables.add((Effector)effector.clone());
				}

				@Override
				public void end() {
				}
			};
			
			action.unify(state, action, callback);
			
			System.out.println("Applicable " + action.getName() + "(s):");
			if (applicables.size() > 0) {
				for (Effector applicable : applicables) {
					System.out.println("  " + applicable.toEffector());
				}
			} else {
				System.out.println("  none");
			}
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	@Test
	public void test02() {
		Problem problem = new Problem();
		
		State state = problem.state;
		
		for (Effector action : problem.domain.getEffectors()) {
			final List<Effector> applicables = new ArrayList<Effector>();
			
			IPDDLUnification<Effector> callback = new IPDDLUnification<Effector>() {

				@Override
				public void start() {
				}

				@Override
				public void unified(Effector effector) {
					applicables.add((Effector)effector.clone());
				}

				@Override
				public void end() {
				}
			};
			
			action.unify(state, action, callback);
			
			System.out.println("APPLICABLE " + action.getName() + "(s):");
			if (applicables.size() > 0) {
				for (Effector applicable : applicables) {
					System.out.println("  " + applicable.toEffector());
				}
				
				for (Effector applicable : applicables) { 
					System.out.println("APPLYING " + applicable.toEffector());
					State clone = state.clone();
					applicable.apply(clone);
					System.out.println("AFFECTED PREDICATES:");
					clone.dumpDiff(state);
				}
				
			} else {
				System.out.println("  none");
			}
			
			
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	public static void main(String[] args) {
		Test02_Effectors test = new Test02_Effectors();
		
		//test.test01();
		test.test02();
	}
	
}
