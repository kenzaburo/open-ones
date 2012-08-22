source env.sh
echo Executing command: $USERNAME/$PASSWD@$DBNAME @FMS_01_Schema.sql > FMS_01_Schema.log
sqlplus $USERNAME/$PASSWD@$DBNAME @FMS_01_Schema.sql > FMS_01_Schema.log