package cz.cuni.mff.auv.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.auv.domain.actions.Ac_Move;
import cz.cuni.mff.auv.domain.actions.Ac_Sample;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public abstract class Action extends Effector {
	
	public static final Ac_Move Move = new Ac_Move();
	public static final Ac_Sample Sample = new Ac_Sample();
	
	public static final Action[] ALL = new Action[] { Move, Sample };
	
	public static final Map<String, Action> BY_NAME;
	
	static {
		BY_NAME = new HashMap<String, Action>();
		for (Action Action : ALL) {
			BY_NAME.put(Action.getName(), Action);
		}
	}
	
	public static Action toAction(PDDLStringInstance se) {
		Action proto = BY_NAME.get(se.name);
		Action result = (Action)proto.create();
		result.assign(se.args.toArray(new String[0]));
		return result;		
	}
	
	public static Action[] toActions(PDDLStringInstance[] ses) {
		if (ses == null) return null;
		Action[] result = new Action[ses.length];
		for (int i = 0; i < ses.length; ++i) {
			result[i] = toAction(ses[i]);
		}
		return result;
	}
	
}
