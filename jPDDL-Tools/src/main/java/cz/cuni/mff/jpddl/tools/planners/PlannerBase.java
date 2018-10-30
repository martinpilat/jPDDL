package cz.cuni.mff.jpddl.tools.planners;

import java.io.File;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLStringEffector;

public abstract class PlannerBase {

	/**
	 * Given the domain and problem file, produce the list of actions (or NULL if the plan does not exist).
	 * @param domainFile
	 * @param problemFile
	 * @return list of actions that solves the problem
	 */
	public abstract List<PDDLStringEffector> plan(File domainFile, File problemFile);
	
}
