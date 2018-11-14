package cz.cuni.mff.auv.domain.actions;

import cz.cuni.mff.auv.domain.Action;
import cz.cuni.mff.auv.domain.State;
import cz.cuni.mff.auv.domain.predicates.P_At;
import cz.cuni.mff.auv.domain.predicates.P_AtRes;
import cz.cuni.mff.auv.domain.predicates.P_DupFree;
import cz.cuni.mff.auv.domain.predicates.P_Free;
import cz.cuni.mff.auv.domain.predicates.P_Sampled;
import cz.cuni.mff.auv.domain.types.T_Auv;
import cz.cuni.mff.auv.domain.types.T_Location;
import cz.cuni.mff.auv.domain.types.T_Resource;
import cz.cuni.mff.auv.problem.E_Auv;
import cz.cuni.mff.auv.problem.E_Location;
import cz.cuni.mff.auv.problem.E_Resource;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * ACTION
 * 
 * :action sample
 * :parameters (?a - auv ?r - resource ?l - location)
 */
public final class Ac_Sample extends Action {
	
	public T_Auv a;
	public T_Resource r;
	public T_Location l;
	
	public boolean[] applied = new boolean[] { false, false };
	
	public Ac_Sample() {		
	}
	
	public Ac_Sample(Ac_Sample source) {
		source.rewrite(this);
	}
	
	public Ac_Sample(T_Auv a, T_Resource r, T_Location l) {
		super();
		this.a = a;
		this.r = r;
		this.l = l;
	}

	@Override
	public String toString() {
		return "Ac_Sample[a - auv = " + a + ", r - resource = " + r + ", l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "sample";		
	}
	
	@Override
	public String toEffector() {
		return "(sample " + a.name + " " + r.name + " " + l.name + ")";
	}
	
	@Override
	public Ac_Sample create() {
		return get();
	}
	
	public Ac_Sample clone() {
		Ac_Sample result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_Sample)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_Sample effector) {
		effector.a = a;
		effector.r = r;
		effector.l = l;
	}
	
	@Override
	public void assign(String[] args) {
		a = E_Auv.THIS.getElement(args[0]);
		r = E_Resource.THIS.getElement(args[1]);
		l = E_Location.THIS.getElement(args[2]);
	}
	
	@Override
	public void reset() {
		a = null;
		r = null;
		l = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (operational ?a)
	 *                    (at ?a ?l)
     *                    (at-res ?r ?l)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Operational.isSet(a)  
			   && state.p_At.isSet(a, l) 
			   && state.p_AtRes.isSet(r, l);
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return    state.p_Operational.isSet(a) && !minusState.p_Operational.isSet(a)  
			   && state.p_At.isSet(a, l)       && !minusState.p_At.isSet(a, l)
			   && state.p_AtRes.isSet(r, l)    && !minusState.p_AtRes.isSet(r, l);
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
			if (applicable = state.p_At.isSet(a, l)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_AtRes.isSet(r, l)) break;
		}
		if (!applicable) return false;
		
		return true;
	}
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (sampled ?r)
	 *              (not at-res ?r ?l)
     *         )
     *  )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Sampled.set(r);
		applied[1] = state.p_AtRes.clear(r, l);
	}
	
	@Override
	public void reverse(State state) {
		if (applied[0]) state.p_Sampled.clear(r);
		if (applied[1]) state.p_AtRes.set(r, l);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_Sampled.toInt(r));
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_Sampled.toInt(r));
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
		compact.set(P_AtRes.toInt(r, l));
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
		compact.clear(P_AtRes.toInt(r, l));
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ac_Sample)effector, callback);
	}
	
	public void unify(State state, Ac_Sample effector, IPDDLUnification<Ac_Sample> callback) {
		callback.start();
		
		Unify_Ac_Sample unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static Pool<Unify_Ac_Sample> pool = new Pool<Unify_Ac_Sample>(1) {

		@Override
		protected Unify_Ac_Sample create() {
			return new Unify_Ac_Sample();
		}
		
	};
	
	private static final class Unify_Ac_Sample {
	
		State state;
		Ac_Sample effector;
		IPDDLUnification<Ac_Sample> callback;
		
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
		
		private class Unify_At_2_l_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l = E_Location.THIS.getElement(key);
				unify_AtRes_3_r();
				return true;
			}
			
		}
		
		private Unify_At_2_l_Proc unify_at_2_l_proc = new Unify_At_2_l_Proc();
		
		private class Unify_AtRes_3_r_Proc extends FastIntMap.ForEachEntry<P_AtRes.Map_T_Location_2> {

			@Override
			public boolean entry(int key, P_AtRes.Map_T_Location_2 data) {
				effector.r = E_Resource.THIS.getElement(key);
				unify_AtRes_3_l(data);
				return true;
			}
			
		}
		
		private Unify_AtRes_3_r_Proc unify_atres_3_r_proc = new Unify_AtRes_3_r_Proc();
		
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
			unify_At_2_l(map);			
		}
		
		private void unify_At_2_l(P_At.Map_T_Location_2 map) {
			if (effector.l == null) {
				map.forEachEntry(unify_at_2_l_proc);
				effector.l = null;
			} else {
				if (!map.containsKey(effector.l)) return;
				unify_AtRes_3_r();
			}
		}
		
		private void unify_AtRes_3_r() {
			if (effector.r == null) {
				state.p_AtRes.internal().forEachEntry(unify_atres_3_r_proc);
				effector.r = null;
			} else {
				P_AtRes.Map_T_Location_2 map = state.p_AtRes.internal().get(effector.r);
				if (map == null || map.size() == 0) return;
				unify_AtRes_3_l(map);
			}			
		}
		
		private void unify_AtRes_3_l(P_AtRes.Map_T_Location_2 map) {
			if (!map.containsKey(effector.l)) return;			
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ac_Sample> effectorPool = new Pool<Ac_Sample>() {

		@Override
		protected Ac_Sample create() {
			return new Ac_Sample();
		}
		
	};
	
	public static Ac_Sample get() {
		Ac_Sample result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_Sample effector) {
		effectorPool.back(effector);
	}

}
