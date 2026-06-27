package com.hiresphere.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiresphere.dto.NotificationDTO;
import com.hiresphere.dto.NotificationStatus;
import com.hiresphere.entity.Notification;
import com.hiresphere.exception.JobPortalException;
import com.hiresphere.repository.NotificationRepository;
import com.hiresphere.utility.Utilities;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException {
		notificationDTO.setId(Utilities.getNextSequence("notification"));
		notificationDTO.setStatus(NotificationStatus.UNREAD);
		notificationDTO.setTimestamp(LocalDateTime.now());
		notificationRepository.save(notificationDTO.toEntity());
	}

	@Override
	public List<Notification> getUnreadNotifications(Long userId) {
		return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
	}

	@Override
	public void readNotification(Long id) throws JobPortalException {
		Notification noti = notificationRepository.findById(id).orElseThrow(() -> new JobPortalException("No notification found"));
		noti.setStatus(NotificationStatus.READ);
		notificationRepository.save(noti);
	}

	
}
