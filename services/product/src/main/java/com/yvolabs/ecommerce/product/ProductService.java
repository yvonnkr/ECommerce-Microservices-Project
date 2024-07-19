package com.yvolabs.ecommerce.product;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 18/07/2024
 */
public interface ProductService {
    Integer createProduct(ProductRequest request);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
