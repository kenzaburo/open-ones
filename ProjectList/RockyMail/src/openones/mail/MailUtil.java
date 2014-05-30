/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
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
package openones.mail;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import rocky.common.CommonUtil;
import rocky.common.Constant;
import rocky.common.PropertiesManager;

import com.sun.mail.pop3.POP3Store;
import com.sun.mail.smtp.SMTPTransport;

/**
 * @author Rocky
 */
public class MailUtil {
    private static final Logger LOG = Logger.getLogger("MailUtil");
    /** . */
    private static final String MAIL_POP3_PORT = "mail.pop3.port";
    /** . */
    private static final String MAIL_PASSWD = "mail.passwd";
    /** . */
    private static final String MAIL_USER = "mail.user";
    /** . */
    public static final String MAIL_POP3_HOST = "mail.pop3.host";

    /**
     * Check the authentication via email server.
     * 
     * @param username
     * @param password
     * @param hostServer
     * @param port
     * @return
     * @throws MessagingException
     */
    public static boolean checkAuthentication(String username, String password, String hostServer, String port)
            throws MessagingException {
        Properties pop3Props = new Properties();

        pop3Props.put(MAIL_POP3_HOST, hostServer);
        pop3Props.put(MAIL_USER, username);
        pop3Props.put(MAIL_PASSWD, password);
        pop3Props.put(MAIL_POP3_PORT, port);

        URLName url = new URLName("pop3://" + pop3Props.getProperty(MAIL_USER) + ":"
                + pop3Props.getProperty(MAIL_PASSWD) + "@" + pop3Props.getProperty(MAIL_POP3_HOST) + ":"
                + pop3Props.getProperty(MAIL_POP3_PORT));
        Session session = Session.getInstance(pop3Props);
        POP3Store pop3Store = new POP3Store(session, url);
        pop3Store.connect();

        boolean authenticated = pop3Store.isConnected();

        return authenticated;
    }

    /**
     * Send complex HTML file with Image(s), attached file(s), read server info from mailConfigPath var (.properties
     * file), read .HTML resource from templatePath var.
     * 
     * from, to, subject, content, host, port, attatchedFile, templatePath, template is a web page which content
     * variable with format: ${var}, HashMap var include variables.
     * 
     * @param varMap
     *            : include value of ${}
     * @param templatePath
     *            : path to .html file
     * @param mailConfigPath
     *            configuration file in CLASSPATH or physical file
     * @param mail
     *            : from, to, ImageFormat, attachedFile, body.
     */
    public static void sendHTMLMail(String mailConfigPath, MailInfo mail, String templatePath,
            Map<String, Object> varMap) throws IOException {
        // try {
        // get server info
        Properties props = PropertiesManager.newInstanceFromProps(mailConfigPath);
        String mailServer = props.getProperty("MailServer");
        String userLogin = props.getProperty("MailUser");
        String passLogin = props.getProperty("MailPass");
        String serverPort = props.getProperty("MailPort");
        String SSL = props.getProperty("SSL");

        LOG.debug("props= : " + mailServer + " - " + userLogin + " - " + passLogin + " - " + serverPort + " - " + SSL);

        ServerMail server = new ServerMail();
        server.setServerName(mailServer);
        server.setUserLogin(userLogin);
        server.setPassLogin(passLogin);
        // server.setServerAuth(Boolean.parseBoolean(SSL));
        server.setServerAuth((userLogin != null) && (userLogin.length() > 1));
        server.setServerPort(serverPort);

        // get content of HTML file from path.
        String content = CommonUtil.getContent(templatePath, true, Constant.DEF_ENCODE);
        LOG.debug("content before replace=: " + content);

        // Content after replaced values of variables
        content = CommonUtil.formatPattern(content, varMap);
        LOG.debug("content after replace=: " + content);

        // set content with HTML to mail's body
        mail.setMailBody(content);

        // sending email.
        try {
            sendSimpleHtmlEmail(server, mail);
        } catch (MessagingException ex) {
            LOG.debug("MESSAGING EXCEPTION", ex);
        }

        // } catch (IOException ex) {
        // LOG.error("Load configuration file '" + mailConfigPath + "'", ex);
        // }
        // Read content of templatePath (html file).
        // Thay cac bien = gia tri cua no = HashMap.
        // Scan noi dung cua content, tim cac the img,
        // lay duoc src cua img,
        // Attach image vao content = src lay dc o tren.
        // Lay content da thay bien lam noi dung mail gui di.
    }

