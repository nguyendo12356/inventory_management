package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.NotificationDao;
import com.java.entity.Notification;

@Service
public class NotificationService {

	@Autowired
	private NotificationDao notificationDao;
	
	public List<Notification> findAllNotification() {
		List<Notification> list = notificationDao.getNotification();
		notificationDao.updateStatus();
		return list;
	}
	
	public int countNotification() {
		return (int)notificationDao.countNotification();
	}
}
