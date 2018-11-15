package cz.cuni.mff.jpddl.console;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

import cz.cuni.mff.auv.problem1.Problem;
import cz.cuni.mff.jpddl.PDDLProblem;
import cz.cuni.mff.jpddl.tools.planners.Lama;
import cz.cuni.mff.jpddl.tools.search.bench.Timed;
import cz.cuni.mff.jpddl.tools.simulations.LamaSimulation;
import cz.cuni.mff.jpddl.tools.utils.Randoms;
import cz.cuni.mff.jpddl.tools.validators.IPlanValidator;
import cz.cuni.mff.jpddl.tools.validators.PlanChecker;
import cz.cuni.mff.jpddl.tools.validators.PlanTester;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterBFS;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterDFS;
import cz.cuni.mff.jpddl.tools.validators.PlanTesterFlat;
import cz.cuni.mff.jpddl.tools.validators.SafeStates;

/**
 * Console-app frontend for the {@link ExperimentEvaluator}.
 * 
 * @author Jimmy
 */
public class Main {
	
	private static final char ARG_FAST_DOWNWARD_DIR_SHORT = 'f';
	
	private static final String ARG_FAST_DOWNWARD_DIR_LONG = "fast-downward-dir";
	
	private static final char ARG_FAST_DOWNWARD_EXEC_SHORT = 'd';
	
	private static final String ARG_FAST_DOWNWARD_EXEC_LONG = "fast-downward-exec";
	
	private static final char ARG_SIMULATION_ID_SHORT = 'i';
	
	private static final String ARG_SIMULATION_ID_LONG = "simuation-id";
	
	private static final char ARG_PROBLEM_CLASS_SHORT = 'p';
	
	private static final String ARG_PROBLEM_CLASS_LONG = "problem-class-fqcn";
	
	private static final char ARG_VALIDATOR_SHORT = 'v';
	
	private static final String ARG_VALIDATOR_LONG = "validator";
	
	private static final char ARG_MAX_ITERATIONS_SHORT = 'm';
	
	private static final String ARG_MAX_ITERATIONS_LONG = "max-iterations";

	private static final char ARG_RANDOM_SEED_SHORT = 'r';
	
	private static final String ARG_RANDOM_SEED_LONG = "random-seed";
	
	private static final char ARG_RESULTS_CSV_FILE_SHORT = 'c';
	
	private static final String ARG_RESULTS_CSV_FILE_LONG = "results-csv-file";
	
	private static final char ARG_RUNS_SHORT = 'a';
	
	private static final String ARG_RUNS_LONG = "total-runs";
	
	private static final char ARG_SAFE_STATES_FILE_SHORT = 's';
	
	private static final String ARG_SAFE_STATES_FILE_LONG = "safe-states-file";
		
	private static JSAP jsap;

	private static String fastDownwardDirString;
	
	private static File fastDownwardDir;
	
	private static String fastDownwardExecString;
	
	private static File fastDownwardExec;
	
	private static String simulationId;
	
	private static String problemClassFQCN;
	
	private static Class problemClass;
	
	private static PDDLProblem problem;
	
	private static String validatorString;
	
	private static IPlanValidator validator;
	
	private static int maxIterations;
	
	private static long randomSeed;

	private static String resultsCSVFileString;
	
	private static File resultsCSVFile;
	
	private static int totalRuns;
	
	private static String safeStatesFileString;
	
	private static File safeStatesFile;
	
	private static SafeStates safeStates;
	
	private static PlanChecker planChecker;
	
	private static boolean headerOutput = false;

	private static JSAPResult config;
	
	private static final Map<String, IPlanValidator> VALIDATORS = new HashMap<String, IPlanValidator>();
	
	static {
		IPlanValidator validator;
		
		validator = new PlanChecker();
		VALIDATORS.put("CHECKER", validator);
		
		validator = new PlanTester();
		VALIDATORS.put("RNG", validator);
		
		validator = new PlanTesterDFS();
		VALIDATORS.put("DFS", validator);
		
		validator = new PlanTesterBFS();
		VALIDATORS.put("BFS", validator);
		
		validator = new PlanTesterFlat();
		VALIDATORS.put("FLAT", validator);
	}

