package cz.cuni.mff.auv.planners;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.cuni.mff.auv.domain.Effector;
import cz.cuni.mff.auv.problem1.Problem;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.search.bench.Timed;
import cz.cuni.mff.jpddl.tools.simulations.LamaSimulation;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterBFS;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterBFS.PlanTesterBFSResult;

public class Test02_LamaSimulation {
	
	public void sim1(int runs) {
		Problem problem = new Problem();		
		SafeStates safeStates = new SafeStates(problem.getDomain(), new File("../Domains/AUV/safe_states"));
				
		PlanTesterBFS planTesterBFS = new PlanTesterBFS();
		planTesterBFS.config(problem.getDomain(), problem.getApplicables(), safeStates, 15);
		
		LamaSimulation simulation = new LamaSimulation();
		simulation.simulate(runs, getClass().getSimpleName(), problem, planTesterBFS, 50, 1, new File("results.csv"));
	}
	
	public static void main(String[] args) {
		Test02_LamaSimulation test = new Test02_LamaSimulation();
		test.sim1(5);
	}

}
