create database jforum default character set utf8;
CREATE USER 'adminforum'@'localhost' IDENTIFIED BY 'adminforum'; 
GRANT ALL PRIVILEGES ON jforum.* TO 'adminforum'@'localhost';        


# For drop database
REVOKE ALL PRIVILEGES ON jforum.* FROM 'adminforum'@'localhost';  
DROP USER 'adminforum'@'localhost';
DROP database jforum;
