#!/bin/bash

# reads a list of problem from the standard inputs (one per line) and runs all of them

# with action costs

set +x

while read -r problem; do
    for algName in REPLAN_APP REPLAN_EVENT C1 DANG
    do
      java -jar target/jpddl-console-0.0.1-SNAPSHOT-onejar.jar -i Perestroika-P1-VAL_FLAT -p cz.cuni.mff.perestroika.$problem.Problem -v FLAT -m 500 -r 1 -c results.csv -a 10 -s "../gen/safe_states/$problem" -f "../../Planners/fast-downward/" -u -l $algName
      java -jar target/jpddl-console-0.0.1-SNAPSHOT-onejar.jar -i Perestroika-P1-VAL_FLAT -p cz.cuni.mff.perestroika.$problem.Problem -v FLAT -m 500 -r 1 -c results.csv -a 10 -s "../gen/safe_states/$problem" -f "../../Planners/fast-downward/" -l $algName
  done
done