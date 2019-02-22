package cz.cuni.mff.jpddl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import cz.cuni.mff.jpddl.utils.StateCompact;

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

	public int dang() {
		throw new UnsupportedOperationException();
	}

	public String getAdditionalLimitInit() {
		throw new UnsupportedOperationException();
	}

	public String getClosestSafeState() {
		throw new UnsupportedOperationException();
	}

	public String toPDDLLimit(PDDLState state, String goal, int limit, String additionalInit) {
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

		sb.append("       (unsafe-times u0)\n");

		for (int i = 0; i < limit; i++) {
			sb.append("       (next u" + i + " u" + (i+1) + ")\n");
		}

		if (additionalInit != null) {
			sb.append(additionalInit);
		}

		sb.append("\n");
		sb.append(")\n");
		sb.append("\n");

		sb.append("\n");
		sb.append(goal);
		sb.append("\n");
		sb.append(")");

		return sb.toString();
	}

	public String toPDDL(PDDLState state, String goal) {
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
		sb.append(goal);
		sb.append("\n");
		sb.append(")");
		
		return sb.toString();
	}

	public void createProblemFileLimit(File targetFile, PDDLState state, int limit, String additionalInit) {
		createProblemFileLimit(targetFile, state, limit, (String)null, additionalInit);
	}

	public void createProblemFileLimit(File targetFile, PDDLState state, int limit, String customGoal, String additionalInit) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(targetFile)));
			if (customGoal == null)
				writer.println(toPDDLLimit(state, getGoal().toPDDL(), limit, additionalInit));
			else
				writer.println((toPDDLLimit(state, customGoal, limit, additionalInit)));
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException("Failed to produce PDDL Problem file at: " + targetFile.getAbsolutePath(), e);
		}
	}

	public void createProblemFile(File targetFile, PDDLState state) {
		createProblemFile(targetFile, state, (String)null);
	}

	public void createProblemFile(File targetFile, PDDLState state, String customGoal) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(targetFile)));
			if (customGoal == null)
				writer.println(toPDDL(state, getGoal().toPDDL()));
			else
				writer.println((toPDDL(state, customGoal)));
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException("Failed to produce PDDL Problem file at: " + targetFile.getAbsolutePath(), e);
		}
	}

	public abstract void createProblemFile(File targetFile, PDDLState state, StateCompact targetState);
	
	
}
