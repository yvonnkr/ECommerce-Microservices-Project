package com.yvolabs.ecommerce.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 17/07/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}