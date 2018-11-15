package cz.cuni.mff.jpddl.tools.simulations;

import java.io.File;
import java.util.Random;

import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;

public class LamaSimulation {
	
	public void simulate(int totalRuns, String id, PDDLProblem problem, PlanChecker planChecker, IPlanValidator validator, int maxIterations, long randomSeed, File csvOutputFile) {
		Random random = new Random(randomSeed);
		
		LamaRun run = new LamaRun();
				
		for (int i = 0; i < totalRuns; ++i) {
			if (i != 0) System.out.println();
			System.out.println("=============================");
			System.out.println("RUN " + (i+1) + " / " + totalRuns);
			System.out.println("=============================");
			
			String runId = id + "-" + (i+1);
			run.run(runId, problem, planChecker, validator, maxIterations, random.nextLong(), csvOutputFile);
		}		
	}

}
