package com.yvolabs.ecommerce.order;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 20/07/2024
 */

public interface OrderService {
    Integer createOrder(OrderRequest request);

    List<OrderResponse> findAll();

    OrderResponse findById(int orderId);
}
