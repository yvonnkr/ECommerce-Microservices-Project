package com.yvolabs.ecommerce.notification;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
