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
 * :event shrink-big
 * :parameters (?l - location)
 */
public final class Ev_ShrinkBig extends Event {
	
	public T_Location l;
	
	public boolean[] applied = new boolean[] { false, false };
	
	public Ev_ShrinkBig() {
	}
	
	public Ev_ShrinkBig(Ev_ShrinkBig source) {
		source.rewrite(this);
	}
	
	public Ev_ShrinkBig(T_Location l) {
		super();
		this.l = l;
	}

	@Override
	public String toString() {
		return "Ev_ShrinkBig[l - location = " + l + "]";
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
	public Ev_ShrinkBig create() {
		return get();
	}
	
	@Override
	public Ev_ShrinkBig clone() {
		Ev_ShrinkBig result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_ShrinkBig)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_ShrinkBig effector) {
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
	 * :precondition (and (big ?l)
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Big.isSet(l); 
	}
		
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Big.isSet(l)) break;
		}
		if (!applicable) return false;
				
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (medium ?l)
     *              (not (big ?l))
     *         )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Medium.set(l);
		applied[1] = state.p_Big.clear(l);
	}
	
	public void reverse(State state) {
		if (applied[0]) state.p_Medium.clear(l);
		if (applied[1]) state.p_Big.set(l);
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_ShrinkBig)effector, callback);
	}
	
	public void unify(State state, Ev_ShrinkBig effector, IPDDLUnification<Ev_ShrinkBig> callback) {
		callback.start();
		
		Unify_Ev_ShrinkBig unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ev_ShrinkBig> pool = new Pool<Unify_Ev_ShrinkBig>(1) {

		@Override
		protected Unify_Ev_ShrinkBig create() {
			return new Unify_Ev_ShrinkBig();
		}
		
	};
	
	private static final class Unify_Ev_ShrinkBig {
	
		State state;
		Ev_ShrinkBig effector;
		IPDDLUnification<Ev_ShrinkBig> callback;
		
		public void unify() {
			unify_Big_1_l();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Big_1_l_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l = E_Location.THIS.getElement(key);
				unify_done();
				return true;
			}
			
		}		
		
		private Unify_Big_1_l_Proc unify_big_1_l_proc = new Unify_Big_1_l_Proc();
				
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Big_1_l() {
			if (effector.l == null) {
				state.p_Big.internal().forEachEntry(unify_big_1_l_proc);
				effector.l = null;
			} else {
				if (!state.p_Big.isSet(effector.l)) return;
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
	
	private static final Pool<Ev_ShrinkBig> effectorPool = new Pool<Ev_ShrinkBig>() {

		@Override
		protected Ev_ShrinkBig create() {
			return new Ev_ShrinkBig();
		}
		
	};
	
	public static Ev_ShrinkBig get() {
		Ev_ShrinkBig result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_ShrinkBig effector) {
		effectorPool.back(effector);
	}


}
