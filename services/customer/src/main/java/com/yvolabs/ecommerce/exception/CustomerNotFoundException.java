package com.yvolabs.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 17/07/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException {

    private final String msg;
}