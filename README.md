# Redis Workshop

Workshop setup instructuiosn for the [January, 19th 2017 Greater Milwaukee Java Meetup](https://www.meetup.com/Greater-Milwaukee-Java-Meetup/events/235904449/).

[Presentation & Workshop Guide](https://docs.google.com/presentation/d/156sNIo9jzVETF_gnxzWMOueuHzBZcvhA8t3X78C397M/edit?usp=sharing)

# Prerequisites

To keep development environments consistent, this workshop uses Vagrant.  Vagrant provisions and configures a virtual machine with all the necessary libraries installed.

* Roughly 8 GB of free disk space
* Git
* Virtualbox >= v5.1.8
* Vagrant >= 1.8.6
* Java code editor of choice.

# Setup

Pull the project code, and run Vagrant.  This will take awhile as it downloads the ubuntu/xenial64 image, Java, and Redis.
```bash
$ cd ~/workspace
$ git clone https://github.com/GreaterMKEMeetup/redis-workshop.git 
$ vagrant up
```
Note:  Vagrant automatically syncs the directory where the Vagrant file is on the host machine with the guest machine's /vagrant directory.  All run, kill, and status scripts must be run from within the virtual machine.

## Verify
Log into the virtual machine, and run the stack-status.sh script.  You should see the following.
```bash
$ vagrant ssh
ubuntu@ubuntu-xenial:/vagrant$ cd /vagrant
ubuntu@ubuntu-xenial:/vagrant$ ./stack-status.sh
tcp        0      0 127.0.0.1:6379          0.0.0.0:*               LISTEN      10643/redis-server
tcp        0      0 0.0.0.0:8000            0.0.0.0:*               LISTEN      9856/python
tcp6       0      0 :::8080                 :::*                    LISTEN      9904/java
```
If any of the above ports don't have a service listening on them, something went wrong.  Check the following logs:
/vagrant/frontend.log
/vagrant/backend.log
/var/log/redis/redis-server.log

## Networking
The Vagrant file maps the following ports from the guest machine to the host machine.  From your host machine, test the following URL in a browser: http://localhost:8080

The Catopia website should be displayed.

----
Congrats, you are all set to develop locally!


