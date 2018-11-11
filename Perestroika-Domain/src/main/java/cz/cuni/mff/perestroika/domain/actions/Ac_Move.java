package cz.cuni.mff.perestroika.domain.actions;


import cz.cuni.mff.jpddl.IPDDLUnification;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.Pool;
import cz.cuni.mff.perestroika.domain.Action;
import cz.cuni.mff.perestroika.domain.State;
import cz.cuni.mff.perestroika.domain.predicates.P_Connected;
import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.problem.E_Location;

/**
 * ACTION
 * 
 * :action move
 * :parameters (?l1 ?l2 - location)
 */
public final class Ac_Move extends Action {
	
	public T_Location l1;
	public T_Location l2;
	
	public boolean[] applied = new boolean[] { false, false };
	
	public Ac_Move() {		
	}
	
	public Ac_Move(Ac_Move source) {
		source.rewrite(this);
	}
	
	public Ac_Move(T_Location l1, T_Location l2) {
		super();
		this.l1 = l1;
		this.l2 = l2;
	}

	@Override
	public String toString() {
		return "Ac_Move[l1 - location = " + l1 + ", l2 - location = " + l2 + "]";
	}
	
	@Override
	public String getName() {
		return "move";		
	}
	
	@Override
	public String toEffector() {
		return "(move " + l1.name + " " + l2.name + ")";
	}
	
	@Override
	public Ac_Move create() {
		return get();
	}
	
	@Override
	public Ac_Move clone() {
		Ac_Move result = effectorPool.get();
		rewrite(result);
		return result;
	}
	
	@Override
	public void recycle() {
		effectorPool.back(this);
	}
	
	@Override
	public void rewrite(PDDLEffector assignInto) {
		rewrite((Ac_Move)assignInto);
	}	
	
	/**
	 * Rewrite arguments of this effector into 'effector'.
	 * @param effector
	 */
	public void rewrite(Ac_Move effector) {
		effector.l1 = l1;
		effector.l2 = l2;
	}
	
	@Override
	public void assign(String[] args) {
		l1 = E_Location.THIS.getElement(args[0]);
		l2 = E_Location.THIS.getElement(args[1]);
	}
	
	@Override
	public void reset() {
		l1 = null;
		l2 = null;
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * 
	 * PRECONDITION
	 * 
	 * :precondition (and (alive)
	 *                    (at-agent ?l1)
     *                    (connected ?l1 ?l2)
     *                    (accessible ?l2)
     *                )
     * 
	 * @param state
	 * @return
	 */	
	@Override
	public boolean isApplicable(State state) {
		return    state.p_Alive.isSet() 
			   && state.p_AtAgent.isSet(l1) 
			   && state.p_Connected.isSet(l1, l2) 
			   && state.p_Accessible.isSet(l2); 
	}
	
	@Override
	public boolean isApplicableUnion(State... states) {
		boolean applicable;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Alive.isSet()) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_AtAgent.isSet(l1)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Connected.isSet(l1, l2)) break;
		}
		if (!applicable) return false;
		
		applicable = false;
		for (State state : states) {
			if (applicable = state.p_Accessible.isSet(l2)) break;
		}
		if (!applicable) return false;		
		
		return true;
	}	
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * 
	 * EFFECT
	 * 
	 * :effect (and (not (at-agent ?l1))
     *              (at-agent ?l2)
     *  )
	 * @param state
	 */
	@Override
	public void apply(State state) {
		applied[0] = state.p_AtAgent.clear(l1);
		applied[1] = state.p_AtAgent.set(l2);
	}
	
	@Override
	public void reverse(State state) {
		if (applied[0]) state.p_AtAgent.set(l1);
		if (applied[1]) state.p_AtAgent.clear(l2);
	}
	
	// ===================================================
	// ENUMERATION OF APPLICABLE EFFECTORS VIA UNIFICATION
	// ===================================================
	
	@Override
	public void unify(PDDLState state, PDDLEffector effector, IPDDLUnification callback) {
		unify((State)state, (Ac_Move)effector, callback);
	}
	
	public void unify(State state, Ac_Move effector, IPDDLUnification<Ac_Move> callback) {
		callback.start();
		
		Unify_Ac_Move unify = unifyPool.get();
		unify.state = state;
		unify.effector = effector;
		unify.callback = callback;
		
		unify.unify();
		
		unifyPool.back(unify);
		
		callback.end();
	}
	
	private static final Pool<Unify_Ac_Move> unifyPool = new Pool<Unify_Ac_Move>(1) {

		@Override
		protected Unify_Ac_Move create() {
			return new Unify_Ac_Move();
		}
		
	};
	
	private static final class Unify_Ac_Move {
	
		State state;
		Ac_Move effector;
		IPDDLUnification<Ac_Move> callback;
		
		public void unify() {
			unify_Alive_1();
		}
		
		// ===========================	
		// ELEMENT STEPPING PROCEDURES
		// ===========================
		
		private class Unify_AtAgent_2_l1_Proc extends FastIntMap.ForEachEntry<Boolean> {
			
			@Override
			public boolean entry(int key, Boolean data) {
				effector.l1 = E_Location.THIS.getElement(key);
				unify_Connected_3_l1();
				return true;
			}
			
		}
		
		private Unify_AtAgent_2_l1_Proc unify_atagent_2_l1_proc = new Unify_AtAgent_2_l1_Proc();
		
		private class Unify_Connected_3_l2_Proc extends FastIntMap.ForEachEntry<Boolean> {

			@Override
			public boolean entry(int key, Boolean data) {
				effector.l2 = E_Location.THIS.getElement(key);
				unify_Accessible_4_l2();
				return true;
			}
			
		}
		
		private Unify_Connected_3_l2_Proc unify_connected_3_l2_proc = new Unify_Connected_3_l2_Proc();
		
		// ===========================
		// UNIFICATION METHODS
		// ===========================		
		
		private void unify_Alive_1() {
			if (!state.p_Alive.isSet()) return;
			state.p_AtAgent.internal().forEachEntry(unify_atagent_2_l1_proc);			
		}
	
		private void unify_Connected_3_l1() {
			P_Connected.Map_T_Location_2 map = state.p_Connected.internal().get(effector.l1);
			if (map == null || map.size() == 0) return;
			unify_Connected_3_l2(map);				
		}
		
		private void unify_Connected_3_l2(P_Connected.Map_T_Location_2 map) {
			if (effector.l2 == null) {
				map.forEachEntry(unify_connected_3_l2_proc);
				effector.l2 = null;
			} else {
				if (!map.containsKey(effector.l2)) return;
				unify_Accessible_4_l2();
			}
		}
		
		private void unify_Accessible_4_l2() {
			if (!state.p_Accessible.isSet(effector.l2)) return;
			callback.unified(effector);
		}
	
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<Ac_Move> effectorPool = new Pool<Ac_Move>() {

		@Override
		protected Ac_Move create() {
			return new Ac_Move();
		}
		
	};
	
	public static Ac_Move get() {
		Ac_Move result = effectorPool.get();
		result.reset();
		return result;
	}
	
	public static void back(Ac_Move effector) {
		effectorPool.back(effector);
	}

}
