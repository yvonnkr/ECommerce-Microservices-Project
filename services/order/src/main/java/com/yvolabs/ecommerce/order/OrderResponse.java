package com.yvolabs.ecommerce.order;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 21/07/2024
 */
@Builder
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
