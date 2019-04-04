package cz.cuni.mff.jpddl.utils;

import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLProblem;

import java.util.ArrayList;
import java.util.List;

public class SelectSingleEvent implements IEventSelector{

    @Override
    public List<PDDLEffector> select(PDDLProblem problem, List<PDDLEffector> events) {
        if (events.isEmpty() || events.get(0) == null) {
            return new ArrayList<>();
        }
        return events.subList(0, 1);
    }

}
