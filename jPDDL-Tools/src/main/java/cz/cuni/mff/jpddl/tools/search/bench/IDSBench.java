package cz.cuni.mff.jpddl.tools.search.bench;

import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.tools.search.SearchBase;

public class IDSBench extends SearchBase {
	
	public Bench bench = new Bench();
	
	public IDSBench() {		
	}

	public IDSBench(PDDLDomain domain, PDDLState state, PDDLGoal goal, PDDLApplicables applicables) {
		super(domain, state, goal, applicables);
	}

	public IDSBench(PDDLProblem problem) {
		super(problem);		
	}
	
	public IDSBench(PDDLProblem problem, PDDLState state) {
		super(problem, state);
	}
	
	public List<PDDLEffector> search(int minDepth, int maxDepth) {
		DLSBench dls = new DLSBench(domain, state, goal, applicables);
		
		System.out.println("RUNNING IDSBench(maxDepth = " + maxDepth + ")...");
		
		bench.start();
		
		try {
				
			for (int limit = minDepth; limit <= maxDepth; ++limit) {
				System.out.println("Running DLS(depth = " + limit + ")...");
				List<PDDLEffector> result = dls.search(limit);
				dls.bench.report("DLS(depth = " + limit + ") FINISHED", "    ");
				bench.nodesVisited += dls.bench.nodesVisited;
				if (result != null) return result;
			}
		} finally {
			bench.end();
			bench.report("IDSBench FINISHED!");
		}
		
		return null;		
	}

}
