package com.yvolabs.ecommerce.kafka;

import com.yvolabs.ecommerce.email.EmailService;
import com.yvolabs.ecommerce.kafka.order.OrderConfirmation;
import com.yvolabs.ecommerce.kafka.payment.PaymentConfirmation;
import com.yvolabs.ecommerce.notification.Notification;
import com.yvolabs.ecommerce.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.yvolabs.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.yvolabs.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic Topic: {}", paymentConfirmation);

        // persist notification
        Notification paymentNotification = Notification.builder()
                .type(PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();
        notificationRepository.save(paymentNotification);

        // send email
        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );


    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic Topic: {}", orderConfirmation);

        // persist notification
        Notification orderNotification = Notification.builder()
                .type(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();
        notificationRepository.save(orderNotification);

        // send email
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );

    }

}
