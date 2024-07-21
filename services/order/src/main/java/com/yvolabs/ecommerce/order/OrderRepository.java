package com.yvolabs.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 20/07/2024
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
