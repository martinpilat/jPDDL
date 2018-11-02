package cz.cuni.mff.auv.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.auv.domain.events.Ev_EnterShipAuv;
import cz.cuni.mff.auv.domain.events.Ev_EnterShipFree;
import cz.cuni.mff.auv.domain.events.Ev_LeaveShip;
import cz.cuni.mff.auv.domain.events.Ev_MoveShipAuv;
import cz.cuni.mff.auv.domain.events.Ev_MoveShipFree;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public abstract class Event extends Effector {
	
	public static final Ev_EnterShipAuv EnterShipAuv = new Ev_EnterShipAuv();
	public static final Ev_EnterShipFree EnterShipFree = new Ev_EnterShipFree();
	public static final Ev_LeaveShip LeaveShip = new Ev_LeaveShip();
	public static final Ev_MoveShipAuv MoveShipAuv = new Ev_MoveShipAuv();
	public static final Ev_MoveShipFree MoveShipFree = new Ev_MoveShipFree();
	
	public static final Event[] ALL = new Event[] { EnterShipAuv, EnterShipFree, LeaveShip, MoveShipAuv, MoveShipFree };
	
	public static final Map<String, Event> BY_NAME;
	
	static {
		BY_NAME = new HashMap<String, Event>();
		for (Event Event : ALL) {
			BY_NAME.put(Event.getName(), Event);
		}
	}
	
	public static Event toEvent(PDDLStringInstance se) {
		Event proto = BY_NAME.get(se.name);
		Event result = (Event)proto.create();
		result.assign(se.args.toArray(new String[0]));
		return result;		
	}
	
	public static Event[] toEvents(PDDLStringInstance[] ses) {
		if (ses == null) return null;
		Event[] result = new Event[ses.length];
		for (int i = 0; i < ses.length; ++i) {
			result[i] = toEvent(ses[i]);
		}
		return result;
	}
	
}
