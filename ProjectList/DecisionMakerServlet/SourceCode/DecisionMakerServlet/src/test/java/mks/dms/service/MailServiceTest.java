/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package mks.dms.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.mail.MessagingException;

import ldap.entry.UserEntry;
import openones.mail.MailInfo;
import openones.mail.MailServerInfo;
import openones.mail.MailUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ThachLe
 *
 */
public class MailServiceTest {

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link mks.dms.service.MailService#sendMailReset(ldap.entry.UserEntry)}.
     */
    @Test
    public void testSendMailReset() {
        MailServerInfo mailServerInfo = new MailServerInfo();
        MailService mailService = new MailService(mailServerInfo);
        
        UserEntry user = new UserEntry();
        
        user.setUid("lnthach@yahoo.com");
        user.setEmail("lnthach@yahoo.com");
        user.setPassword("New password");
        
        boolean sentOK = mailService.sendMailReset(user);
        assertTrue(sentOK);
    }

    @Test
    public void testSendSimpleHtmlEmail() {
        MailServerInfo smtpServer = new MailServerInfo();
        smtpServer.setName("smtp.googlemail.com");
        smtpServer.setPort(587);
        smtpServer.setUsername("youremail");
        smtpServer.setPassword("yourpassword");
        smtpServer.setEnableStarttls(true);
        
        smtpServer.setRequireAuth(true);

        MailInfo mail = new MailInfo();
        ArrayList<String> list = new ArrayList<String>();
        list.add("lnthach@yahoo.com");


        mail.setMailTo(list);
        mail.setMailFrom("youremail");
        mail.setMailSubject("[TEST]SENDING EMAIL");
        mail.setMailBody("<h1>this is test sending email</h1>"
                + "http://open-ones.googlecode.com/svn/trunk/ProjectList/RockyMail/"
                );

        try {
            MailUtil.sendSimpleHtmlEmail(smtpServer, mail);
        } catch (MessagingException ex) {
            fail(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
