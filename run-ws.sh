#!/usr/bin/env bash
rm -f  cp.txt
rm -rf target/*
rm -rf log/*

mvn test dependency:build-classpath -Dmdep.outputFile=cp.txt
CLASSPATH=$(cat cp.txt):target/classes
java -Dlog4j.configurationFile=config/log4j2.xml -classpath ${CLASSPATH} WeatherServer &


while ! nc localhost 9090 > /dev/null 2>&1 < /dev/null; do
    echo "$(date) - waiting for server at localhost:9090..."
    sleep 1
done

java -classpath ${CLASSPATH} WeatherClient http://localhost:9090/ db/lite_airports.dat 0



