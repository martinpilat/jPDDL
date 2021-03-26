package cz.cuni.mff.auv.problem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import cz.cuni.mff.auv.domain.Predicate;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.domain.predicates.*;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;
import gnu.trove.procedure.TIntProcedure;

public abstract class AuvProblem extends PDDLProblem {

	private T_Auv a;
	private HashSet<T_Location> accessible_ship_locations;
	private HashMap<T_Ship, HashMap<T_Location, ArrayList<T_Location>>> ship_neighbors;
	private Set<T_Ship> ships;
	private HashMap<T_Ship, HashMap<T_Location, HashMap<T_Location, Integer>>> ship_distances;
	private Set<T_Location> safeLocations;

	private HashSet<T_Location> accessible_auv_locations;
	HashMap<T_Location, HashSet<T_Location>> auv_neighbors;

	public String getAdditionalLimitInit() {

		if (accessible_ship_locations == null)
 			initializeStaticDang();

		if (safeLocations == null) {
			safeLocations = new HashSet<>();
			ArrayList<P_Connected> conns = new ArrayList<>();
			State state = (State) getState();
			state.p_Connected.getAll(conns);


			for (P_Connected c : conns) {
				if (!accessible_ship_locations.contains(c.l1))
					safeLocations.add(c.l1);
				if (!accessible_ship_locations.contains(c.l2))
					safeLocations.add(c.l2);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (T_Location loc : safeLocations) {
			sb.append("(safe a " + loc.name + ")");
		}

		return sb.toString();
	}

	private void initializeStaticAUV() {
		State state = (State) getState();
		accessible_auv_locations = new HashSet<>();
		auv_neighbors = new HashMap<>();

		ArrayList<P_Connected> conn_auv = new ArrayList<>();
		state.p_Connected.getAll(conn_auv);

		for (P_Connected conn : conn_auv) {
			accessible_auv_locations.add(conn.l1);
			accessible_auv_locations.add(conn.l2);

			if (!auv_neighbors.containsKey(conn.l1)) {
				auv_neighbors.put(conn.l1, new HashSet<>());
			}

			auv_neighbors.get(conn.l1).add(conn.l2);
		}
	}

	private void initializeStaticDang() {

		State state = (State) getState();

		accessible_ship_locations = new HashSet<>();
		ship_neighbors = new HashMap<>();
		ship_distances = new HashMap<>();
		ArrayList<P_ConnectedShip> conn_ship = new ArrayList<>();
		state.p_ConnectedShip.getAll(conn_ship);

		for (P_ConnectedShip conn : conn_ship) {
			accessible_ship_locations.add(conn.l1);
			accessible_ship_locations.add(conn.l2);
			if (!ship_neighbors.containsKey(conn.s))
				ship_neighbors.put(conn.s, new HashMap<>());
			if (!ship_neighbors.get(conn.s).containsKey(conn.l1))
				ship_neighbors.get(conn.s).put(conn.l1, new ArrayList<>());
			ship_neighbors.get(conn.s).get(conn.l1).add(conn.l2);
		}

		ships = ship_neighbors.keySet();


		T_Location outside = new T_Location("out");
		accessible_ship_locations.add(outside);
		ArrayList<P_Entry> entries = new ArrayList<>();
		state.p_Entry.getAll(entries);

		for (P_Entry entry : entries) {
			ship_neighbors.get(entry.s).put(outside, new ArrayList<>());
			ship_neighbors.get(entry.s).get(outside).add(entry.l);
		}

		ArrayList<P_Exit> exits = new ArrayList<>();
		state.p_Exit.getAll(exits);
		for (P_Exit exit : exits) {
			if (!ship_neighbors.get(exit.s).containsKey(exit.l)) {
				ship_neighbors.get(exit.s).put(exit.l, new ArrayList<>());
			}
			ship_neighbors.get(exit.s).get(exit.l).add(outside);
		}

		for (T_Ship ship : ships) {
			HashMap<T_Location, HashMap<T_Location, Integer>> dists = new HashMap<>();
			HashMap<T_Location, ArrayList<T_Location>> ship_neig = ship_neighbors.get(ship);
			for (T_Location a : accessible_ship_locations) {
				if (!ship_neig.containsKey(a)) {
					continue;
				}
				dists.put(a, new HashMap<>());
				Set<T_Location> open = new HashSet<>();
				open.add(a);

				int d = 0;
				while (!open.isEmpty()) {
					Set<T_Location> new_open = new HashSet<>();
					for (T_Location l : open) {
						dists.get(a).put(l, d);
						new_open.addAll(ship_neig.get(l).stream().filter(x -> !dists.get(a).containsKey(x)).collect(Collectors.toSet()));
					}
					d++;
					open = new_open;
				}
			}

			ship_distances.put(ship, dists);
		}
	}

	public int dang(PDDLEffector step) {

		ArrayList<P_At> ship_locations = new ArrayList<>();
		P_At auv_location = null;

		if (accessible_ship_locations == null) {
			initializeStaticDang();
		}

		State state = (State)getState().clone();
		if (step != null)
			step.apply(state);

		ArrayList<P_At> locations = new ArrayList<>();
		state.p_At.getAll(locations);

		for (P_At pat : locations) {
			if (pat.v instanceof T_Ship) {
				ship_locations.add(pat);
			}
			if (pat.v instanceof T_Auv) {
				auv_location = pat;
			}
		}

		if (!accessible_ship_locations.contains(auv_location.l)) {
			return Integer.MAX_VALUE;
		}

		int dang = Integer.MAX_VALUE;
		for (P_At pat : ship_locations) {
			if (!ship_distances.get(pat.v).containsKey(auv_location.l))
				continue;
			int blocked = 0;
			for (T_Location loc : ship_neighbors.get(pat.v).get(pat.l)) {
				if (loc.name.equals("out"))
					continue;
				if (!state.p_Free.isSet(loc)) {
					blocked = 1;
				}
			}
			if (!ship_distances.get(pat.v).containsKey(pat.l))
				return Integer.MAX_VALUE;
			if (!ship_distances.get(pat.v).get(pat.l).containsKey(auv_location.l))
				return Integer.MAX_VALUE;
			int dist = ship_distances.get(pat.v).get(pat.l).get(auv_location.l);
			if (dist > 1) {
				dist += blocked;
			}
			dang = Math.min(dang, dist);
		}

		return Math.max(dang - 1, 0);
	}

	public String getClosestSafeState() {

		State state = (State)getState();

		if (auv_neighbors == null) {
			initializeStaticAUV();
		}

		HashSet<T_Location> ship_locations = new HashSet<>();
		P_At auv_location = null;

		ArrayList<P_At> locations = new ArrayList<>();
		state.p_At.getAll(locations);

		for (P_At pat : locations) {
			if (pat.v instanceof T_Ship) {
				ship_locations.add(pat.l);
			}
			if (pat.v instanceof T_Auv) {
				auv_location = pat;
			}
		}

		Set<T_Location> open = new HashSet<>();
		open.add(auv_location.l);
		while (!open.isEmpty()) {
			Set<T_Location> new_open = new HashSet<>();
			for (T_Location l : open) {
				if (!accessible_ship_locations.contains(l)) {
					return "(:goal (and " + (new P_At(auv_location.v, l)).toPredicate() + " ))";
				}
				new_open.addAll(auv_neighbors.get(l).stream().filter(x -> !ship_locations.contains(x)).collect(Collectors.toSet()));
			}
			open = new_open;
		}
		return null;
	}

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