	private static void fail(String errorMessage) {
		fail(errorMessage, null);
	}

	private static void fail(String errorMessage, Throwable e) {
		header();
		System.out.println("ERROR: " + errorMessage);
		System.out.println();
		if (e != null) {
			e.printStackTrace();
			System.out.println("");
		}		
        System.out.println("Usage: java -jar adventure.jar ");
        System.out.println("                " + jsap.getUsage());
        System.out.println();
        System.out.println(jsap.getHelp());
        System.out.println();
        throw new RuntimeException("FAILURE: " + errorMessage);
	}

	private static void header() {
		if (headerOutput) return;
		System.out.println();
		System.out.println("=================================");
		System.out.println("jPDDL Console - Simulation Runner");
		System.out.println("=================================");
		System.out.println();
		System.out.println("This program is running LamaSimulation with specified settings.");
		System.out.println();
		headerOutput = true;
	}
		
	private static void initJSAP() throws JSAPException {
		jsap = new JSAP();
		
		FlaggedOption opt1 = new FlaggedOption(ARG_FAST_DOWNWARD_DIR_LONG)
	    	.setStringParser(JSAP.STRING_PARSER)
	    	.setRequired(false)
	    	.setDefault(Lama.fdDir)
	    	.setShortFlag(ARG_FAST_DOWNWARD_DIR_SHORT)
	    	.setLongFlag(ARG_FAST_DOWNWARD_DIR_LONG);    
	    opt1.setHelp("Directory containing compiled FastDownward.");
	
	    jsap.registerParameter(opt1);
	    
	    FlaggedOption opt2 = new FlaggedOption(ARG_FAST_DOWNWARD_EXEC_LONG)
	    	.setStringParser(JSAP.STRING_PARSER)
	    	.setRequired(false)
	    	.setDefault(Lama.fdExec)
	    	.setShortFlag(ARG_FAST_DOWNWARD_EXEC_SHORT)
	    	.setLongFlag(ARG_FAST_DOWNWARD_EXEC_LONG);    
	    opt2.setHelp("Relative path to fast-downward-dir pointing to the shell script that runs FD.");
	
	    jsap.registerParameter(opt2);
		    	
	    FlaggedOption opt3 = new FlaggedOption(ARG_SIMULATION_ID_LONG)
	    	.setStringParser(JSAP.STRING_PARSER)
	    	.setRequired(false)
	    	.setDefault("SimLama")
	    	.setShortFlag(ARG_SIMULATION_ID_SHORT)
	    	.setLongFlag(ARG_SIMULATION_ID_LONG);    
	    opt3.setHelp("Id of the simulation that is reported into CSV file.");
	    
	    jsap.registerParameter(opt3);
	    
	    FlaggedOption opt5 = new FlaggedOption(ARG_PROBLEM_CLASS_LONG)
		    	.setStringParser(JSAP.STRING_PARSER)
		    	.setRequired(false)
		    	.setDefault("cz.cuni.mff.auv.problem1.Problem")
		    	.setShortFlag(ARG_PROBLEM_CLASS_SHORT)
		    	.setLongFlag(ARG_PROBLEM_CLASS_LONG);    
	    opt5.setHelp("FQCN of the problem to instantiate and run..");
	    
	    jsap.registerParameter(opt5);
	    
	    FlaggedOption opt4 = new FlaggedOption(ARG_VALIDATOR_LONG)
	    	.setStringParser(JSAP.STRING_PARSER)
	    	.setRequired(false)
	    	.setDefault("FLAT")
	    	.setShortFlag(ARG_VALIDATOR_SHORT)
	    	.setLongFlag(ARG_VALIDATOR_LONG);    
	    
	    String valid = "";
	    for (String key : VALIDATORS.keySet()) {
	    	if (valid.length() > 0) valid += ", ";
	    	valid += key;
	    }
	    opt4.setHelp("Validator to be used with the planner, possible options: " + valid + ".");
	    
	    jsap.registerParameter(opt4);
	    
	    FlaggedOption opt44 = new FlaggedOption(ARG_MAX_ITERATIONS_LONG)
	    		.setStringParser(JSAP.INTEGER_PARSER)
	    		.setRequired(false)
		    	.setDefault("50")
		    	.setShortFlag(ARG_MAX_ITERATIONS_SHORT)
		    	.setLongFlag(ARG_MAX_ITERATIONS_LONG);    
		opt44.setHelp("Maximum number of plan/apply iterations to try.");
		    
		jsap.registerParameter(opt44);
	    
	    FlaggedOption opt6 = new FlaggedOption(ARG_RANDOM_SEED_LONG)
				.setStringParser(JSAP.LONG_PARSER)
				.setRequired(false)
				.setDefault("" + System.currentTimeMillis())
				.setShortFlag(ARG_RANDOM_SEED_SHORT)
				.setLongFlag(ARG_RANDOM_SEED_LONG);
	    opt6.setHelp("Random seed to be used, default: System.currentTimeMillis().");

	    jsap.registerParameter(opt6);
	    
	    FlaggedOption opt7 = new FlaggedOption(ARG_RESULTS_CSV_FILE_LONG)
				.setStringParser(JSAP.STRING_PARSER)
				.setRequired(false)
				.setDefault("results.csv")
				.setShortFlag(ARG_RESULTS_CSV_FILE_SHORT)
				.setLongFlag(ARG_RESULTS_CSV_FILE_LONG);
	    opt7.setHelp("File where to output the the results.");

	    jsap.registerParameter(opt7);
	    
	    FlaggedOption opt444 = new FlaggedOption(ARG_RUNS_LONG)
	    		.setStringParser(JSAP.INTEGER_PARSER)
	    		.setRequired(false)
		    	.setDefault("10")
		    	.setShortFlag(ARG_RUNS_SHORT)
		    	.setLongFlag(ARG_RUNS_LONG);    
		opt444.setHelp("Total number of runs to perform with the given settings.");
		    
		jsap.registerParameter(opt444);
		
		FlaggedOption opt544 = new FlaggedOption(ARG_SAFE_STATES_FILE_LONG)
	    		.setStringParser(JSAP.STRING_PARSER)
	    		.setRequired(true)
		    	.setShortFlag(ARG_SAFE_STATES_FILE_SHORT)
		    	.setLongFlag(ARG_SAFE_STATES_FILE_LONG);    
		opt544.setHelp("File containing list of safe states.");
		    
		jsap.registerParameter(opt544);
	    
   	}

