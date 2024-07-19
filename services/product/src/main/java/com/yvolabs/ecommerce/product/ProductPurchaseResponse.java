package com.yvolabs.ecommerce.product;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 18/07/2024
 */
public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
