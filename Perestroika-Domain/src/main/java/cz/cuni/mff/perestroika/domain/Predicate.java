package cz.cuni.mff.perestroika.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLStringInstance;
import cz.cuni.mff.perestroika.domain.predicates.P_Accessible;
import cz.cuni.mff.perestroika.domain.predicates.P_ActRound;
import cz.cuni.mff.perestroika.domain.predicates.P_Alive;
import cz.cuni.mff.perestroika.domain.predicates.P_AtAgent;
import cz.cuni.mff.perestroika.domain.predicates.P_AtRes;
import cz.cuni.mff.perestroika.domain.predicates.P_Big;
import cz.cuni.mff.perestroika.domain.predicates.P_Connected;
import cz.cuni.mff.perestroika.domain.predicates.P_Dead;
import cz.cuni.mff.perestroika.domain.predicates.P_Medium;
import cz.cuni.mff.perestroika.domain.predicates.P_None;
import cz.cuni.mff.perestroika.domain.predicates.P_Small;
import cz.cuni.mff.perestroika.domain.predicates.P_Solid;
import cz.cuni.mff.perestroika.domain.predicates.P_Taken;

public abstract class Predicate extends PDDLPredicate {
	
	public static final int MASK_TYPE = 1 + 2 + 4 + 8;
	
	public static final int MASK_TYPE_BIT_COUNT = 4;

	public static final Predicate[] ALL = new Predicate[] {
			new P_Accessible(),
			new P_ActRound(), new P_Alive(), new P_AtAgent(), new P_AtRes(), 
			new P_Big(), new P_Connected(), new P_Dead(),
			new P_Medium(), new P_None(), new P_Small(),
			new P_Solid(), new P_Taken()
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
