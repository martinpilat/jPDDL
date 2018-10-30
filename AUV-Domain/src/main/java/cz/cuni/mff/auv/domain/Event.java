package cz.cuni.mff.auv.domain;

import cz.cuni.mff.auv.domain.events.Ev_EnterShipAuv;
import cz.cuni.mff.auv.domain.events.Ev_EnterShipFree;
import cz.cuni.mff.auv.domain.events.Ev_LeaveShip;
import cz.cuni.mff.auv.domain.events.Ev_MoveShipAuv;
import cz.cuni.mff.auv.domain.events.Ev_MoveShipFree;

public abstract class Event extends Effector {
	
	public static final Ev_EnterShipAuv EnterShipAuv = new Ev_EnterShipAuv();
	public static final Ev_EnterShipFree EnterShipFree = new Ev_EnterShipFree();
	public static final Ev_LeaveShip LeaveShip = new Ev_LeaveShip();
	public static final Ev_MoveShipAuv MoveShipAuv = new Ev_MoveShipAuv();
	public static final Ev_MoveShipFree MoveShipFree = new Ev_MoveShipFree();
	
	public static final Event[] ALL = new Event[] { EnterShipAuv, EnterShipFree, LeaveShip, MoveShipAuv, MoveShipFree };
	
}
