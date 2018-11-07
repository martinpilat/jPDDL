package cz.cuni.mff.jpddl.tools.validators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterDFS.PlanTesterDFSResult;
import cz.cuni.mff.jpddl.utils.StateCompact;

/**
 * Breadth-first search that tests all sequences of events against the plan, the first (shortest) sequence of events
 * that breaks the plan is returned.
 * 
 * @author Jimmy
 */
public class PlanTesterBFS {
	
	public static class PlanTesterBFSResult {
		
		public PDDLState state;
		
		public SafeStates safeStates;
		
		public PDDLEffector[] plan;
		
		public PDDLEffector[] events;
		
		public boolean valid;				
		
		public int lastExecutableEffectorIndex;
		
		/**
		 * State was safe after actions and events [0;lastSafeStateIndex) were applied.
		 * I.e., if you take state and applies plan[0];events[0];plane[1];events[1];...;plan[lastSafeStateIndex-1];events[lastSafeStateIndex-1]
		 * you are in the safe state.
		 */
		public int lastSafeStateIndex;
		
		public int statesProbed = 0;
		
		public int bfsLimit;
	}
	
	private static class BFSState {
		
		/**
		 * Action index to execute next.
		 */
		public int index;
		
		/**
		 * State of the problem.
		 */
		public StateCompact state;
		
		/**
		 * Event that were executed.
		 */
		public PDDLEffector event;
		
		/**
		 * Previous state.
		 */
		public BFSState parent;
		
		public BFSState(int index, StateCompact state, PDDLEffector event, BFSState parent) {
			super();
			this.index = index;
			this.state = state;
			this.event = event;
			this.parent = parent;
		}
		
	}

	private PDDLDomain domain;
	private PDDLApplicables applicables;
	
	public PlanTesterBFS() {		
	}

	public PlanTesterBFS(PDDLDomain domain, PDDLApplicables applicables) {
		this.domain = domain;
		this.applicables = applicables;
	}
	
	public PlanTesterBFSResult check(PDDLGoal goal, PDDLState state, SafeStates safeStates, int levelLimit, PDDLEffector... plan) {
		PlanTesterBFSResult result = new PlanTesterBFSResult();
		
		result.valid = true;
		result.state = state;
		result.plan = plan;
		result.safeStates = safeStates;
		result.events = new PDDLEffector[plan.length];
		result.lastExecutableEffectorIndex = -1;
		result.lastSafeStateIndex = -1;
		result.bfsLimit = levelLimit;
		
		// SAVE STATE
		StateCompact dynamic = state.getDynamic().clone();
		
		if (bfs(goal, state, safeStates, plan, result, levelLimit)) {
			result.valid = true;
		} else {
			result.valid = false;
		}
		
		// RESTORE STATE
		state.setDynamic(dynamic);

		// RETURN RESULT
		return result;
		
	}
	
	public boolean bfs(PDDLGoal goal, PDDLState state, SafeStates safeStates, PDDLEffector[] plan, PlanTesterBFSResult result, int levelLimit) {
		// GOAL ACHIEVED?
		if (goal.isAchieved(state)) {
			// WE'RE DONE
			return true;
		}
		
		ArrayDeque<BFSState> queue = new ArrayDeque<BFSState>();

		// ADD THE FIRST STATE
		queue.add(new BFSState(0, state.getDynamic().clone(), null, null));
		
		// LAST CONFIRMED SAFE STATE INDEX
		boolean initialStateSafe = safeStates.isSafe(state);
		boolean[] currIndexLevelSafe = new boolean[] {initialStateSafe, initialStateSafe};
		int currIndexLevel = -1;
		
		// UTILS
		List<PDDLEffector> events = new ArrayList<PDDLEffector>();	
		
		// RUN THE SEARCH
		while (queue.size() > 0) {
			++result.statesProbed;
			
			BFSState s = queue.poll();
			
			if (s.index > currIndexLevel) {
				// WE HAVE JUST FINISHED EVALUATING NEXT BFS LAYER
				//System.out.println("PlanTesterBFS: reached layer " + s.index + ", queue size = " + queue.size());

				// s => state after applying action[0],any-event[0],...,action[s.index-1],any-event[s.index-1]
				//   => all sequence of events events [0]-[s.index-1] were checked not to mess up the plan
				//   => we have checked that under all circumstances action[s.index-1] is applicable				
				if (currIndexLevelSafe[0]) {
					result.lastSafeStateIndex = s.index-1;
				}
				
				// RESTART SAFE STATE CHECKING
				currIndexLevelSafe[0] = currIndexLevelSafe[1];
				currIndexLevelSafe[1] = true;
				currIndexLevel = s.index;
			}
			
			// IS SAFE STATE?
			boolean isSafeState = safeStates.isSafe(state);
			currIndexLevelSafe[1] = currIndexLevelSafe[1] && isSafeState;
			
			// RESET THE STATE
			state.setDynamic(s.state);
			
			// CHECK PLAN APPLICABILITY
			if (!plan[s.index].isApplicable(state)) {
				// ACTION NOT APPLICABLE!
				result.lastExecutableEffectorIndex = s.index-1;
				result.valid = false;
				
				// WE HAVE FOUND THE LIST OF EVENTS THAT PREVENTS THE PLAN FROM APPLICATION
				for (int i = s.index-1; i >= 0; --i) {
					result.events[i] = s.event;
					s = s.parent;
				}
				
				// END THE SEARCH
				return false;
			}
			
			// ACTION STILL EXECUTABLE
			if (s.index < levelLimit) {
				// APPLY ACTION
				plan[s.index].apply(state);
				
				// GOAL ACHIEVED?
				if (goal.isAchieved(state)) {
					// WE'RE DONE
					return true;
				}
							
				// COLLECT APPLICABLE EVENTS
				events.clear();
				events.add(null); // add NO-EVENT
				applicables.collectApplicableEvents(domain, state, events);
				Collections.shuffle(events); // randomize
				
				// CONSTRUCT OFFSPRING BFS STATES
				for (PDDLEffector event : events) {
					if (event != null) {
						event.apply(state);
					}
					StateCompact newState = state.getDynamic().clone();					
					queue.add(new BFSState(s.index+1, newState, event, s));
					if (event != null) {
						event.reverse(state);
					}
				}
			}
				
			// ERASE THE STATE TO SAVE MEMORY
			s.state = null;			
		}
		
		// NO SEQUENCE OF EVENTS PREVENT THE PLAN FROM APPLICATION
		if (currIndexLevelSafe[1]) {
			// LAST LEVEL CHECKED OK
			result.lastSafeStateIndex = levelLimit;
		}
		
		return true;		
	}
	
}
