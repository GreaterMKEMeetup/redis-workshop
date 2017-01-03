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

Pull the project code, and run Vagrant.  This will take awhile as it downloads the ubuntu/xenial64 image, and redis server.
```bash
cd ~/workspace
git clone https://github.com/GreaterMKEMeetup/redis-workshop.git 
vagrant up
```
## Verify
```bash
vagrant ssh
```
