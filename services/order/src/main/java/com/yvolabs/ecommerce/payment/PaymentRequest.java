package com.yvolabs.ecommerce.payment;

import com.yvolabs.ecommerce.customer.CustomerResponse;
import com.yvolabs.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 22/07/2024
 */
public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {

}
