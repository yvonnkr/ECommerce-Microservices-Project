package com.yvolabs.ecommerce.notification;

import com.yvolabs.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 22/07/2024
 */
public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
