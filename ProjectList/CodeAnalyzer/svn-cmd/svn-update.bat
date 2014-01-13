@ECHO OFF
REM Refer: http://tortoisesvn.net/docs/release/TortoiseSVN_en/tsvn-automation.html
REM Usage: svn-update.bat <Working copy folder> <revision number>
REM SET WC=D:/Project/HCAM/svn-check/source
SET WC=%1
SET SVN_HOME=C:\Program Files\TortoiseSVN
SET REVISION=%2
SET CMD_UPDATE="%SVN_HOME%\bin\TortoiseProc.exe" /command:update /path:"%WC%" /rev:%REVISION% /closeonend:1
ECHO Execute command: %CMD_UPDATE%
%CMD_UPDATE%
REM Exit the prompt of cmd
exit