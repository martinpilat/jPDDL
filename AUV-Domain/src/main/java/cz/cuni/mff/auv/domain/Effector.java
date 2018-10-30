package cz.cuni.mff.auv.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.auv.domain.actions.Ac_Move;
import cz.cuni.mff.auv.domain.actions.Ac_Sample;
import cz.cuni.mff.auv.domain.events.Ev_EnterShipAuv;
import cz.cuni.mff.auv.domain.events.Ev_EnterShipFree;
import cz.cuni.mff.auv.domain.events.Ev_LeaveShip;
import cz.cuni.mff.auv.domain.events.Ev_MoveShipAuv;
import cz.cuni.mff.auv.domain.events.Ev_MoveShipFree;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.PDDLStringEffector;

public abstract class Effector extends PDDLEffector {
		
	public static final Effector[] ALL = new Effector[] {
		new Ac_Move(), new Ac_Sample(), new Ev_EnterShipAuv(), new Ev_EnterShipFree(), new Ev_LeaveShip(), new Ev_MoveShipAuv(), new Ev_MoveShipFree()	
	};
	
	public static final Map<String, Effector> BY_NAME;
	
	static {
		BY_NAME = new HashMap<String, Effector>();
		for (Effector effector : ALL) {
			BY_NAME.put(effector.getName(), effector);
		}
	}
	
	public static Effector toEffector(PDDLStringEffector se) {
		Effector proto = BY_NAME.get(se.name);
		Effector result = (Effector)proto.create();
		result.assign(se.args.toArray(new String[0]));
		return result;		
	}
	
	public static Effector[] toEffectors(PDDLStringEffector[] ses) {
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
	
	/**
	 * Is action applicable in the given 'state'?
	 * @param state
	 * @return
	 */
	public abstract boolean isApplicable(State state);
	
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
