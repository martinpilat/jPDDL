package cz.cuni.mff.auv.problem;

import java.util.ArrayList;
import java.util.List;

import cz.cuni.mff.auv.domain.Effector;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.problem1.Problem;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.utils.StateCompact;

public class Test06_StateCompact {

	//@Test
	public void test() {
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
					System.out.println("  Reseting dynamic state!");
					StateCompact dynamic = state.getDynamic().clone();
					state.setDynamic(dynamic);
					if (!applicable.isApplicable(state)) {
						System.out.println("  Effector NOT APPLICABLE ANYMORE!");
						return;
					} else {
						System.out.println("  Effector still applicable.");
					}
				}
				
			} else {
				System.out.println("  none");
			}
			
		}
		
		System.out.println("--// TEST END //--");
	}
	
	public static void main(String[] args) {
		Test06_StateCompact test = new Test06_StateCompact();
		test.test();
	}
	
}
