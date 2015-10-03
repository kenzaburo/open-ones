# Introduction #

This text helps you prepare an IDE Eclipse to develop Web Application, Google App and Android


# Details #
## 1. Download and install JDK 1.6 ##
## 2. Download customized Eclipse JEE ##
### 2.1 For Window ###

1) Download eclipse-jee-indigo-SR1-win32-OOG-0.3.zip.001 at: http://www.mediafire.com/file/n2tv1rwy59r6mcw/eclipse-jee-indigo-SR1-win32-OOG-0.3.zip.001

2) Download eclipse-jee-indigo-SR1-win32-OOG-0.3.zip.002 at: http://www.mediafire.com/file/9c78qg86gslp4cb/eclipse-jee-indigo-SR1-win32-OOG-0.3.zip.002

Use [7zip ](http://7zip.org) to combine and un-compress three above files (Assumption: you un-compress into D:/jPackages/eclipse-jee-indigo-SR1-win32-OOG-0.3. This folder is called $ECLIPSE\_HOME)

You can try to run the eclipse by execute $ECLIPSE\_HOME/eclipse/eclipse.exe.

### 2.1 For Linux (I'm using Fedora 15 + KDE) ###

1) http://www.mediafire.com/file/n8f8rbm77rh49tr/eclipse-jee-indigo-SR1-linux-gtk-OOG-0.2.tar.gzaa


2) http://www.mediafire.com/file/qbqya67x70kjl2b/eclipse-jee-indigo-SR1-linux-gtk-OOG-0.2.tar.gzab

Join these files by command:

| cat eclipse-jee-indigo-SR1-linux-gtk-OOG-0.2.tar.gz`*` > eclipse-jee-indigo-SR1-linux-gtk-OOG-0.2.gz |
|:-----------------------------------------------------------------------------------------------------|

Uncompress file eclipse-jee-indigo-SR1-linux-gtk-OOG-0.2.tar.gz the by command:

| tar -zxvf eclipse-jee-indigo-SR1-linux-gtk-OOG-0.2.tar.gz --directory ~/jPackages |
|:----------------------------------------------------------------------------------|

To re-use the configuration in folder "~/jPackages/eclipse-jee-indigo-SR1-linux-gtk-OOG-0.2/workspace", you must start the eclipse with working folder is ""~/jPackages/eclipse-jee-indigo-SR1-linux-gtk-OOG-0.2/eclipse".

_Note:_

~: Home directory of current user
If folder ~/jPackages has not existed, create it by command

| mkdir ~/jPackages |
|:------------------|



### 3. Configure JDK for eclipse ###
Default, the eclipse uses JRE. Now, you re-configure the eclipse to use JDK.

<a href='http://s1083.photobucket.com/albums/j387/thachln/?action=view&amp;current=JDK-Eclipse.png'><img src='http://i1083.photobucket.com/albums/j387/thachln/JDK-Eclipse.png' alt='Configure Eclipse using JDK' border='0'>