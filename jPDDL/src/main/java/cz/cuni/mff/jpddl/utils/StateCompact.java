package cz.cuni.mff.jpddl.utils;

import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.Pool;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;

/**
 * Represents dynamic part of the {@link PDDLState} in the form of integer {@link TIntSet} realized by {@link TIntHashSet}.
 * @author Jimmy
 */
public class StateCompact implements Cloneable {

	private TIntSet state;
	
	public StateCompact() {
		state = new TIntHashSet();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof StateCompact)) return false;
		StateCompact other = (StateCompact)obj;
		return state.equals(other.state);
	}
	
	public StateCompact(StateCompact source) {
		state = new TIntHashSet(source.state);
	}
	
	public void add(StateCompact other) {
		state.addAll(other.state);
	}
	
	public void remove(StateCompact other) {
		state.removeAll(other.state);
	}	
	
	public StateCompact clone() {
		StateCompact state = get();
		state.add(this);
		return state;
	}
	
	public boolean set(int predicate) {
		return state.add(predicate);
	}
	
	public boolean clear(int predicate) {
		return state.remove(predicate);
	}
	
	public void recycle() {
		pool.back(this);
	}
	
	public void reset() {
		state.clear();
	}
	
	public void forEach(TIntProcedure proc) {
		state.forEach(proc);
	}
	
	// =======
	// POOLING
	// =======
	
	private static final Pool<StateCompact> pool = new Pool<StateCompact>() {

		@Override
		protected StateCompact create() {
			return new StateCompact();
		}
		
	};
	
	public static StateCompact get() {
		StateCompact result = pool.get();
		return result;
	}
	
	public static void back(StateCompact state) {
		state.reset();
		pool.back(state);
	}
}
