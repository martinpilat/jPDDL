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
import cz.cuni.mff.perestroika.domain.predicates.P_AtAgent;
import cz.cuni.mff.perestroika.domain.predicates.P_Big;
import cz.cuni.mff.perestroika.domain.predicates.P_None;
import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.problem.E_Location;

/**
 * EVENT
 * 
 * :event create
 * :parameters (?l - location)
 */
public final class Ev_Create extends Event {
	
	public T_Location l;
	
	public boolean[] applied = new boolean[] { false, false, false };
	
	public Ev_Create() {
	}
	
	public Ev_Create(Ev_Create source) {
		source.rewrite(this);
	}
	
	public Ev_Create(T_Location l) {
		super();
		this.l = l;
	}

	@Override
	public String toString() {
		return "Ev_Create[l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "create";		
	}
	
	@Override
	public String toEffector() {
		return "(" + getName() + " " + l.name + ")";
	}
	
	@Override
	public Ev_Create create() {
		return get();
	}
	
	@Override
	public Ev_Create clone() {
		Ev_Create result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_Create)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_Create effector) {
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
	 * :precondition (and (none ?l)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_None.isSet(l); 
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return    state.p_None.isSet(l) && !minusState.p_None.isSet(l); 
	}
		
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_None.isSet(l)) break;
		}
		if (!applicable) return false;
				
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (big ?l)
     *              (not (none ?l))
     *              (accessible ?l)
     *         )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Big.set(l);
		applied[1] = state.p_None.clear(l);
		applied[2] = state.p_Accessible.set(l);
	}
	
	public void reverse(State state) {
		if (applied[0]) state.p_Big.clear(l);
		if (applied[1]) state.p_None.set(l);
		if (applied[2]) state.p_Accessible.clear(l);
	}
		
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_Big.toInt(l));
		compact.set(P_Accessible.toInt(l));
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_Big.toInt(l));
		compact.clear(P_Accessible.toInt(l));
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
		compact.set(P_None.toInt(l));
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
		compact.clear(P_None.toInt(l));
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_Create)effector, callback);
	}
	
	public void unify(State state, Ev_Create effector, IPDDLUnification<Ev_Create> callback) {
		callback.start();
		
		Unify_Ev_Create unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ev_Create> pool = new Pool<Unify_Ev_Create>(1) {

		@Override
		protected Unify_Ev_Create create() {
			return new Unify_Ev_Create();
		}
		
	};
	
	private static final class Unify_Ev_Create {
	
		State state;
		Ev_Create effector;
		IPDDLUnification<Ev_Create> callback;
		
		public void unify() {
			unify_None_1_l();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_None_1_l_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l = E_Location.THIS.getElement(key);
				unify_done();
				return true;
			}
			
		}		
		
		private Unify_None_1_l_Proc unify_none_1_l_proc = new Unify_None_1_l_Proc();
				
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_None_1_l() {
			if (effector.l == null) {
				state.p_None.internal().forEachEntry(unify_none_1_l_proc);
				effector.l = null;
			} else {
				if (!state.p_None.isSet(effector.l)) return;
				unify_done();
			}			
		}
		
		private void unify_done() {			
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ev_Create> effectorPool = new Pool<Ev_Create>() {

		@Override
		protected Ev_Create create() {
			return new Ev_Create();
		}
		
	};
	
	public static Ev_Create get() {
		Ev_Create result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_Create effector) {
		effectorPool.back(effector);
	}


}
