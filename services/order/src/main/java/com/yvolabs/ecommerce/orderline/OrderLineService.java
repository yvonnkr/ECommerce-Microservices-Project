package com.yvolabs.ecommerce.orderline;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 21/07/2024
 */
public interface OrderLineService {
    Integer saveOrderLine(OrderLineRequest orderLineRequest);

    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}
