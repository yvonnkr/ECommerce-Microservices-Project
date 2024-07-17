package com.yvolabs.ecommerce.customer;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 17/07/2024
 */
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
