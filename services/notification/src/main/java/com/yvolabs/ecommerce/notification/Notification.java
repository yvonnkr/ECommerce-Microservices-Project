package com.yvolabs.ecommerce.notification;

import com.yvolabs.ecommerce.kafka.order.OrderConfirmation;
import com.yvolabs.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "notification")
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
