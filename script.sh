#!/bin/sh
find . -iname "*.$1" -exec wc -l {} \; | awk '{sum+=$0;} END {print sum;}'


