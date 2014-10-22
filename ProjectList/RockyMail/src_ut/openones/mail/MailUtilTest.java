package openones.mail;

import java.util.ArrayList;

import javax.mail.MessagingException;

import junit.framework.TestCase;

public class MailUtilTest extends TestCase {
    private static final String GOOGLE_PASSWD = "";
    private static final String GOOGLE_ACCOUNT = "";
    final static String MAIL_SERVER = "";

    public void testSendSimpleHtmlEmail() {
        MailServerInfo smtpServer = new MailServerInfo();
        smtpServer.setName("smtp.googlemail.com");
        smtpServer.setPort(465);
        smtpServer.setUsername(MailUtilTest.GOOGLE_ACCOUNT);
        smtpServer.setPassword(GOOGLE_PASSWD);
        
        smtpServer.setRequireAuth(true);

        MailInfo mail = new MailInfo();
        ArrayList<String> list = new ArrayList<String>();
        list.add("lnthach@yahoo.com");
        //list.add("");
        //list.add("");
        mail.setMailTo(list);
        mail.setMailFrom(MailUtilTest.GOOGLE_ACCOUNT);
        mail.setMailSubject("[TEST]SENDING EMAIL");
        mail.setMailBody("<h1>this is test sending email</h1>");

        try {
            MailUtil.sendSimpleHtmlEmail(smtpServer, mail);
        } catch (MessagingException ex) {
            fail(ex.getMessage());
        }
    }

    public void testSendSimpleTextEmail() {
        String from = "";
        String to = "";
        String subject = "[TEST]SENDING EMAIL WITH TEXT";
        String body = "TEXT TEXT TEXT";
        String mailhost = MAIL_SERVER;
        String port = "587";
        String username = "";
        String password = "";
        try {
            MailUtil.sendSimpleTextEmail(from, to, subject, body, mailhost, port, username, password);
        } catch (MessagingException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
