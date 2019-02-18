package cz.cuni.mff.perestroika.problem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Predicate;
import cz.cuni.mff.perestroika.domain.State;
import cz.cuni.mff.perestroika.domain.predicates.P_Alive;
import cz.cuni.mff.perestroika.domain.predicates.P_AtAgent;
import cz.cuni.mff.perestroika.domain.predicates.P_Connected;
import cz.cuni.mff.perestroika.domain.types.T_Location;
import gnu.trove.procedure.TIntProcedure;

public abstract class PerestroikaProblem extends PDDLProblem {

	HashMap<T_Location, HashSet<T_Location>> neighbors;

	private void initializeStatic() {
		neighbors = new HashMap<>();
		State state = (State)getState();
		ArrayList<P_Connected> conn = new ArrayList<>();
		state.p_Connected.getAll(conn);

		for (P_Connected pconn : conn) {
			if (!neighbors.containsKey(pconn.l1)) {
				neighbors.put(pconn.l1, new HashSet<>());
			}
			neighbors.get(pconn.l1).add(pconn.l2);
		}
	}

	public String getClosestSafeState() {

		State state = (State)getState();

		if (neighbors == null) {
			initializeStatic();
		}

		ArrayList<P_AtAgent> atAgents = new ArrayList<>();
		state.p_AtAgent.getAll(atAgents);

		assert atAgents.size() == 1;

		HashSet<T_Location> open = new HashSet<>();
		ArrayList<T_Location> closest = new ArrayList<>();
		open.add(atAgents.get(0).l);
		while (!open.isEmpty() && closest.isEmpty()) {
			HashSet<T_Location> new_open = new HashSet<>();
			for (T_Location op : open) {
				if (state.p_Solid.isSet(op))
					closest.add(op);
				else {
					new_open.addAll(neighbors.get(op).stream().filter(x -> !state.p_None.isSet(x)).collect(Collectors.toSet()));
				}
			}
			open = new_open;
		}

		if (closest.isEmpty())
			return null;

		Collections.shuffle(closest);
		T_Location safeLocation = closest.get(0);
		return "(:goal (and " + (new P_AtAgent(safeLocation)).toPredicate() + "))";

	}

	public int dang() {
		State state = (State)getState();
		ArrayList<P_AtAgent> predicates = new ArrayList<>();
		state.p_AtAgent.getAll(predicates);
		T_Location agent_loc = predicates.get(0).l; // agent should always be somewhere

		if (state.p_Big.isSet(agent_loc)) {
			return 3;
		}
		if (state.p_Medium.isSet(agent_loc)) {
			return 2;
		}
		if (state.p_Small.isSet(agent_loc)) {
			return 1;
		}
		return Integer.MAX_VALUE;
	}

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
