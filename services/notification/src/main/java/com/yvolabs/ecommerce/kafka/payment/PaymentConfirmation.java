package com.yvolabs.ecommerce.kafka.payment;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
