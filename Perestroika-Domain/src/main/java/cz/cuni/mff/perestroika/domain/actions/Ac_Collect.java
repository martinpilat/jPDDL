package cz.cuni.mff.perestroika.domain.actions;


import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Action;
import cz.cuni.mff.perestroika.domain.State;
import cz.cuni.mff.perestroika.domain.predicates.P_AtRes;
import cz.cuni.mff.perestroika.domain.predicates.P_Taken;
import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.domain.types.T_Resource;
import cz.cuni.mff.perestroika.problem.E_Location;
import cz.cuni.mff.perestroika.problem.E_Resource;

/**
 * ACTION
 * 
 * :action collect
 * :parameters (?r - resource ?l - location)
 */
public final class Ac_Collect extends Action {
	
	public T_Resource r;
	public T_Location l;
	
	public boolean[] applied = new boolean[] { false };
	
	public Ac_Collect() {		
	}
	
	public Ac_Collect(Ac_Collect source) {
		source.rewrite(this);
	}
	
	public Ac_Collect(T_Resource r, T_Location l) {
		super();
		this.r = r;
		this.l = l;
	}

	@Override
	public String toString() {
		return "Ac_Collect[r - resource = " + r + ", l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "collect";		
	}
	
	@Override
	public String toEffector() {
		return "(collect " + r.name + " " + l.name + ")";
	}
	
	@Override
	public Ac_Collect create() {
		return get();
	}
	
	public Ac_Collect clone() {
		Ac_Collect result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_Collect)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_Collect effector) {
		effector.r = r;
		effector.l = l;
	}
	
	@Override
	public void assign(String[] args) {
		r = E_Resource.THIS.getElement(args[0]);
		l = E_Location.THIS.getElement(args[1]);
	}
	
	@Override
	public void reset() {
		r = null;
		l = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (alive)
	 *                    (at-agent ?a ?l)
     *                    (at-res ?r ?l)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Alive.isSet()  
			   && state.p_AtAgent.isSet(l) 
			   && state.p_AtRes.isSet(r, l);
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return    state.p_Alive.isSet()     && !minusState.p_Alive.isSet()  
			   && state.p_AtAgent.isSet(l)  && !minusState.p_AtAgent.isSet(l)
			   && state.p_AtRes.isSet(r, l) && !minusState.p_AtRes.isSet(r, l);
	}
	
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Alive.isSet()) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_AtAgent.isSet(l)) break;
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
		applied[0] = state.p_Taken.set(r);
	}
	
	@Override
	public void reverse(State state) {
		if (applied[0]) state.p_Taken.clear(r);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_Taken.toInt(r));
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_Taken.toInt(r));
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ac_Collect)effector, callback);
	}
	
	public void unify(State state, Ac_Collect effector, IPDDLUnification<Ac_Collect> callback) {
		callback.start();
		
		Unify_Ac_Collect unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static Pool<Unify_Ac_Collect> pool = new Pool<Unify_Ac_Collect>(1) {

		@Override
		protected Unify_Ac_Collect create() {
			return new Unify_Ac_Collect();
		}
		
	};
	
	private static final class Unify_Ac_Collect {
	
		State state;
		Ac_Collect effector;
		IPDDLUnification<Ac_Collect> callback;
		
		public void unify() {
			unify_Alive_1();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_AtAgent_2_l_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l = E_Location.THIS.getElement(key);
				unify_AtRes_3_r();
				return true;
			}
			
		}
		
		private Unify_AtAgent_2_l_Proc unify_atagent_2_l_proc = new Unify_AtAgent_2_l_Proc();
		
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
		
		private void unify_Alive_1() {
			if (!state.p_Alive.isSet()) return;
			unify_AtAgent_2_l();			
		}
	
		private void unify_AtAgent_2_l() {
			if (effector.l == null) {
				state.p_AtAgent.internal().forEachEntry(unify_atagent_2_l_proc);
				effector.l = null;
			} else {
				if (!state.p_AtAgent.isSet(effector.l)) return;
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
	
	private static final Pool<Ac_Collect> effectorPool = new Pool<Ac_Collect>() {

		@Override
		protected Ac_Collect create() {
			return new Ac_Collect();
		}
		
	};
	
	public static Ac_Collect get() {
		Ac_Collect result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_Collect effector) {
		effectorPool.back(effector);
	}

}
