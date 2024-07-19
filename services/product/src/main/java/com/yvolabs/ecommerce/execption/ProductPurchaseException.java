package com.yvolabs.ecommerce.execption;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 18/07/2024
 */
public class ProductPurchaseException extends RuntimeException {
    public ProductPurchaseException(String s) {
        super(s);
    }
}