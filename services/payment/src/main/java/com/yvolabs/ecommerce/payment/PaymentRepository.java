package com.yvolabs.ecommerce.payment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 22/07/2024
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
