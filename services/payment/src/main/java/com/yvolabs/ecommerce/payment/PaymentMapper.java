package com.yvolabs.ecommerce.payment;

import org.springframework.stereotype.Service;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 22/07/2024
 */
@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
