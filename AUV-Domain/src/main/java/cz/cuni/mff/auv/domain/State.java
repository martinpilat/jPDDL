package cz.cuni.mff.auv.domain;

import cz.cuni.mff.auv.domain.predicates.P_ActTurn.Storage_P_ActTurn;
import cz.cuni.mff.auv.domain.predicates.P_At.Storage_P_At;
import cz.cuni.mff.auv.domain.predicates.P_AtRes.Storage_P_AtRes;
import cz.cuni.mff.auv.domain.predicates.P_Connected.Storage_P_Connected;
import cz.cuni.mff.auv.domain.predicates.P_ConnectedShip.Storage_P_ConnectedShip;
import cz.cuni.mff.auv.domain.predicates.P_DupFree.Storage_P_DupFree;
import cz.cuni.mff.auv.domain.predicates.P_Entry.Storage_P_Entry;
import cz.cuni.mff.auv.domain.predicates.P_Exit.Storage_P_Exit;
import cz.cuni.mff.auv.domain.predicates.P_Free.Storage_P_Free;
import cz.cuni.mff.auv.domain.predicates.P_Operational.Storage_P_Operational;
import cz.cuni.mff.auv.domain.predicates.P_Outside.Storage_P_Outside;
import cz.cuni.mff.auv.domain.predicates.P_Sampled.Storage_P_Sampled;
import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLState;

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
	
	public State() {
		this(true);
	}
	
	protected State(boolean init) {
		if (init) {
			p_ActTurn = new Storage_P_ActTurn();
			p_At = new Storage_P_At();
			p_AtRes = new Storage_P_AtRes();
			p_Connected = new Storage_P_Connected();
			p_ConnectedShip = new Storage_P_ConnectedShip();
			p_DupFree = new Storage_P_DupFree();
			p_Entry = new Storage_P_Entry();
			p_Exit = new Storage_P_Exit();
			p_Free = new Storage_P_Free();
			p_Operational = new Storage_P_Operational();
			p_Outside = new Storage_P_Outside();
			p_Sampled = new Storage_P_Sampled();
		}
	}
	
	public IStorage[] getStorages() {
		return new IStorage[] {
				p_ActTurn, p_At, p_AtRes, p_Connected, p_ConnectedShip,
				p_DupFree, p_Entry, p_Exit, p_Free, p_Operational,
				p_Outside, p_Sampled
		};
	}

	
	@Override
	public State clone() {
		State result = new State(false);
		result.p_ActTurn = p_ActTurn.clone();
		result.p_At = p_At.clone();
		result.p_AtRes = p_AtRes.clone();
		result.p_Connected = p_Connected.clone();
		result.p_ConnectedShip = p_ConnectedShip.clone();
		result.p_DupFree = p_DupFree.clone();
		result.p_Entry = p_Entry.clone();
		result.p_Exit = p_Exit.clone();
		result.p_Free = p_Free.clone();
		result.p_Operational = p_Operational.clone();
		result.p_Outside = p_Outside.clone();
		result.p_Sampled = p_Sampled.clone();
		return result;
	}

	@Override
	public void dump(boolean includeEmpty, int maxLineLength) {
		dumpStorage(p_ActTurn, includeEmpty, maxLineLength);
		dumpStorage(p_At, includeEmpty, maxLineLength);
		dumpStorage(p_AtRes, includeEmpty, maxLineLength);
		dumpStorage(p_Connected, includeEmpty, maxLineLength);
		dumpStorage(p_ConnectedShip, includeEmpty, maxLineLength);
		dumpStorage(p_DupFree, includeEmpty, maxLineLength);
		dumpStorage(p_Entry, includeEmpty, maxLineLength);
		dumpStorage(p_Exit, includeEmpty, maxLineLength);
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
	
}
