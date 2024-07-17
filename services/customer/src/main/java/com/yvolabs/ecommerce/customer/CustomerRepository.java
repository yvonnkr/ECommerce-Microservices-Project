package com.yvolabs.ecommerce.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 17/07/2024
 */
public interface CustomerRepository extends MongoRepository<Customer,String> {
}
