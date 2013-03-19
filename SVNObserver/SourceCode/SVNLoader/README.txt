SVNLoader loads statistical data from the Subversion (SVN) repository to the relational database.

Current version: 0.2.2

How to make the binary package and run the demo from the source code?
=========================================
The demo version uses built-in database HSQL.

Step 1: Run script dist.bat to compile and make distribution. The binary package is generated at ./dist/svnloader-x.y.z (This folder is called $SVNLOADER_HOME)

Step 2: Start database by change current folder into "$SVNLOADER_HOME\bin" and running the batch script "start-hsql.bat".
  Information to connect this database by Java:
    JDBC Driver: org.hsqldb.jdbcDriver
	JDBC URL: jdbc:hsqldb:hsql://localhost/svnreport
	JDBC User: sa
	JDBC Password: blank (no password)
  
Step 3: Check the SVN configuration and database configuration 
  + Check the configuration of database (JDBC) in file "bin/persistence.xml" to connect to the database HSQL  which is mentioned in step 2
  + Fill the arguments for SVN: URL, Username, Password
  + Fill the value for property PROJECTCODE. This project alias will be named for the URL SVN in the database.

Step 4: Run by change current folder into $SVNLOADER_HOME/bin and running script SVNLoader-4Project.bat

How to run the binary?
=========================================

How to run the Test Case?
=========================================
1) Start the database by executing batch script "hsqldb-8.1\svnreport\StartDB.bat"
2) Refer samples at /SVNLoader/src_ut/openones/svnloader/engine/SVNLoaderBizTest.java

How to develop?
=========================================
- Eclipse. Refer http://code.google.com/p/open-ones/wiki/PrepareIDEEclipse
- Support tools:
  + Database client: Squirrel (http://squirrel-sql.sourceforge.net/)
  + HSQL: http://hsqldb.org/
- Libraries:
  + svnkit: http://svnkit.com
  
Bugs/Changes tracking
==============================
* Version: 0.2.2
#1: Could not load the URL which is not the root.
Solution:
Upgrade library svnkit into svnkit-1.7.8.jar

*Version: 0.2.1
#1: Violation of PRIMARY KEY constraint 'PK__SVNVersion__7DC38901'. Cannot insert duplicate key in object 'dbo.SVNVersion'.