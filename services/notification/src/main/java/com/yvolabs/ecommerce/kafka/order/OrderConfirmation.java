package com.yvolabs.ecommerce.kafka.order;

import com.yvolabs.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
