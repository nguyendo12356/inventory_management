package com.java.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;


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

}
