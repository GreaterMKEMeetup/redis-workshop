#!/bin/bash
name="frontend"
kill $(cat $name.pid)
rm $name.pid
