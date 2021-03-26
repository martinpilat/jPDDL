from collections import namedtuple
import os
import sys

import jinja2

sys.path.append('./fast_downward/translate')

import pddl_parser
import instantiate

Action = namedtuple("Action", ["name", "pre", "add", "delete", "ceff"])
Event = namedtuple("Event", ["name", "pre", "add", "delete", "ceff"])
ConditionalEffect = namedtuple("ConditionalEffect", ["condition", "effects"])

def enabler(action, event):
    return action.add & event.pre

def disabler(action, event):
    return action.delete & event.pre

def sole_enabler(action, event):
    return event.pre <= action.add

def missing_precond(action, event):
    return event.pre - action.add

def atom_to_name(atom):
    return "(" + atom.predicate + "_" + "_".join(atom.args) + ")"

def transform_action(action):
    add_effects = set(map(lambda x: atom_to_name(x[1]), action.add_effects))
    del_effects = set(map(lambda x: atom_to_name(x[1]), action.del_effects))
    pre = set(map(atom_to_name, action.precondition))

    return Action(action.name.replace(" ", "_"), pre, add_effects, del_effects, [])

if __name__ == '__main__':
    task = pddl_parser.open(sys.argv[1], sys.argv[2])
    actions = task.actions
    events = task.events
    inst = instantiate.explore(task)

    task.actions = events
    inst_ev = instantiate.explore(task)
    task.actions = actions

    inst_atoms = inst[1]
    inst_actions = inst[2]
    inst_events = inst_ev[2]

    ground_actions = list(map(transform_action, inst_actions))
    ground_events = list(map(transform_action, inst_events))
    ground_atoms = list(map(atom_to_name, inst_atoms))

    for a in ground_actions:
        a.pre.add('(act-turn)')
        a.delete.add('(act-turn)')
        a.add.add('(ev-turn)')

    init = set(map(atom_to_name, task.init))
    goal = task.goal.parts

    for e in ground_events:
        if e.pre <= init:
            init.add(f'(enab-{e.name[1:-1]})')
        else:
            init.add(f'(disab-{e.name[1:-1]})')

    init = sorted(init)

    for a in ground_actions:
        for e in ground_events:
            if enabler(a, e) and not sole_enabler(a, e):
                print(a.name, "is an enabler of", e.name, a.add, e.pre)
                a.ceff.append(ConditionalEffect(missing_precond(a,e), [f"(enab-{e.name[1:-1]})", f"(not (disab-{e.name[1:-1]}))"]))
                #print("\t missing preconditions:", missing_precond(a, e))
            if disabler(a, e):
                a.delete.add(f"(enab-{e.name[1:-1]})")
                a.add.add(f"(disab-{e.name[1:-1]})")
                #print(a.name, "is a disabler of", e.name)
            if sole_enabler(a, e):
                a.add.add(f"(enab-{e.name[1:-1]})")
                a.delete.add(f"(disab-{e.name[1:-1]})")
                #print(a.name, "is a sole enabler of", e.name)

    for a in ground_events:
        for e in ground_events:
            if a.name == e.name:
                continue
            if enabler(a, e) and not sole_enabler(a, e):
                #print(a.name, "is an enabler of", e.name, a.add, e.pre)
                a.ceff.append(ConditionalEffect(missing_precond(a,e), [f"(enab-{e.name[1:-1]})", f"(not (disab-{e.name[1:-1]}))"]))
                #print("\t missing preconditions:", missing_precond(a, e))
            if disabler(a, e):
                a.delete.add(f"(enab-{e.name[1:-1]})")
                a.add.add(f"(disab-{e.name[1:-1]})")
                #print(a.name, "is a disabler of", e.name)
            if sole_enabler(a, e):
                a.add.add(f"(enab-{e.name[1:-1]})")
                a.delete.add(f"(disab-{e.name[1:-1]})")
                print(a.name, "is a sole enabler of", e.name)

    for a in ground_events:
        a.pre.add(f'(selected-{a.name[1:-1]})')
        a.pre.add(f'(enab-{a.name[1:-1]})')
        a.delete.add('(act-turn)')
        a.add.add('(ev-turn)')

    noop_events = []
    for e in ground_events:
        pre = {f'(selected-{e.name[1:-1]})', f'(disab-{e.name[1:-1]})'}
        delete = {f'(selected-{e.name[1:-1]})'}
        add = {'(act-turn)'}
        noop_events.append(Action(f'({e.name[1:-1]}-noop)', pre, add, delete, []))

    ground_events += noop_events

    typed_objects = {}
    for o in task.objects:
        if o.type_name not in typed_objects:
            typed_objects[o.type_name] = []
        typed_objects[o.type_name].append(o.name)

    goal_atoms = list(map(atom_to_name, goal))
    print(goal_atoms)

    print("="*80)

    template_loader = jinja2.FileSystemLoader(searchpath="./")
    template_env = jinja2.Environment(loader=template_loader)
    problem_template = template_env.get_template('problem_template.pddl')
    output_text = problem_template.render(problem_name=task.task_name,
                                          domain_name=task.domain_name,
                                          goals=goal_atoms,
                                          inits=init)

    p_path, p_name = os.path.split(sys.argv[1])

    with open(f'fond-{p_name}', 'w') as f_problem:
        f_problem.write(output_text)

    domain_template = template_env.get_template('domain_template.pddl')
    output_text = domain_template.render(domain_name=task.domain_name,
                                         actions=ground_actions,
                                         events=ground_events,
                                         atoms=ground_atoms)

    d_path, d_name = os.path.split(sys.argv[2])

    with open(f'fond-{d_name}', 'w') as f_domain:
        f_domain.write(output_text)