ALTER TABLE jforum_forums ADD forum_tooltip varchar(150);
ALTER TABLE jforum_forums ADD forum_img_url varchar(255) DEFAULT '${contextPath}/templates/${templateName}/images/folder_new_big.gif';