    /**
     * Send simple email with HTML format with image(s), attached file(s)
     * 
     * @param smtpServer
     *            : host, port, username, password, serverAuth.
     * @param mail
     *            : from, to, ImageFormat, attachedFile, body.
     */
    public static void sendSimpleHtmlEmail(ServerMail smtpServer, MailInfo mail) throws MessagingException {
        // try {
        Properties props = new Properties();
        props.clear();

        // Lay ten server
        props.put("mail.smtp.host", smtpServer.getServerName());
        props.put("mail.smtp.auth", smtpServer.isServerAuth());
        props.put("mail.smtp.port", smtpServer.getServerPort());
        props.put("mail.smtp.socketFactory.port", smtpServer.getServerPort());

        final String login = smtpServer.getUserLogin();
        final String pwd = smtpServer.getPassLogin();

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, pwd);
            }
        });

        Message msg = buildMessage(mail, session);
        Transport.send(msg);
        LOG.debug("Message sent OK.");
        // } catch (MessagingException mex) {
        // mex.printStackTrace();
        // }
    }

    public static Message buildMessage(MailInfo mail, Session session) throws MessagingException {
        Message msg = new MimeMessage(session);
        Address addrFrom = new InternetAddress(mail.getMailFrom());
        Address[] listTo = new Address[mail.getMailTo().length];
        for (int i = 0; i < mail.getMailTo().length; i++) {
            listTo[i] = new InternetAddress(mail.getMailTo()[i]);
        }

        msg.setSubject(mail.getMailSubject());
        msg.setFrom(addrFrom);
        msg.addRecipients(Message.RecipientType.TO, listTo);

        // create the second message part
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(mail.getMailBody(), "text/html");
        Multipart multipart = new MimeMultipart("related");
        multipart.addBodyPart(messageBodyPart);

        // second part (the image(s) )
        if (mail.getImage() != null) {
            for (int i = 0; i < mail.getImage().length; i++) {
                messageBodyPart = new MimeBodyPart();
                DataSource fds = new FileDataSource(mail.getImage()[i].getImgSrc());
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", mail.getImage()[i].getImgTag());
                multipart.addBodyPart(messageBodyPart);
            }
        }

        // attach file
        if (mail.getAttachedFile() != null) {
            for (int i = 0; i < mail.getAttachedFile().length; i++) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(mail.getAttachedFile()[i]);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(mail.getAttachedFile()[i].getName());
                multipart.addBodyPart(messageBodyPart);
            }
        }
        // set body of mail
        msg.setContent(multipart);

        // set the Date: header
        msg.setSentDate(new Date());
        msg.saveChanges();
        return msg;
    }
    /**
     * Send Simple email with normal format (text)
     * 
     * @param from
     * @param to
     * @param subject
     * @param body
     * @param mailhost
     * @param username
     * @param password
     * @throws AddressException
     * @throws MessagingException
     */
    public static void sendSimpleTextEmail(String from, String to, String subject, String body, String mailhost,
            String port, String username, String password) throws MessagingException {
        Properties props = System.getProperties();

        // Attempt to authenticate the user using the AUTH command
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.socketFactory.port", port);

        // Get a Session object
        Session session = Session.getInstance(props, null);
        // try {
        // construct the message
        Message msg = new MimeMessage(session);
        if (from != null) {
            msg.setFrom(new InternetAddress(from));
        } else {
            msg.setFrom();
        }

        // Set the subject
        msg.setSubject(subject);

        msg.setText(body);

        // msg.setHeader("X-Mailer", mailer);
        msg.setSentDate(new Date());

        // Using SMTPTransport to send email with authentication
        SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
        transport.connect(mailhost, username, password);

        // Using method sendMessage of SMTPTransport
        transport.sendMessage(msg, InternetAddress.parse(to, false));
        transport.close();

        LOG.debug("\nMail was sent successfully.");
        // } catch (AddressException e) {
        // e.printStackTrace();
        // } catch (MessagingException e) {
        // e.printStackTrace();
        // }
    }

    /**
     * Send simple email with input is internet address
     * 
     * @param fromAddr
     * @param toAddr
     * @param subject
     * @param content
     * @throws Exception
     */
    public static void sendMailTo(InternetAddress fromAddr, InternetAddress toAddr, String subject, String content)
            throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(fromAddr);

            msg.addRecipient(Message.RecipientType.TO, toAddr);

            msg.setSubject(subject);
            msg.setText(content);
            Transport.send(msg);

        } catch (AddressException aEx) {
            throw aEx;
        } catch (MessagingException mEx) {
            throw mEx;
        }
    }
}
