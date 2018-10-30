package cz.cuni.mff.jpddl;

public class PDDLType {

	public final String name;
	
	protected String inherits = "PDDLType";
	
	public PDDLType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return (this == null ? "PDDLType[" : this.getClass().getSimpleName() + "[") + name + "|" + inherits + "]";
	}
	
}
