package cz.cuni.mff.auv.problem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import cz.cuni.mff.auv.domain.Predicate;
import cz.cuni.mff.auv.domain.predicates.P_At;
import cz.cuni.mff.auv.domain.predicates.P_Operational;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;
import gnu.trove.procedure.TIntProcedure;

public abstract class AuvProblem extends PDDLProblem {

	private T_Auv a;
	
	@Override
	public void createProblemFile(File targetFile, PDDLState state, StateCompact targetState) {
		
		final StringBuffer customGoal = new StringBuffer();	
		customGoal.append("(:goal (and\n");
		
		targetState.forEach(new TIntProcedure() {
			
			@Override
			public boolean execute(int predicate) {
				if ((predicate & Predicate.MASK_TYPE) == P_At.FLAG_TYPE) {
					T_Vehicle veh = P_At.fromInt_v(predicate);
					if (veh instanceof T_Auv) {
						a = (T_Auv)veh;
						customGoal.append("            ");
						customGoal.append(new P_At(a, P_At.fromInt_l(predicate)).toPredicate());
						customGoal.append("\n");
						return false;
					}					
				}
				return true;
			}
			
		});
		
		customGoal.append("            ");
		customGoal.append(new P_Operational(a).toPredicate());		
		customGoal.append("\n       )\n)");
		
		try {
			PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(targetFile)));
			writer.println(toPDDL(state, customGoal.toString()));
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException("Failed to produce custom PDDL Problem file at: " + targetFile.getAbsolutePath(), e);
		}
	}
	
}
