package cz.cuni.mff.jpddl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDDLStringEffector {
	
	public String name;
	public List<String> args;
	
	public PDDLStringEffector() {		
		args = new ArrayList<String>();
	}
	
	public PDDLStringEffector(String name) {
		this.name = name;
		args = new ArrayList<String>();
	}
	
	public PDDLStringEffector(String name, List<String> args) {
		this.name = name;
		this.args = new ArrayList<String>(args);
	}
	
	public PDDLStringEffector(String name, String[] args) {
		this.name = name;
		this.args = Arrays.asList(args);
	}

}