	private static void readConfig(String[] args) {
		System.out.println("Parsing command arguments.");
		
		try {
	    	config = jsap.parse(args);
	    } catch (Exception e) {
	    	fail(e.getMessage());
	    	System.out.println("");
	    	e.printStackTrace();
	    	throw new RuntimeException("FAILURE!");
	    }
		
		if (!config.success()) {
			String error = "Invalid arguments specified.";
			Iterator errorIter = config.getErrorMessageIterator();
			if (!errorIter.hasNext()) {
				error += "\n-- No details given.";
			} else {
				while (errorIter.hasNext()) {
					error += "\n-- " + errorIter.next();
				}
			}
			fail(error);
    	}

		fastDownwardDirString = config.getString(ARG_FAST_DOWNWARD_DIR_LONG);
		
		fastDownwardExecString = config.getString(ARG_FAST_DOWNWARD_EXEC_LONG);
		
		simulationId = config.getString(ARG_SIMULATION_ID_LONG);
		
		problemClassFQCN = config.getString(ARG_PROBLEM_CLASS_LONG);
		
		validatorString = config.getString(ARG_VALIDATOR_LONG);
		
		maxIterations = config.getInt(ARG_MAX_ITERATIONS_LONG);
		
		randomSeed = config.getLong(ARG_RANDOM_SEED_LONG);

		resultsCSVFileString = config.getString(ARG_RESULTS_CSV_FILE_LONG);
		
		totalRuns = config.getInt(ARG_RUNS_LONG);
		
		safeStatesFileString = config.getString(ARG_SAFE_STATES_FILE_LONG);
	}
	
