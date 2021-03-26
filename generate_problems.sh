#!/bin/bash

for GRID_SIZE in 5 10 15 20 25
do    
    for RES_RND in 0.1 0.2 0.3 0.4 0.5
    do
        for SHR_RND in 0.1 0.2 0.3 0.4 0.5
        do
            echo $GRID_SIZE-$RES_RND-$SHR_RND
            for PROB_NUM in 0 1 2 3 4 5 6 7 8 9 
            do
                python3 generator.py $GRID_SIZE 3 $RES_RND $SHR_RND $PROB_NUM > tmp.pddl
                python3 tojPDDL.py Domains/Perestroika/domain-actionsonly.pddl tmp.pddl
            done
        done
    done
done