package cz.cuni.mff.jpddl.tools.validators;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public class SafeStates {
	
	public final List<PDDLPredicate> predicates;
	
	public SafeStates(List<PDDLPredicate> predicates) {
		this.predicates = predicates;
	}
	
	public SafeStates(PDDLDomain domain, File file) {
		predicates = new ArrayList<PDDLPredicate>();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			String last = "";
			
			while (reader.ready()) {
				String line = reader.readLine();
				last = last + " " + line;
				
				int start = last.indexOf("(");
				while (start >= 0) {
					if (start < 0) break;
					
					int end = last.indexOf(")", start);
					if (end < 0) break;
					
					String instance = last.substring(start, end+1);
					predicates.add(domain.toPredicate(PDDLStringInstance.fromPDDLInstance(instance)));
					
					last = last.substring(end+1);
					start = last.indexOf("(");
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Failed to read safe states from file: " + file.getAbsolutePath(), e);
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (Exception e) {
				}
		}
	}
	
	public boolean isSafe(PDDLState state) {
		for (PDDLPredicate predicate : predicates) {
			if (state.isSet(predicate)) return true;
		}
		return false;
	}

}
