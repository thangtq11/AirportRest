#!/usr/bin/env bash
pid=$(echo `jps -m |grep "WeatherServer"` | cut -d' ' -f 1)
echo "kill current WeatherServer process with process_id:" $pid
kill -9 $pid

