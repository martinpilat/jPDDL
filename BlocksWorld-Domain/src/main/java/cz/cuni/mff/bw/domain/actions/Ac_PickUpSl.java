package cz.cuni.mff.bw.domain.actions;

import cz.cuni.mff.bw.domain.Action;
import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.bw.domain.predicates.P_Clear;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_Movable;
import cz.cuni.mff.bw.domain.predicates.P_OnTable;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.domain.types.T_Slippery;
import cz.cuni.mff.bw.problem.E_Block;
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
 * :action pick-up-sl                     
 * :parameters (?x - block ?h - slippery)
 */
public final class Ac_PickUpSl extends Action {
	
	public T_Block x;
	public T_Slippery h;	
	
	public boolean[] applied = new boolean[] { false, false, false, false, false };
	
	public Ac_PickUpSl() {
	}
	
	public Ac_PickUpSl(Ac_PickUpSl source) {
		source.rewrite(this);
	}
	
	public Ac_PickUpSl(T_Block x, T_Slippery h) {
		super();
		this.x = x;
		this.h = h;		
	}

	@Override
	public String toString() {
		return "Ac_PickUpSl[x - block = " + x + ", h - slippery = " + h + "]";
	}
	
	@Override
	public String getName() {
		return "pick-up-sl";		
	}
	
	@Override
	public String toEffector() {
		return "(pick-up-sl " + x.name + " " + h.name + ")";
	}
	
	@Override
	public Ac_PickUpSl create() {
		return get();
	}
	
	@Override
	public Ac_PickUpSl clone() {
		Ac_PickUpSl result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_PickUpSl)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_PickUpSl effector) {
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
	 * :precondition (and (clear ?x) 
	 *                    (ontable ?x) 
	 *                    (handempty ?h))
     *               )
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Clear.isSet(x) 
			   && state.p_OnTable.isSet(x) 
			   && state.p_HandEmpty.isSet(h); 
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return    state.p_Clear.isSet(x)     && !minusState.p_Clear.isSet(x) 
			   && state.p_OnTable.isSet(x)   && !minusState.p_OnTable.isSet(x) 
			   && state.p_HandEmpty.isSet(h) && !minusState.p_HandEmpty.isSet(h); 
	}
		
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Clear.isSet(x)) break;
		}
		if (!applicable) return false;	
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_OnTable.isSet(x) ) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_HandEmpty.isSet(h)) break;
		}
		if (!applicable) return false;
		
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect
	 *       (and (movable ?x)
     *            (not (ontable ?x))
	 *            (not (clear ?x))
	 *            (not (handempty ?h))
	 *            (holding ?h ?x))
     *       
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Movable.set(x);
		applied[1] = state.p_OnTable.clear(x);
		applied[2] = state.p_Clear.clear(x);
		applied[3] = state.p_HandEmpty.clear(h);
		applied[4] = state.p_Holding.set(h, x);
	}
	
	public void reverse(State state) {
		if (applied[0]) state.p_Movable.clear(x);
		if (applied[1]) state.p_OnTable.set(x);
		if (applied[2]) state.p_Clear.set(x);
		if (applied[3]) state.p_HandEmpty.set(h);
		if (applied[4]) state.p_Holding.clear(h, x);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_Movable.toInt(x));
		compact.set(P_Holding.toInt(h, x));
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_Movable.toInt(x));
		compact.clear(P_Holding.toInt(h, x));
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
		compact.set(P_OnTable.toInt(x));
		compact.set(P_Clear.toInt(x));
		compact.set(P_HandEmpty.toInt(h));
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
		compact.clear(P_OnTable.toInt(x));
		compact.clear(P_Clear.toInt(x));
		compact.clear(P_HandEmpty.toInt(h));
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ac_PickUpSl)effector, callback);
	}
	
	public void unify(State state, Ac_PickUpSl effector, IPDDLUnification<Ac_PickUpSl> callback) {
		callback.start();
		
		Unify_Ac_PickUpSl unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ac_PickUpSl> pool = new Pool<Unify_Ac_PickUpSl>(1) {

		@Override
		protected Unify_Ac_PickUpSl create() {
			return new Unify_Ac_PickUpSl();
		}
		
	};
	
	private static final class Unify_Ac_PickUpSl {
	
		State state;
		Ac_PickUpSl effector;
		IPDDLUnification<Ac_PickUpSl> callback;
		
		public void unify() {
			unify_Clear_1_x();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Clear_1_x_Proc extends FastIntMap.ForEachEntry<Boolean> {
	
			@Override
			public boolean entry(int key, Boolean data) {
				effector.x = E_Block.THIS.getElement(key);
				unify_OnTable_2_x();
				return true;
			}
			
		}
		
		private Unify_Clear_1_x_Proc unify_clear_1_x_proc = new Unify_Clear_1_x_Proc();
		
		private class Unify_HandEmpty_3_h_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.h = E_Slippery.THIS.getElement(key);
				unified();
				return true;
			}
			
		}
		
		private Unify_HandEmpty_3_h_Proc unify_handempty_3_h_proc = new Unify_HandEmpty_3_h_Proc();
				
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Clear_1_x() {
			if (effector.x == null) {
				state.p_Clear.internal().forEachEntry(unify_clear_1_x_proc);
				effector.x = null;
			} else {
				if (!state.p_Clear.internal().containsKey(effector.x)) return;
				unify_OnTable_2_x();
			}
		}
	
		private void unify_OnTable_2_x() {
			if (!state.p_OnTable.internal().containsKey(effector.x)) return;
			unify_HandEmpty_3_h();			
		}
		
		private void unify_HandEmpty_3_h() {
			if (effector.h == null) {
				state.p_HandEmpty.internal().forEachEntry(unify_handempty_3_h_proc);
				effector.h = null;
			} else {
				if (!state.p_HandEmpty.internal().containsKey(effector.h)) return;
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
	
	private static final Pool<Ac_PickUpSl> effectorPool = new Pool<Ac_PickUpSl>() {

		@Override
		protected Ac_PickUpSl create() {
			return new Ac_PickUpSl();
		}
		
	};
	
	public static Ac_PickUpSl get() {
		Ac_PickUpSl result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_PickUpSl effector) {
		effectorPool.back(effector);
	}


}
