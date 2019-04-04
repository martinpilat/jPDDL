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
 * :action put-down
 * :parameters (?x - block ?h - hand)
 */
public final class Ac_PutDown extends Action {
	
	public T_Block x;
	public T_Hand h;	
	
	public boolean[] applied = new boolean[] { false, false, false, false };
	
	public Ac_PutDown() {		
	}
	
	public Ac_PutDown(Ac_PutDown source) {
		source.rewrite(this);
	}
	
	public Ac_PutDown(T_Block x, T_Hand h) {
		super();
		this.x = x;
		this.h = h;		
	}

	@Override
	public String toString() {
		return "Ac_PutDown[x - block = " + x + ", h - hand = " + h + "]";
	}
	
	@Override
	public String getName() {
		return "put-down";		
	}
	
	@Override
	public String toEffector() {
		return "(put-down " + x.name + " " + h.name + ")";
	}
	
	@Override
	public Ac_PutDown create() {
		return get();
	}
	
	public Ac_PutDown clone() {
		Ac_PutDown result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_PutDown)assignInto);
	}
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_PutDown effector) {
		effector.x = x;
		effector.h = h;		
	}
	
	@Override
	public void assign(String[] args) {
		x = E_Block.THIS.getElement(args[0]);
		h = E_Hand.THIS.getElement(args[1]);
		if (h == null) h = E_Sticky.THIS.getElement(args[1]);
		if (h == null) h = E_Slippery.THIS.getElement(args[1]);		
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
	 *              (clear ?x)
	 *              (handempty ?h)
	 *              (ontable ?x))
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
		unify((State)state, (Ac_PutDown)effector, callback);
	}
	
	public void unify(State state, Ac_PutDown effector, IPDDLUnification<Ac_PutDown> callback) {
		callback.start();
		
		Unify_Ac_PutDown unify = pool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		pool.back(unify);
		
		callback.end();
	}
	
	private static Pool<Unify_Ac_PutDown> pool = new Pool<Unify_Ac_PutDown>(1) {

		@Override
		protected Unify_Ac_PutDown create() {
			return new Unify_Ac_PutDown();
		}
		
	};
	
	private static final class Unify_Ac_PutDown {
	
		State state;
		Ac_PutDown effector;
		IPDDLUnification<Ac_PutDown> callback;
		
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
	
	private static final Pool<Ac_PutDown> effectorPool = new Pool<Ac_PutDown>() {

		@Override
		protected Ac_PutDown create() {
			return new Ac_PutDown();
		}
		
	};
	
	public static Ac_PutDown get() {
		Ac_PutDown result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_PutDown effector) {
		effectorPool.back(effector);
	}

}
