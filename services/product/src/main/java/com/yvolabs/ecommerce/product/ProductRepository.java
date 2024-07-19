package com.yvolabs.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 18/07/2024
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> ids);
}
