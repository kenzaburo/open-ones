@ECHO OFF
SET CERT_FILE=mail-der-x509.cer
SET PASSWD=changeit
SET KEYSTORE_FILE="C:\Program Files\Java\jdk1.6.0\jre\lib\security\cacerts"
REM SET KEYSTORE_FILE="C:\Program Files\Java\jre1.6.0\lib\security\cacerts"
keytool -list -keystore %KEYSTORE_FILE% -storepass %PASSWD%
@PAUSE
keytool -importcert -file %CERT_FILE% -keystore %KEYSTORE_FILE% -storepass %PASSWD%
@PAUSE