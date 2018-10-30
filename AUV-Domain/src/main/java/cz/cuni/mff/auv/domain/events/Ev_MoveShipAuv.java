package cz.cuni.mff.auv.domain.events;

import cz.cuni.mff.auv.domain.Event;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.domain.predicates.P_At;
import cz.cuni.mff.auv.domain.predicates.P_ConnectedShip;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.domain.types.T_Vehicle;
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
 * :event move-ship-auv
 * :parameters (?s - ship ?l1 ?l2 - location ?a - auv)
 */
public final class Ev_MoveShipAuv extends Event {
	
	public T_Ship s;
	public T_Location l1;
	public T_Location l2;
	public T_Auv a;
	
	public Ev_MoveShipAuv() {
	}
	
	public Ev_MoveShipAuv(Ev_MoveShipAuv source) {
		source.rewrite(this);
	}
	
	public Ev_MoveShipAuv(T_Ship s, T_Location l1, T_Location l2, T_Auv a) {
		super();
		this.s = s;
		this.l1 = l1;
		this.l2 = l2;
		this.a = a;
	}

	@Override
	public String toString() {
		return "Ev_MoveShipFree[s - ship = " + s + ", l1 - location = " + l1 + ", l2 - location = " + l1 + ", a - auv = " + a + "]";
	}
	
	@Override
	public String getName() {
		return "move-ship-auv";		
	}
	
	@Override
	public String toEffector() {
		return "(move-ship-auv " + s.name + " " + l1.name + " " + l2.name + " " + a.name + ")";
	}
	
	@Override
	public Ev_MoveShipAuv create() {
		return get();
	}
	
