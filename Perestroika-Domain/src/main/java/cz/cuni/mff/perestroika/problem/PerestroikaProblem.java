package cz.cuni.mff.perestroika.problem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Predicate;
import cz.cuni.mff.perestroika.domain.predicates.P_Alive;
import cz.cuni.mff.perestroika.domain.predicates.P_AtAgent;
import gnu.trove.procedure.TIntProcedure;

public abstract class PerestroikaProblem extends PDDLProblem {

	@Override
	public void createProblemFile(File targetFile, PDDLState state, StateCompact targetState) {
		
		final StringBuffer customGoal = new StringBuffer();	
		customGoal.append("(:goal (and\n");
		
		targetState.forEach(new TIntProcedure() {
			
			@Override
			public boolean execute(int predicate) {
				if ((predicate & Predicate.MASK_TYPE) == P_AtAgent.FLAG_TYPE) {
					customGoal.append("            ");
					customGoal.append(new P_AtAgent(P_AtAgent.fromInt_l(predicate)).toPredicate());
					customGoal.append("\n");
				}
				return true;
			}
			
		});
		customGoal.append(new P_Alive().toPredicate());
		customGoal.append("       )\n)");
		
		try {
			PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(targetFile)));
			writer.println(toPDDL(state, customGoal.toString()));
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException("Failed to produce custom PDDL Problem file at: " + targetFile.getAbsolutePath(), e);
		}
	}
	
}
