package cz.cuni.mff.perestroika.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;
import cz.cuni.mff.perestroika.domain.predicates.P_Accessible;
import cz.cuni.mff.perestroika.domain.predicates.P_Accessible.Storage_P_Accessible;
import cz.cuni.mff.perestroika.domain.predicates.P_ActRound;
import cz.cuni.mff.perestroika.domain.predicates.P_ActRound.Storage_P_ActRound;
import cz.cuni.mff.perestroika.domain.predicates.P_Alive;
import cz.cuni.mff.perestroika.domain.predicates.P_Alive.Storage_P_Alive;
import cz.cuni.mff.perestroika.domain.predicates.P_AtAgent;
import cz.cuni.mff.perestroika.domain.predicates.P_AtAgent.Storage_P_AtAgent;
import cz.cuni.mff.perestroika.domain.predicates.P_AtRes;
import cz.cuni.mff.perestroika.domain.predicates.P_AtRes.Storage_P_AtRes;
import cz.cuni.mff.perestroika.domain.predicates.P_Big;
import cz.cuni.mff.perestroika.domain.predicates.P_Big.Storage_P_Big;
import cz.cuni.mff.perestroika.domain.predicates.P_Connected;
import cz.cuni.mff.perestroika.domain.predicates.P_Connected.Storage_P_Connected;
import cz.cuni.mff.perestroika.domain.predicates.P_Dead;
import cz.cuni.mff.perestroika.domain.predicates.P_Dead.Storage_P_Dead;
import cz.cuni.mff.perestroika.domain.predicates.P_Medium;
import cz.cuni.mff.perestroika.domain.predicates.P_Medium.Storage_P_Medium;
import cz.cuni.mff.perestroika.domain.predicates.P_None;
import cz.cuni.mff.perestroika.domain.predicates.P_None.Storage_P_None;
import cz.cuni.mff.perestroika.domain.predicates.P_Small;
import cz.cuni.mff.perestroika.domain.predicates.P_Small.Storage_P_Small;
import cz.cuni.mff.perestroika.domain.predicates.P_Solid;
import cz.cuni.mff.perestroika.domain.predicates.P_Solid.Storage_P_Solid;
import cz.cuni.mff.perestroika.domain.predicates.P_Taken;
import cz.cuni.mff.perestroika.domain.predicates.P_Taken.Storage_P_Taken;
import gnu.trove.procedure.TIntProcedure;

public class State extends PDDLState {
	
	public Storage_P_Accessible p_Accessible;
	public Storage_P_ActRound p_ActRound;
	public Storage_P_Alive p_Alive;
	public Storage_P_AtAgent p_AtAgent;
	public Storage_P_AtRes p_AtRes;
	public Storage_P_Big p_Big;
	public Storage_P_Connected p_Connected;
	public Storage_P_Dead p_Dead;
	public Storage_P_Medium p_Medium;
	public Storage_P_None p_None;
	public Storage_P_Small p_Small;
	public Storage_P_Solid p_Solid;
	public Storage_P_Taken p_Taken;	
	
	public StateCompact compact;
	
	private Map<Class, IStorage> predicate2storage;
	
	public State() {
		this(true);
	}
	
	protected State(boolean init) {
		if (init) {
			compact = new StateCompact();
			p_Accessible = new Storage_P_Accessible();
			p_Accessible.compact = compact;
			p_ActRound = new Storage_P_ActRound();
			p_Alive = new Storage_P_Alive();
			p_Alive.compact = compact;
			p_AtAgent = new Storage_P_AtAgent();
			p_AtAgent.compact = compact;
			p_AtRes = new Storage_P_AtRes();
			p_AtRes.compact = compact;
			p_Big = new Storage_P_Big();
			p_Big.compact = compact;
			p_Connected = new Storage_P_Connected();
			p_Dead = new Storage_P_Dead();
			p_Dead.compact = compact;
			p_Medium = new Storage_P_Medium();
			p_Medium.compact = compact;
			p_None = new Storage_P_None();
			p_None.compact = compact;
			p_Small = new Storage_P_Small();
			p_Small.compact = compact;
			p_Solid = new Storage_P_Solid();
			p_Solid.compact = compact;
			p_Taken = new Storage_P_Taken();
			p_Taken.compact = compact;
		}
	}
	
	public IStorage[] getStorages() {
		return new IStorage[] {
				p_Accessible, p_ActRound, p_Alive, p_AtAgent, p_AtRes, p_Big,
				p_Connected, p_Dead, p_Medium,
				p_None, p_Small, p_Solid, p_Taken
		};
	}
	
