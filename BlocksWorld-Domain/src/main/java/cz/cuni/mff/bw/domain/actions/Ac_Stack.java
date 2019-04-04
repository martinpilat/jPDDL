package cz.cuni.mff.bw.domain.actions;

import cz.cuni.mff.bw.domain.Action;
import cz.cuni.mff.bw.domain.State;
import cz.cuni.mff.bw.domain.predicates.P_Clear;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_Holding.Map_T_Block_2;
import cz.cuni.mff.bw.domain.predicates.P_OnTable;
import cz.cuni.mff.bw.domain.types.T_Block;
import cz.cuni.mff.bw.domain.types.T_Hand;
import cz.cuni.mff.bw.problem.E_Block;
import cz.cuni.mff.bw.problem.E_Hand;
import cz.cuni.mff.bw.problem.E_Slippery;
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
 * :action stack
 * :parameters (?x - block ?y - block ?h - hand)
 */
public final class Ac_Stack extends Action {
	
	public T_Block x;
	public T_Block y;
	public T_Hand h;	
	
	public boolean[] applied = new boolean[] { false, false, false, false, false };
	
	public Ac_Stack() {		
	}
	
	public Ac_Stack(Ac_Stack source) {
		source.rewrite(this);
	}
	
	public Ac_Stack(T_Block x, T_Block y, T_Hand h) {
		super();
		this.x = x;
		this.y = y;
		this.h = h;		
	}

	@Override
	public String toString() {
		return "Ac_Stack[x - block = " + x + ", y - block = " + y + ", h - hand = " + h + "]";
	}
	
	@Override
	public String getName() {
		return "stack";		
	}
	
	@Override
	public String toEffector() {
		return "(stack " + x.name + " " + y.name + " " + h.name + ")";
	}
	
	@Override
	public Ac_Stack create() {
		return get();
	}
	
	public Ac_Stack clone() {
		Ac_Stack result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_Stack)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_Stack effector) {
		effector.x = x;
		effector.y = y;
		effector.h = h;		
	}
	
	@Override
	public void assign(String[] args) {
		x = E_Block.THIS.getElement(args[0]);
		y = E_Block.THIS.getElement(args[1]);
		h = E_Hand.THIS.getElement(args[2]);
		if (h == null) h = E_Sticky.THIS.getElement(args[2]);
		if (h == null) h = E_Slippery.THIS.getElement(args[2]);		
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
	 * :precondition (and (holding ?h ?x) 
	 *                    (clear ?y))
     * 
	 * @param state
	 * @return
	 */
	@Override
	public boolean isApplicable(State state) {
		return state.p_Holding.isSet(h, x) && state.p_Clear.isSet(y);		  
	}
	
	@Override
	public boolean isApplicable(State state, State minusState) {
		return    state.p_Holding.isSet(h, x) && !minusState.p_Holding.isSet(h, x)
			   && state.p_Clear.isSet(y)      && !minusState.p_Clear.isSet(y);			   
	}
	
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;		
		for (State state : states) {
			if (applicable = state.p_Holding.isSet(h, x)) break;
		}
		if (!applicable) return false;
		
		applicable = false;		
		for (State state : states) {
			if (applicable = state.p_Clear.isSet(y)) break;
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
	 *              (not (clear ?y))
	 *              (clear ?x)
	 *              (handempty ?h)
	 *              (on ?x ?y))
     *  )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_Holding.clear(h, x);
		applied[1] = state.p_Clear.clear(y);
		applied[2] = state.p_Clear.set(x);
		applied[3] = state.p_HandEmpty.set(h);
		applied[4] = state.p_On.set(x, y);
	}
	
	@Override
	public void reverse(State state) {
		if (applied[0]) state.p_Holding.set(h, x);
		if (applied[1]) state.p_Clear.set(y);
		if (applied[2]) state.p_Clear.clear(x);
		if (applied[3]) state.p_HandEmpty.clear(h);
		if (applied[4]) state.p_On.clear(x, y);
	}
	
	@Override
	public void addAdds(StateCompact compact) {
		compact.set(P_Clear.toInt(x));
		compact.set(P_HandEmpty.toInt(h));
	}
	
	@Override
	public void removeAdds(StateCompact compact) {
		compact.clear(P_Clear.toInt(x));
		compact.clear(P_HandEmpty.toInt(h));
	}
	
	@Override
	public void addDeletes(StateCompact compact) {
		compact.set(P_Holding.toInt(h, x));
		compact.set(P_Clear.toInt(y));
	}
	
	@Override
	public void removeDeletes(StateCompact compact) {
		compact.clear(P_Holding.toInt(h, x));
		compact.clear(P_Clear.toInt(y));
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ac_Stack)effector, callback);
	}
	
	public void unify(State state, Ac_Stack effector, IPDDLUnification<Ac_Stack> callback) {
		callback.start();
		
		Unify_Ac_Stack unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static Pool<Unify_Ac_Stack> pool = new Pool<Unify_Ac_Stack>(1) {

		@Override
		protected Unify_Ac_Stack create() {
			return new Unify_Ac_Stack();
		}
		
	};
	
	private static final class Unify_Ac_Stack {
	
		State state;
		Ac_Stack effector;
		IPDDLUnification<Ac_Stack> callback;
		
		public void unify() {
			unify_Holding_1_h();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_Holding_1_h_Proc extends FastIntMap.ForEachEntry<P_Holding.Map_T_Block_2> {
	
			@Override
			public boolean entry(int key, P_Holding.Map_T_Block_2 data) {
				effector.h = E_Hand.THIS.getElement(key);
				unify_Holding_1_x(data);
				return true;
			}
			
		}
		
		private Unify_Holding_1_h_Proc unify_holding_1_h_proc = new Unify_Holding_1_h_Proc();
		
		private class Unify_Holding_1_x_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.x = E_Block.THIS.getElement(key);
				unify_Clear_2_y();
				return true;
			}
			
		}
		
		private Unify_Holding_1_x_Proc unify_holding_1_x_proc = new Unify_Holding_1_x_Proc();	
		
		private class Unify_Clear_2_y_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.y = E_Block.THIS.getElement(key);
				unified();
				return true;
			}
			
		}
		
		private Unify_Clear_2_y_Proc unify_clear_2_y_proc = new Unify_Clear_2_y_Proc();
		
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
		
		private void unify_Clear_2_y() {
			if (effector.h == null) {
				state.p_Clear.internal().forEachEntry(unify_clear_2_y_proc);
				effector.y = null;
			} else {			
				P_Holding.Map_T_Block_2 map = state.p_Holding.internal().get(effector.h);
				if (map == null) return;
				unify_Holding_1_x(map);
			}
		}
				
		private void unified() {
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ac_Stack> effectorPool = new Pool<Ac_Stack>() {

		@Override
		protected Ac_Stack create() {
			return new Ac_Stack();
		}
		
	};
	
	public static Ac_Stack get() {
		Ac_Stack result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_Stack effector) {
		effectorPool.back(effector);
	}

}
