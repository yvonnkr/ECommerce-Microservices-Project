package com.yvolabs.ecommerce.orderline;

import lombok.Builder;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 21/07/2024
 */
@Builder
public record OrderLineResponse(
        Integer id,
        double quantity
) {
}
