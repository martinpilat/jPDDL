package cz.cuni.mff.jpddl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class PDDLProblem {	

	public PDDLApplicables applicables;
	
	public PDDLProblem() {
		applicables = new PDDLApplicables();
	}
	
	public abstract String getName();
	public abstract PDDLDomain getDomain();
	public abstract PDDLState getState();
	public abstract PDDLGoal getGoal();
	public abstract PDDLDeadEnd getDeadEnd();
	
	public PDDLApplicables getApplicables() {
		return applicables;
	}

	
	public String toPDDL(PDDLState state) {
		StringBuffer sb = new StringBuffer();
		sb.append("(define (problem " + getName() + ")\n");
		sb.append("(:domain " + getDomain().getName() + ")\n");
		sb.append("  (:objects");
		
		boolean first = true;
		for (PDDLEnum pddlEnum : getDomain().getEnums()) {
			if (!pddlEnum.isFinalType()) continue;
			if (pddlEnum.getSize() <= 1) continue;
			if (first) first = false;
			else sb.append("            ");
			for (PDDLType type : pddlEnum.elements()) {
				if (type == null) continue;
				sb.append(" ");
				sb.append(type.name);
			}
			sb.append(" - " + pddlEnum.getName() + "\n");
		}
		
		sb.append(")\n");
		sb.append("\n");
		
		sb.append("(:init\n");
		
		List predicates = new ArrayList();
		for (IStorage storage : state.getStorages()) {
			predicates.clear();
			storage.getAll(predicates);
			PDDLPredicate.dumpPredicates(sb, (List<PDDLPredicate>)predicates, 80, "       ");
		}
		sb.append("\n");
		sb.append(")\n");		
		sb.append("\n");
		sb.append(getGoal().toPDDL());
		sb.append("\n");
		sb.append(")");
		
		return sb.toString();
	}
	
	public void createProblemFile(File targetFile, PDDLState state) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(targetFile)));
			writer.println(toPDDL(state));
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException("Failed to produce PDDL Problem file at: " + targetFile.getAbsolutePath(), e);
		}
	}
	
	
}
