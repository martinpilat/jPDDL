package cz.cuni.mff.perestroika.{{problem_name|java}};

import cz.cuni.mff.perestroika.domain.types.T_Resource;
import cz.cuni.mff.perestroika.problem.E_Resource;

public final class E_Resources {

	{% for l in resources -%}
	public static final T_Resource {{l|java}} = new T_Resource("{{l}}");
	{% endfor %}

	static {

		{% for l in resources -%}
		E_Resource.THIS.register(T_Resource.getIndex({{l|java}}), {{l|java}});
		{% endfor %}
	}
		
	protected E_Resources() {
	}

}
