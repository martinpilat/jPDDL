package cz.cuni.mff.auv.domain.events;

import cz.cuni.mff.auv.domain.Event;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.domain.predicates.P_At;
import cz.cuni.mff.auv.domain.predicates.P_Entry;
import cz.cuni.mff.auv.domain.predicates.P_Exit;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.auv.problem.E_Auv;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.auv.problem.E_Ship;
import cz.cuni.mff.auv.problem.E_Vehicle;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * EVENT
 * 
 * :event leave-ship
 * :parameters (?s - ship ?l - location)
 */
public final class Ev_LeaveShip extends Event {
	
	public T_Ship s;
	public T_Location l;
	
	public Ev_LeaveShip() {
	}
	
	public Ev_LeaveShip(Ev_LeaveShip source) {
		source.rewrite(this);
	}
	
	public Ev_LeaveShip(T_Ship s, T_Location l) {
		super();
		this.s = s;
		this.l = l;
	}

	@Override
	public String toString() {
		return "Ev_LeaveShip[s - ship = " + s + ", l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "leave-ship";		
	}
	
	@Override
	public String toEffector() {
		return "(leave-ship " + s.name + " " + l.name + ")";
	}
	
	@Override
	public Ev_LeaveShip create() {
		return get();
	}
	
	@Override
	public Ev_LeaveShip clone() {
		Ev_LeaveShip result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_LeaveShip)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_LeaveShip effector) {
		effector.s = s;
		effector.l = l;
	}
	
	@Override
	public void assign(String[] args) {
		s = E_Ship.THIS.getElement(args[0]);
		l = E_Location.THIS.getElement(args[1]);
	}
	
	@Override
	public void reset() {
		s = null;
		l = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (exit ?s ?l)
     *                    (at ?s ?l)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Exit.isSet(s, l)
			   && state.p_At.isSet(s, l); 
	}
		
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Exit.isSet(s, l)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_At.isSet(s, l)) break;
		}
		if (!applicable) return false;
				
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (not (at ?s ?l))
     *              (free ?l)
     *              (dup-free ?l)
     *              (outside ?s)
     *         )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		state.p_At.clear(s, l);
		state.p_Free.set(l);
		state.p_DupFree.set(l);
		state.p_Outside.set(s);
	}
	
	public void reverse(State state) {
		state.p_At.set(s, l);
		state.p_Free.clear(l);
		state.p_DupFree.clear(l);
		state.p_Outside.clear(s);
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_LeaveShip)effector, callback);
	}
	
	public void unify(State state, Ev_LeaveShip effector, IPDDLUnification<Ev_LeaveShip> callback) {
		callback.start();
		
		Unify_Ev_LeaveShip unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ev_LeaveShip> pool = new Pool<Unify_Ev_LeaveShip>(1) {

		@Override
		protected Unify_Ev_LeaveShip create() {
			return new Unify_Ev_LeaveShip();
		}
		
	};
	
	private static final class Unify_Ev_LeaveShip {
	
		State state;
		Ev_LeaveShip effector;
		IPDDLUnification<Ev_LeaveShip> callback;
		
		public void unify() {
			unify_Exit_1_s();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Exit_1_s_Proc extends FastIntMap.ForEachEntry<P_Exit.Map_T_Location_2> {
	
			@Override
			public boolean entry(int key, P_Exit.Map_T_Location_2 data) {
				effector.s = E_Ship.THIS.getElement(key);
				unify_Exit_1_l(data);
				return true;
			}
			
		}
		
		private Unify_Exit_1_s_Proc unify_exit_1_s_proc = new Unify_Exit_1_s_Proc();
		
		private class Unify_Exit_1_l_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l = E_Location.THIS.getElement(key);
				unify_At_2_s();
				return true;
			}
			
		}		
		
		private Unify_Exit_1_l_Proc unify_exit_1_l_proc = new Unify_Exit_1_l_Proc();
				
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Exit_1_s() {
			if (effector.s == null) {
				state.p_Exit.internal().forEachEntry(unify_exit_1_s_proc);
				effector.s = null;
			} else {
				P_Exit.Map_T_Location_2 map = state.p_Exit.internal().get(effector.s);
				if (map == null) return;
				unify_Exit_1_l(map);
			}
		}
	
		private void unify_Exit_1_l(P_Exit.Map_T_Location_2 map) {
			if (effector.l == null) {
				map.forEachEntry(unify_exit_1_l_proc);
				effector.l = null;
			} else {
				if (!map.containsKey(effector.l)) return;
				unify_At_2_s();
			}			
		}
		
		private void unify_At_2_s() {
			P_At.Map_T_Location_2 map = state.p_At.internal().get(effector.s);
			if (map == null) return;
			unify_At_2_l(map);
		}
		
		private void unify_At_2_l(P_At.Map_T_Location_2 map) {
			if (!map.containsKey(effector.l)) return;
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ev_LeaveShip> effectorPool = new Pool<Ev_LeaveShip>() {

		@Override
		protected Ev_LeaveShip create() {
			return new Ev_LeaveShip();
		}
		
	};
	
	public static Ev_LeaveShip get() {
		Ev_LeaveShip result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_LeaveShip effector) {
		effectorPool.back(effector);
	}


}
