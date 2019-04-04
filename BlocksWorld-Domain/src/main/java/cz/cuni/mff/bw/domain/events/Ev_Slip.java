package cz.cuni.mff.bw.domain.events;

import cz.cuni.mff.bw.domain.Event;
import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.bw.domain.predicates.P_Clear;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_Holding.Map_T_Block_2;
import cz.cuni.mff.bw.domain.predicates.P_OnTable;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.bw.domain.types.T_Slippery;
import cz.cuni.mff.bw.problem.E_Block;
import cz.cuni.mff.bw.problem.E_Hand;
import cz.cuni.mff.bw.problem.E_Slippery;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * ACTION
 * 
 * :event slip
 * :parameters (?x - block ?h - slippery)
 */
public final class Ev_Slip extends Event {
	
	public T_Block x;
	public T_Slippery h;	
	
	public boolean[] applied = new boolean[] { false, false, false, false };
	
	public Ev_Slip() {		
	}
	
	public Ev_Slip(Ev_Slip source) {
		source.rewrite(this);
	}
	
	public Ev_Slip(T_Block x, T_Slippery h) {
		super();
		this.x = x;
		this.h = h;		
	}

	@Override
	public String toString() {
		return "Ev_Slip[x - block = " + x + ", h - hand = " + h + "]";
	}
	
	@Override
	public String getName() {
		return "slip";		
	}
	
	@Override
	public String toEffector() {
		return "(slip " + x.name + " " + h.name + ")";
	}
	
	@Override
	public Ev_Slip create() {
		return get();
	}
	
	public Ev_Slip clone() {
		Ev_Slip result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ev_Slip)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ev_Slip effector) {
		effector.x = x;
		effector.h = h;		
	}
	
	@Override
	public void assign(String[] args) {
		x = E_Block.THIS.getElement(args[0]);
		h = E_Slippery.THIS.getElement(args[1]);		
	}
	
	@Override
	public void reset() {
		x = null;
		h = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (holding ?h ?x))
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return state.p_Holding.isSet(h, x);		  
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return state.p_Holding.isSet(h, x) && !minusState.p_Holding.isSet(h, x);			   
	}
	
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;		
		for (State state : states) {
			if (applicable = state.p_Holding.isSet(h, x)) break;
		}
		if (!applicable) return false;
		
		return true;
	}
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (not (holding ?h ?x))
		            (clear ?x)
		            (handempty ?h)
		            (ontable ?x))
     *  )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Holding.clear(h, x);
		applied[1] = state.p_Clear.set(x);
		applied[2] = state.p_HandEmpty.set(h);
		applied[3] = state.p_OnTable.set(x);
	}
	
	@Override
	public void reverse(State state) {
		if (applied[0]) state.p_Holding.set(h, x);
		if (applied[1]) state.p_Clear.clear(x);
		if (applied[2]) state.p_HandEmpty.clear(h);
		if (applied[3]) state.p_OnTable.clear(x);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_Clear.toInt(x));
		compact.set(P_HandEmpty.toInt(h));
		compact.set(P_OnTable.toInt(x));
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_Clear.toInt(x));
		compact.clear(P_HandEmpty.toInt(h));
		compact.clear(P_OnTable.toInt(x));
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
		compact.set(P_Holding.toInt(h, x));
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
		compact.clear(P_Holding.toInt(h, x));
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ev_Slip)effector, callback);
	}
	
	public void unify(State state, Ev_Slip effector, IPDDLUnification<Ev_Slip> callback) {
		callback.start();
		
		Unify_Ev_Slip unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static Pool<Unify_Ev_Slip> pool = new Pool<Unify_Ev_Slip>(1) {

		@Override
		protected Unify_Ev_Slip create() {
			return new Unify_Ev_Slip();
		}
		
	};
	
	private static final class Unify_Ev_Slip {
	
		State state;
		Ev_Slip effector;
		IPDDLUnification<Ev_Slip> callback;
		
		public void unify() {
			unify_Holding_1_h();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Holding_1_h_Proc extends FastIntMap.ForEachEntry<P_Holding.Map_T_Block_2> {
	
			@Override
			public boolean entry(int key, P_Holding.Map_T_Block_2 data) {
				T_Hand h = E_Hand.THIS.getElement(key);
				if (h instanceof T_Slippery) {
					effector.h = (T_Slippery)h;
					unify_Holding_1_x(data);
				}
				return true;
			}
			
		}
		
		private Unify_Holding_1_h_Proc unify_holding_1_h_proc = new Unify_Holding_1_h_Proc();
		
		private class Unify_Holding_1_x_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.x = E_Block.THIS.getElement(key);
				unified();
				return true;
			}
			
		}
		
		private Unify_Holding_1_x_Proc unify_holding_1_x_proc = new Unify_Holding_1_x_Proc();	
		
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Holding_1_h() {
			if (effector.h == null) {
				state.p_Holding.internal().forEachEntry(unify_holding_1_h_proc);
				effector.h = null;
			} else {			
				P_Holding.Map_T_Block_2 map = state.p_Holding.internal().get(effector.h);
				if (map == null) return;
				unify_Holding_1_x(map);
			}
		}
	
		private void unify_Holding_1_x(Map_T_Block_2 map) {
			if (effector.x == null) {
				map.forEachEntry(unify_holding_1_x_proc);
				effector.x = null;
			} else {
				if (!map.containsKey(effector.x)) return;
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
	
	private static final Pool<Ev_Slip> effectorPool = new Pool<Ev_Slip>() {

		@Override
		protected Ev_Slip create() {
			return new Ev_Slip();
		}
		
	};
	
	public static Ev_Slip get() {
		Ev_Slip result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ev_Slip effector) {
		effectorPool.back(effector);
	}

}
