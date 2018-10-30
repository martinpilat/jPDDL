package cz.cuni.mff.jpddl;

import java.io.File;

public abstract class PDDLProblem {	
	
	public abstract String getName();
	public abstract void createProblemFile(File targetFile, PDDLState state);
	public abstract String toPDDL(PDDLState state);	
	public abstract PDDLDomain getDomain();
	public abstract PDDLState getState();
	public abstract PDDLGoal getGoal();
	public abstract PDDLApplicables getApplicables();
	
	
}
