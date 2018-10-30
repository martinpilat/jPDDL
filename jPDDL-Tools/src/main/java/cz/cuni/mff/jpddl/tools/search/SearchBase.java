package cz.cuni.mff.jpddl.tools.search;

import java.util.ArrayList;
import java.util.List;

import cz.cuni.mff.jpddl.PDDLApplicables;
import cz.cuni.mff.jpddl.PDDLDomain;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.jpddl.store.Pool;

public abstract class SearchBase {

	public PDDLDomain domain;
	
	public PDDLState state;
	
	public PDDLGoal goal;
	
	public PDDLApplicables applicables;
	
	public SearchBase() {		
	}

	public SearchBase(PDDLDomain domain, PDDLState state, PDDLGoal goal, PDDLApplicables applicables) {
		this.domain = domain;
		this.state = state;
		this.goal = goal;
		this.applicables = applicables;
	}

	public SearchBase(PDDLProblem problem) {
		this.domain = problem.getDomain();
		this.state = problem.getState();
		this.goal = problem.getGoal();
		this.applicables = problem.getApplicables();
	}
	
	public SearchBase(PDDLProblem problem, PDDLState state) {
		this.domain = problem.getDomain();
		this.state = state;
		this.goal = problem.getGoal();
		this.applicables = problem.getApplicables();
	}
	
	// =======
	// POOLING
	// =======
	
	public static Pool<List<PDDLEffector>> pool = new Pool<List<PDDLEffector>>() {

		@Override
		protected List<PDDLEffector> create() {
			return new ArrayList<PDDLEffector>();
		}
		
		@Override
		public void back(List<PDDLEffector> obj) {
			obj.clear();
			super.back(obj);
		}
		
	};
		
}
