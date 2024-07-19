package com.yvolabs.ecommerce.product;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 18/07/2024
 */
public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
