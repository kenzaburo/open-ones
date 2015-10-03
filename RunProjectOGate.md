# Introduction #

This text helps you run a Google App with eclipse.

# Details #
**A. Prepare the eclipse and Google App Engine SDK**

I) Prepare Eclipse IDE:

Refer http://code.google.com/p/open-ones/wiki/PrepareIDEEclipse

II) Download Google App Engine SDK 1.5.4 or later for Java version at:
http://code.google.com/p/googleappengine/downloads/list
Then, unzip it into D:\jPackages

III) Start eclipse by execute "D:\jPackages\eclipse-jee-helios-SR2-win32-OpenOnes-0.8\eclipse\eclipse.exe".
- Open Preferences by going to Window > Preferences
- Select node "Google > App Engine", click Add button. Then browse to folder "D:\jPackages\appengine-java-sdk-1.4.3". Click OK twice.

**B. Check-out OGate's source code.
https://open-ones.googlecode.com/svn/trunk/OGate/SourceCode**

**C. Run the OGate project**

I) Import the OGate project into Eclipse

Step 1: Going to menu File > Import. The dialog Import is displayed.

Step 2: Select node General > Existing Projects into Workspace. Click on Next, Browse to folder of OGate Source.

II)Run the OGate project

Right click on OGate project in the Project Explorer of the eclipse. Select Run As > Web Application.

Follow the result of compilation in window "Console".
Then use browser to open URL: htt://localhost:8888/

In case the eclipse could not compile the source code of OGate. Please check and update the Java Build Path of the project.

Login by Admin email which is declared at "/OGate/resources/conf.properties" (you should modified it) to maintain tabs and its content.

Tips:

+ Configure JDK is the default JVM instead of JRE.

+ If your GAE SDK's version is different from files .../OGate/war/WEB-INF/lib/appengine-api-1.0-sdk-x.y.z.jar, appengine-api-labs-x.y.z.jar, please replace these files by your SDK's ones.