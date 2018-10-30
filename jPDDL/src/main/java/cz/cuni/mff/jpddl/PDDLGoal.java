package cz.cuni.mff.jpddl;

public abstract class PDDLGoal {
	
	public abstract boolean isAchieved(PDDLState state);
	public abstract boolean isAchievedAll(PDDLState... states);
	public abstract boolean isAchievedUnion(PDDLState... states);
	public abstract boolean isAchievedAny(PDDLState... states);
	
	public abstract String toPDDL();
	

}
