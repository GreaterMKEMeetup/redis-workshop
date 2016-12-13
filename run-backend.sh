#!/bin/bash
name="backend"
logfile="$(pwd)/$name.log"
pidfile="$(pwd)/$name.pid"
pushd ./catopia-backend
./gradlew bootRun >> $logfile  2>&1 & echo $! > $pidfile
