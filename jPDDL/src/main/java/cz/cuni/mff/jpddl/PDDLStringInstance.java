package cz.cuni.mff.jpddl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDDLStringInstance {
	
	public String name;
	public List<String> args;
	
	public PDDLStringInstance() {		
		args = new ArrayList<String>();
	}
	
	public PDDLStringInstance(String name) {
		this.name = name;
		args = new ArrayList<String>();
	}
	
	public PDDLStringInstance(String name, List<String> args) {
		this.name = name;
		this.args = new ArrayList<String>(args);
	}
	
	public PDDLStringInstance(String name, String[] args) {
		this.name = name;
		this.args = Arrays.asList(args);
	}
	
	public static PDDLStringInstance fromPDDLInstance(String str) {
		str = str.trim();
		String[] parts = str.split(" ");
		if (parts[0].startsWith("(")) parts[0] = parts[0].substring(1);
		if (parts[parts.length-1].endsWith(")")) parts[parts.length-1] = parts[parts.length-1].substring(0, parts[parts.length-1].length()-1);
		
		PDDLStringInstance result = new PDDLStringInstance();
		
		for (String part : parts) {
			part = part.trim();
			if (part.length() == 0) continue;
			if (result.name == null) result.name = part;
			else result.args.add(part);
		}
		
		return result;		
	}
	
	@Override
	public String toString() {
		return "PDDLStringInstance[name=" + name + ", args.size()=" + (args == null ? "null" : args.size()) + "]";
	}

}
