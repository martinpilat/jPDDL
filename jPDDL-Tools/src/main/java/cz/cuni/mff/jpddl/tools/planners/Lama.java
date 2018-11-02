package cz.cuni.mff.jpddl.tools.planners;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.*;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.io.FileUtils;

import cz.cuni.mff.jpddl.PDDLStringInstance;

public class Lama extends PlannerBase {

	protected String fdDir = "../Planners/fast-downward/";
	
	protected String fdExec = "../../lama.sh"; // relative to $fdDir/tmp/dir 
	

	protected File lamaWorkingDir;

	public Lama() {
	    prepareEnvironment();
    }

	@Override
	public List<PDDLStringInstance> plan(File domainFile, File problemFile) {

	    try {
            File resultFile = new File(lamaWorkingDir, "plan.sol");

            FileUtils.copyFile(domainFile, new File(lamaWorkingDir, "domain.pddl"));
            FileUtils.copyFile(problemFile, new File(lamaWorkingDir, "problem.pddl"));

            Map<String, String> config = new HashMap<String, String>();
            config.put("domain", "domain.pddl");
            config.put("problem", "problem.pddl");
            config.put("result", "plan.sol");

            CommandLine commandLine = new CommandLine("bash");
            commandLine.addArgument(fdExec);
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
                System.out.println("plan not found");
                return null;
            }

            // PROCESS RESULT
            String resultLines = FileUtils.readFileToString(resultFile);

            FileUtils.deleteQuietly(new File(lamaWorkingDir, "output.sas"));
            //resultFile.delete();

            return parseLines(resultLines);
        }
        catch (IOException e) {
	        e.printStackTrace();
        }

		// PARSE LINES AS PDDL ACTION
		return null;

	}

	public void prepareEnvironment() {
            lamaWorkingDir = new File(fdDir, "tmp/" + System.currentTimeMillis());
            lamaWorkingDir.mkdirs();
            lamaWorkingDir.deleteOnExit();

//			File prepareFile = new File(fdDir, "prepare.sh");
//			FileUtils.copyFile(prepareFile, new File(lamaWorkingDir, "prepare.sh"));
//
//			CommandLine commandLine = new CommandLine("bash");
//			commandLine.addArgument("prepare.sh");
//			commandLine.addArgument("../../");

//			DefaultExecutor executor = new DefaultExecutor();
//			executor.setWorkingDirectory(lamaWorkingDir);
//			executor.execute(commandLine);
	}

	protected List<PDDLStringInstance> parseLines(String lines) {
		if (lines == null || lines.trim().length() == 0) {
			return null;
		}

		String[] parts = lines.split("\n");
		List<PDDLStringInstance> result = new ArrayList<PDDLStringInstance>(parts.length);
		for (String line : parts) {
			if (line.endsWith("\r")) line = line.substring(0, line.length()-1);
			PDDLStringInstance step = parseLine(line);
			if (step != null)
			    result.add(step);
		}

		return result;
	}


	protected PDDLStringInstance parseLine(String line) {
		String action = line.trim();

        if (action.startsWith(";"))
            return null;

        action = action.substring(1, action.length() - 1); 	// remove ()
		String[] tokens = action.split(" ");
		String action_name = tokens[0];
		List<String> args = new LinkedList<String>(Arrays.asList(tokens));
		args.remove(0);

		return new PDDLStringInstance(action_name, args);
	}
}
