# Introduction #

MySQL command for beginer.


# Details #

**1) Create database**

create database dbname default character set utf8;

**2) Create account with full permissions**

GRANT ALL ON dbname.`*` TO dbaccount@localhost IDENTIFIED BY 'dbpassword';

**3) Create account with read-only access**

GRANT SELECT ON dbname.**TO 'dbaccount'@'%' IDENTIFIED BY 'password';**

Notes:

- dbname: Your new database name

- dbaccount: account of the database with ALL PRIVILEDGEs

- dbpassword: password of the account