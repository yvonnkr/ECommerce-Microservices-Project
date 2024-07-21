package com.yvolabs.ecommerce.product;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 20/07/2024
 */
public record PurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
