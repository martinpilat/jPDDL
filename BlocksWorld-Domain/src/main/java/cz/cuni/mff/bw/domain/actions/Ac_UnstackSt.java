package cz.cuni.mff.bw.domain.actions;

import cz.cuni.mff.bw.domain.Action;
import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.bw.domain.predicates.P_Clear;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_Movable;
import cz.cuni.mff.bw.domain.predicates.P_On;
import cz.cuni.mff.bw.domain.predicates.P_Sticky;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.bw.domain.types.T_Slippery;
import cz.cuni.mff.bw.domain.types.T_Sticky;
import cz.cuni.mff.bw.problem.E_Block;
import cz.cuni.mff.bw.problem.E_Hand;
import cz.cuni.mff.bw.problem.E_Sticky;
import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * ACTION
 * 
 * :action unstack-st 
 * :parameters (?x - block ?y - block ?h - sticky)
 */
public final class Ac_UnstackSt extends Action {
	
	public T_Block x;
	public T_Block y;
	public T_Sticky h;	
	
	public boolean[] applied = new boolean[] { false, false, false, false, false, false, false };
	
	public Ac_UnstackSt() {		
	}
	
	public Ac_UnstackSt(Ac_UnstackSt source) {
		source.rewrite(this);
	}
	
	public Ac_UnstackSt(T_Block x, T_Block y, T_Sticky h) {
		super();
		this.x = x;
		this.y = y;
		this.h = h;		
	}

	@Override
	public String toString() {
		return "Ac_UnstackSt[x - block = " + x + ", y - block = " + y + ", h - hand = " + h + "]";
	}
	
	@Override
	public String getName() {
		return "unstack-st";		
	}
	
	@Override
	public String toEffector() {
		return "(unstack-st " + x.name + " " + y.name + " " + h.name + ")";
	}
	
	@Override
	public Ac_UnstackSt create() {
		return get();
	}
	
