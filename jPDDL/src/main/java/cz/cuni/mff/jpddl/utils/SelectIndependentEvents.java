package cz.cuni.mff.jpddl.utils;

import cz.cuni.mff.jpddl.IStorage;
import cz.cuni.mff.jpddl.PDDLEffector;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.PDDLState;

import java.util.ArrayList;
import java.util.List;

public class SelectIndependentEvents implements IEventSelector {

    private boolean independent(PDDLState state, PDDLEffector e1, PDDLEffector e2) {

        assert e1.isApplicable(state) && e2.isApplicable(state);

        PDDLState os1 = state.clone();
        PDDLState os2 = state.clone();

        if (e1.isApplicable(os1)) {
            e1.clone().apply(os1);
            if (e2.isApplicable(os1))
                e2.clone().apply(os1);
            else
                return false;
        }

        if (e2.isApplicable(os2)) {
            e2.clone().apply(os2);
            if (e1.isApplicable(os2))
                e1.clone().apply(os2);
            else
                return false;
        }

        IStorage[] os1storages = os1.getStorages();
        IStorage[] os2storages = os2.getStorages();

        for (int i = 0; i < os1storages.length; i++) {
            ArrayList added = new ArrayList();
            ArrayList removed = new ArrayList();

            PDDLState.diff(os1storages[i], os2storages[i], added, removed);

            if (added.size() > 0 || removed.size() > 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<PDDLEffector> select(PDDLProblem problem, List<PDDLEffector> events) {

        events.add(null); // make sure null is always in the list of events - it is used to end the loop
        PDDLEffector event = events.remove(0);
        ArrayList<PDDLEffector> selected_events = new ArrayList<>();
        while (event != null) {
            boolean canAdd = true;
            for (PDDLEffector se : selected_events) {
                if (!independent(problem.getState(), se, event)) {
                    System.out.println("Events not independent: " + se.toEffector() + " and " + event.toEffector());
                    canAdd = false;
                    break;
                }
            }
            if (canAdd)
                selected_events.add(event);
            event = events.remove(0);
        }

        return selected_events;

    }
}
