package cz.cuni.mff.bw.domain;

import java.util.HashMap;
import java.util.Map;

import cz.cuni.mff.bw.domain.predicates.P_Clear;
import cz.cuni.mff.bw.domain.predicates.P_Clear.Storage_P_Clear;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_HandEmpty.Storage_P_HandEmpty;
import cz.cuni.mff.bw.domain.predicates.P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_Holding.Storage_P_Holding;
import cz.cuni.mff.bw.domain.predicates.P_Movable;
import cz.cuni.mff.bw.domain.predicates.P_Movable.Storage_P_Movable;
import cz.cuni.mff.bw.domain.predicates.P_On;
import cz.cuni.mff.bw.domain.predicates.P_On.Storage_P_On;
import cz.cuni.mff.bw.domain.predicates.P_OnTable;
import cz.cuni.mff.bw.domain.predicates.P_OnTable.Storage_P_OnTable;
import cz.cuni.mff.bw.domain.predicates.P_Sticky;
import cz.cuni.mff.bw.domain.predicates.P_Sticky.Storage_P_Sticky;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLPredicate;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.utils.StateCompact;
import gnu.trove.procedure.TIntProcedure;

public class State extends PDDLState {
	
	public Storage_P_Clear p_Clear;
	public Storage_P_HandEmpty p_HandEmpty;
	public Storage_P_Holding p_Holding;
	public Storage_P_Movable p_Movable;
	public Storage_P_On p_On;	
	public Storage_P_OnTable p_OnTable;
	public Storage_P_Sticky p_Sticky;
	
	public StateCompact compact;
	
	private Map<Class, IStorage> predicate2storage;
	
	public State() {
		this(true);
	}
	
	protected State(boolean init) {
		if (init) {
			compact = new StateCompact();
			p_Clear = new Storage_P_Clear();
			p_Clear.compact = compact;			
			p_HandEmpty = new Storage_P_HandEmpty();
			p_HandEmpty.compact = compact;
			p_Holding = new Storage_P_Holding();
			p_Holding.compact = compact;								
			p_Movable = new Storage_P_Movable();
			p_Movable.compact = compact;
			p_On = new Storage_P_On();
			p_On.compact = compact;
			p_OnTable = new Storage_P_OnTable();
			p_OnTable.compact = compact;			
			p_Sticky = new Storage_P_Sticky();
			p_Sticky.compact = compact;
		}
	}
	
	public IStorage[] getStorages() {
		return new IStorage[] {
				p_Clear, p_HandEmpty, p_Holding, p_Movable, p_On, p_OnTable, p_Sticky
		};
	}
	
	public Map<Class, IStorage> getPredicate2StorageMap() {
		if (predicate2storage != null) return predicate2storage;
		
		predicate2storage = new HashMap<Class, IStorage>();
		
		predicate2storage.put(P_Clear.class, p_Clear);
		predicate2storage.put(P_HandEmpty.class, p_HandEmpty);
		predicate2storage.put(P_Holding.class, p_Holding);
		predicate2storage.put(P_Movable.class, p_Movable);
		predicate2storage.put(P_On.class, p_On);
		predicate2storage.put(P_OnTable.class, p_OnTable);
		predicate2storage.put(P_Sticky.class, p_Sticky);
		
		return predicate2storage;
	}

	
	@Override
	public State clone() {
		State result = new State(false);
		result.compact = compact.clone();
		p_Clear = p_Clear.clone();
		p_Clear.compact = compact;			
		p_HandEmpty = p_HandEmpty.clone();
		p_HandEmpty.compact = compact;
		p_Holding = p_Holding.clone();
		p_Holding.compact = compact;								
		p_Movable = p_Movable.clone();
		p_Movable.compact = compact;
		p_On = p_On.clone();
		p_On.compact = compact;
		p_OnTable = p_OnTable.clone();
		p_OnTable.compact = compact;			
		p_Sticky = p_Sticky.clone();
		p_Sticky.compact = compact;
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
		dumpStorage(p_Clear, includeEmpty, maxLineLength);		
		dumpStorage(p_HandEmpty, includeEmpty, maxLineLength);
		dumpStorage(p_Holding, includeEmpty, maxLineLength);
		dumpStorage(p_Movable, includeEmpty, maxLineLength);		
		dumpStorage(p_On, includeEmpty, maxLineLength);
		dumpStorage(p_OnTable, includeEmpty, maxLineLength);
		dumpStorage(p_Sticky, includeEmpty, maxLineLength);
				
	}
	
	public void dumpDiff(State diffFrom) {
		PDDLState.dumpDiff(
			new IStorage[] {
				p_Clear, p_HandEmpty, p_Holding, p_Movable, p_On, p_OnTable, p_Sticky
			}, 
			new IStorage[] {
				diffFrom.p_Clear, diffFrom.p_HandEmpty, diffFrom.p_Holding, diffFrom.p_Movable, diffFrom.p_On, diffFrom.p_OnTable, diffFrom.p_Sticky
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
		p_Clear.clearAll();
		p_Holding.clearAll();
		p_HandEmpty.clearAll();
		p_Movable.clearAll();
		p_On.clearAll();
		p_OnTable.clearAll();		
		p_Sticky.clearAll();
		
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
	
	private TIntProcedure addClear = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Clear.set(P_Clear.fromInt_x(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addHandEmpty = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_HandEmpty.set(P_HandEmpty.fromInt_h(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addHolding = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Holding.set(P_Holding.fromInt_h(predicate), P_Holding.fromInt_x(predicate));
			return true;
		}
		
	};
		
	private TIntProcedure addMovable = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Movable.set(P_Movable.fromInt_x(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addOn = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_On.set(P_On.fromInt_x(predicate), P_On.fromInt_y(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addOnTable = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_OnTable.set(P_OnTable.fromInt_x(predicate));
			return true;
		}
		
	};
	
	private TIntProcedure addSticky = new TIntProcedure() {

		@Override
		public boolean execute(int predicate) {
			p_Sticky.set(P_Sticky.fromInt_x(predicate));
			return true;
		}
		
	};
		
	private TIntProcedure[] predicateConvertors = new TIntProcedure[] {
			null,
			addClear,
			addHandEmpty,
			addHolding,
			addMovable,
			addOn,
			addOnTable,
			addSticky		
	};	
	
}
