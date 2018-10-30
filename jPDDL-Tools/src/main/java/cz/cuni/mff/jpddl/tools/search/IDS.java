package cz.cuni.mff.jpddl.tools.search;

import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;

public class IDS extends SearchBase {
	
	public IDS() {		
	}

	public IDS(PDDLDomain domain, PDDLState state, PDDLGoal goal, PDDLApplicables applicables) {
		super(domain, state, goal, applicables);
	}

	public IDS(PDDLProblem problem) {
		super(problem);		
	}
	
	public IDS(PDDLProblem problem, PDDLState state) {
		super(problem, state);
	}
	
	public List<PDDLEffector> search(int maxDepth) {
		
		DLS dls = new DLS(domain, state, goal, applicables);
		
		for (int limit = 1; limit <= maxDepth; ++limit) {
			List<PDDLEffector> result = dls.search(limit);
			if (result != null) return result;
		}
		
		return null;		
	}

}
