package cz.cuni.mff.perestroika.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.perestroika.domain.events.Ev_Create;
import cz.cuni.mff.perestroika.domain.events.Ev_ShrinkBig;
import cz.cuni.mff.perestroika.domain.events.Ev_ShrinkMedium;
import cz.cuni.mff.perestroika.domain.events.Ev_ShrinkSmallAgent;
import cz.cuni.mff.perestroika.domain.events.Ev_ShrinkSmallEmpty;

public abstract class Event extends Effector {
	
	public static final Ev_Create Create = new Ev_Create();
	public static final Ev_ShrinkBig ShrinkBig = new Ev_ShrinkBig();
	public static final Ev_ShrinkMedium ShrinkMedium = new Ev_ShrinkMedium();
	public static final Ev_ShrinkSmallAgent ShrinkSmallAgent = new Ev_ShrinkSmallAgent();
	public static final Ev_ShrinkSmallEmpty ShrinkSmallEmpty = new Ev_ShrinkSmallEmpty();
	
	public static final Event[] ALL = new Event[] { Create, ShrinkBig, ShrinkMedium, ShrinkSmallAgent, ShrinkSmallEmpty };
	
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
