package com.hiresphere.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hiresphere.dto.NotificationStatus;
import com.hiresphere.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
	public List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);
}
