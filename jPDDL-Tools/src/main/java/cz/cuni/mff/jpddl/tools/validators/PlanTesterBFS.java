package cz.cuni.mff.jpddl.tools.validators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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
public class PlanTesterBFS implements IPlanValidator {
	
	public static class PlanTesterBFSResult extends PlanValidatorResult {

		/**
		 * Safe states used.
		 */
		public SafeStates safeStates;
		
		/**
		 * Unfortunate sequence of events in case of failure.
		 */
		public PDDLEffector[] events;

		/**
		 * How many states were probed.
		 */
		public int statesProbed = 0;
		
		/**
		 * With what limit the validator was running.
		 */
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
	private SafeStates safeStates;
	private int levelLimit;
	
	public void config(PDDLDomain domain, PDDLApplicables applicables, SafeStates safeStates, int levelLimit) {
		this.domain = domain;
		this.applicables = applicables;
		this.safeStates = safeStates;
		this.levelLimit = levelLimit;
	}
	
	@Override
	public PlanTesterBFSResult validate(PDDLGoal goal, PDDLState state, PDDLEffector... plan) {
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
		
		if (bfs(goal, state, plan, result, levelLimit)) {
			result.valid = true;
		} else {
			result.valid = false;
		}
		
		// RESTORE STATE
		state.setDynamic(dynamic);

		// RETURN RESULT
		return result;
		
	}
	
	public boolean bfs(PDDLGoal goal, PDDLState state, PDDLEffector[] plan, PlanTesterBFSResult result, int levelLimit) {
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
		boolean[] indexSafe = new boolean[plan.length+1];
		Arrays.fill(indexSafe, true);
		indexSafe[0] = initialStateSafe;
		int currIndexLevel = 0;
		
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
				if (indexSafe[currIndexLevel]) {
					if (result.firstSafeStateIndex < 0 && currIndexLevel > 0) {
						result.firstSafeStateIndex = currIndexLevel;
					}
					result.lastSafeStateIndex = currIndexLevel;
				}

				currIndexLevel = s.index;
			}
			
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
					result.lastExecutableEffectorIndex = s.index;
					result.lastSafeStateIndex = s.index+1;
					return true;
				}
							
				// COLLECT APPLICABLE EVENTS
				events.clear();
				events.add(null); // add NO-EVENT
				applicables.collectApplicableEvents(domain, state, events);
				
				// CONSTRUCT OFFSPRING BFS STATES
				for (PDDLEffector event : events) {
					// APPLY EVENT TO AFFECT THE STATE
					if (event != null) {
						event.apply(state);						
					}
					
					// STATE AFTER THE EVENT SAFE?
					if (indexSafe[s.index+1]) {
						if (!safeStates.isSafe(state)) {
							// NO! Applying this event is breaking the safety!
							indexSafe[s.index+1] = false;
						}
					}
					
					// ADD NEW BFS NODE
					StateCompact newState = state.getDynamic().clone();					
					queue.add(new BFSState(s.index+1, newState, event, s));
					
					// REVERSE THE EVENT TO RESTORE THE STATE
					if (event != null) {
						event.reverse(state);
					}
				}
			}
				
			// ERASE THE STATE TO SAVE MEMORY
			s.state = null;			
		}
		
		// NO SEQUENCE OF EVENTS PREVENT THE PLAN FROM APPLICATION
		if (indexSafe[levelLimit]) {
			// LAST LEVEL CHECKED OK
			if (result.firstSafeStateIndex < 0) {
				result.firstSafeStateIndex = levelLimit;
			}
			result.lastSafeStateIndex = levelLimit;
		}
		
		return true;		
	}
	
	@Override
	public String getDescription() {
		return getClass().getSimpleName() + "[limit=" + levelLimit + "]";
	}

	
}