	public Map<Class, IStorage> getPredicate2StorageMap() {
		if (predicate2storage != null) return predicate2storage;
		
		predicate2storage = new HashMap<Class, IStorage>();
		
		predicate2storage.put(P_Accessible.class, p_Accessible);
		predicate2storage.put(P_ActRound.class, p_ActRound);
		predicate2storage.put(P_Alive.class, p_Alive);
		predicate2storage.put(P_AtAgent.class, p_AtAgent);
		predicate2storage.put(P_AtRes.class, p_AtRes);
		predicate2storage.put(P_Big.class, p_Big);
		predicate2storage.put(P_Connected.class, p_Connected);
		predicate2storage.put(P_Dead.class, p_Dead);
		predicate2storage.put(P_Medium.class, p_Medium);
		predicate2storage.put(P_None.class, p_None);
		predicate2storage.put(P_Small.class, p_Small);
		predicate2storage.put(P_Solid.class, p_Solid);
		predicate2storage.put(P_Taken.class, p_Taken);
		
		return predicate2storage;
	}

	
	@Override
	public State clone() {
		State result = new State(false);
		result.compact = compact.clone();
		result.p_Accessible = p_Accessible.clone();
		result.p_Accessible.compact = result.compact;
		result.p_ActRound = p_ActRound.clone();
		result.p_Alive = p_Alive.clone();
		result.p_Alive.compact = result.compact;
		result.p_AtAgent = p_AtAgent.clone();
		result.p_AtAgent.compact = result.compact;
		result.p_AtRes = p_AtRes.clone();
		result.p_AtRes.compact = result.compact;
		result.p_Big = p_Big.clone();
		result.p_Big.compact = result.compact;
		result.p_Connected = p_Connected;
		result.p_Dead = p_Dead.clone();
		result.p_Dead.compact = result.compact;
		result.p_Medium = p_Medium.clone();
		result.p_Medium.compact = result.compact;
		result.p_None = p_None.clone();
		result.p_None.compact = result.compact;
		result.p_Small = p_Small.clone();
		result.p_Small.compact = result.compact;
		result.p_Solid = p_Solid.clone();
		result.p_Solid.compact = result.compact;
		result.p_Taken = p_Taken.clone();
		result.p_Taken.compact = result.compact;
		return result;
	}
	
	@Override
	public boolean isSet(PDDLPredicate predicate) {
		IStorage storage = getPredicate2StorageMap().get(predicate.getClass());
		if (storage == null) return false;
		return storage.isSet(predicate);
	}

	@Override
	public void dump(boolean includeStatic, boolean includeEmpty, int maxLineLength) {
		dumpStorage(p_Accessible, includeEmpty, maxLineLength);
		if (includeStatic) dumpStorage(p_ActRound, includeEmpty, maxLineLength);
		dumpStorage(p_Alive, includeEmpty, maxLineLength);
		dumpStorage(p_AtAgent, includeEmpty, maxLineLength);
		dumpStorage(p_AtRes, includeEmpty, maxLineLength);
		dumpStorage(p_Big, includeEmpty, maxLineLength);
		if (includeStatic) dumpStorage(p_Connected, includeEmpty, maxLineLength);
		dumpStorage(p_Dead, includeEmpty, maxLineLength);
		dumpStorage(p_Medium, includeEmpty, maxLineLength);
		dumpStorage(p_None, includeEmpty, maxLineLength);
		dumpStorage(p_Small, includeEmpty, maxLineLength);
		dumpStorage(p_Solid, includeEmpty, maxLineLength);
		dumpStorage(p_Taken, includeEmpty, maxLineLength);					
	}
	
	public void dumpDiff(State diffFrom) {
		PDDLState.dumpDiff(
			getStorages(),
			diffFrom.getStorages(),
			80
		);
	}
	
	// =====================
	// SETTING COMPACT STATE
	// =====================
	
	@Override
	public StateCompact getDynamic() {
		return compact;
	}
	
	@Override
	public void setDynamic(StateCompact compact) {
		// CLEAR DYNAMIC PART OF THE STATE
		p_Accessible.clearAll();
		p_Alive.clearAll();
		p_AtAgent.clearAll();
		p_AtRes.clearAll();
		p_Big.clearAll();		
		p_Dead.clearAll();
		p_Medium.clearAll();
		p_None.clearAll();
		p_Small.clearAll();
		p_Solid.clearAll();
		p_Taken.clearAll();
		this.compact.reset();
		
		// ADD DYNAMIC STATE FROM compact
		compact.forEach(forEachPredicateAdd); // this automatically affects this.compact as well!
	}
	
	
	private TIntProcedure forEachPredicateAdd = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			int predicateType = predicate & Predicate.MASK_TYPE;
			predicateConvertors[predicateType].execute(predicate);
			return true;
		}
		
	};

	private TIntProcedure addAccessible = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Accessible.set(P_Accessible.fromInt_l(predicate));
			return true;
		}
		
	};

	private TIntProcedure addAlive = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Alive.set();
			return true;
		}
		
	};
	
	
	private TIntProcedure addAtAgent = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_AtAgent.set(P_AtAgent.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addAtRes = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_AtRes.set(P_AtRes.fromInt_r(predicate), P_AtAgent.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addBig = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Big.set(P_Big.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addDead = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Dead.set();
			return true;
		}
		
	};
	
	private TIntProcedure addMedium = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Medium.set(P_Medium.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addNone = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_None.set(P_None.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addSmall = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Small.set(P_Small.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addSolid = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Solid.set(P_Solid.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addTaken = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Taken.set(P_Taken.fromInt_r(predicate));
			return true;
		}
		
	};
		
	private TIntProcedure[] predicateConvertors = new TIntProcedure[] {
			null,
			addAccessible,
			null,
			addAlive,
			addAtAgent,
			addAtRes,
			addBig,
			null,
			addDead,
			null,
			addMedium,
			addNone,
			addSmall,
			addSolid,
			addTaken			
	};	
	
}
