package com.java.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Common {

	public boolean checkSession(HttpServletRequest request) {
		return request.getSession().getAttribute("session") == null ? true : false;
	}
}
