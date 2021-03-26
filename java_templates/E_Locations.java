package cz.cuni.mff.perestroika.{{problem_name|java}};

import cz.cuni.mff.perestroika.domain.types.T_Location;
import cz.cuni.mff.perestroika.problem.E_Location;

public final class E_Locations {

	{% for l in locations -%}
	public static final T_Location {{l|java}} = new T_Location("{{l}}");
	{% endfor %}

	static {

		{% for l in locations -%}
		E_Location.THIS.register(T_Location.getIndex({{l|java}}), {{l|java}});
		{% endfor %}
	}
		
	protected E_Locations() {
	}

}
