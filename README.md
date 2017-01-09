# Redis Workshop

Workshop setup instructuiosn for the [January, 19th 2017 Greater Milwaukee Java Meetup](https://www.meetup.com/Greater-Milwaukee-Java-Meetup/events/235904449/).

[Presentation & Workshop Guide](https://docs.google.com/presentation/d/156sNIo9jzVETF_gnxzWMOueuHzBZcvhA8t3X78C397M/edit?usp=sharing)

# Prerequisites

To keep development environments consistent, this workshop uses Vagrant.  Vagrant provisions and configures a virtual machine with all the necessary libraries installed.

* Roughly 8 GB of free disk space
* Git
* [Virtualbox](https://www.virtualbox.org/wiki/Downloads) >= v5.1.8
* [Vagrant](https://www.vagrantup.com/downloads.html) >= 1.8.6
* Java code editor of choice.

>:warning: Be sure to install Vagrant AND Virtualbox.  Virtualbox is the VM provider for Vagrant.  A common mistake can be to only install Vagrant.

# Setup

Pull the project code, and run Vagrant.  This will take awhile as it downloads the ubuntu/xenial64 image, Java, Nginx, and Redis.
```bash
$ cd ~/workspace
$ git clone https://github.com/GreaterMKEMeetup/redis-workshop.git
$ cd redis-workshop
$ vagrant up
```
Note:  Vagrant automatically syncs the directory where the Vagrant file is on the host machine with the guest machine's /vagrant directory.  All run, kill, and status scripts must be run from within the virtual machine.

## Verify

Go to your browser, and visit http://localhost:8000/

![Catopia Snapsot](http://i.imgur.com/jyCegkh.png)

The banner text was loaded and served by Redis.  Congrats, the stack is working!

### Troubleshoot
If you were unable to verify the stack is working, start here.

Log into the virtual machine, and run the stack-status.sh script.  You should see the following.
Note, the java process may take a minute to come up, as it must download all the dependencies before it starts.

```bash
$ cd ~/workspace/redis-workshop
$ vagrant ssh
ubuntu@ubuntu-xenial:/vagrant$ cd /vagrant
ubuntu@ubuntu-xenial:/vagrant$ ./stack-status.sh
tcp        0      0 127.0.0.1:6379          0.0.0.0:*               LISTEN      10643/redis-server
tcp        0      0 0.0.0.0:8000            0.0.0.0:*               LISTEN      9856/python
tcp6       0      0 :::8080                 :::*                    LISTEN      9904/java
```
If any of the above ports don't have a service listening on them, something went wrong.  Check the following logs:
/vagrant/backend.log
/var/log/nginx/error.log
/var/log/redis/redis-server.log

## Networking
The Vagrant file maps the following ports from the guest machine to the host machine.

Proxy: localhost:8000  
Backend: localhost:8080  
Redis: localhost:6397  

----
# Good Job!
You are all set to develop locally!

# Cleanup
```bash
$ cd ~/workspace/redis-workshop
$ vagrant destroy
$ cd ..
$ rm -rf redis-workshop
```

