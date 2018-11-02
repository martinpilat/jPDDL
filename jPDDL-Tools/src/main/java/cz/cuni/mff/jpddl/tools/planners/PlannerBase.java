package cz.cuni.mff.jpddl.tools.planners;

import java.io.File;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLStringInstance;

public abstract class PlannerBase {

	/**
	 * Given the domain and problem file, produce the list of actions (or NULL if the plan does not exist).
	 * @param domainFile
	 * @param problemFile
	 * @return list of actions that solves the problem
	 */
	public abstract List<PDDLStringInstance> plan(File domainFile, File problemFile);
	
}
