package cz.cuni.mff.auv.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.auv.domain.predicates.P_ActTurn;
import cz.cuni.mff.auv.domain.predicates.P_ActTurn.Storage_P_ActTurn;
import cz.cuni.mff.auv.domain.predicates.P_At;
import cz.cuni.mff.auv.domain.predicates.P_At.Storage_P_At;
import cz.cuni.mff.auv.domain.predicates.P_AtRes;
import cz.cuni.mff.auv.domain.predicates.P_AtRes.Storage_P_AtRes;
import cz.cuni.mff.auv.domain.predicates.P_Connected;
import cz.cuni.mff.auv.domain.predicates.P_Connected.Storage_P_Connected;
import cz.cuni.mff.auv.domain.predicates.P_ConnectedShip;
import cz.cuni.mff.auv.domain.predicates.P_ConnectedShip.Storage_P_ConnectedShip;
import cz.cuni.mff.auv.domain.predicates.P_DupFree;
import cz.cuni.mff.auv.domain.predicates.P_DupFree.Storage_P_DupFree;
import cz.cuni.mff.auv.domain.predicates.P_Entry;
import cz.cuni.mff.auv.domain.predicates.P_Entry.Storage_P_Entry;
import cz.cuni.mff.auv.domain.predicates.P_Exit;
import cz.cuni.mff.auv.domain.predicates.P_Exit.Storage_P_Exit;
import cz.cuni.mff.auv.domain.predicates.P_Free;
import cz.cuni.mff.auv.domain.predicates.P_Free.Storage_P_Free;
import cz.cuni.mff.auv.domain.predicates.P_Operational;
import cz.cuni.mff.auv.domain.predicates.P_Operational.Storage_P_Operational;
import cz.cuni.mff.auv.domain.predicates.P_Outside;
import cz.cuni.mff.auv.domain.predicates.P_Outside.Storage_P_Outside;
import cz.cuni.mff.auv.domain.predicates.P_Sampled;
import cz.cuni.mff.auv.domain.predicates.P_Sampled.Storage_P_Sampled;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;
import gnu.trove.procedure.TIntProcedure;

public class State extends PDDLState {
	
	public Storage_P_ActTurn p_ActTurn;
	public Storage_P_At p_At;
	public Storage_P_AtRes p_AtRes;
	public Storage_P_Connected p_Connected;
	public Storage_P_ConnectedShip p_ConnectedShip;
	public Storage_P_DupFree p_DupFree;
	public Storage_P_Entry p_Entry;
	public Storage_P_Exit p_Exit;
	public Storage_P_Free p_Free;
	public Storage_P_Operational p_Operational;
	public Storage_P_Outside p_Outside;
	public Storage_P_Sampled p_Sampled;
	
	public StateCompact compact;
	
	private Map<Class, IStorage> predicate2storage;
	
	public State() {
		this(true);
	}
	
	protected State(boolean init) {
		if (init) {
			compact = new StateCompact();
			p_ActTurn = new Storage_P_ActTurn();
			p_At = new Storage_P_At();
			p_At.compact = compact;
			p_AtRes = new Storage_P_AtRes();
			p_AtRes.compact = compact;
			p_Connected = new Storage_P_Connected();
			p_ConnectedShip = new Storage_P_ConnectedShip();
			p_DupFree = new Storage_P_DupFree();
			p_DupFree.compact = compact;
			p_Entry = new Storage_P_Entry();
			p_Exit = new Storage_P_Exit();
			p_Free = new Storage_P_Free();
			p_Free.compact = compact;
			p_Operational = new Storage_P_Operational();
			p_Operational.compact = compact;
			p_Outside = new Storage_P_Outside();
			p_Outside.compact = compact;
			p_Sampled = new Storage_P_Sampled();
			p_Sampled.compact = compact;
		}
	}
	
	public IStorage[] getStorages() {
		return new IStorage[] {
				p_ActTurn, p_At, p_AtRes, p_Connected, p_ConnectedShip,
				p_DupFree, p_Entry, p_Exit, p_Free, p_Operational,
				p_Outside, p_Sampled
		};
	}
	
