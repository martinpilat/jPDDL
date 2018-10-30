package cz.cuni.mff.auv.domain;

import cz.cuni.mff.auv.domain.actions.Ac_Move;
import cz.cuni.mff.auv.domain.actions.Ac_Sample;
import cz.cuni.mff.auv.domain.events.Ev_EnterShipAuv;
import cz.cuni.mff.auv.domain.events.Ev_EnterShipFree;
import cz.cuni.mff.auv.domain.events.Ev_LeaveShip;
import cz.cuni.mff.auv.domain.events.Ev_MoveShipAuv;
import cz.cuni.mff.auv.domain.events.Ev_MoveShipFree;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLState;

public abstract class Effector extends PDDLEffector {
		
	public static Effector[] ALL = new Effector[] {
		new Ac_Move(), new Ac_Sample(), new Ev_EnterShipAuv(), new Ev_EnterShipFree(), new Ev_LeaveShip(), new Ev_MoveShipAuv(), new Ev_MoveShipFree()	
	};
	
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
