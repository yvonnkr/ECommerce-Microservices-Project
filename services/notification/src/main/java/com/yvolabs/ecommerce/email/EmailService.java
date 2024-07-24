package com.yvolabs.ecommerce.email;

import com.yvolabs.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
public interface EmailService {

    void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference) throws MessagingException;

    void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference, List<Product> products) throws MessagingException;
}
