package com.java.test;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailSMTP {

	// for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "dob1505822@student.ctu.edu.vn";
    private static final String PASSWORD = "mykiss123";

    private static final String EMAIL_FROM = "testdo1@yopmail.com";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    public static void SendEmail() {

        Properties prop = System.getProperties();
        prop.setProperty("mail.transport.protocol", "smtp"); 
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587"); // default port 25

        Session session = Session.getInstance(prop, new Authenticator() {
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(USERNAME, PASSWORD);
        	}
        });
        
        Message message = prepareMessage(session, USERNAME, EMAIL_FROM);
        try {
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Success");
    }
    
    public static void main(String[] args) {
		SendEmail();
	}
    
    private static Message prepareMessage(Session session, String email, String receiver) {
    	try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(EMAIL_SUBJECT);
			message.setText(EMAIL_TEXT);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}