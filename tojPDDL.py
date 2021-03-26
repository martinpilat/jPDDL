from collections import namedtuple, defaultdict
import os
import sys

import jinja2

sys.path.append('./translate')

import pddl_parser

Action = namedtuple("Action", ["name", "pre", "add", "delete", "ceff"])
Event = namedtuple("Event", ["name", "pre", "add", "delete", "ceff"])
ConditionalEffect = namedtuple("ConditionalEffect", ["condition", "effects"])

def java_name(s):
    return s.replace('-', '_').replace('.', '_')

output_dir = 'gen/'

if __name__ == '__main__':
    task = pddl_parser.open(sys.argv[1], sys.argv[2])

    print(task.task_name)

    problem = defaultdict(list)
    type_objects = defaultdict(list)

    for a in task.init:
        if hasattr(a, 'predicate'):
            problem[a.predicate].append(a.args)

    for o in task.objects:
        type_objects[o.type_name].append(o.name)

    template_loader = jinja2.FileSystemLoader(searchpath="./java_templates")
    template_env = jinja2.Environment(loader=template_loader)
    template_env.filters['java'] = java_name
    problem_template = template_env.get_template('Problem.java')
    output_text = problem_template.render(problem_name=task.task_name,
                                          domain_name=task.domain_name,
                                          problem = problem)

    safe_states_dir = os.path.join(output_dir, 'safe_states')
    ss_file_name = os.path.join(safe_states_dir, java_name(task.task_name))
    output_dir += java_name(task.task_name)
    
    print(task.task_name)

    os.makedirs(output_dir, exist_ok=True)
    os.makedirs(safe_states_dir, exist_ok=True)

    with open(os.path.join(output_dir, 'Problem.java'), 'w') as f_problem:
        f_problem.write(output_text)

    locations_template = template_env.get_template('E_Locations.java')
    output_text = locations_template.render(problem_name=task.task_name,
                                          domain_name=task.domain_name,
                                          locations=type_objects['location'])

    with open(os.path.join(output_dir, 'E_Locations.java'), 'w') as f_problem:
        f_problem.write(output_text)

    resources_template = template_env.get_template('E_Resources.java')
    output_text = resources_template.render(problem_name=task.task_name,
                                          domain_name=task.domain_name,
                                          resources=type_objects['resource'])

    with open(os.path.join(output_dir, 'E_Resources.java'), 'w') as f_problem:
        f_problem.write(output_text)


    goal_template = template_env.get_template('Goal.java')
    output_text = goal_template.render(problem_name=task.task_name,
                                          domain_name=task.domain_name,
                                          resources=type_objects['resource'])

    with open(os.path.join(output_dir, 'Goal.java'), 'w') as f_problem:
        f_problem.write(output_text)

    safe_states_template = template_env.get_template('safe_states')
    output_text = safe_states_template.render(problem=problem)

    with open(ss_file_name, 'w') as ss_file:
        ss_file.write(output_text)