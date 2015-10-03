# Introduction #

This page provides guidelines to compile, pack and extend the Central Authentication System (http://www.jasig.org/cas/download)


# Details #

## Prepare the development environment ##
- JDK 6, Tomcat (Refer: [Hands-on Lab\_Install JDK and Tomcat\_v1.1.pdf](https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B4vjYnf6j5_4ZDMzY2NhM2MtMDBlMy00OWE1LWE4MDUtNGNkMWJlYzczMjAy&hl=en_US))

(Assumption the Tomcat package is at D:\jPackages\apache-tomcat-6.0.26)

## Download and deploy ##
Step 1) Download the CAS package at "http://downloads.jasig.org/cas/cas-server-3.4.8-release.tar.gz". Uncompress (suggest using 7zip at http://7zip.org) it into D:/jPackages

Step 2) Copy file "D:\jPackages\cas-server-3.4.8\modules\cas-server-webapp-3.4.8.war" into D:\jPackages\apache-tomcat-6.0.26\webapps\cas.war (rename file into cas.war)

Step 3) Start the tomcat by run D:\jPackages\apache-tomcat-6.0.26\bin\startup.bat

Step 4) Browse URL http://localhost:8080/cas to test cas.
Login by the use name and password are the same, it will successful.

Now, you have deployed the default cas version 3.4.8 into the tomcat 6.0.26.

Next exercise, you will practice by create your own web application which will use cas for Authentication