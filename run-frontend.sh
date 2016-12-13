#!/bin/bash
name="frontend"
logfile="$(pwd)/$name.log"
pidfile="$(pwd)/$name.pid"
pushd ./catopia-frontend/site
python -m SimpleHTTPServer 8000 >> $logfile  2>&1 & echo $! > $pidfile
