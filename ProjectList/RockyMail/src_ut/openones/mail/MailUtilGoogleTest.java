package openones.mail;

import java.util.ArrayList;

import javax.mail.MessagingException;

import junit.framework.TestCase;

public class MailUtilGoogleTest extends TestCase {
    private static final String USERNAME = "";
    private static final String PASSWORD = "";


    public void testSendSimpleTextEmail() {
        String from = MailUtilGoogleTest.USERNAME;
        String to = "lnthach@yahoo.com";
        String subject = "[TEST]SENDING EMAIL WITH TEXT";
        String body = "TEXT TEXT TEXT\n" + 
                "http://open-ones.googlecode.com/svn/trunk/ProjectList/RockyMail/";
        String mailhost = "smtp.googlemail.com";
        String port = "587";
        String username = USERNAME;
        String password = PASSWORD;
        try {
            boolean isStartTLS = true;
            MailUtil.sendSimpleTextEmail(from, to, subject, body, mailhost, port, username, password, isStartTLS);
        } catch (MessagingException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    public void testSendSimpleHtmlEmail() {
        MailServerInfo smtpServer = new MailServerInfo();
        smtpServer.setName("smtp.googlemail.com");
        smtpServer.setPort(587);
        smtpServer.setUsername(MailUtilGoogleTest.USERNAME);
        smtpServer.setPassword(MailUtilGoogleTest.PASSWORD);
        smtpServer.setEnableStarttls(true);
        
        smtpServer.setRequireAuth(true);

        MailInfo mail = new MailInfo();
        ArrayList<String> list = new ArrayList<String>();
        list.add("lnthach@yahoo.com");
        //list.add("");
        //list.add("");
        mail.setMailTo(list);
        mail.setMailFrom(MailUtilGoogleTest.USERNAME);
        mail.setMailSubject("[TEST]SENDING EMAIL");
        mail.setMailBody("<h1>this is test sending email</h1>"
                + "Đây là email được gởi từ thư viện:"
                + "http://open-ones.googlecode.com/svn/trunk/ProjectList/RockyMail/"
                );

        try {
            MailUtil.sendSimpleHtmlEmail(smtpServer, mail);
        } catch (MessagingException ex) {
            fail(ex.getMessage());
        }
    }

}
