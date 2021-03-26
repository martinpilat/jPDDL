package cz.cuni.mff.perestroika.{{problem_name|java}};

import cz.cuni.mff.jpddl.PDDLGoal;
import cz.cuni.mff.jpddl.PDDLState;
import cz.cuni.mff.perestroika.domain.State;

public class Goal extends PDDLGoal {
	
	@Override
	public String toPDDL() {
		return  "(:goal (and (alive)\n"
			  {% for r in resources -%}
			  + "            (taken {{r}})\n"			
			  {% endfor -%}
			  + "       )\n"
			  + ")";
	}

	
	/**
	 * Whether the goal has been achieved in 'state'.
	 * 
	 * GOAL
	 * 
	 * :goal (and (alive r1)
     *            (taken r1)
     *            (taken r2)
     *            (taken r3)
     *            (taken r4)
     *       )
	 * 
	 * @param state
	 * @return
	 */
	public boolean isAchieved(State state) {
		return    state.p_Alive.isSet()
			  {%- for r in resources %}
			   && state.p_Taken.isSet(E_Resources.{{r}})
			  {%- endfor %};
	}
	
	public boolean isAchievedAll(State... states) {
		for (State state : states) {
			if (!isAchieved(state)) return false;
		}
		return true;
	}
	
	public boolean isAchievedUnion(State... states) {
		boolean achieved;
		
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Alive.isSet()) break;
		}
		if (!achieved) return false;

		{% for r in resources -%}
		achieved = false;
		for (State state : states) {
			if (achieved = state.p_Taken.isSet(E_Resources.{{r}})) break;
		}
		if (!achieved) return false;

		{% endfor -%}
		return true;
	}
	
	public boolean isAchievedAny(State... states) {
		for (State state : states) {
			if (isAchieved(state)) return true;
		}
		return false;
	}

	// ========================
	// PDDLGoal Generic Methods
	// ========================
	
	@Override
	public boolean isAchieved(PDDLState state) {
		return isAchieved((State)state);
	}

	@Override
	public boolean isAchievedAll(PDDLState... states) {
		return isAchievedAll((State[])states);
	}

	@Override
	public boolean isAchievedUnion(PDDLState... states) {
		return isAchievedUnion((State[])states);
	}

	@Override
	public boolean isAchievedAny(PDDLState... states) {
		return isAchievedAny((State[])states);
	}

}
