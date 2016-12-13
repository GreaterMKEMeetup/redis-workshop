#!/bin/bash
logfile="$(pwd)/frontend.log"
pidfile="$(pwd)/frontend.pid"
pushd ./catopia-frontend/site
python -m SimpleHTTPServer 8000 >> $logfile  2>&1 & echo $! > $pidfile
