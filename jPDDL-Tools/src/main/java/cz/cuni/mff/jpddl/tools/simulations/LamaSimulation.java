package cz.cuni.mff.jpddl.tools.simulations;

import java.io.File;
import java.util.Random;

import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.utils.IEventSelector;

public class LamaSimulation {
	
	public void simulate(int totalRuns, String id, PDDLProblem problem, PlanChecker planChecker, IPlanValidator validator, int maxIterations, long randomSeed, File csvOutputFile, IEventSelector eventSelector) {
		Random random = new Random(randomSeed);
		
		//LamaRun run = new LamaRun();
		//LamaRun_ReplanAfterEvent run = new LamaRun_ReplanAfterEvent();
		//LamaRun_ReplanNotApplicable run = new LamaRun_ReplanNotApplicable();
		LamaRun_Dang run = new LamaRun_Dang();
				
		for (int i = 0; i < totalRuns; ++i) {
			if (i != 0) System.out.println();
			System.out.println("=============================");
			System.out.println("RUN " + (i+1) + " / " + totalRuns);
			System.out.println("=============================");

			run.run(id, i+1, problem, planChecker, validator, maxIterations, random.nextLong(), csvOutputFile, eventSelector);
		}		
	}

}
