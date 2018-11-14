package cz.cuni.mff.perestroika.domain.events;

import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.Event;
import cz.cuni.mff.perestroika.domain.State;
import cz.cuni.mff.perestroika.domain.predicates.P_Accessible;
import cz.cuni.mff.perestroika.domain.predicates.P_None;
import cz.cuni.mff.perestroika.domain.predicates.P_Small;
import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.problem.E_Location;

/**
 * EVENT
 * 
 * :event shrink-small-empty
 * :parameters (?s - ship ?l - location)
 */
public final class Ev_ShrinkSmallEmpty extends Event {
	
	public T_Location l;
	
	public boolean[] applied = new boolean[] { false, false, false };
	
	public Ev_ShrinkSmallEmpty() {
	}
	
	public Ev_ShrinkSmallEmpty(Ev_ShrinkSmallEmpty source) {
		source.rewrite(this);
	}
	
	public Ev_ShrinkSmallEmpty(T_Location l) {
		super();
		this.l = l;
	}

	@Override
	public String toString() {
		return "Ev_ShrinkSmallEmpty[l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "shrink-small-empty";		
	}
	
	@Override
	public String toEffector() {
		return "(" + getName() + " " + l.name + ")";
	}
	
	@Override
	public Ev_ShrinkSmallEmpty create() {
		return get();
	}
	
	@Override
	public Ev_ShrinkSmallEmpty clone() {
		Ev_ShrinkSmallEmpty result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_ShrinkSmallEmpty)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_ShrinkSmallEmpty effector) {
		effector.l = l;
	}
	
	@Override
	public void assign(String[] args) {
		l = E_Location.THIS.getElement(args[0]);
	}
	
	@Override
	public void reset() {
		l = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (small ?l)
     *                    (not (at-agent ?l))      
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Small.isSet(l) 
			   && !state.p_AtAgent.isSet(l);
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return    state.p_Small.isSet(l)    && !minusState.p_Small.isSet(l)
			   && !state.p_AtAgent.isSet(l) /* && !minusState.p_AtAgent.isSet(l) */ ;
	}
		
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Small.isSet(l)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = !state.p_AtAgent.isSet(l)) break;
		}
		if (!applicable) return false;		
		
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (none ?l)
     *              (not (small ?l))
     *              (not (accessible ?l))
     *         )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_None.set(l);
		applied[1] = state.p_Small.clear(l);
		applied[2] = state.p_Accessible.clear(l);
	}
	
	public void reverse(State state) {
		if (applied[0]) state.p_None.clear(l);
		if (applied[1]) state.p_Small.set(l);
		if (applied[2]) state.p_Accessible.set(l);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_None.toInt(l));
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_None.toInt(l));
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
		compact.set(P_Small.toInt(l));
		compact.set(P_Accessible.toInt(l));
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
		compact.clear(P_Small.toInt(l));
		compact.clear(P_Accessible.toInt(l));
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_ShrinkSmallEmpty)effector, callback);
	}
	
	public void unify(State state, Ev_ShrinkSmallEmpty effector, IPDDLUnification<Ev_ShrinkSmallEmpty> callback) {
		callback.start();
		
		Unify_Ev_ShrinkSmallEmpty unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ev_ShrinkSmallEmpty> pool = new Pool<Unify_Ev_ShrinkSmallEmpty>(1) {

		@Override
		protected Unify_Ev_ShrinkSmallEmpty create() {
			return new Unify_Ev_ShrinkSmallEmpty();
		}
		
	};
	
	private static final class Unify_Ev_ShrinkSmallEmpty {
	
		State state;
		Ev_ShrinkSmallEmpty effector;
		IPDDLUnification<Ev_ShrinkSmallEmpty> callback;
		
		public void unify() {
			unify_Small_1_l();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Small_1_l_Proc extends FastIntMap.ForEachEntry<Boolean> {
	
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l = E_Location.THIS.getElement(key);
				unify_AtAgent_2_l();
				return true;
			}
			
		}
		
		private Unify_Small_1_l_Proc unify_small_1_l_proc = new Unify_Small_1_l_Proc();
		
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Small_1_l() {
			if (effector.l == null) {
				state.p_Small.internal().forEachEntry(unify_small_1_l_proc);
				effector.l = null;
			} else {
				if (!state.p_Small.internal().containsKey(effector.l)) return;
				unify_AtAgent_2_l();
			}
		}
	
		private void unify_AtAgent_2_l() {
			if (state.p_AtAgent.isSet(effector.l)) return;
			callback.unified(effector);	
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ev_ShrinkSmallEmpty> effectorPool = new Pool<Ev_ShrinkSmallEmpty>() {

		@Override
		protected Ev_ShrinkSmallEmpty create() {
			return new Ev_ShrinkSmallEmpty();
		}
		
	};
	
	public static Ev_ShrinkSmallEmpty get() {
		Ev_ShrinkSmallEmpty result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_ShrinkSmallEmpty effector) {
		effectorPool.back(effector);
	}


}
