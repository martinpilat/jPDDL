package cz.cuni.mff.bw.problem;

import java.util.Collections;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.tools.search.SearchBase;

/**
 * Depth-limited search not searching for anything, just applying events.
 * @author Jimmy
 */
public class EventsTester extends SearchBase {

	private List<PDDLEffector> result;
	
	public int events = 0;
	
	public EventsTester() {		
	}

	public EventsTester(PDDLDomain domain, PDDLState state, PDDLGoal goal, PDDLApplicables applicables) {
		super(domain, state, goal, applicables);
	}

	public EventsTester(PDDLProblem problem) {
		super(problem);		
	}
	
	public EventsTester(PDDLProblem problem, PDDLState state) {
		super(problem, state);
	}
	
	public List<PDDLEffector> search(int limit) {
		result = pool.get();
		
		if (doSearch(limit)) {
			Collections.reverse(result);
			return result;
		}
		
		return null;
	}
	
	private boolean doSearch(int limit) {		
		if (limit < 1) return false;
		
		List<PDDLEffector> events = pool.get();
		
		System.out.println();
		System.out.println("=============");
		System.out.println("CURRENT STATE");
		System.out.println("=============");
		state.dump();
		
		applicables.collectApplicableEvents(domain, state, events);
		
		System.out.println("APPLICABLE EVENTS: " + events.size());
		
		for (PDDLEffector event : events) {
			System.out.println("APPLYING:  " + event.toEffector());
			
			event.apply(state);
			++this.events;
			
			if (doSearch(limit-1)) {
				System.out.println("REVERTING: " + event.toEffector());
				event.reverse(state);
				result.add(event);
				return true;
			}
			
			event.reverse(state);
			
			// WE WILL NOT BE USING THIS INSTANCE AGAIN
			event.recycle();
		}
		
				
		pool.back(events);
		
		return false;		
	}
	
}
