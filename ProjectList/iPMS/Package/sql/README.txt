These scripts are used to create the schema and intial data for FMS.
The scripts are fixed bugs by Open-Ones Group (http://www.open-ones.com).

Process to run the scripts:
Step 1: Prepare an account with DBA privileges

For Windows
==============================
Step 2: Check to modify the parameters in env.bat
Step 3: Execute Run_FMS_01_Schema.bat, Run_FMS_02_Package.bat, Run_FMS_03_InsertData.bat, Run_FMS_04_FMS_Fix.bat

For Linux
==============================
Step 2: Check to modify the parameters in env.sh
Step 3: Execute Run_FMS_01_Schema.sh, Run_FMS_02_Package.sh, Run_FMS_03_InsertData.sh, Run_FMS_04_FMS_Fix.sh.

Appendix:
==============================

1) Script to connect to Oracle with account sys/password
sqlplus sys/password@XE as SYSDBA

2) Script to create and grant permission to PRJ360
CREATE USER "PRJ360" PROFILE "DEFAULT" IDENTIFIED BY "PRJ360" DEFAULT TABLESPACE "USERS" TEMPORARY TABLESPACE "TEMP" ACCOUNT UNLOCK;

GRANT CREATE SESSION TO "PRJ360";
GRANT DBA TO "PRJ360";


Contact
thachln@gmail.com