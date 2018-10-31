#!/bin/bash

set -x

python3 ../fast-downward.py --alias lama-first --plan-file $3 $1 $2 &>result.txt
