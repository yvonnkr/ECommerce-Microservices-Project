package com.yvolabs.ecommerce.payment;

import com.yvolabs.ecommerce.notification.NotificationProducer;
import com.yvolabs.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 22/07/2024
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Integer createPayment(PaymentRequest request) {
        Payment payment = repository.save(mapper.toPayment(request));

        //send notification to kafka
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
