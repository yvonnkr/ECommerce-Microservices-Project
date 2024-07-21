package com.yvolabs.ecommerce.kafka;

import com.yvolabs.ecommerce.customer.CustomerResponse;
import com.yvolabs.ecommerce.order.PaymentMethod;
import com.yvolabs.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 21/07/2024
 */
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
