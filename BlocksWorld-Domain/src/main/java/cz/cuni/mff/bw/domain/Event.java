package cz.cuni.mff.bw.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.bw.domain.events.Ev_Slip;
import cz.cuni.mff.bw.domain.events.Ev_Stick;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public abstract class Event extends Effector {
	
	public static final Ev_Slip Slip = new Ev_Slip();
	public static final Ev_Stick Stick = new Ev_Stick();
	
	public static final Event[] ALL = new Event[] { Slip, Stick };
	
	public static final Map<String, Event> BY_NAME;
	
	static {
		BY_NAME = new HashMap<String, Event>();
		for (Event Event : ALL) {
			BY_NAME.put(Event.getName(), Event);
		}
	}
	
	public static Event toEvent(PDDLStringInstance se) {
		Event proto = BY_NAME.get(se.name);
		if (proto == null) return null;
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
