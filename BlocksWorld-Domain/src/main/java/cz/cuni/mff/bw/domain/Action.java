package cz.cuni.mff.bw.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.bw.domain.actions.Ac_PickUpSl;
import cz.cuni.mff.bw.domain.actions.Ac_PickUpSt;
import cz.cuni.mff.bw.domain.actions.Ac_PutDown;
import cz.cuni.mff.bw.domain.actions.Ac_Stack;
import cz.cuni.mff.bw.domain.actions.Ac_UnstackSl;
import cz.cuni.mff.bw.domain.actions.Ac_UnstackSt;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public abstract class Action extends Effector {
	
	public static final Ac_PickUpSl  PickUpSl  = new Ac_PickUpSl();
	public static final Ac_PickUpSt  PickUpSt  = new Ac_PickUpSt();
	public static final Ac_PutDown   PutDown   = new Ac_PutDown();
	public static final Ac_Stack     Stack     = new Ac_Stack();
	public static final Ac_UnstackSl UnstackSl = new Ac_UnstackSl();
	public static final Ac_UnstackSt UnstackSt = new Ac_UnstackSt();
	
	public static final Action[] ALL = new Action[] { PickUpSl, PickUpSt, PutDown, Stack, UnstackSl, UnstackSt };
	
	public static final Map<String, Action> BY_NAME;
	
	static {
		BY_NAME = new HashMap<String, Action>();
		for (Action Action : ALL) {
			BY_NAME.put(Action.getName(), Action);
		}
	}
	
	public static Action toAction(PDDLStringInstance se) {
		Action proto = BY_NAME.get(se.name);
		if (proto == null) return null;
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
