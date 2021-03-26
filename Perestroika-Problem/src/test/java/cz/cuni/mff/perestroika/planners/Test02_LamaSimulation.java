package cz.cuni.mff.perestroika.planners;

import java.io.File;

import cz.cuni.mff.jpddl.tools.simulations.LamaSimulation;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterFlat;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;
import cz.cuni.mff.jpddl.utils.IEventSelector;
import cz.cuni.mff.jpddl.utils.SelectIndependentEvents;
import cz.cuni.mff.perestroika.problem1.Problem;

public class Test02_LamaSimulation {
	
	public void sim1(int runs) {
		Problem problem = new Problem();		
		//SafeStates safeStates = new SafeStates(problem.getDomain(), new File("Domains/Perestroika/safe_states-2"));
		SafeStates safeStates = new SafeStates();
				
		PlanChecker planChecker = new PlanChecker();
		planChecker.config(problem.getDomain(), safeStates);
		
		PlanTesterFlat planTesterFlat = new PlanTesterFlat();
		planTesterFlat.config(problem.getDomain(), problem.getApplicables(), safeStates);
		
		LamaSimulation simulation = new LamaSimulation("C1");
		IEventSelector eventSelector = new SelectIndependentEvents();
		simulation.simulate(runs, getClass().getSimpleName(), problem, planChecker, planTesterFlat, 1000, 1, new File("results.csv"), eventSelector, true);
	}
	
	public static void main(String[] args) {
		Test02_LamaSimulation test = new Test02_LamaSimulation();
		test.sim1(100);
	}

}
