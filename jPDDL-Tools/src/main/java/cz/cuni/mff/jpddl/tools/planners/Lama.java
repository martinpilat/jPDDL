package cz.cuni.mff.jpddl.tools.planners;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.io.FileUtils;

import cz.cuni.mff.jpddl.PDDLStringEffector;

public class Lama extends PlannerBase {

	protected String fdDir = "../fast-downward/";

	protected File lamaWorkingDir;

	public Lama() {
	    prepareEnvironment();
    }

	@Override
	public List<PDDLStringEffector> plan(File domainFile, File problemFile) {

	    try {
            File resultFile = new File(lamaWorkingDir, "plan.sol");

            FileUtils.copyFile(domainFile, new File(lamaWorkingDir, "domain.pddl"));
            FileUtils.copyFile(problemFile, new File(lamaWorkingDir, "problem.pddl"));

            Map<String, String> config = new HashMap<String, String>();
            config.put("domain", "domain.pddl");
            config.put("problem", "problem.pddl");
            config.put("result", "plan.sol");

            CommandLine commandLine = new CommandLine("bash");
            commandLine.addArgument("lama.sh");
            commandLine.addArgument("${domain}");
            commandLine.addArgument("${problem}");
            commandLine.addArgument("${result}");
            commandLine.setSubstitutionMap(config);

            final Executor executor = new DefaultExecutor();
            executor.setWorkingDirectory(lamaWorkingDir);
            executor.setExitValue(0);

            // SYNC EXECUTION
            try {
                executor.execute(commandLine);
            } catch (ExecuteException e) {
                // FAILED TO EXECUTE THE PLANNER
                // => cannot be distinguished from "no plan exists"
                //            String path = lamaWorkingDir.getCanonicalPath();
                //            File crashDir = new File("lamaCrashes/" + path.substring(path.length() - 40, path.length()));
                //            FileUtils.copyDirectory(lamaWorkingDir, crashDir);
                //            System.err.print(crashDir.getCanonicalPath());
                //            e.printStackTrace();
                return null;
            }

            // NOW RESULT FILE SHOULD BE READY
            if (!resultFile.exists()) {
                // TODO: logging
                // nplan failed to produce results
                System.out.println("plan not found");
                return null;
            }

            // PROCESS RESULT
            String resultLines = FileUtils.readFileToString(resultFile);

            // add step numbers, lama does not include them
            String[] lines = resultLines.split("[\n\r]+");
            StringBuilder resLines = new StringBuilder();

            for (int i = 0; i < lines.length; i++) {
                if (!lines[i].contains(";"))
                    resLines.append(i).append(" : ").append(lines[i]).append("\n");
            }

            resultLines = resLines.toString();

            //rename both moves to "move"
            resultLines = resultLines.replaceAll("move[12]", "move");

            System.out.println("PLAN");
            System.out.println(resultLines);

            FileUtils.deleteQuietly(new File(lamaWorkingDir, "output.sas"));
            resultFile.delete();

            return parseLines(resultLines);
        }
        catch (IOException e) {
	        e.printStackTrace();
        }

		// PARSE LINES AS PDDL ACTION
		return null;

	}

	public void prepareEnvironment() {

		lamaWorkingDir = new File(this.fdDir, "tmp");
		lamaWorkingDir.mkdirs();
		lamaWorkingDir.deleteOnExit();

		try {
			File prepareFile = new File(fdDir, "prepare.sh");
			FileUtils.copyFile(prepareFile, new File(lamaWorkingDir, "prepare.sh"));

			CommandLine commandLine = new CommandLine("bash");
			commandLine.addArgument("prepare.sh");
			commandLine.addArgument("../");

			DefaultExecutor executor = new DefaultExecutor();
			executor.setWorkingDirectory(lamaWorkingDir);
			executor.execute(commandLine);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<PDDLStringEffector> parseLines(String lines) {
		if (lines == null || lines.trim().length() == 0) {
			return null;
		}

		String[] parts = lines.split("\n");
		List<PDDLStringEffector> result = new ArrayList<PDDLStringEffector>(parts.length);
		for (String line : parts) {
			if (line.endsWith("\r")) line = line.substring(0, line.length()-1);
			result.add(parseLine(line));
		}

		return result;
	}

	/**
	 * 0 : (pickup_sword r1)
	 * 1 : (move r1 r2)
	 * 2 : (kill r2)
	 * 3 : (drop_sword r2)
	 * 4 : (move r2 r3)
	 * 5 : (disarm r3)
	 * 6 : (move r3 r4)
	 */

	protected PDDLStringEffector parseLine(String line) {
		String action = line.split(":")[1].trim(); 	// split number from action
		action = action.substring(1, action.length() - 1); 	// remove ()
		String[] tokens = action.split(" ");
		String action_name = tokens[0];
		List<String> args = new LinkedList<String>(Arrays.asList(tokens));
		args.remove(0);

		return new PDDLStringEffector(action_name, args);


	}
}
