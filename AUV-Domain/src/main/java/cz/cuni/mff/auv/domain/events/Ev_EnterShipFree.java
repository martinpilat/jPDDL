package cz.cuni.mff.auv.domain.events;

import cz.cuni.mff.auv.domain.Event;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.domain.predicates.P_Entry;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Ship;
import cz.cuni.mff.auv.problem.E_Auv;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.auv.problem.E_Ship;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * EVENT
 * 
 * :event enter-ship-free
 * :parameters (?s - ship ?l - location)
 */
public final class Ev_EnterShipFree extends Event {
	
	public T_Ship s;
	public T_Location l;
	
	public Ev_EnterShipFree() {
	}
	
	public Ev_EnterShipFree(Ev_EnterShipFree source) {
		source.rewrite(this);
	}
	
	public Ev_EnterShipFree(T_Ship s, T_Location l) {
		super();
		this.s = s;
		this.l = l;
	}

	@Override
	public String toString() {
		return "Ev_EnterShipFree[s - ship = " + s + ", l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "enter-ship-free";		
	}
	
	@Override
	public String toEffector() {
		return "(enter-ship-free " + s.name + " " + l.name + ")";
	}
	
	@Override
	public Ev_EnterShipFree create() {
		return get();
	}
	
	@Override
	public Ev_EnterShipFree clone() {
		Ev_EnterShipFree result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_EnterShipFree)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_EnterShipFree effector) {
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
	 * :precondition (and (outside ?s)
     *                    (entry ?s ?l)                   
     *                    (free ?l)
     *                    (dup-free ?l)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Outside.isSet(s) 
			   && state.p_Entry.isSet(s, l) 
			   && state.p_Free.isSet(l) 
			   && state.p_DupFree.isSet(l); 
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
     *         )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		state.p_At.set(s, l);
		state.p_Free.clear(l);
		state.p_DupFree.clear(l);
		state.p_Outside.clear(s);
	}
	
	public void reverse(State state) {
		state.p_At.clear(s, l);
		state.p_Free.set(l);
		state.p_DupFree.set(l);
		state.p_Outside.set(s);
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_EnterShipFree)effector, callback);
	}
	
	public void unify(State state, Ev_EnterShipFree effector, IPDDLUnification<Ev_EnterShipFree> callback) {
		callback.start();
		
		Unify_Ev_EnterShipFree unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ev_EnterShipFree> pool = new Pool<Unify_Ev_EnterShipFree>(1) {

		@Override
		protected Unify_Ev_EnterShipFree create() {
			return new Unify_Ev_EnterShipFree();
		}
		
	};
	
	private static final class Unify_Ev_EnterShipFree {
	
		State state;
		Ev_EnterShipFree effector;
		IPDDLUnification<Ev_EnterShipFree> callback;
		
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
				unify_Free_3_l();
				return true;
			}
			
		}
		
		private Unify_Entry_2_l_Proc unify_entry_2_l_proc = new Unify_Entry_2_l_Proc();
				
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
				unify_Free_3_l();
			}
		}
		
		private void unify_Free_3_l() {
			if (!state.p_Free.internal().containsKey(effector.l)) return;
			unify_DupFree_4_l();	
		}
		
		private void unify_DupFree_4_l() {
			if (!state.p_DupFree.internal().containsKey(effector.l)) return;
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ev_EnterShipFree> effectorPool = new Pool<Ev_EnterShipFree>() {

		@Override
		protected Ev_EnterShipFree create() {
			return new Ev_EnterShipFree();
		}
		
	};
	
	public static Ev_EnterShipFree get() {
		Ev_EnterShipFree result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_EnterShipFree effector) {
		effectorPool.back(effector);
	}


}
