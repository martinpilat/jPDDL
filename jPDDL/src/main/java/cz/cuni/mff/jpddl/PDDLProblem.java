package cz.cuni.mff.jpddl;

public abstract class PDDLProblem {	
	
	public abstract PDDLDomain getDomain();
	public abstract PDDLState getState();
	public abstract PDDLGoal getGoal();
	public abstract PDDLApplicables getApplicables();
	
	
}
