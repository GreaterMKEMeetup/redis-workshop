#!/bin/bash
name="frontend"
sudo kill $(cat $name.pid)
sudo rm $name.pid
