package com.java.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static String validate(String password, String email) {
		String regex = "\\w+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		if(!matcher.matches()) {
			return "Password nên là kí tự hoa, kí tự thường hoặc số";
		}
		regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(email);
		if(!matcher.find()) {
			return "Email không hợp lệ";
		}
		return "";
	}

}
