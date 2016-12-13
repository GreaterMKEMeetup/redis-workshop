#!/bin/bash
name="redis"
kill $(cat $name.pid)
rm $name.pid