	public Ac_UnstackSt clone() {
		Ac_UnstackSt result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_UnstackSt)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_UnstackSt effector) {
		effector.x = x;
		effector.y = y;
		effector.h = h;		
	}
	
	@Override
	public void assign(String[] args) {
		x = E_Block.THIS.getElement(args[0]);
		y = E_Block.THIS.getElement(args[1]);
		h = E_Sticky.THIS.getElement(args[2]);
	}
	
	@Override
	public void reset() {
		x = null;
		y = null;
		h = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (handempty ?h) 
	 *                    (clear ?x) 
	 *                    (on ?x ?y))
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return state.p_HandEmpty.isSet(h) && state.p_Clear.isSet(x) && state.p_On.isSet(x, y);		  
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return    state.p_HandEmpty.isSet(h) && !minusState.p_HandEmpty.isSet(h)
			   && state.p_Clear.isSet(x)     && !minusState.p_Clear.isSet(x)
			   && state.p_On.isSet(x, y)     && !minusState.p_On.isSet(x, y);			   
	}
	
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;		
		for (State state : states) {
			if (applicable = state.p_HandEmpty.isSet(h)) break;
		}
		if (!applicable) return false;
		
		applicable = false;		
		for (State state : states) {
			if (applicable = state.p_Clear.isSet(x)) break;
		}
		if (!applicable) return false;
		
		applicable = false;		
		for (State state : states) {
			if (applicable = state.p_On.isSet(x, y)) break;
		}
		if (!applicable) return false;
		
		return true;
	}
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (movable ?x)
     *              (holding ?h ?x)
	 *	            (clear ?y)
	 *	            (not (clear ?x))
	 *	            (not (handempty ?h))
	 *	            (not (on ?x ?y))
     *              (sticky ?x))
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Movable.set(x);
		applied[1] = state.p_Holding.set(h, x);
		applied[2] = state.p_Clear.set(y);
		applied[3] = state.p_Clear.clear(x);
		applied[4] = state.p_HandEmpty.clear(h);
		applied[5] = state.p_On.clear(x, y);
		applied[6] = state.p_Sticky.set(x);
	}
	
	@Override
	public void reverse(State state) {
		if (applied[0]) state.p_Movable.clear(x);
		if (applied[1]) state.p_Holding.clear(h, x);
		if (applied[2]) state.p_Clear.clear(y);
		if (applied[3]) state.p_Clear.set(x);
		if (applied[4]) state.p_HandEmpty.set(h);
		if (applied[5]) state.p_On.set(x, y);
		if (applied[6]) state.p_Sticky.clear(x);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_Movable.toInt(x));
		compact.set(P_Holding.toInt(h, x));
		compact.set(P_Clear.toInt(y));
		compact.set(P_Sticky.toInt(x));
		
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_Movable.toInt(x));
		compact.clear(P_Holding.toInt(h, x));
		compact.clear(P_Clear.toInt(y));
		compact.clear(P_Sticky.toInt(x));
		
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
		compact.set(P_Clear.toInt(x));
		compact.set(P_HandEmpty.toInt(h));
		compact.set(P_On.toInt(x, y));		
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
		compact.clear(P_Clear.toInt(x));
		compact.clear(P_HandEmpty.toInt(h));
		compact.clear(P_On.toInt(x, y));
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ac_UnstackSt)effector, callback);
	}
	
	public void unify(State state, Ac_UnstackSt effector, IPDDLUnification<Ac_UnstackSt> callback) {
		callback.start();
		
		Unify_Ac_UnstackSt unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static Pool<Unify_Ac_UnstackSt> pool = new Pool<Unify_Ac_UnstackSt>(1) {

		@Override
		protected Unify_Ac_UnstackSt create() {
			return new Unify_Ac_UnstackSt();
		}
		
	};
	
	private static final class Unify_Ac_UnstackSt {
	
		State state;
		Ac_UnstackSt effector;
		IPDDLUnification<Ac_UnstackSt> callback;
		
		public void unify() {
			unify_HandEmpty_1_h();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_HandEmpty_1_h_Proc extends FastIntMap.ForEachEntry<Boolean> {
	
			@Override
			public boolean entry(int key, Boolean data) {
				T_Hand h = E_Hand.THIS.getElement(key);
				if (h instanceof T_Sticky) {
					effector.h = (T_Sticky)h;
					unify_Clear_2_x();	
				}				
				return true;
			}
			
		}
		
		private Unify_HandEmpty_1_h_Proc unify_handempty_1_h_proc = new Unify_HandEmpty_1_h_Proc();
		
		private class Unify_Clear_2_x_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				T_Block x = E_Block.THIS.getElement(key);
				unify_On_3_x();	
				return true;
			}
			
		}
		
		private Unify_Clear_2_x_Proc unify_clear_2_x_proc = new Unify_Clear_2_x_Proc();
		
		private class Unify_On_3_y_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.y = E_Block.THIS.getElement(key);
				unified();
				return true;
			}
			
		}
		
		private Unify_On_3_y_Proc unify_on_3_y_proc = new Unify_On_3_y_Proc();	
					
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_HandEmpty_1_h() {
			if (effector.h == null) {
				state.p_HandEmpty.internal().forEachEntry(unify_handempty_1_h_proc);
				effector.h = null;
			} else {			
				if (!state.p_HandEmpty.isSet(effector.h)) return;
				unify_Clear_2_x();
			}
		}
	
		private void unify_Clear_2_x() {
			if (effector.x == null) {
				state.p_Clear.internal().forEachEntry(unify_clear_2_x_proc);
				effector.x = null;
			} else {
				if (!state.p_Clear.isSet(effector.x)) return;
				unify_On_3_x();
			}
		}
		
		private void unify_On_3_x() {
			P_On.Map_T_Block_2 map = state.p_On.internal().get(effector.x);
			if (map == null) return;
			unify_On_3_y(map);
		}
		
		private void unify_On_3_y(P_On.Map_T_Block_2 map) {
			if (effector.y == null) {
				map.forEachEntry(unify_on_3_y_proc);
				effector.y = null;
			} else {
				if (!state.p_On.isSet(effector.x, effector.y)) return;
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
	
	private static final Pool<Ac_UnstackSt> effectorPool = new Pool<Ac_UnstackSt>() {

		@Override
		protected Ac_UnstackSt create() {
			return new Ac_UnstackSt();
		}
		
	};
	
	public static Ac_UnstackSt get() {
		Ac_UnstackSt result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_UnstackSt effector) {
		effectorPool.back(effector);
	}

}
