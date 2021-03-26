# jPDDL

Implementation of the experiments published in the following papers:

> Lukáš Chrpa, Jakub Gemrot, Martin Pilát: "Planning and Acting with Non-deterministic Events: Navigating between Safe States". AAAI 2020.

> Lukáš Chrpa, Martin Pilát, Jakub Gemrot: "Compiling Planning Problems with Non-deterministic Events into FOND Planning". RCRA Workshop @ 18th International Conference of the Italian Association for Artificial Intelligence (AIIA 2019). CEUR-WS. 2019.

> Lukáš Chrpa, Martin Pilát, Jakub Gemrot: "Planning and Acting in Dynamic Environments:Identifying and Avoiding Dangerous Situations". Journal of Experimental & Theoretical Artificial Intelligence. 

# Files and directories

- `*-Problem`, `*-Domain` - contain the implementation of the problems described in the paper (BlocksWorld was used only in the RCRA paper)
- `Domains` - PDDL description of the domains
- `jPDDL*` - implementation of the simulator
- `lama.sh`, `prepare.sh` - helper scripts to run the planner from Java
- `results.xlsx` - the raw results of the experiments together with the pivot table used process them and the table that can be found in the paper

The algorithms (PER/DANG/LIMIT) can be found in `jPDDL-Tools/src/main/java/cz/cuni/mff/jpddl/tools/simulations/`. In order to run these, it is important to put the fast-downward binaries in a directory referenced in `jPDDL-Tools/src/main/java/cz/cuni/mff/jpddl/tools/planners/Lama.java` and also include the `lama.sh` file found in this directory in the path referenced in the same file.

# Additional resources

In the AAAI paper, we mention that we also tested compilation to FOND. This compilation and its detailed description is available in the RCRA workshop paper. Source codes for this compilation (and experiments performed for the paper) are available in [a separate repository](https://github.com/martinpilat/events-FOND).
