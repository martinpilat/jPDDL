package cz.cuni.mff.jpddl.tools.simulations;

import cz.cuni.mff.jpddl.*;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.planners.LamaCost;
import cz.cuni.mff.jpddl.tools.planners.PlannerBase;
import cz.cuni.mff.jpddl.tools.search.bench.Timed;
import cz.cuni.mff.jpddl.tools.utils.CSV;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.utils.IEventSelector;
import cz.cuni.mff.jpddl.utils.StateCompact;

import java.io.File;
import java.util.*;

public class LamaRun_Escape {

    public static enum LamaRunResult {
        /**
         * We got to the goal state.
         */
        GOAL_ACHIEVED,

        /**
         * Maximum number of iterations were reached.
         */
        MAX_ITERATIONS_REACHED,

        /**
         * We got to the dead end.
         */
        DEAD_END

    }

    private boolean event_applied = false;

    private List<PDDLEffector> events = new ArrayList<PDDLEffector>();

    private int action = 0;

    private long planningMillis = 0;

    Timed allTime = new Timed();
    Timed planningTime = new Timed();
    Timed validationTime = new Timed();

    PDDLProblem problem = null;
    File domainFile = null;
    File flatDomainFile = null;
    PlannerBase lama = null;
    int plannerCalls = 0;

    boolean useActionCost = false;

    private List<PDDLStringInstance> plan(boolean useCost) {
        System.out.println("  +-- Planning...");
        File problemFile = new File("problem.pddl");
        if (useCost) {
            problem.createProblemFileCost(problemFile, problem.getState());
        } else {
            problem.createProblemFile(problemFile, problem.getState());
        }
        planningTime.start();
        plannerCalls++;
        List<PDDLStringInstance> lamaPlan = lama.plan(domainFile, problemFile);
        planningTime.end();
        planningMillis += planningTime.durationMillis;
        problemFile.delete();
        return lamaPlan;
    }

    private void simulateEvent(PDDLProblem problem, Random random, IEventSelector eventSelector) {
        // COLLECT APPLICABLE EVENTS
        event_applied = false;
        events.clear();
        events.add(null); // add NO-EVENT
        problem.getApplicables().collectApplicableEvents(problem.getDomain(), problem.getState(), events);

        Collections.shuffle(events); // randomize

        List<PDDLEffector> selected_events = eventSelector.select(problem, events);

        for (PDDLEffector ev : selected_events) {
            ev.apply(problem.getState());
            event_applied = true;
            System.out.println("    +-- EVENT [" + action + ".]: " + ev.toEffector());
        }

        if (!event_applied)
            System.out.println("    +-- EVENT [" + action + ".]: no-event");
    }

