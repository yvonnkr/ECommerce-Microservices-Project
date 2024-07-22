package com.yvolabs.ecommerce.payment;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 22/07/2024
 */
public interface PaymentService {
    Integer createPayment(PaymentRequest request);
}
