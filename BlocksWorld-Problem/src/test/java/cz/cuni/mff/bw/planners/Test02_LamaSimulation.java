package cz.cuni.mff.bw.planners;

import java.io.File;

import cz.cuni.mff.bw.problem1.Problem;
import cz.cuni.mff.jpddl.tools.simulations.LamaSimulation;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterBFS;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;
import cz.cuni.mff.jpddl.utils.IEventSelector;
import cz.cuni.mff.jpddl.utils.SelectIndependentEvents;

public class Test02_LamaSimulation {
	
	public void sim1(int runs) {
		Problem problem = new Problem();		
		SafeStates safeStates = new SafeStates(problem.getDomain(), new File("../Domains/Perestroika/safe_states"));
				
		PlanChecker planChecker = new PlanChecker();
		planChecker.config(problem.getDomain(), safeStates);
		
		PlanTesterBFS planTesterBFS = new PlanTesterBFS();
		planTesterBFS.config(problem.getDomain(), problem.getApplicables(), safeStates, 15);
		
		LamaSimulation simulation = new LamaSimulation();
		IEventSelector eventSelector = new SelectIndependentEvents();
		simulation.simulate(runs, getClass().getSimpleName(), problem, planChecker, planTesterBFS, 10, 1, new File("results.csv"), eventSelector);
	}
	
	public static void main(String[] args) {
		Test02_LamaSimulation test = new Test02_LamaSimulation();
		test.sim1(5);
	}

}