	public Map<Class, IStorage> getPredicate2StorageMap() {
		if (predicate2storage != null) return predicate2storage;
		
		predicate2storage = new HashMap<Class, IStorage>();
		
		predicate2storage.put(P_ActTurn.class, p_ActTurn);
		predicate2storage.put(P_At.class, p_At);
		predicate2storage.put(P_AtRes.class, p_AtRes);
		predicate2storage.put(P_Connected.class, p_Connected);
		predicate2storage.put(P_ConnectedShip.class, p_ConnectedShip);
		predicate2storage.put(P_DupFree.class, p_DupFree);
		predicate2storage.put(P_Entry.class, p_Entry);
		predicate2storage.put(P_Exit.class, p_Exit);
		predicate2storage.put(P_Free.class, p_Free);
		predicate2storage.put(P_Operational.class, p_Operational);
		predicate2storage.put(P_Outside.class, p_Outside);
		predicate2storage.put(P_Sampled.class, p_Sampled);
		
		return predicate2storage;
	}

	
	@Override
	public State clone() {
		State result = new State(false);
		result.compact = compact.clone();
		result.p_ActTurn = p_ActTurn;
		result.p_At = p_At.clone();
		result.p_At.compact = result.compact;
		result.p_AtRes = p_AtRes.clone();
		result.p_AtRes.compact = result.compact;
		result.p_Connected = p_Connected;
		result.p_ConnectedShip = p_ConnectedShip;
		result.p_DupFree = p_DupFree.clone();
		result.p_DupFree.compact = result.compact;
		result.p_Entry = p_Entry;
		result.p_Exit = p_Exit;
		result.p_Free = p_Free.clone();
		result.p_Free.compact = result.compact;
		result.p_Operational = p_Operational.clone();
		result.p_Operational.compact = result.compact;
		result.p_Outside = p_Outside.clone();
		result.p_Outside.compact = result.compact;
		result.p_Sampled = p_Sampled.clone();
		result.p_Sampled.compact = result.compact;
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
		if (includeStatic) dumpStorage(p_ActTurn, includeEmpty, maxLineLength);
		dumpStorage(p_At, includeEmpty, maxLineLength);
		dumpStorage(p_AtRes, includeEmpty, maxLineLength);
		if (includeStatic) dumpStorage(p_Connected, includeEmpty, maxLineLength);
		if (includeStatic) dumpStorage(p_ConnectedShip, includeEmpty, maxLineLength);
		dumpStorage(p_DupFree, includeEmpty, maxLineLength);
		if (includeStatic) dumpStorage(p_Entry, includeEmpty, maxLineLength);
		if (includeStatic) dumpStorage(p_Exit, includeEmpty, maxLineLength);
		dumpStorage(p_Free, includeEmpty, maxLineLength);
		dumpStorage(p_Operational, includeEmpty, maxLineLength);
		dumpStorage(p_Outside, includeEmpty, maxLineLength);
		dumpStorage(p_Sampled, includeEmpty, maxLineLength);				
	}
	
	public void dumpDiff(State diffFrom) {
		PDDLState.dumpDiff(
			new IStorage[] {
				p_ActTurn, p_At, p_AtRes, p_Connected, p_ConnectedShip, p_DupFree,
				p_Entry, p_Exit, p_Free, p_Operational, p_Outside, p_Sampled
			}, 
			new IStorage[] {
				diffFrom.p_ActTurn, diffFrom.p_At, diffFrom.p_AtRes, diffFrom.p_Connected, diffFrom.p_ConnectedShip, diffFrom.p_DupFree,
				diffFrom.p_Entry, diffFrom.p_Exit, diffFrom.p_Free, diffFrom.p_Operational, diffFrom.p_Outside, diffFrom.p_Sampled	
			},
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
		p_At.clearAll();
		p_AtRes.clearAll();
		p_DupFree.clearAll();
		p_Free.clearAll();
		p_Operational.clearAll();
		p_Outside.clearAll();
		p_Sampled.clearAll();
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
	
	private TIntProcedure addAt = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_At.set(P_At.fromInt_v(predicate), P_At.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addAtRes = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_AtRes.set(P_AtRes.fromInt_r(predicate), P_At.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addDupFree = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_DupFree.set(P_DupFree.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addFree = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Free.set(P_Free.fromInt_l(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addOperational = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Operational.set(P_Operational.fromInt_a(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addOutside = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Outside.set(P_Outside.fromInt_s(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addSampled = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Sampled.set(P_Sampled.fromInt_r(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure[] predicateConvertors = new TIntProcedure[] {
			null,
			null,
			addAt,
			addAtRes,
			null,
			null,
			addDupFree,
			null,
			null,
			addFree,
			addOperational,
			addOutside,
			addSampled			
	};	
	
}
