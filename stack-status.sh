#!/bin/bash
sudo netstat -plnt | grep -E 'redis-server|nginx|java'
