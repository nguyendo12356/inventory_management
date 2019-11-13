package com.java.dao;

import java.util.List;

import com.java.common.BaseDao;
import com.java.entity.Notification;

public interface NotificationDao extends BaseDao<Notification>{

	List<Notification> getNotification();
	
	long countNotification();
}
