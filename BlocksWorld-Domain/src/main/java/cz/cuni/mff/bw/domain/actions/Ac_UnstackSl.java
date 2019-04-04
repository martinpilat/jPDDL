package cz.cuni.mff.bw.domain.actions;

import cz.cuni.mff.bw.domain.Action;
import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.bw.domain.predicates.P_Clear;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_Holding.Map_T_Block_2;
import cz.cuni.mff.bw.domain.predicates.P_Movable;
import cz.cuni.mff.bw.domain.predicates.P_On;
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
 * :action unstack-sl
 * :parameters (?x - block ?y - block ?h - slippery)
 */
public final class Ac_UnstackSl extends Action {
	
	public T_Block x;
	public T_Block y;
	public T_Slippery h;	
	
	public boolean[] applied = new boolean[] { false, false, false, false, false, false };
	
	public Ac_UnstackSl() {		
	}
	
	public Ac_UnstackSl(Ac_UnstackSl source) {
		source.rewrite(this);
	}
	
	public Ac_UnstackSl(T_Block x, T_Block y, T_Slippery h) {
		super();
		this.x = x;
		this.y = y;
		this.h = h;		
	}

	@Override
	public String toString() {
		return "Ac_UnstackSl[x - block = " + x + ", y - block = " + y + ", h - hand = " + h + "]";
	}
	
	@Override
	public String getName() {
		return "unstack-sl";		
	}
	
	@Override
	public String toEffector() {
		return "(unstack-sl " + x.name + " " + y.name + " " + h.name + ")";
	}
	
	@Override
	public Ac_UnstackSl create() {
		return get();
	}
	
	public Ac_UnstackSl clone() {
		Ac_UnstackSl result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_UnstackSl)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_UnstackSl effector) {
		effector.x = x;
		effector.y = y;
		effector.h = h;		
	}
	
	@Override
	public void assign(String[] args) {
		x = E_Block.THIS.getElement(args[0]);
		y = E_Block.THIS.getElement(args[1]);
		h = E_Slippery.THIS.getElement(args[2]);
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
	 *              (clear ?y)
	 *   	        (not (clear ?x))
	 *	            (not (handempty ?h))
	 * 	            (not (on ?x ?y)))
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
	}
	
	@Override
	public void reverse(State state) {
		if (applied[0]) state.p_Movable.clear(x);
		if (applied[1]) state.p_Holding.clear(h, x);
		if (applied[2]) state.p_Clear.clear(y);
		if (applied[3]) state.p_Clear.set(x);
		if (applied[4]) state.p_HandEmpty.set(h);
		if (applied[5]) state.p_On.set(x, y);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_Movable.toInt(x));
		compact.set(P_Holding.toInt(h, x));
		compact.set(P_Clear.toInt(y));
		
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_Movable.toInt(x));
		compact.clear(P_Holding.toInt(h, x));
		compact.clear(P_Clear.toInt(y));
		
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
		unify((State)state, (Ac_UnstackSl)effector, callback);
	}
	
	public void unify(State state, Ac_UnstackSl effector, IPDDLUnification<Ac_UnstackSl> callback) {
		callback.start();
		
		Unify_Ac_UnstackSl unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static Pool<Unify_Ac_UnstackSl> pool = new Pool<Unify_Ac_UnstackSl>(1) {

		@Override
		protected Unify_Ac_UnstackSl create() {
			return new Unify_Ac_UnstackSl();
		}
		
	};
	
	private static final class Unify_Ac_UnstackSl {
	
		State state;
		Ac_UnstackSl effector;
		IPDDLUnification<Ac_UnstackSl> callback;
		
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
				if (h instanceof T_Slippery) {
					effector.h = (T_Slippery)h;
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
	
	private static final Pool<Ac_UnstackSl> effectorPool = new Pool<Ac_UnstackSl>() {

		@Override
		protected Ac_UnstackSl create() {
			return new Ac_UnstackSl();
		}
		
	};
	
	public static Ac_UnstackSl get() {
		Ac_UnstackSl result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_UnstackSl effector) {
		effectorPool.back(effector);
	}

}
