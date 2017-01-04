#!/bin/bash
name="backend"
sudo kill $(cat $name.pid)
sudo rm $name.pid
