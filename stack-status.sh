#!/bin/bash
ps -p $(cat frontend.pid)
ps -p $(cat backend.pid)
ps -p $(cat redis.pid)
