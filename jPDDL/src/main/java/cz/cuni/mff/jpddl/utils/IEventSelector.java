package cz.cuni.mff.jpddl.utils;

import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLProblem;

import java.util.List;

public interface IEventSelector {

    public List<PDDLEffector> select(PDDLProblem problem, List<PDDLEffector> events);

}
