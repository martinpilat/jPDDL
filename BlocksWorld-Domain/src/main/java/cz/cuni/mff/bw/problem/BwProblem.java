package cz.cuni.mff.bw.problem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import cz.cuni.mff.bw.domain.Predicate;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_On;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.domain.types.T_Slippery;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;
import gnu.trove.procedure.TIntProcedure;

public abstract class BwProblem extends PDDLProblem {

	private T_Slippery a;
	
	@Override
	public void createProblemFile(File targetFile, PDDLState state, StateCompact targetState) {
		
		final StringBuffer customGoal = new StringBuffer();	
		customGoal.append("(:goal (and\n");
		
		targetState.forEach(new TIntProcedure() {
			
			@Override
			public boolean execute(int predicate) {
				if ((predicate & Predicate.MASK_TYPE) == P_On.FLAG_TYPE) {
					T_Block x = P_On.fromInt_x(predicate);
					T_Block y = P_On.fromInt_y(predicate);
					customGoal.append("            ");
					customGoal.append(new P_On(x, y).toPredicate());
					customGoal.append("\n");									
				}
				return true;
			}
			
		});
		
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
