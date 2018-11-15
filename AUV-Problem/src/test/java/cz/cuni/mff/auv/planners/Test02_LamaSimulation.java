package cz.cuni.mff.auv.planners;

import java.io.File;

import cz.cuni.mff.auv.problem3.Problem;
import cz.cuni.mff.jpddl.tools.simulations.LamaSimulation;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterFlat;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;

public class Test02_LamaSimulation {
	
	public void sim3(int runs) {
		Problem problem = new Problem();		
		SafeStates safeStates = new SafeStates(problem.getDomain(), new File("../Domains/AUV/safe_states-3"));
				
		PlanChecker planChecker = new PlanChecker();
		planChecker.config(problem.getDomain(), safeStates);
		
		PlanTesterFlat validator = new PlanTesterFlat();
		validator.config(problem.getDomain(), problem.getApplicables(), safeStates);
		
		LamaSimulation simulation = new LamaSimulation();
		simulation.simulate(runs, getClass().getSimpleName(), problem, planChecker, validator, 50, 1, new File("results.csv"));
	}
	
	public static void main(String[] args) {
		Test02_LamaSimulation test = new Test02_LamaSimulation();
		test.sim3(5);
	}

}
