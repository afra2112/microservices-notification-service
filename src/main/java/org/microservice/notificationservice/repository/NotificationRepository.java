package org.microservice.notificationservice.repository;

import org.microservice.notificationservice.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
