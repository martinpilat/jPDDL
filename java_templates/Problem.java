package cz.cuni.mff.perestroika.{{problem_name|java}};

import cz.cuni.mff.jpddl.PDDLDeadEnd;
import cz.cuni.mff.perestroika.domain.Domain;
import cz.cuni.mff.perestroika.domain.State;
import cz.cuni.mff.perestroika.problem.PerestroikaProblem;
import cz.cuni.mff.perestroika.problem1.DeadEnd;

public final class Problem extends PerestroikaProblem {
	
	static {
		// ENSURE STATIC INITIALIZATION OF THE CLASSES
		new E_Locations();
		new E_Resources();
	}
	
	public Domain domain;
	
	public State state;
	
	public Goal goal;
	
	public DeadEnd deadEnd;
	
	public Problem() {
		domain = new Domain();
		state = new State();
		goal = new Goal();
		deadEnd = new DeadEnd();
		
		state.p_ActRound.set();
		state.p_Alive.set();

		
		state.p_AtAgent.set(E_Locations.{{problem['at-agent'][0][0]|java}});

		{% for r, l in problem['at-res'] -%}
		state.p_AtRes.set(E_Resources.{{r|java}}, E_Locations.{{l|java}});
		{% endfor %}

		{% for r, l in problem['connected'] -%}
		state.p_Connected.set(E_Locations.{{r|java}}, E_Locations.{{l|java}});
		{% endfor %}

		{% for (l,) in problem['accessible'] -%}
		state.p_Accessible.set(E_Locations.{{l|java}});
		{% endfor %}

		{% for (l,) in problem['solid'] -%}
		state.p_Solid.set(E_Locations.{{l|java}});
		{% endfor %}

		{% for (l,) in problem['medium'] -%}
		state.p_Medium.set(E_Locations.{{l|java}});
		{% endfor %}

		{% for (l,) in problem['small'] -%}
		state.p_Small.set(E_Locations.{{l|java}});
		{% endfor %}

		{% for (l,) in problem['big'] -%}
		state.p_Big.set(E_Locations.{{l|java}});
		{% endfor %}

	}
	
	@Override
	public String getName() {
		return "Perestroika-Problem";
	}
	
	@Override
	public Domain getDomain() {
		return domain;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public Goal getGoal() {
		return goal;
	}	
	
	@Override
	public PDDLDeadEnd getDeadEnd() {
		return deadEnd;
	}
	
}
