#!/usr/bin/env bash
CLASSPATH=$(cat cp.txt):target/classes
while ! nc localhost 9090 > /dev/null 2>&1 < /dev/null; do
    echo "$(date) - waiting for server at localhost:9090..."
    sleep 1
done
java -classpath ${CLASSPATH} WeatherClient http://localhost:9090 db/db_airports.csv 1
