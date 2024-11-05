#!/bin/bash

set -e  # exit if failure

JAR_FILE="out/artifacts/jpddlplus_jar/jpddlplus.jar"

if [ ! -f $JAR_FILE ]; then
    echo "Jar file not found. Please build the project first."
    exit 1
fi

for config in sat-mq3h3n sat-mq3h sat-mq3n sat-hiqb2add sat-hiqb2mrphj sat-hmd; do
    echo "******************************"
    echo "Testing $config"
    echo "******************************"
    java -jar $JAR_FILE -o examples/counters/domain.pddl -f examples/counters/instance_12.pddl -planner $config
done
