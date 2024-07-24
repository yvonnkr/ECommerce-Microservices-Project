package com.yvolabs.ecommerce.kafka.order;


/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {

}
