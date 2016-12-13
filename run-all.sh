#!/bin/bash
bash modfiles.sh
./run-redis.sh
sleep 5
./run-backend.sh
./run-frontend.sh
