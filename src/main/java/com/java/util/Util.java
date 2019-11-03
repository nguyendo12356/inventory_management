package com.java.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.multipart.MultipartFile;

import com.java.common.Constant;


public class Util {
	
	public static boolean validatePassword(String password) {
		String regex = "\\w+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		if(!matcher.matches()) {
			return false;
		}
		return true;
	}
	
	public static boolean validateEmail(String email) {
		String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.find()) {
			return false;
		}
		return true;
	}
	
	public static boolean saveImage(File path, MultipartFile multipartFile) throws IOException {
		String regex = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(multipartFile.getOriginalFilename());
		if(matcher.matches()) {
			File file = new File(path + File.separator + multipartFile.getOriginalFilename());
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
			outputStream.write(multipartFile.getBytes());
			outputStream.close();
			return true;
		}
		return false;
	}
	
	public static String sendEmail(String recipient, String usernameEncode) {
		String SMTP_SERVER = "smtp.gmail.com";
	    String USERNAME = "dob1505822@student.ctu.edu.vn";
	    String PASSWORD = "mykiss123";

	    String EMAIL_FROM = "dob1505822@student.ctu.edu.vn";
	    String EMAIL_CC = "testdo2@yopmail.com";

	    String EMAIL_SUBJECT = "Yêu cầu thay đổi mật khẩu";
	    //String EMAIL_TEXT = "Nhấp vào link để thay đổi mật khẩu <br />";
	    StringBuilder EMAIL_TEXT = new StringBuilder();
	    EMAIL_TEXT.append("Nhấp vào link để thay đổi mật khẩu <br />");
	    EMAIL_TEXT.append("<a href='http://localhost:8080/inventory-management/changePassword/"+usernameEncode+"'>Thay đổi mật khẩu</a>");
		
	    Properties prop = System.getProperties();
	    prop.setProperty("mail.transport.protocol", "smtp"); 
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587"); // default port 25
        
        javax.mail.Session session = javax.mail.Session.getInstance(prop, new Authenticator() {
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(USERNAME, PASSWORD);
        	}
        });
        
        try {
        	Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL_FROM));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setRecipient(Message.RecipientType.CC, new InternetAddress(EMAIL_CC));
			message.setSubject(EMAIL_SUBJECT);
			message.setContent(EMAIL_TEXT.toString(), "text/html");
			Transport.send(message);
			return Constant.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	

}
