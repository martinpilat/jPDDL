package cz.cuni.mff.bw.domain.events;

import cz.cuni.mff.bw.domain.Event;
import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.bw.domain.predicates.P_Movable;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.problem.E_Block;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * ACTION
 * 
 * :event stick
 * :parameters (?x - block)
 */
public final class Ev_Stick extends Event {
	
	public T_Block x;
	
	public boolean[] applied = new boolean[] { false };
	
	public Ev_Stick() {		
	}
	
	public Ev_Stick(Ev_Stick source) {
		source.rewrite(this);
	}
	
	public Ev_Stick(T_Block x) {
		super();
		this.x = x;
	}

	@Override
	public String toString() {
		return "Ev_Stick[x - block = " + x + "]";
	}
	
	@Override
	public String getName() {
		return "stick";		
	}
	
	@Override
	public String toEffector() {
		return "(stick " + x.name + ")";
	}
	
	@Override
	public Ev_Stick create() {
		return get();
	}
	
	public Ev_Stick clone() {
		Ev_Stick result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_Stick)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_Stick effector) {
		effector.x = x;	
	}
	
	@Override
	public void assign(String[] args) {
		x = E_Block.THIS.getElement(args[0]);
	}
	
	@Override
	public void reset() {
		x = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (sticky ?x))
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return state.p_Sticky.isSet(x);		  
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return state.p_Sticky.isSet(x) && !minusState.p_Sticky.isSet(x);		   
	}
	
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;		
		for (State state : states) {
			if (applicable = state.p_Sticky.isSet(x)) break;
		}
		if (!applicable) return false;
		
		return true;
	}
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (not (movable ?x))
     *         )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Movable.clear(x);
	}
	
	@Override
	public void reverse(State state) {
		if (applied[0]) state.p_Movable.set(x);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
		compact.set(P_Movable.toInt(x));
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
		compact.clear(P_Movable.toInt(x));
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_Stick)effector, callback);
	}
	
	public void unify(State state, Ev_Stick effector, IPDDLUnification<Ev_Stick> callback) {
		callback.start();
		
		Unify_Ev_Stick unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static Pool<Unify_Ev_Stick> pool = new Pool<Unify_Ev_Stick>(1) {

		@Override
		protected Unify_Ev_Stick create() {
			return new Unify_Ev_Stick();
		}
		
	};
	
	private static final class Unify_Ev_Stick {
	
		State state;
		Ev_Stick effector;
		IPDDLUnification<Ev_Stick> callback;
		
		public void unify() {
			unify_Sticky_1_x();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Sticky_1_x_Proc extends FastIntMap.ForEachEntry<Boolean> {
	
			@Override
			public boolean entry(int key, Boolean data) {
				effector.x = E_Block.THIS.getElement(key);
				unified();
				return true;
			}
			
		}
		
		private Unify_Sticky_1_x_Proc unify_sticky_1_x_proc = new Unify_Sticky_1_x_Proc();
				
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Sticky_1_x() {
			if (effector.x == null) {
				state.p_Sticky.internal().forEachEntry(unify_sticky_1_x_proc);
				effector.x = null;
			} else {		
				if (!state.p_Sticky.isSet(effector.x)) return;
				unified();
			}
		}

		private void unified() {
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ev_Stick> effectorPool = new Pool<Ev_Stick>() {

		@Override
		protected Ev_Stick create() {
			return new Ev_Stick();
		}
		
	};
	
	public static Ev_Stick get() {
		Ev_Stick result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_Stick effector) {
		effectorPool.back(effector);
	}

}
