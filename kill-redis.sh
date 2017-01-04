#!/bin/bash
#!/bin/bash
name="redis"
sudo kill $(cat $name.pid)
sudo rm $name.pid
