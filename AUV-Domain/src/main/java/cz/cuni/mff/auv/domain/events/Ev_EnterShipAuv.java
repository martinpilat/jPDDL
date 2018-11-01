package cz.cuni.mff.auv.domain.events;

import cz.cuni.mff.auv.domain.Event;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.domain.actions.Ac_Sample;
import cz.cuni.mff.auv.domain.predicates.P_At;
import cz.cuni.mff.auv.domain.predicates.P_Entry;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.domain.types.T_Vehicle;
import cz.cuni.mff.auv.problem.E_Auv;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.auv.problem.E_Resource;
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
 * :event enter-ship-auv
 * :parameters (?s - ship ?l - location ?a - auv)
 */
public final class Ev_EnterShipAuv extends Event {
	
	public T_Ship s;
	public T_Location l;
	public T_Auv a;
	
	public Ev_EnterShipAuv() {
	}
	
	public Ev_EnterShipAuv(Ev_EnterShipAuv source) {
		source.rewrite(this);
	}
	
	public Ev_EnterShipAuv(T_Ship s, T_Location l, T_Auv a) {
		super();
		this.s = s;
		this.l = l;
		this.a = a;
	}

	@Override
	public String toString() {
		return "Ev_EnterShipAuv[s - ship = " + s + ", l - location = " + l + ", a - auv = " + a + "]";
	}
	
	@Override
	public String getName() {
		return "enter-ship-auv";		
	}
	
	@Override
	public String toEffector() {
		return "(enter-ship-auv " + s.name + " " + l.name + " " + a.name + ")";
	}
	
	@Override
	public Ev_EnterShipAuv create() {
		return get();
	}
	
	@Override
	public Ev_EnterShipAuv clone() {
		Ev_EnterShipAuv result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_EnterShipAuv)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_EnterShipAuv effector) {
		effector.s = s;
		effector.l = l;
		effector.a = a;
	}

	@Override
	public void assign(String[] args) {
		s = E_Ship.THIS.getElement(args[0]);
		l = E_Location.THIS.getElement(args[1]);
		a = E_Auv.THIS.getElement(args[2]);
	}
	
	@Override
	public void reset() {
		s = null;
		l = null;
		a = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (outside ?s)
     *                    (entry ?s ?l)                   
     *                    (at ?a ?l)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Outside.isSet(s) 
			   && state.p_Entry.isSet(s, l) 
			   && state.p_At.isSet(a, l); 
	}
		
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Outside.isSet(s)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Entry.isSet(s, l)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Free.isSet(l) ) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_DupFree.isSet(l)) break;
		}
		if (!applicable) return false;	
		
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (at ?s ?l)
     *              (not (free ?l))
     *              (not (dup-free ?l))
     *              (not (outside ?s))
     *              (not (operational ?a))
     *          )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		state.p_At.set(s, l);
		state.p_Free.clear(l);
		state.p_DupFree.clear(l);
		state.p_Outside.clear(s);
		state.p_Operational.clear(a);
	}
	
	public void reverse(State state) {
		state.p_At.clear(s, l);
		state.p_Free.set(l);
		state.p_DupFree.set(l);
		state.p_Outside.set(s);
		state.p_Operational.set(a);
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_EnterShipAuv)effector, callback);
	}
	
	public void unify(State state, Ev_EnterShipAuv effector, IPDDLUnification<Ev_EnterShipAuv> callback) {
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
		Ev_EnterShipAuv effector;
		IPDDLUnification<Ev_EnterShipAuv> callback;
		
		public void unify() {
			unify_Outside_1_s();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Outside_1_s_Proc extends FastIntMap.ForEachEntry<Boolean> {
	
			@Override
			public boolean entry(int key, Boolean data) {
				effector.s = E_Ship.THIS.getElement(key);
				unify_Entry_2_s();
				return true;
			}
			
		}
		
		private Unify_Outside_1_s_Proc unify_outside_1_s_proc = new Unify_Outside_1_s_Proc();
		
		private class Unify_Entry_2_l_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l = E_Location.THIS.getElement(key);
				unify_At_3_a();
				return true;
			}
			
		}
		
		private Unify_Entry_2_l_Proc unify_entry_2_l_proc = new Unify_Entry_2_l_Proc();
		
		private class Unify_At_3_a_Proc extends FastIntMap.ForEachEntry<P_At.Map_T_Location_2> {
			
			@Override
			public boolean entry(int key, P_At.Map_T_Location_2 data) {
				T_Vehicle obj = E_Vehicle.THIS.getElement(key);
				if (!(obj instanceof T_Auv)) return true;
				effector.a = (T_Auv)obj;
				unify_At_3_l(data);
				return true;
			}
			
		}
		
		private Unify_At_3_a_Proc unify_at_3_a_proc = new Unify_At_3_a_Proc();
				
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Outside_1_s() {
			if (effector.s == null) {
				state.p_Outside.internal().forEachEntry(unify_outside_1_s_proc);
				effector.s = null;
			} else {
				if (!state.p_Outside.internal().containsKey(effector.s)) return;
				unify_Entry_2_s();
			}
		}
	
		private void unify_Entry_2_s() {
			P_Entry.Map_T_Location_2 map = state.p_Entry.internal().get(effector.s);
			if (map == null || map.size() == 0) return;
			unify_Entry_2_l(map);			
		}
		
		private void unify_Entry_2_l(P_Entry.Map_T_Location_2 map) {
			if (effector.l == null) {
				map.forEachEntry(unify_entry_2_l_proc);
				effector.l = null;
			} else {
				if (!map.containsKey(effector.l)) return;
				unify_At_3_a();
			}
		}
		
		private void unify_At_3_a() {
			if (effector.a == null) {
				state.p_At.internal().forEachEntry(unify_at_3_a_proc);
				effector.a = null;
			} else {				
				if (!state.p_At.internal().containsKey(effector.a)) return;
				unify_At_3_l(state.p_At.internal().get(effector.a));
			}
		}
		
		private void unify_At_3_l(P_At.Map_T_Location_2 map) {
			if (!map.containsKey(effector.l)) return;
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ev_EnterShipAuv> effectorPool = new Pool<Ev_EnterShipAuv>() {

		@Override
		protected Ev_EnterShipAuv create() {
			return new Ev_EnterShipAuv();
		}
		
	};
	
	public static Ev_EnterShipAuv get() {
		Ev_EnterShipAuv result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_EnterShipAuv effector) {
		effectorPool.back(effector);
	}


}
