package com.yvolabs.ecommerce.kafka.order;

import java.math.BigDecimal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {

}
