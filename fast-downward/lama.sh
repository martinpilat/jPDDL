#!/bin/bash

set -x

python3 ../../fast-downward.py --build release64 --alias lama-first --plan-file $3 $1 $2 &>result.txt
