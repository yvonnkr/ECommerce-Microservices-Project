package com.yvolabs.ecommerce.handler;

import lombok.Builder;

import java.util.Map;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 18/07/2024
 */
@Builder
public record ErrorResponse(
        Map<String, String> errors
) {
}