    public void run(String id, int run, PDDLProblem problem, PlanChecker planChecker, IPlanValidator validator, int maxIterations, long randomSeed, File csvOutputFile, IEventSelector event_selector, boolean useActionCost) {
        StateCompact initialState = problem.getState().getDynamic().clone();

        this.useActionCost = useActionCost;

        Random random = new Random(randomSeed);

        long validatingMillis = 0;
        allTime.start();

        System.out.println();
        System.out.println("---------");
        System.out.println("LAMA RUN: " + id);
        System.out.println("---------");
        System.out.println("  +-- Validator: " + validator.getDescription());

        // RESET
        events.clear();
        action = 0;

        this.problem = problem;

        flatDomainFile = problem.getDomain().getDomainFlatFile();
        if (!useActionCost) {
            lama = new Lama();
            domainFile = problem.getDomain().getDomainPureFile();
        }
        else {
            lama = new LamaCost();
            domainFile = problem.getDomain().getDomainCostFile();
        }

        // INTERNAL VARS
        int iteration = 0;

        // RESULT
        LamaRunResult result = LamaRunResult.MAX_ITERATIONS_REACHED;

        List<PDDLStringInstance> lamaPlan = plan(useActionCost);
        System.out.println(problem.dang());

        while (true) {

            System.out.println("dang:" + problem.dang());

            if (problem.getGoal().isAchieved(problem.getState())) {
                System.out.println("  +-- GOAL ACHIEVED!");
                result = LamaRunResult.GOAL_ACHIEVED;
                break;
            }

            if (iteration >= maxIterations) {
                result = LamaRunResult.MAX_ITERATIONS_REACHED;
                break;
            }

            iteration++;
            System.out.println("ITERATION " + iteration);

            if (problem.getDeadEnd().isDeadEnd(problem.getState())) {
                System.out.println("  +-- GOT INTO THE DEAD END STATE!");
                result = LamaRunResult.DEAD_END;
                break;
            } else {
                System.out.println("  +-- Not in dead-end state.");
            }

            if (lamaPlan != null && !lamaPlan.isEmpty()) {
                PDDLEffector[] pl = problem.getDomain().toEffectors(lamaPlan.toArray(new PDDLStringInstance[0]));
                if (!pl[0].isApplicable(problem.getState())) { // plan not applicable -> restart iteration
                    System.out.println("  +-- Plan is not applicable -> replan.");
                    lamaPlan = null;
                }
            }

            if (lamaPlan != null && !lamaPlan.isEmpty()) {
                PDDLEffector[] pl = problem.getDomain().toEffectors(lamaPlan.toArray(new PDDLStringInstance[0]));
                int dang = problem.dang(pl[0]);
                if (dang < 2) {
                    System.out.println("  +-- Next state is dangerous, dang: " + dang + " current dang: " + problem.dang());
                    System.out.println("  +-- Reactive escape... ");
                    List<PDDLEffector> actions = new ArrayList<>();
                    problem.applicables.collectApplicableActions(problem.getDomain(), problem.getState(), actions);
                    PDDLEffector beff = null;
                    while (problem.dang() < 2) {
                        int bdang = Integer.MIN_VALUE;
                        for (PDDLEffector eff : actions) {
                            dang = problem.dang(eff);
                            if (dang > bdang) {
                                bdang = dang;
                                beff = eff;
                            }
                        }
                        System.out.println("  +-- Performing best action: " + beff.toEffector());
                        beff.apply(problem.getState());
                        action++;
                        simulateEvent(problem, random, event_selector);
                        iteration++;
                    }
                    lamaPlan = null;
                }
                else {
                    System.out.println("  +-- Next state is safe, dang: " + dang);
                }
            }

            // PLAN
            if (lamaPlan == null || lamaPlan.isEmpty()) {
                lamaPlan = plan(useActionCost);
            }
            if (lamaPlan == null) {
                System.out.println("  +-- LAMA FAILED TO FIND THE PLAN!");
                simulateEvent(problem, random, event_selector);
            } else {
                // just obtained a new plan, no events happened yet
                event_applied = false;
                // TRANSLATE PLAN
                PDDLEffector[] plan = problem.getDomain().toEffectors(lamaPlan.toArray(new PDDLStringInstance[0]));
                System.out.println("  +-- Plan has " + plan.length + " steps");

                if (!plan[0].isApplicable(problem.getState())) {
                    System.out.println("    +-- Action: " + plan[0].toEffector() + " is NOT APPLICABLE, re-planning!");
                    iteration--; 	 // An event made the first action non-applicable -> replan
                    lamaPlan = null; // (and do not increase iteration number, we made no steps)
                }
                else {

                    System.out.println("  +-- Executing first action of plan");
                    if (problem.dang(plan[0]) < 2) {
                        System.out.println("    +-- Action " + plan[0].toEffector() + " would be dangerous and I am safe - not moving");
                    }
                    else {
                        System.out.println("    +-- ACTION[" + (++action) + ".]: " + plan[0].toEffector());
                        plan[0].apply(problem.getState());
                        lamaPlan.remove(0);
                        if (problem.getGoal().isAchieved(problem.getState())) {
                            System.out.println("    +-- GOAL ACHIEVED!");
                            result = LamaRunResult.GOAL_ACHIEVED;
                            break;
                        }
                    }
                    simulateEvent(problem, random, event_selector);

                }
            }
        }

        System.out.println("LamaRun Finished");
        System.out.println("  +-- result " + result);
        System.out.println("  +-- iterations " + iteration);
        System.out.println("  +-- total actions " + action);
        allTime.end();

        long simulationMillis = allTime.durationMillis - planningMillis - validatingMillis;

        System.out.println("  +-- total time   " + Timed.getTimeString(allTime.durationMillis));
        System.out.println("    +-- planning   " + Timed.getTimeString(planningMillis));
        System.out.println("    +-- validation " + Timed.getTimeString(validatingMillis));
        System.out.println("    +-- simulation " + Timed.getTimeString(simulationMillis));

        outputToCSV(csvOutputFile, id, run, problem, validator, result, iteration, allTime.durationMillis, planningMillis, validatingMillis, simulationMillis, randomSeed, maxIterations);

        problem.getState().setDynamic(initialState);
    }

    private void outputToCSV(File csvOutputFile, String id, int run, PDDLProblem problem, IPlanValidator validator, LamaRunResult result, int iterations,
                             long durationMillis, long planningMillis, long validatingMillis, long simulationMillis, long randomSeed,
                             int maxIterations) {

        System.out.println("  +-- appending result into " + csvOutputFile.getAbsolutePath());

        Date now = Calendar.getInstance().getTime();

        CSV.appendCSVRow(csvOutputFile,
                new String[] {"date", "id", "run", "problem",            "validator",                 "result", "iterations", "durationMillis", "planningMillis", "validatingMillis", "simulationMillis", "randomSeed", "maxIterations", "actions", "algorithm", "plannerCalls"},
                now,    id,   run,   problem.getClass(),   validator.getDescription(),  result,   iterations,   durationMillis,   planningMillis,   validatingMillis,   simulationMillis,   randomSeed,   maxIterations, action, "C1" + (useActionCost ? "C":""), plannerCalls
        );

    }

}
