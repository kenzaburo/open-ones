DROP TABLE IF EXISTS WATCHER;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS REQUESTTYPE;
DROP TABLE IF EXISTS REQUEST;
DROP TABLE IF EXISTS DEPARTMENT;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS PARAMETER;
DROP TABLE IF EXISTS LABEL;
DROP TABLE IF EXISTS USER_ROLE;
DROP TABLE IF EXISTS TEMPLATE;
DROP TABLE IF EXISTS REQUEST_DEPARTMENT;
DROP TABLE IF EXISTS RATE;
DROP TABLE IF EXISTS COMMENT;
DROP TABLE IF EXISTS STATUS_FLOW;

CREATE TABLE USER (
       ID INT NOT NULL AUTO_INCREMENT
     , USERNAME VARCHAR(50) NOT NULL UNIQUE    -- Code
     , EMAIL VARCHAR(50)
     , FIRSTNAME VARCHAR(20)
     , LASTNAME VARCHAR(50)
     , ENABLED BOOLEAN
     , DEPARTMENT_CD VARCHAR(50)
     , DEPARTMENT_NAME VARCHAR(100)
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
);

CREATE TABLE USER_ROLE (
       ID INT NOT NULL AUTO_INCREMENT
     , USERNAME VARCHAR(50)  NOT NULL
     , ROLE VARCHAR(50)
     , ENABLED BOOLEAN
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
);


CREATE TABLE ROLE (
       ID INT NOT NULL  AUTO_INCREMENT
     , ROLE VARCHAR(32) NOT NULL UNIQUE
     , ENABLED BOOLEAN
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
);

CREATE TABLE DEPARTMENT (
       ID INT NOT NULL AUTO_INCREMENT
     , PARENTID INT NOT NULL
     , CD VARCHAR(20) NOT NULL UNIQUE
     , PARENTCD VARCHAR(10)
     , NAME VARCHAR(50) NOT NULL
     , DESCRIPTION VARCHAR(200)
     , ENABLED BOOLEAN
     , MANAGER_USERNAME VARCHAR(50)
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
);

CREATE TABLE REQUESTTYPE (
       ID INT NOT NULL AUTO_INCREMENT
     , SEQ_NO INT
     , CD VARCHAR(20) NOT NULL UNIQUE
     , NAME VARCHAR(50) NOT NULL
     , DESCRIPTION VARCHAR(200)
     , ENABLED BOOLEAN
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
);

/**
 * Case reset password
 * CD = "ResetPasswd"
 * NAME = account
 * VALUE = yyyy-MM-dd HH:mm:ss
 */
CREATE TABLE PARAMETER (
       ID INT NOT NULL AUTO_INCREMENT
     , CD VARCHAR(20) NOT NULL
     , NAME VARCHAR(50)
     , VALUE VARCHAR(100)
     , DESCRIPTION VARCHAR(200)
     , ENABLED BOOLEAN
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
     , CONSTRAINT NODUPLICATE UNIQUE (CD, NAME, VALUE, ENABLED)
);

CREATE TABLE TEMPLATE (
       ID INT NOT NULL AUTO_INCREMENT
     , CD VARCHAR(20) NOT NULL UNIQUE
     , NAME VARCHAR(50) NOT NULL
     , DESCRIPTION VARCHAR(200)
     , CONTENT TEXT
     , ENABLED BOOLEAN
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
);

CREATE TABLE LABEL (
       ID INT NOT NULL AUTO_INCREMENT
     , CD VARCHAR(20) NOT NULL
     , TYPE VARCHAR(50) NOT NULL
     , NAME VARCHAR(50) NOT NULL
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
     , CONSTRAINT NODUPLICATE UNIQUE (CD, TYPE, NAME)
);

