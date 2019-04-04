package cz.cuni.mff.bw.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.bw.domain.predicates.P_Clear;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_Movable;
import cz.cuni.mff.bw.domain.predicates.P_On;
import cz.cuni.mff.bw.domain.predicates.P_OnTable;
import cz.cuni.mff.bw.domain.predicates.P_Sticky;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLStringInstance;

public abstract class Predicate extends PDDLPredicate {
	
	public static final int MASK_TYPE = 1 + 2 + 4;
	
	public static final int MASK_TYPE_BIT_COUNT = 3;

	public static final Predicate[] ALL = new Predicate[] {
			new P_Clear(), new P_HandEmpty(), new P_Holding(),
			new P_Movable(), new P_On(), new P_OnTable(),
			new P_Sticky()	
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