	private static void sanityChecks() {
		System.out.println("Sanity checks...");
		
		fastDownwardDir = new File(fastDownwardDirString);
		System.out.println("-- FastDownward dir: " + fastDownwardDirString + " --> " + fastDownwardDir.getAbsolutePath());		
		if (!fastDownwardDir.exists()) {
			fail("Directory with FastDownward does not exist at '" + fastDownwardDirString + "', resolved as: " + fastDownwardDir.getAbsolutePath());
		}
		if (!fastDownwardDir.isDirectory()) {
			fail("Directory with FastDownward is not a directory at '" + fastDownwardDirString + "', resolved as: " + fastDownwardDir.getAbsolutePath());
		}
		System.out.println("---- FastDownward directory exists, ok");
		
//		fastDownwardExec = new File(fastDownwardDir, fastDownwardExecString);
//		System.out.println("-- FastDownward exec file: " + fastDownwardDirString + "/" + fastDownwardExecString + " --> " + fastDownwardExec.getAbsolutePath());
		System.out.println("-- FastDownward exec file: " + fastDownwardExecString);
//		if (!fastDownwardExec.exists()) {
//			fail("Shell file executing FastDownward does not exist at '" + fastDownwardDirString + "/" + fastDownwardExecString + "', resolved as: " + fastDownwardExec.getAbsolutePath());
//		}
//		if (!fastDownwardExec.isFile()) {
//			fail("Shell file executing FastDownward is not a file at '" + fastDownwardDirString + "/" + fastDownwardExecString + "', resolved as: " + fastDownwardExec.getAbsolutePath());
//		}
		System.out.println("---- FastDownward exec file exists, ok");
		
		System.out.println("-- Simulation id: " + simulationId);
		
		System.out.println("-- Problem class FQCN: " + problemClassFQCN);		
		try {
			problemClass = Class.forName(problemClassFQCN);
		} catch (ClassNotFoundException e) {
			fail("Problem class not found: " + problemClassFQCN, e);
		}
		if (problemClass == null) {
			fail("Problem class not found: " + problemClassFQCN);
		}
		System.out.println("---- Resolved.");
		
		try {
			problem = (PDDLProblem)problemClass.getConstructor().newInstance();
		} catch (Exception e) {
			fail("Failed to instantiate: " + problemClass, e);
		}
		if (problem == null) {
			fail("Failed to instantiate: " + problemClass);
		}
		
		System.out.println("-- Validator: " + validatorString);
		validator = VALIDATORS.get(validatorString);
		if (validator == null) {
			fail("Unrecognized validator value: " + validatorString);
		}
		
		System.out.println("-- Max iterations set to: " + maxIterations);
		if (maxIterations < 1) {
			fail("Invalid number of iterations = " + maxIterations + " < 1 specified.");
		}
		
		System.out.println("-- Random seed set to: " + randomSeed);
		
		resultsCSVFile = new File(resultsCSVFileString);
		System.out.println("-- Results CSV file: " + resultsCSVFileString + " --> " + resultsCSVFile.getAbsolutePath());
		
		if (resultsCSVFile.getParentFile() != null && !resultsCSVFile.getParentFile().exists()) {		
			System.out.println("---- Parent dir does not exist, creating.");
			if (!resultsCSVFile.getParentFile().mkdirs() && !resultsCSVFile.getParentFile().exists()) {
				fail("Failed to create parent directories for: " + resultsCSVFile.getAbsolutePath());
			}
		}
		
		System.out.println("-- Total runs set to: " + totalRuns);
		if (totalRuns < 1) {
			fail("Invalid number of total runs = " + totalRuns + " < 1.");
		}
		
		safeStatesFile = new File(safeStatesFileString);
		System.out.println("-- Safe states file: " + safeStatesFileString + " --> " + safeStatesFile.getAbsolutePath());		
		if (!safeStatesFile.exists()) {
			fail("Safe states file does not exist at '" + safeStatesFileString + "', resolved as: " + safeStatesFile.getAbsolutePath());
		}
		if (!safeStatesFile.isFile()) {
			fail("Safe states file is not a file at at '" + safeStatesFileString + "', resolved as: " + safeStatesFile.getAbsolutePath());
		}
		System.out.println("---- Safe states file exist.");
		safeStates = new SafeStates(problem.getDomain(), safeStatesFile);
		System.out.println("---- Safe states file loaded, safe states count: " + safeStates.predicates.size());
		
		if (safeStatesFile == null) {
			fail("Selected plan validator (" + validatorString + ") requires a safe states file!");
		}
				
	    System.out.println("Sanity checks OK!");
	}
	
