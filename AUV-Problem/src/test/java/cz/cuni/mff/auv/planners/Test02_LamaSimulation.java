package cz.cuni.mff.auv.planners;

import java.io.File;

import cz.cuni.mff.auv.problem7.Problem;
import cz.cuni.mff.jpddl.tools.simulations.LamaSimulation;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterFlat;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;
import cz.cuni.mff.jpddl.utils.IEventSelector;
import cz.cuni.mff.jpddl.utils.SelectIndependentEvents;

public class Test02_LamaSimulation {
	
	public void sim3(int runs) {
		Problem problem = new Problem();		
		//SafeStates safeStates = new SafeStates(problem.getDomain(), new File("Domains/AUV/safe_states-3"));
		SafeStates safeStates = new SafeStates();
				
		PlanChecker planChecker = new PlanChecker();
		planChecker.config(problem.getDomain(), safeStates);
		
		PlanTesterFlat validator = new PlanTesterFlat();
		validator.config(problem.getDomain(), problem.getApplicables(), safeStates);
		
		LamaSimulation simulation = new LamaSimulation("REPLAN_EVENT");
		IEventSelector eventSelector = new SelectIndependentEvents();
		simulation.simulate(runs, getClass().getSimpleName(), problem, planChecker, validator, 50, 1, new File("results.csv"), eventSelector, false);
	}
	
	public static void main(String[] args) {
		Test02_LamaSimulation test = new Test02_LamaSimulation();
		test.sim3(5);
	}

}
