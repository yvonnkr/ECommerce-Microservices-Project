package com.yvolabs.ecommerce.orderline;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 21/07/2024
 */
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(int orderId);
}
