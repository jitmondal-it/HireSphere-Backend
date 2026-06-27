package com.hiresphere.service;

import java.util.List;

import com.hiresphere.dto.NotificationDTO;
import com.hiresphere.entity.Notification;
import com.hiresphere.exception.JobPortalException;

public interface NotificationService {
	
	public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException;
	public List<Notification> getUnreadNotifications(Long userId);
	public void readNotification(Long id) throws JobPortalException;
}
