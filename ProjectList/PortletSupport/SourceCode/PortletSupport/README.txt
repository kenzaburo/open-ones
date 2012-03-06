This project provides utilities to:
 - Get logon user of the portal or configurable logon user for development environment.

*** Version 0.0.1
  Binary folder: release/PortletSupport-0.0.1/
How to use
===================================
1. Copy file dev.properties into your CLASSPATH of the Java runtime machine or CLASSPATH of the application.
   Ex: Copy dev.properties into the Source folder of the project
2. Adjust properties of dev.properties to match with your needs.
3. Copy portletsupport-x.y.z-yyyyMMddhhMMss.jar into your CLASSPATH.
4. Sample caller code:
   PortletUtil portletUtil = new PortletUtil(request);   
   String logonUser = portletUtil.getLogonUser();

Explanation:
If your portlet is running within the Portal, logonUser is user which is authenticated.
If your portlet is running within development environement such as Glashfish (+ OpenPortal Container), the logonUser is loaded from the configuration file "dev.properties" in step 1.

Note:
For uPortal, the none-authenticated user is "guest".

Open-Ones (http://open-ones.com)
--------------------------------
