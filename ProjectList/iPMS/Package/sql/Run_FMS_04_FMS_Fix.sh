#./env.sh
#export ORACLE_HOME=/usr/lib/oracle/xe/app/oracle/product/10.2.0/server
#export PATH=$PATH:$ORACLE_HOME/bin

source env.sh
sqlplus $USERNAME/$PASSWD@$DBNAME @FMS_04_Fix.sql > FMS_04_Fix.log