	@Override
	public Ev_MoveShipAuv clone() {
		Ev_MoveShipAuv result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_MoveShipAuv)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_MoveShipAuv effector) {
		effector.s = s;
		effector.l1 = l1;
		effector.l2 = l2;
		effector.a = a;
	}
	
	@Override
	public void reset() {
		s = null;
		l1 = null;
		l2 = null;
		a = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (at ?s ?l1)
     *                    (connected-ship ?s ?l1 ?l2)
     *                    (at ?a ?l2)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_At.isSet(s, l1) 
			   && state.p_ConnectedShip.isSet(s, l1, l2) 
			   && state.p_At.isSet(a, l2); 
	}
		
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_At.isSet(s, l1)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_ConnectedShip.isSet(s, l1, l2)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_At.isSet(a, l2) ) break;
		}
		if (!applicable) return false;		
		
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (at ?s ?l2)
     *              (not (at ?s ?l1))
     *              (free ?l1)
     *              (dup-free ?l1)
     *              (not (free ?l2))
     *              (not (dup-free ?l2))
     *              (not (operational ?a))
     *         )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		state.p_At.set(s, l2);
		state.p_At.clear(s, l1);
		state.p_Free.set(l1);
		state.p_DupFree.set(l1);
		state.p_Free.clear(l2);
		state.p_DupFree.clear(l2);
		state.p_Operational.clear(a);
	}
	
	public void reverse(State state) {
		state.p_At.clear(s, l2);
		state.p_At.set(s, l1);
		state.p_Free.clear(l1);
		state.p_DupFree.clear(l1);
		state.p_Free.set(l2);
		state.p_DupFree.set(l2);
		state.p_Operational.set(a);
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_MoveShipAuv)effector, callback);
	}
	
	public void unify(State state, Ev_MoveShipAuv effector, IPDDLUnification<Ev_MoveShipAuv> callback) {
		callback.start();
		
		Unify_Ev_EnterShipAuv unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ev_EnterShipAuv> pool = new Pool<Unify_Ev_EnterShipAuv>(1) {

		@Override
		protected Unify_Ev_EnterShipAuv create() {
			return new Unify_Ev_EnterShipAuv();
		}
		
	};
	
	private static final class Unify_Ev_EnterShipAuv {
	
		State state;
		Ev_MoveShipAuv effector;
		IPDDLUnification<Ev_MoveShipAuv> callback;
		
		public void unify() {
			unify_At_1_s();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_At_1_s_Proc extends FastIntMap.ForEachEntry<P_At.Map_T_Location_2> {
	
			@Override
			public boolean entry(int key, P_At.Map_T_Location_2 data) {
				effector.s = E_Ship.THIS.getElement(key);
				unify_At_1_l1(data);
				return true;
			}
			
		}
		
		private Unify_At_1_s_Proc unify_at_1_s_proc = new Unify_At_1_s_Proc();
		
		private class Unify_At_1_l1_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l1 = E_Location.THIS.getElement(key);
				unify_ConnectedShip_2_s();
				return true;
			}
			
		}
		
		private Unify_At_1_l1_Proc unify_at_1_l1_proc = new Unify_At_1_l1_Proc();
		
		private class Unify_ConnectedShip_2_l2_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.s = E_Ship.THIS.getElement(key);
				unify_At_3_a();
				return true;
			}
			
		}
		
		private Unify_ConnectedShip_2_l2_Proc unify_connectedship_2_l2_proc = new Unify_ConnectedShip_2_l2_Proc();
		
		private class Unify_At_3_a_Proc extends FastIntMap.ForEachEntry<P_At.Map_T_Location_2> {
			
			@Override
			public boolean entry(int key, P_At.Map_T_Location_2 data) {
				T_Vehicle obj = E_Vehicle.THIS.getElement(key);
				if (!(obj instanceof T_Auv)) return true;
				effector.a = (T_Auv)obj;
				unify_At_3_l2(data);
				return true;
			}
			
		}
		
		private Unify_At_3_a_Proc unify_at_3_a_proc = new Unify_At_3_a_Proc();
				
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_At_1_s() {
			if (effector.s == null) {
				state.p_At.internal().forEachEntry(unify_at_1_s_proc);
				effector.s = null;
			} else {
				P_At.Map_T_Location_2 map = state.p_At.internal().get(effector.s);
				if (map == null || map.size() == 0) return;
				unify_At_1_l1(map);
			}
		}
	
		private void unify_At_1_l1(P_At.Map_T_Location_2 map) {
			if (effector.l1 == null) {
				map.forEachEntry(unify_at_1_l1_proc);
				effector.l1 = null;
			} else {
				if (!map.containsKey(effector.l1)) return;
				unify_ConnectedShip_2_s();
			}		
		}
		
		private void unify_ConnectedShip_2_s() {
			P_ConnectedShip.Map_T_Location_2 map = state.p_ConnectedShip.internal().get(effector.s);
			if (map == null || map.size() == 0) return;
			unify_ConnectedShip_2_l1(map);
		}
		
		private void unify_ConnectedShip_2_l1(P_ConnectedShip.Map_T_Location_2 map) {
			P_ConnectedShip.Map_T_Location_3 map2 = map.get(effector.l1);
			if (map2 == null || map2.size() == 0) return;
			unify_ConnectedShip_2_l2(map2);
		}
		
		private void unify_ConnectedShip_2_l2(P_ConnectedShip.Map_T_Location_3 map) {
			if (effector.l2 == null) {
				map.forEachEntry(unify_connectedship_2_l2_proc);
				effector.l2 = null;
			} else {
				if (!map.containsKey(effector.l2)) return;
				unify_At_3_a();
			}		
		}
		
		private void unify_At_3_a() {
			if (effector.a == null) {
				state.p_At.internal().forEachEntry(unify_at_3_a_proc);
				effector.a = null;
			} else {
				P_At.Map_T_Location_2 map = state.p_At.internal().get(effector.a);
				if (map == null || map.size() == 0) return;
				unify_At_3_l2(map);
			}	
		}
		
		private void unify_At_3_l2(P_At.Map_T_Location_2 map) {
			if (!map.containsKey(effector.l2)) return;
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ev_MoveShipAuv> effectorPool = new Pool<Ev_MoveShipAuv>() {

		@Override
		protected Ev_MoveShipAuv create() {
			return new Ev_MoveShipAuv();
		}
		
	};
	
	public static Ev_MoveShipAuv get() {
		Ev_MoveShipAuv result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_MoveShipAuv effector) {
		effectorPool.back(effector);
	}


}
