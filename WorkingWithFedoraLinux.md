# Introduction #

This page shares experiences, tips for developers want to use Fedora/Rehat Linux (RH) in programming


# Details #

## How to setup JDK for RH ##
1) Open terminal with root permission

2) Create a script to set system environment for JDK by commands: touch and vi

touch /etc/profile.d/jdk.sh

vi /etc/profile.d/jdk.sh


Input the content for jdk.sh:

export JAVA\_HOME=/opt/jdk1.6.0\_24

export PATH=$JAVA\_HOME:$JAVA\_HOME/bin


3) Set executable permission for file jdk.sh by command chmod

chmod +x /etc/profile.d/jdk.sh