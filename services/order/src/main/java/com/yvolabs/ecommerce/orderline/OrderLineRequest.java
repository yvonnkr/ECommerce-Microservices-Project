package com.yvolabs.ecommerce.orderline;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 21/07/2024
 */

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}
