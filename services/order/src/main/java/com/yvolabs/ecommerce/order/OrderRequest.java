package com.yvolabs.ecommerce.order;

import com.yvolabs.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 20/07/2024
 */

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message = "Order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method should not be null")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer id should not be null")
        @NotEmpty(message = "Customer id should not be empty")
        @NotBlank(message = "Customer id should not be blank")
        String customerId,

        @NotEmpty(message = "You should purchase at least one product")
        List<PurchaseRequest> products
) {
}
