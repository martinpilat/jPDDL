package cz.cuni.mff.perestroika.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.perestroika.domain.actions.Ac_Collect;
import cz.cuni.mff.perestroika.domain.actions.Ac_Move;
import cz.cuni.mff.perestroika.domain.events.Ev_Create;
import cz.cuni.mff.perestroika.domain.events.Ev_ShrinkBig;
import cz.cuni.mff.perestroika.domain.events.Ev_ShrinkMedium;
import cz.cuni.mff.perestroika.domain.events.Ev_ShrinkSmallAgent;
import cz.cuni.mff.perestroika.domain.events.Ev_ShrinkSmallEmpty;

public abstract class Effector extends PDDLEffector {
		
	public static final Effector[] ALL = new Effector[] {
		new Ac_Move(), new Ac_Collect(), new Ev_Create(), new Ev_ShrinkBig(), new Ev_ShrinkMedium(), new Ev_ShrinkSmallAgent(), new Ev_ShrinkSmallEmpty()	
	};
	
	public static final Map<String, Effector> BY_NAME;
	
	static {
		BY_NAME = new HashMap<String, Effector>();
		for (Effector effector : ALL) {
			BY_NAME.put(effector.getName(), effector);
		}
	}
	
	public static Effector toEffector(PDDLStringInstance se) {
		if (se.name.equals("move-unsafe")) {
			se.name = "move";
			se.args = se.args.subList(0,2);
		}
		if (se.name.equals("move-safe")) {
			se.name = "move";
		}
		Effector proto = BY_NAME.get(se.name);
		if (proto == null) return null;
		Effector result = (Effector)proto.create();
		result.assign(se.args.toArray(new String[0]));
		return result;		
	}
	
	public static Effector[] toEffectors(PDDLStringInstance[] ses) {
		if (ses == null) return null;
		Effector[] result = new Effector[ses.length];
		for (int i = 0; i < ses.length; ++i) {
			result[i] = toEffector(ses[i]);
		}
		return result;
	}
	
	@Override
	public boolean isApplicable(PDDLState state) {
		return isApplicable((State)state);
	}
	
	@Override
	public boolean isApplicable(PDDLState state, PDDLState minusState) {
		return isApplicable((State)state, (State)minusState);
	}
	
	/**
	 * Is action applicable in the given 'state'?
	 * @param state
	 * @return
	 */
	public abstract boolean isApplicable(State state);
	
	/**
	 * Is action applicable in the: state / minusState ?
	 * @param state
	 * @param minusState
	 * @return
	 */
	public abstract boolean isApplicable(State state, State minusState);
	
	/**
	 * {@link #isApplicable(State)} in all 'states' ?
	 * @param states
	 * @return
	 */
	public boolean isApplicableAll(State... states) {
		for (State state : states) {
			if (!isApplicable(state)) return false;
		}
		return true;
	}
	
	/**
	 * {@link #isApplicable(State)} in the state that is the union of 'states' ?
	 * @param states
	 * @return
	 */
	public abstract boolean isApplicableUnion(State... states);
	
	/**
	 * {@link #isApplicable(State)} in at least one of 'states' ?
	 * @param states
	 * @return
	 */
	public boolean isApplicableAny(State... states) {
		for (State state : states) {
			if (isApplicable(state)) return true;
		}
		return false;
	}
	
	@Override
	public void apply(PDDLState state) {
		apply((State)state);
	}
	
	/**
	 * Apply effects in the given 'state' without checking preconditions.
	 * @param state
	 */
	public abstract void apply(State state);
	
	
	@Override
	public void reverse(PDDLState state) {
		reverse((State)state);
	}
	
	/**
	 * Reverse effects in the given 'state' without any checks assuming effects can be reverted.
	 * @param state
	 */
	public abstract void reverse(State state);
	
}