CREATE TABLE REQUEST (
       ID INT NOT NULL AUTO_INCREMENT
     , REQUESTTYPE_CD VARCHAR(20)
     , REQUESTTYPE_NAME VARCHAR(50)
     , TITLE VARCHAR(200) NOT NULL
     , CONTENT TEXT
     , STARTDATE DATETIME
     , ENDDATE DATETIME
     , ASSIGNEE_USERNAME CHAR(50)
     , ASSIGNEE_NAME CHAR(100)
     , ASSIGNEE_NOTE TEXT       -- Comment for Assignee
     , MANAGER_USERNAME VARCHAR(50)
     , MANAGER_NAME VARCHAR(100)
     , LABEL1 VARCHAR(20)
     , LABEL2 VARCHAR(20)
     , LABEL3 VARCHAR(20)
     , DURATION INT
     , DURATIONUNIT INT          -- 0: hour; 1: day; 2: week; 3: moth; 4: year
     , DEPARTMENT_CD VARCHAR(10)
     , DEPARTMENT_NAME VARCHAR(50)
     , STATUS VARCHAR(30)                -- Created | Doing | Rejected | Approved | Updated | Finish | Done | Re-assignT
     , PLANEFFORT INT
     , PLANUNIT VARCHAR(50)
     , ATTACHMENT1 BLOB
     , FILENAME1 VARCHAR(255)
     , ATTACHMENT2 BLOB
     , FILENAME2 VARCHAR(255)
     , ATTACHMENT3 BLOB
     , FILENAME3 VARCHAR(255)
     , LIKES TEXT                         -- List of username: Mapping to String[] into entity
     , FOR_USER TEXT                      -- List of username: Mapping to String[] into entity
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
     , INDEX (ASSIGNEE_USERNAME)
);

-- Rate for Task (when manager confirms a task is DONE); or Rate an employee
CREATE TABLE RATE (
       ID INT NOT NULL AUTO_INCREMENT
     , REQ_ID INT NOT NULL
     , USERNAME VARCHAR(50) NOT NULL
     , EMAIL VARCHAR(50)
     , CONTENT TEXT                        --
     , RANK VARCHAR(10)                        -- A | B | C | D | E 
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
     , INDEX (REQ_ID)
     , INDEX (USERNAME)
);

-- Comment for Request
CREATE TABLE COMMENT (
       ID INT NOT NULL AUTO_INCREMENT
     , REQ_ID INT NOT NULL
     , REQ_STATUS VARCHAR(30) NOT NULL
     , USERNAME VARCHAR(50) NOT NULL 
     , EMAIL VARCHAR(50)
     , CONTENT TEXT                        --
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
     , INDEX (REQ_ID)
     , INDEX (USERNAME)
);

-- Reading status for Request
CREATE TABLE READ_STATUS (
       ID INT NOT NULL AUTO_INCREMENT
     , REQ_ID INT NOT NULL
     , REQ_STATUS VARCHAR(30) NOT NULL
     , USERNAME VARCHAR(50) NOT NULL 
     , EMAIL VARCHAR(50)
     , IS_READ INT                    -- 0: Not read; 1: Read
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)     
     , PRIMARY KEY (ID)
     , INDEX (REQ_ID)
     , INDEX (USERNAME)
);

CREATE TABLE WATCHER (
       ID INT NOT NULL  AUTO_INCREMENT
     , REQ_ID INT NOT NULL
     , USERNAME VARCHAR(50) NOT NULL
     , EMAIL VARCHAR(50)
     , CONTENT TEXT                        --
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
     , INDEX (REQ_ID)
     , INDEX (USERNAME)
);

CREATE TABLE REQUEST_DEPARTMENT (
       ID INT NOT NULL AUTO_INCREMENT
     , REQ_ID INT NOT NULL
     , DEPARTMENT_ID INT NOT NULL
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
);

CREATE TABLE STATUS_FLOW (
       ID INT NOT NULL AUTO_INCREMENT
     , SEQ_NO INT NOT NULL
     , REQUESTTYPE_CD VARCHAR(20) NOT NULL
     , TYPE_USER VARCHAR(20) NOT NULL          -- Owner | Manager | Creator
     , CURRENT_STATUS VARCHAR(30) NOT NULL
     , NEXT_STATUS VARCHAR(30) NOT NULL
     , CREATED DATETIME NOT NULL
     , CREATEDBY_USERNAME VARCHAR(50) NOT NULL
     , LASTMODIFIED DATETIME
     , LASTMODIFIEDBY_USERNAME VARCHAR(50)
     , PRIMARY KEY (ID)
);