	// =====================
	// EXPERIMENT EVALUATION
	// =====================
	
	private static void evaluate() {
		
		System.out.println("Setting up the simulation...");
		
		System.out.println("-- setting up the master random seed: " + randomSeed);
		Randoms.setMasterSeed(randomSeed);
		
		System.out.println("-- setting FastDownward execution dir and shell script");
		Lama.fdDir = fastDownwardDirString;
		Lama.fdExec = fastDownwardExecString;
		
		System.out.println("-- configuring plan checker");
		planChecker = new PlanChecker();
		planChecker.config(problem.getDomain(), safeStates);
		
		
		System.out.println("-- configuring the plan validator");
		if (validatorString.equals("CHECKER")) {
			((PlanChecker)validator).config(problem.getDomain(), safeStates);
		} else
		if (validatorString.equals("RNG")) {
			((PlanTester)validator).config(problem.getDomain(), problem.getApplicables(), randomSeed, safeStates);
		} else
		if (validatorString.equals("DFS")) {
			((PlanTesterDFS)validator).config(problem.getDomain(), problem.getApplicables(), safeStates);
		} else
		if (validatorString.equals("BFS")) {
			((PlanTesterBFS)validator).config(problem.getDomain(), problem.getApplicables(), safeStates, 10);
		} else
		if (validatorString.equals("FLAT")) {
			((PlanTesterFlat)validator).config(problem.getDomain(), problem.getApplicables(), safeStates);
		} else {
			fail("Unhandled validator: " + validatorString);
		}
		
		System.out.println("-- creating the simulation");		
		LamaSimulation simulation = new LamaSimulation();
		
		System.out.println("-- RUNNING THE SIMULATION!");
		System.out.println();
		
		Timed time = new Timed();
		time.start();
		simulation.simulate(totalRuns, simulationId, problem, planChecker, validator, maxIterations, randomSeed, resultsCSVFile);
		time.end();
		
		System.out.println();
		System.out.println("SIMULATION TIME: " + Timed.getTimeString(time.durationMillis));
	}
	
	// ==============
	// TEST ARGUMENTS
	// ==============
	
	public static String[] getTestArgs() {
		return new String[] {
				   "-f", Lama.fdDir                   // Lama working dir
				  ,"-d", Lama.fdExec                  // Lama shell script relative to the dir
				  ,"-i", "ExampleSim"                 // simulation id
				  ,"-p", Problem.class.getName()      // FQCN of the problem to instantiate
				  ,"-v", "FLAT"					      // plan validator
				  ,"-m", "20"					      // max iterations per run to try
				  ,"-r", "1"                          // random seed
				  ,"-c", "results.csv"			      // results CSV file
				  ,"-a", "5"					      // total runs to perform
				  ,"-s", "../Domains/AUV/safe_states" // safe states file to load
		};
	}
	
	public static void main(String[] args) throws JSAPException {
		// -----------
		// FOR TESTING
		// -----------
		if (args.length == 0) {
			header();
			System.out.println("NO PARAMETERS SPECIFIED, using test arguments.");
			args = getTestArgs();		
		}

		// --------------
		// IMPLEMENTATION
		// --------------
		
		initJSAP();
	    
	    header();
	    
	    readConfig(args);
	    
	    sanityChecks();
	    
	    evaluate();
	    
	    System.out.println();
	    System.out.println("---// FINISHED //---");
	    System.out.println();
	    
	    System.exit(0);
	}

	

}
