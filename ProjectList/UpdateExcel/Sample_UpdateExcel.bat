@ECHO OFF
ECHO Check to adjust parameters: CONFIG_RESOURCE, INPUT_FOLDER
SET CONFIG_RESOURCE=/excel-layout.xml
SET INPUT_FOLDER=./
ECHO Update excel find within folder %INPUT_FOLDER%
AppUpdateExcel "%CONFIG_RESOURCE%" "%INPUT_FOLDER%"