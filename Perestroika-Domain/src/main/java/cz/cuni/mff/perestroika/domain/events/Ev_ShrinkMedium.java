package cz.cuni.mff.perestroika.domain.events;

import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.perestroika.domain.Event;
import cz.cuni.mff.perestroika.domain.State;
import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.problem.E_Location;

/**
 * EVENT
 * 
 * :event shrink-medium
 * :parameters (?l - location)
 */
public final class Ev_ShrinkMedium extends Event {
	
	public T_Location l;
	
	public boolean[] applied = new boolean[] { false, false };
	
	public Ev_ShrinkMedium() {
	}
	
	public Ev_ShrinkMedium(Ev_ShrinkMedium source) {
		source.rewrite(this);
	}
	
	public Ev_ShrinkMedium(T_Location l) {
		super();
		this.l = l;
	}

	@Override
	public String toString() {
		return "Ev_ShrinkMedium[l - location = " + l + "]";
	}
	
	@Override
	public String getName() {
		return "shrink-big";		
	}
	
	@Override
	public String toEffector() {
		return "(" + getName() + " " + l.name + ")";
	}
	
	@Override
	public Ev_ShrinkMedium create() {
		return get();
	}
	
	@Override
	public Ev_ShrinkMedium clone() {
		Ev_ShrinkMedium result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_ShrinkMedium)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_ShrinkMedium effector) {
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
	 * :precondition (and (medium ?l)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Medium.isSet(l); 
	}
		
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Medium.isSet(l)) break;
		}
		if (!applicable) return false;
				
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (small ?l)
     *              (not (medium ?l))
     *         )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Small.set(l);
		applied[1] = state.p_Medium.clear(l);
	}
	
	public void reverse(State state) {
		if (applied[0]) state.p_Small.clear(l);
		if (applied[1]) state.p_Medium.set(l);
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_ShrinkMedium)effector, callback);
	}
	
	public void unify(State state, Ev_ShrinkMedium effector, IPDDLUnification<Ev_ShrinkMedium> callback) {
		callback.start();
		
		Unify_Ev_ShrinkMedium unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ev_ShrinkMedium> pool = new Pool<Unify_Ev_ShrinkMedium>(1) {

		@Override
		protected Unify_Ev_ShrinkMedium create() {
			return new Unify_Ev_ShrinkMedium();
		}
		
	};
	
	private static final class Unify_Ev_ShrinkMedium {
	
		State state;
		Ev_ShrinkMedium effector;
		IPDDLUnification<Ev_ShrinkMedium> callback;
		
		public void unify() {
			unify_Medium_1_l();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Medium_1_l_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l = E_Location.THIS.getElement(key);
				unify_done();
				return true;
			}
			
		}		
		
		private Unify_Medium_1_l_Proc unify_medium_1_l_proc = new Unify_Medium_1_l_Proc();
				
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Medium_1_l() {
			if (effector.l == null) {
				state.p_Medium.internal().forEachEntry(unify_medium_1_l_proc);
				effector.l = null;
			} else {
				if (!state.p_Medium.isSet(effector.l)) return;
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
	
	private static final Pool<Ev_ShrinkMedium> effectorPool = new Pool<Ev_ShrinkMedium>() {

		@Override
		protected Ev_ShrinkMedium create() {
			return new Ev_ShrinkMedium();
		}
		
	};
	
	public static Ev_ShrinkMedium get() {
		Ev_ShrinkMedium result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_ShrinkMedium effector) {
		effectorPool.back(effector);
	}


}
