#!/bin/bash
name="redis"
logfile="$(pwd)/$name.log"
pidfile="$(pwd)/$name.pid"
redis-server >> $logfile  2>&1 & echo $! > $pidfile
