package cz.cuni.mff.jpddl.tools.simulations;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.search.bench.Timed;
import cz.cuni.mff.jpddl.tools.utils.CSV;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator.PlanValidatorResult;

public class LamaSimulation {
	
	public void simulate(int totalRuns, String id, PDDLProblem problem, IPlanValidator validator, int maxIterations, long randomSeed, File csvOutputFile) {
		Random random = new Random(randomSeed);
		
		LamaRun run = new LamaRun();
				
		for (int i = 0; i < totalRuns; ++i) {
			if (i != 0) System.out.println();
			System.out.println("=============================");
			System.out.println("RUN " + (i+1) + " / " + totalRuns);
			System.out.println("=============================");
			
			String runId = id + "-" + (i+1);
			run.run(runId, problem, validator, maxIterations, random.nextLong(), csvOutputFile);
		}		
	}

}
