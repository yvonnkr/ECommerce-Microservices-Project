package com.yvolabs.ecommerce.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 22/07/2024
 */
public record PaymentRequest(
        Integer id,
        @Positive(message = "Amount should be positive")
        @NotNull(message = "Amount should not be null")
        BigDecimal amount,
        @NotNull(message = "Payment Method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Order id is required")
        Integer orderId,
        String orderReference,
        @Valid
        Customer customer
) {
}
