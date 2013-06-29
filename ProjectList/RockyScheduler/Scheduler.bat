@ECHO OFF
SET CLASSPATH=%CLASSSPATH%;./lib/*;./resource
java dms.export.ExportEngine %1 %2 %3 %4 %5 %6 %7 %8 %9