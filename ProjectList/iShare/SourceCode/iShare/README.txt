This project is developed from jForum (jforum.net) 2.1.8.

How to run the project from Eclipse
---------------------------------------------------------------------------------
Prepare the MySQL Database
1) Run SQL client with root permission
mysql -h localhost -u root -p

2) Execute script ./WebContent/upgrade/2.1.9 to upgrade database from jForum 2.1.8 to iShare 2.1.9.

3) Edit the file "WebContent\WEB-INF\config\database\mysql\mysql.properties" to match
the SQL login infomation and the database name.

4) Prepare IDE environment: Eclipse + Tomcat 6
For the first time, right-click on /jForum/WebContent/install.jsp to run On Server
__________________
Open-Ones Group (http://www.open-ones.com)
