#!/usr/bin/env bash
CLASSPATH=$(cat cp.txt):target/classes
while ! nc localhost 9090 > /dev/null 2>&1 < /dev/null; do
    echo "$(date) - waiting for server at localhost:9090..."
    sleep 1
done
java -classpath ${CLASSPATH} AirportLoader http://localhost:9090 db/lite_airports.dat
