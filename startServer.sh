#!/usr/bin/env bash
pid=$(echo `jps -m |grep "WeatherServer"` | cut -d' ' -f 1)
echo "process_id:"
echo $pid
echo "kill current WeatherServer process"
kill -9 $pid
sleep 5
echo "start process WeatherServer"
CLASSPATH=$(cat cp.txt):target/classes
nohup java -Dlog4j.configurationFile=config/log4j2.xml -classpath ${CLASSPATH} WeatherServer > /dev/null &
pid=$(echo `jps -m |grep "WeatherServer"` | cut -d' ' -f 1)
echo "WeatherServer process is running with process_id:"
echo $pid