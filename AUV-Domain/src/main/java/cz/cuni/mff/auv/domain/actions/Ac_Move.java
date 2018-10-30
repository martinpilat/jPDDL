package cz.cuni.mff.auv.domain.actions;

import cz.cuni.mff.auv.domain.Action;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.domain.predicates.P_At;
import cz.cuni.mff.auv.domain.predicates.P_Connected;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.problem.E_Auv;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;

/**
 * ACTION
 * 
 * :action move
 * :parameters (?a - auv ?l1 ?l2 - location)
 */
public final class Ac_Move extends Action {
	
	public T_Auv a;
	public T_Location l1;
	public T_Location l2;
	
	public Ac_Move() {		
	}
	
	public Ac_Move(Ac_Move source) {
		source.rewrite(this);
	}
	
	public Ac_Move(T_Auv a, T_Location l1, T_Location l2) {
		super();
		this.a = a;
		this.l1 = l1;
		this.l2 = l2;
	}

	@Override
	public String toString() {
		return "Ac_Move[a - auv = " + a + ", l1 - location = " + l1 + ", l2 - location = " + l2 + "]";
	}
	
	@Override
	public String getName() {
		return "move";		
	}
	
	@Override
	public String toEffector() {
		return "(move " + a.name + " " + l1.name + " " + l2.name + ")";
	}
	
	@Override
	public Ac_Move create() {
		return get();
	}
	
	@Override
	public Ac_Move clone() {
		Ac_Move result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_Move)assignInto);
	}	
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_Move effector) {
		effector.a = a;
		effector.l1 = l1;
		effector.l2 = l2;
	}
	
	@Override
	public void assign(String[] args) {
		a = E_Auv.THIS.getElement(args[0]);
		l1 = E_Location.THIS.getElement(args[1]);
		l2 = E_Location.THIS.getElement(args[2]);
	}
	
	@Override
	public void reset() {
		a  = null;
		l1 = null;
		l2 = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (operational ?a)
     *                    (at ?a ?l1)                   
     *                    (connected ?l1 ?l2)
     *                    (free ?l2)
     *                    (dup-free ?l2)
     *                )
     * 
	 * @param state
	 * @return
	 */	
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Operational.isSet(a) 
			   && state.p_At.isSet(a, l1) 
			   && state.p_Connected.isSet(l1, l2) 
			   && state.p_Free.isSet(l2) 
			   && state.p_DupFree.isSet(l2); 
	}
	
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Operational.isSet(a)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_At.isSet(a, l1)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Connected.isSet(l1, l2)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Free.isSet(l2)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_DupFree.isSet(l2)) break;
		}
		if (!applicable) return false;
		
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (not (at ?a ?l1))
     *              (at ?a ?l2)
     *              (free ?l1)
     *              (dup-free ?l1)
     *              (not (free ?l2))
     *              (not (dup-free ?l2))
     *  )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		state.p_At.clear(a, l1);
		state.p_At.set(a, l2);
		state.p_Free.set(l1);
		state.p_DupFree.set(l1);
		state.p_Free.clear(l2);
		state.p_DupFree.clear(l2);
	}
	
	@Override
	public void reverse(State state) {
		state.p_At.set(a, l1);
		state.p_At.clear(a, l2);
		state.p_Free.clear(l1);
		state.p_DupFree.clear(l1);
		state.p_Free.set(l2);
		state.p_DupFree.set(l2);
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ac_Move)effector, callback);
	}
	
	public void unify(State state, Ac_Move effector, IPDDLUnification<Ac_Move> callback) {
		callback.start();
		
		Unify_Ac_Move unify = unifyPool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		unifyPool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ac_Move> unifyPool = new Pool<Unify_Ac_Move>(1) {

		@Override
		protected Unify_Ac_Move create() {
			return new Unify_Ac_Move();
		}
		
	};
	
	private static final class Unify_Ac_Move {
	
		State state;
		Ac_Move effector;
		IPDDLUnification<Ac_Move> callback;
		
		public void unify() {
			unify_Operational_1_a();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Operational_1_a_Proc extends FastIntMap.ForEachEntry<Boolean> {
	
			@Override
			public boolean entry(int key, Boolean data) {
				effector.a = E_Auv.THIS.getElement(key);
				unify_At_2_a();
				return true;
			}
			
		}
		
		private Unify_Operational_1_a_Proc unify_operational_1_a_proc = new Unify_Operational_1_a_Proc();
		
		private class Unify_At_2_l1_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l1 = E_Location.THIS.getElement(key);
				unify_Connected_3_l1();
				return true;
			}
			
		}
		
		private Unify_At_2_l1_Proc unify_at_2_l_proc = new Unify_At_2_l1_Proc();
		
		private class Unify_Connected_3_l2_Proc extends FastIntMap.ForEachEntry<Boolean> {

			@Override
			public boolean entry(int key, Boolean data) {
				effector.l2 = E_Location.THIS.getElement(key);
				unify_Connected_3_l1();
				return true;
			}
			
		}
		
		private Unify_Connected_3_l2_Proc unify_connected_3_l2_proc = new Unify_Connected_3_l2_Proc();
		
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Operational_1_a() {
			if (effector.a == null) {
				state.p_Operational.internal().forEachEntry(unify_operational_1_a_proc);
				effector.a = null;
			} else {
				if (!state.p_Operational.internal().containsKey(effector.a)) return;
				unify_At_2_a();
			}
		}
	
		private void unify_At_2_a() {
			P_At.Map_T_Location_2 map = state.p_At.internal().get(effector.a);
			if (map == null || map.size() == 0) return;
			unify_At_2_l1(map);			
		}
		
		private void unify_At_2_l1(P_At.Map_T_Location_2 map) {
			if (effector.l1 == null) {
				map.forEachEntry(unify_at_2_l_proc);
				effector.l1 = null;
			} else {
				if (!map.containsKey(effector.l1)) return;
				unify_Connected_3_l1();
			}
		}
		
		private void unify_Connected_3_l1() {
			P_Connected.Map_T_Location_2 map = state.p_Connected.internal().get(effector.l1);
			if (map == null || map.size() == 0) return;
			unify_Connected_3_l2(map);				
		}
		
		private void unify_Connected_3_l2(P_Connected.Map_T_Location_2 map) {
			if (effector.l2 == null) {
				map.forEachEntry(unify_connected_3_l2_proc);
				effector.l2 = null;
			} else {
				if (!map.containsKey(effector.l2)) return;
				unify_Free_4_l2();
			}
		}
		
		private void unify_Free_4_l2() {
			if (!state.p_Free.internal().containsKey(effector.l2)) return;
			unify_DupFree_5_l2();	
		}
		
		private void unify_DupFree_5_l2() {
			if (!state.p_DupFree.internal().containsKey(effector.l2)) return;
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ac_Move> effectorPool = new Pool<Ac_Move>() {

		@Override
		protected Ac_Move create() {
			return new Ac_Move();
		}
		
	};
	
	public static Ac_Move get() {
		Ac_Move result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_Move effector) {
		effectorPool.back(effector);
	}

}
