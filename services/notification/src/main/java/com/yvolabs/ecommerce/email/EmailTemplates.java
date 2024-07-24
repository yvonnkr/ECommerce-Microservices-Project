package com.yvolabs.ecommerce.email;

import lombok.Getter;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment_confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order_confirmation.html", "Order confirmation");

    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;

    }


}
