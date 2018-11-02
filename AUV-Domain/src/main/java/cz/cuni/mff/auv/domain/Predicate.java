package cz.cuni.mff.auv.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.auv.domain.predicates.P_ActTurn;
import cz.cuni.mff.auv.domain.predicates.P_At;
import cz.cuni.mff.auv.domain.predicates.P_AtRes;
import cz.cuni.mff.auv.domain.predicates.P_Connected;
import cz.cuni.mff.auv.domain.predicates.P_ConnectedShip;
import cz.cuni.mff.auv.domain.predicates.P_DupFree;
import cz.cuni.mff.auv.domain.predicates.P_Entry;
import cz.cuni.mff.auv.domain.predicates.P_Exit;
import cz.cuni.mff.auv.domain.predicates.P_Free;
import cz.cuni.mff.auv.domain.predicates.P_Operational;
import cz.cuni.mff.auv.domain.predicates.P_Outside;
import cz.cuni.mff.auv.domain.predicates.P_Sampled;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public abstract class Predicate extends PDDLPredicate {

	public static final Predicate[] ALL = new Predicate[] {
			new P_ActTurn(), new P_At(), new P_AtRes(), new P_Connected(), 
			new P_ConnectedShip(), new P_DupFree(), new P_Entry(),
			new P_Exit(), new P_Free(), new P_Operational(), new P_Outside(),
			new P_Sampled()	
	};
	
	public static final Map<String, Predicate> BY_NAME;
	
	static {
		BY_NAME = new HashMap<String, Predicate>();
		for (Predicate effector : ALL) {
			BY_NAME.put(effector.getName(), effector);
		}
	}
	
	public static Predicate toPredicate(PDDLStringInstance se) {
		Predicate proto = BY_NAME.get(se.name);
		Predicate result = (Predicate)proto.create();
		result.assign(se.args.toArray(new String[0]));
		return result;		
	}
	
	public static Predicate[] toPredicates(PDDLStringInstance[] ses) {
		if (ses == null) return null;
		Predicate[] result = new Predicate[ses.length];
		for (int i = 0; i < ses.length; ++i) {
			result[i] = toPredicate(ses[i]);
		}
		return result;
	}
	
	@Override
	public abstract Predicate create();
	
}
