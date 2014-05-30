package openones.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import junit.framework.TestCase;

public class MailUtilTest extends TestCase {

    public void testCheckAuthentication() {
        fail("Not yet implemented");
    }

    public void testSendHTMLMail() {
        // server config file
        String mailConfigPath = "/AppConfig.properties";

        // template path
        String templatePath = "/testdata/TEST SEND MAIL.htm";

        // variable path
        Map<String, Object> varMap = new HashMap<String, Object>();
        varMap.put("username", "Hai");
        varMap.put("company", "MKS");

        // create Mail
        MailInfo mail = new MailInfo();
        mail.setMailSubject("[TEST]SENDING EMAIL");
        mail.setMailFrom("");

        ArrayList<String> list = new ArrayList<String>();
        list.add("");
        mail.setMailTo(list);

        // Set attach files
        ArrayList<String> listFile = new ArrayList<String>();
        String file1 = "src_ut/testdata/images/file01.rar";
        listFile.add(file1);
        String file2 = "src_ut/testdata/images/file02.rar";
        listFile.add(file2);
        mail.setAttachedFile(listFile);

        // setup images for attaching in email. with src and tag id
        ArrayList<ImageFormat> listImage = new ArrayList<ImageFormat>();
        ImageFormat image01 = new ImageFormat();
        image01.setImgSrc("src_ut/testdata/images/image001.jpg");
        image01.setImgTag("image01");
        listImage.add(image01);

        ImageFormat image02 = new ImageFormat();
        image02.setImgSrc("src_ut/testdata/images/image002.jpg");
        image02.setImgTag("image02");
        listImage.add(image02);

        mail.setImage(listImage);
        try {
            MailUtil.sendHTMLMail(mailConfigPath, mail, templatePath, varMap);
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testSendMailTo() {
        fail("Not yet implemented");
    }

    public void testSendSimpleHtmlEmail() {
        ServerMail smtpServer = new ServerMail();
        smtpServer.setServerName("");
        smtpServer.setUserLogin("");
        smtpServer.setPassLogin("");
        smtpServer.setServerPort("25");
        smtpServer.setServerAuth(true);

        MailInfo mail = new MailInfo();
        ArrayList<String> list = new ArrayList<String>();
        list.add("");
        list.add("");
        list.add("");
        mail.setMailTo(list);
        mail.setMailFrom("");
        mail.setMailSubject("[TEST]SENDING EMAIL");
        mail.setMailBody("<h1>this is test sending email</h1>");
        try {
            MailUtil.sendSimpleHtmlEmail(smtpServer, mail);
        } catch (MessagingException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testSendSimpleTextEmail() {
        String from = "";
        String to = "";
        String subject = "[TEST]SENDING EMAIL WITH TEXT";
        String body = "TEXT TEXT TEXT";
        String mailhost = "";
        String port = "25";
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
