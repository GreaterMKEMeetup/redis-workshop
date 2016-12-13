#!/bin/bash
name="backend"
kill $(cat $name.pid)
rm $name.pid
