package com.yvolabs.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 20/07/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    private final String message;
}
